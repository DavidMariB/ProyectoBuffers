package model;

import java.io.Serializable;

public class LibroVO implements Serializable{
	private String identificador;
	private String titulo;
	private String autor;
	private String anyo;
	private String editor;
	private String paginas;
	
	
	public LibroVO(String identificador, String titulo, String autor, String anyo, String editor, String paginas) {
		this.identificador = identificador;
		this.titulo = titulo;
		this.autor = autor;
		this.anyo = anyo;
		this.editor = editor;
		this.paginas = paginas;
	}
	
	@Override
	public String toString() {
		String descripcion = "Titulo: "+titulo+"\n Autor: "+autor+"\n A\u00f1o: "+anyo+"\n Editor: "+editor+"\n P\u00e1ginas: "+paginas;
		return descripcion;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAnyo() {
		return anyo;
	}

	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

}
