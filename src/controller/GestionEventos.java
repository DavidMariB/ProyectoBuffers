package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar,actionListener_copiar,actionListener_guardar,
	actionListener_recuperar,actionListener_recuperarTodos;

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void control() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_compararContenido();
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);
		
		actionListener_copiar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_copiarFichero();
			}
		};
		view.getCopiar().addActionListener(actionListener_copiar);
		
		actionListener_guardar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_guardarLibro();
			}
		};
		view.getBtnGuardar().addActionListener(actionListener_guardar);
		
		actionListener_recuperar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_recuperarLibro();
			}
		};
		view.getBtnRecuperar().addActionListener(actionListener_recuperar);
				
		actionListener_recuperarTodos = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_recuperarTodos();
			}
		};
		view.getBtnRecuperarTodos().addActionListener(actionListener_recuperarTodos);
	}

	private void call_compararContenido(){
		String fichero1 = view.getFichero1().getText();
		String fichero2 = view.getFichero2().getText();
		boolean ficherosIguales;

		try {
			ficherosIguales = model.compararContenido(fichero1, fichero2);			
			if (ficherosIguales) 
				view.getTextArea().setText("Los ficheros son iguales.");
			else
				view.getTextArea().setText("Los ficheros son diferentes.");
			
		} catch (FileNotFoundException e) {
			// Comprobamos si el error se ha producido por que el usuario ha dejado algún campo vacío o si se ha
			//prducido por que no existe el fichero
			if (fichero1.length() == 0) {
					view.showError("No puedes dejar vacio el campo \"Fichero 1\"");
			} else if (fichero2.length() == 0) {
					view.showError("No puedes dejar vacio el campo \"Fichero 2\"");
			} else
				view.showError("No existe ning\u00fan fichero llamado \""+e.getMessage()+"\"");
		} catch (IOException e) {
			view.showError("Se ha producido un error, por favor, revisa los campos");
		}
	}

	private void call_buscarPalabra() {
		String fichero1 = view.getFichero1().getText();
		String busqueda = view.getPalabra().getText().trim();
		boolean primera_aparicion = view.getPrimera().isSelected();
		int resultado;
		
		try {
			resultado = model.buscarPalabra(fichero1, busqueda, primera_aparicion);			
			if (resultado > 0) {
				if (primera_aparicion)
					view.getTextArea().setText("\""+busqueda+"\""+" aparece por primera vez en la linea "+resultado);
				else
					view.getTextArea().setText("\""+busqueda+"\""+" aparece por /u00faltima vez en la linea "+resultado);
			} else if (resultado == -1) {
				view.getTextArea().setText("No se ha encontrado ninguna coincidencia");
			}
			
		}  catch (FileNotFoundException e) {
			/* Comprobamos si el error se ha producido por dejar un campo vacio o por que
			el archivo es inexistente*/
			if (fichero1.length() == 0)
				view.showError("No puedes dejar vacio el campo \"Fichero 1\"");
			else
				view.showError("No existe ning\u00fan fichero llamado \""+e.getMessage()+"\"");
		} catch (IOException e) {
			view.showError("Se ha producido un error");
		}
	}
	
	private void call_copiarFichero() {
		String fichero1 = view.getFichero1().getText();
		String fichero2 = view.getFichero2().getText();
		int bytesCopiados = 0;
		
		try {
			bytesCopiados = model.copiarFichero(fichero1, fichero2);
			view.getTextArea().setText("Nuevo fichero creado: \""+fichero2+"\". Se han copiado "+bytesCopiados+" bytes.");
		} catch (IOException e) {
			view.showError("Se ha producido un error");
		}
	}
	
	private void call_guardarLibro() {
		String titulo,autor,anyo,editor,paginas;
		
		titulo = view.getTitulo().getText();
		autor = view.getAutor().getText();
		anyo = view.getAnyo().getText();
		editor = view.getEditor().getText();
		paginas = view.getPaginas().getText();
		
		
		if (titulo.length() != 0) {		
			try {
				model.guardar_libro(titulo, titulo, autor, anyo, editor, paginas);
			} catch (IOException e) {
				view.showError("Se ha producido un error");
			}
		} else
			view.showError("Rellene los campos necesarios");
		view.limpiarCampos();
	}
	
	private void call_recuperarLibro() {
		String titulo;
		LibroVO libro;
		
		titulo = view.getTitulo().getText();
		if (titulo.length() != 0) {		
			try {
				libro = model.recuperar_libro(titulo);
				view.getTextArea().setText(libro.toString());
			} catch (FileNotFoundException e) {
				view.showError("No se ha encontrado ninguna coincidencia");
			} catch (ClassNotFoundException | IOException e) {
				view.showError("Se ha producido un error");
			}
		}		
	}
	
	private void call_recuperarTodos() {
		ArrayList<LibroVO> libros = new ArrayList<LibroVO>();
		StringBuilder resultado = new StringBuilder();

		try {
			libros = model.recuperar_todos();
			resultado.append(libros.size()+" Libros encontrados:\n");
			for (int i=0; i<libros.size(); i++){
				resultado.append("\n- ");
				resultado.append(libros.get(i).getTitulo()+"\n");
			}
			view.getTextArea().setText(resultado.toString());
		} catch (Exception e) {
			view.showError("Se ha producido un error");
		}
	}

}
