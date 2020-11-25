package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bd.JDBC;
import clases.CamisetaYPantalon;
import clases.Producto;
import clases.Talla;
import gui.VentanaPrincipal;

public class Inicio {
	public static JFrame ventana;
	public static Thread cargarMas;
	public static void main(String[] args) {
		JDBC.importar();
		JDBC.conectarBD();
		

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ventana=new VentanaPrincipal();
				
			}

		});
	}

}
