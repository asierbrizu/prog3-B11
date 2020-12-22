package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bd.JDBC;
import clases.CamisetaYPantalon;
import clases.Producto;
import clases.Talla;
import clases.Usuario;
import clases.Zapato;
import gui.Seleccionar;
import gui.VentanaPrincipal;

public class Inicio {
	public static Map<String, Usuario> mapaUsuario = new HashMap<String, Usuario>();
	public static Map<String, Producto> mapaProducto = new HashMap<String, Producto>();
	public static Usuario usuarioIniciado = null;
	public static JFrame ventana;
	public static Thread cargarMas;
	public static Set<CamisetaYPantalon> camisetasYPantalones = new HashSet<CamisetaYPantalon>();
	public static Set<Zapato> zapatos = new HashSet<Zapato>();
	public static Set<Usuario> usuarios = new HashSet<Usuario>();

	public static void main(String[] args) {

		JDBC.importar();
		JDBC.conectarBD();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ventana = new VentanaPrincipal();
			}

		});

	}

}
