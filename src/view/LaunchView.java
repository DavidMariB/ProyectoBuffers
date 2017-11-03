package view;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LaunchView extends JFrame {

	private JButton comparar,buscar,copiar,btnGuardar,btnRecuperar,btnRecuperarTodos,btnModificarLibro;
	private JTextArea textArea;
	private JTextField fichero1,fichero2,palabra,titulo,autor,anyo,editor,paginas;
	private JLabel label_f1,label_f2,label_pal,label_titulo,label_autor,label_anyo,label_editor,label_paginas;
	private JCheckBox primera;
	private JPanel campos_ficheros,acciones_ficheros,libros,acciones_libros,campos_libros,ficheros,consola,global;
	private JLabel lblLongitudFichero;
	private JTextField longitudFichero;
	private JButton btnCompararPalabras;
	
	public LaunchView() {
		
		setBounds(200,200,1000,450);
		setTitle("Proyecto Buffers");	
        
        global = new JPanel();
        getContentPane().add(global, BorderLayout.CENTER);
        
        ficheros = new JPanel();
        global.add(ficheros);
        ficheros.setLayout(new BorderLayout(0, 0));
        
        acciones_ficheros = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) acciones_ficheros.getLayout();
        ficheros.add(acciones_ficheros, BorderLayout.SOUTH);
        
        lblLongitudFichero = new JLabel("Longitud Palabra:");
        acciones_ficheros.add(lblLongitudFichero);
        
        longitudFichero = new JTextField();
        acciones_ficheros.add(longitudFichero);
        longitudFichero.setColumns(10);
        
        btnCompararPalabras = new JButton("Comparar Palabras");
        acciones_ficheros.add(btnCompararPalabras);
        
        comparar = new JButton("Comparar contenido");
        acciones_ficheros.add(comparar);
        comparar.setPreferredSize(new Dimension(150, 26));
        buscar = new JButton("Buscar palabra");
        acciones_ficheros.add(buscar);
        buscar.setPreferredSize(new Dimension(150, 26));
        copiar = new JButton("Copiar fichero");
        acciones_ficheros.add(copiar);
        copiar.setPreferredSize(new Dimension(150, 26));
        campos_ficheros = new JPanel();
        ficheros.add(campos_ficheros, BorderLayout.NORTH);
        
		fichero1 = new JTextField("",10);
		fichero2 = new JTextField("",10);
		palabra = new JTextField("",10);
		
		label_f1 = new JLabel("Fichero 1:");
		label_f2 = new JLabel("Fichero 2:");
		label_pal = new JLabel("Palabra:");
		
		primera = new JCheckBox("Primera aparici\u00f3n");
		campos_ficheros.add(label_f1);
		campos_ficheros.add(fichero1);
		campos_ficheros.add(label_f2);
		campos_ficheros.add(fichero2);
		campos_ficheros.add(label_pal);
		campos_ficheros.add(palabra);
		campos_ficheros.add(primera);
        
        consola = new JPanel();
        global.add(consola);
        
        		textArea = new JTextArea(15, 80);
        		JScrollPane sp = new JScrollPane(textArea);
        		consola.add(sp);
        		textArea.setBounds(50,50,50,50);
        		textArea.setEditable(false);
        
        libros = new JPanel();
        global.add(libros);
        
			libros.setLayout(new BorderLayout(0, 0));
		                
		    		campos_libros = new JPanel();
		        libros.add(campos_libros, BorderLayout.NORTH);
		                
		        		label_titulo = new JLabel("Titulo");
		            campos_libros.add(label_titulo);
		                        
    	
		titulo = new JTextField("",10);
		campos_libros.add(titulo);
		label_autor = new JLabel("Autor");
		campos_libros.add(label_autor);
		autor = new JTextField("",10);
		campos_libros.add(autor);
		label_anyo = new JLabel("A\u00f1o");
		campos_libros.add(label_anyo);
		anyo = new JTextField("",10);
		campos_libros.add(anyo);
		label_editor = new JLabel("Editor");
		campos_libros.add(label_editor);
		editor = new JTextField("",10);
		campos_libros.add(editor);
		label_paginas = new JLabel("Paginas");
		campos_libros.add(label_paginas);
		paginas = new JTextField("",10);
		campos_libros.add(paginas);
		                
		acciones_libros = new JPanel();
		FlowLayout flowLayout = (FlowLayout) acciones_libros.getLayout();
		libros.add(acciones_libros, BorderLayout.SOUTH);
		                
		btnGuardar = new JButton("Guardar libro");
		                btnGuardar.setPreferredSize(new Dimension(150, 26));
		                acciones_libros.add(btnGuardar);
		                
		                btnRecuperar = new JButton("Recuperar libro");
		                btnRecuperar.setPreferredSize(new Dimension(150, 26));
		                acciones_libros.add(btnRecuperar);
		                
		                btnRecuperarTodos = new JButton("Recuperar Todos");
		                btnRecuperarTodos.setPreferredSize(new Dimension(150, 26));
		                acciones_libros.add(btnRecuperarTodos);
		                
		                btnModificarLibro = new JButton("Modificar Libro");
		                btnModificarLibro.setPreferredSize(new Dimension(150, 26));
		                acciones_libros.add(btnModificarLibro);
	}	
	
	public JButton getComparar() {
		return comparar;
	}

	public void setComparar(JButton comparar) {
		this.comparar = comparar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public JButton getCopiar() {
		return copiar;
	}

	public void setCopiar(JButton copiar) {
		this.copiar = copiar;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
			
	public JTextField getFichero1() {
		return fichero1;
	}

	public void setFichero1(JTextField fichero1) {
		this.fichero1 = fichero1;
	}

	public JTextField getFichero2() {
		return fichero2;
	}

	public void setFichero2(JTextField fichero2) {
		this.fichero2 = fichero2;
	}

	public JTextField getPalabra() {
		return palabra;
	}

	public void setPalabra(JTextField palabra) {
		this.palabra = palabra;
	}

	public JCheckBox getPrimera() {
		return primera;
	}

	public void setPrimera(JCheckBox primera) {
		this.primera = primera;
	}

	public void showError(String m){
		JOptionPane.showMessageDialog(this.campos_ficheros,
			    m,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}

	public JTextField getTitulo() {
		return titulo;
	}

	public void setTitulo(JTextField titulo) {
		this.titulo = titulo;
	}

	public JTextField getAutor() {
		return autor;
	}

	public void setAutor(JTextField autor) {
		this.autor = autor;
	}

	public JTextField getAnyo() {
		return anyo;
	}

	public void getAnyo(JTextField anyo) {
		this.anyo = anyo;
	}

	public JTextField getEditor() {
		return editor;
	}

	public void setEditor(JTextField editor) {
		this.editor = editor;
	}

	public JTextField getPaginas() {
		return paginas;
	}

	public void setPaginas(JTextField paginas) {
		this.paginas = paginas;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnRecuperar() {
		return btnRecuperar;
	}

	public void setBtnRecuperar(JButton btnRecuperar) {
		this.btnRecuperar = btnRecuperar;
	}

	public JButton getBtnRecuperarTodos() {
		return btnRecuperarTodos;
	}

	public void setBtnRecuperarTodos(JButton btnRecuperarTodos) {
		this.btnRecuperarTodos = btnRecuperarTodos;
	}
	
	public JButton getBtnModificarLibro() {
		return btnModificarLibro;
	}
	
	public void setBtnModificarLibro(JButton btnModificarLibro) {
		this.btnModificarLibro = btnModificarLibro;
	}
	
	public JTextField getLongitudFichero() {
		return longitudFichero;
	}

	public void setLongitudFichero(JTextField longitudFichero) {
		this.longitudFichero = longitudFichero;
	}

	public JButton getBtnCompararPalabras() {
		return btnCompararPalabras;
	}

	public void setBtnCompararPalabras(JButton btnCompararPalabras) {
		this.btnCompararPalabras = btnCompararPalabras;
	}

	public void limpiarCampos() {
		titulo.setText("");
		autor.setText("");
		anyo.setText("");
		editor.setText("");
		paginas.setText("");
	}
	
}
