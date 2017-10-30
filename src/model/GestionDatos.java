package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionDatos {

	public GestionDatos() {

	}
	
	public boolean compararContenido (String fichero1, String fichero2) throws FileNotFoundException, IOException{
		File f1 = new File(fichero1);
		File f2 = new File(fichero2);
		boolean iguales = false;
		
		// Comprobamos si existen los ficheros introducidos
		if (!ficheroExiste(f1))
			throw new FileNotFoundException(f1.getName());
		
		if (!ficheroExiste(f2))
			throw new FileNotFoundException(f2.getName());
			
		// Comparamos el tamaño de los ficheros
		if (volumenFichero(f1) == volumenFichero(f2)) {
			BufferedReader br1 = abrirFichero(f1);
			BufferedReader br2 = abrirFichero(f2);
			// Comparamos el contenido de los ficheros linea a linea
			iguales = compararLineas(br1, br2);
			cerrarFichero(br1);
			cerrarFichero(br2);
		}
		return iguales;
	}
	
	//Aquí hacemos una compración de si puede abrir el fichero para evitar errores
	private BufferedReader abrirFichero(File fichero) throws FileNotFoundException, IOException {
		if(puedeLeerFichero(fichero)) 
			return new BufferedReader(new FileReader(fichero));
		else
			throw new IOException(); 
	}
	
	private void cerrarFichero(BufferedReader bf) throws IOException {
		bf.close();
	}
	
	private boolean compararLineas(BufferedReader br1, BufferedReader br2) throws IOException {
		String lineaF1;
		String lineaF2;
		
		while (((lineaF1 = br1.readLine()) != null) && ((lineaF2 = br2.readLine())!= null)) {
			if (!lineaF1.equals(lineaF2))
				return false;  
			} 
		return true;
	}
	
	public int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws IOException{
		File f1 = new File(fichero1);
		String linea;
		int contadorLinea = 0;
		int ultima_aparicion = 0;

		if (!ficheroExiste(f1))
			throw new FileNotFoundException(f1.getName());
		
		// Comprobamos si ha introducido una palabra a buscar
		if (palabra.length() == 0)
			return 0;
		
	    BufferedReader br1 = abrirFichero(f1);
	    
	    // Recorremos el fichero linea a linea buscando esa palabra
	    while ((linea = br1.readLine()) != null) {
			contadorLinea++;
			if (linea.equalsIgnoreCase(palabra)) {
				if (primera_aparicion) {
					return contadorLinea;
				} else {
					ultima_aparicion = contadorLinea;
				}
			} else 
				ultima_aparicion = -1;	    	
	    }
	    return ultima_aparicion;
	}	
	
	private long volumenFichero(File f) {
		return f.length();
	}
	
	private boolean ficheroExiste(File fichero) {
		return fichero.exists();
	}
	
	private boolean puedeLeerFichero(File fichero) {
		return fichero.canRead();
	}
	
	public int copiarFichero(String origen, String destino) throws IOException {
        FileInputStream fis;       
        FileOutputStream fos;
        int bytesRead = 0;
        int bytesCopiados = 0;
        byte[] buffer;
        
        // Aqui hacemos un FileInputStream sobre el fichero de lectura (el que copiamos)
        fis = new FileInputStream(origen);
        // Y aquí lo hacemos sobre el fichero de escritura (donde se hace la copia)
        fos = new FileOutputStream(destino);
        
        // Mientras haya datos en el stream de lectura los copiamos en el fichero de destino                                
        buffer = new byte[4096];
        while( (bytesRead = fis.read(buffer)) != -1 ){
        		bytesCopiados = bytesRead;
        		fos.write(buffer, 0, bytesRead);
        }
        
        fis.close();
        fos.close();  
        
        return bytesCopiados;
	}
	
	public void guardar_libro(String identificador, String titulo, String autor, String anyo, String editor, String paginas) throws IOException {
		LibroVO libro = new LibroVO(identificador, titulo, autor, anyo, editor, paginas);
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
			
		// Se crea el fichero
		fos = new FileOutputStream("libros//"+identificador);
		salida = new ObjectOutputStream(fos);
		// Se escriben los objetos en el fichero
		salida.writeObject(libro);
	
		if (fos != null)
			fos.close();
		if (salida != null)
			salida.close();
	}
	
	public LibroVO recuperar_libro(String identificador) throws IOException, ClassNotFoundException, FileNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		LibroVO libro = null;

		// Obtenemos el fichero
		fis = new FileInputStream("libros//"+identificador);
		entrada = new ObjectInputStream(fis);
		// Leemos la informacion del libro
		libro = (LibroVO) entrada.readObject();
	
		if (fis != null)
			fis.close();
		if (entrada != null)
			entrada.close();
	
		return libro;
	}
	
	public ArrayList<LibroVO> recuperar_todos() throws ClassNotFoundException, FileNotFoundException, IOException {
		File file = new File("libros");
		File[] ficheros;  
		ArrayList<LibroVO> libros = new ArrayList<LibroVO>();
		// Obtenemos el listado de ficheros y los guardamos en un array
		ficheros = file.listFiles();
		for (int i=0; i<ficheros.length; i++){
			/*Este if lo hacemos en MacOS, debido a que al crear un directorio se crea
			archivo .DS_Store y por tanto da error, debido a que no puede obtener la información de este*/
			if(!ficheros[i].isHidden()) {
				libros.add(recuperar_libro(ficheros[i].getName()));
			}
		}
		return libros;
	}
}
