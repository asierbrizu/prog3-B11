package gui;

import javax.swing.*;

import bd.JDBC;
import clases.Usuario;
import main.CambiarImagen;
import main.Inicio;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class VentanaPrincipal extends JFrame {

	static Usuario usuario = null;

	public static JMenuBar barra;
	public static JMenuItem registrarse = new JMenuItem("Registrarse");
	public static JMenuItem iniciarSesion = new JMenuItem("Iniciar sesión");
	public static JMenu miCuenta = new JMenu("Mi cuenta");
	public static JMenuItem perfil = new JMenuItem("Perfil");
	public static JMenuItem pedidos = new JMenuItem("Pedidos");
	public static JMenuItem deseados = new JMenuItem("Lista de deseados");
	public static JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");

	public static URL icono;
	public static Image img;
	public static Image resizedImage;
	public static JButton boton1;
	public static JPanel superior;

	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Principal");
		setLayout(new GridLayout(2, 1));

		Thread transicion = new CambiarImagen();
		transicion.start();
		// Menú
		barra = new JMenuBar();
		registrarse = new JMenuItem("Registrarse");
		iniciarSesion = new JMenuItem("Iniciar sesión");
		miCuenta = new JMenu("Mi cuenta");
		perfil = new JMenuItem("Perfil");
		pedidos = new JMenuItem("Pedidos");
		deseados = new JMenuItem("Lista de deseados");
		cerrarSesion = new JMenuItem("Cerrar sesión");

		registrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IniciarSesion.registrar();
			}
		});
		iniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IniciarSesion.iniciar("");
			}
		});
		cerrarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				barra.removeAll();
				barra.add(iniciarSesion);
				barra.add(registrarse);
				Inicio.ventana.validate();
				Inicio.ventana.repaint();
			}
		});
		ActionListener abrirProductos = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio.ventana.dispose();
				transicion.interrupt();
				Inicio.ventana = new VentanaProductos();
			}
		};
		barra.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		iniciarSesion.setMaximumSize(new Dimension(120, 30));

		registrarse.setMaximumSize(new Dimension(120, 30));
		setJMenuBar(barra);
		miCuenta.add(perfil);
		miCuenta.add(pedidos);
		miCuenta.add(deseados);
		miCuenta.add(cerrarSesion);
		barra.add(iniciarSesion);
		barra.add(registrarse);

		superior = new JPanel();
		JPanel inferiores = new JPanel();
		JPanel izquierdo = new JPanel();
		JPanel derecho = new JPanel();
		JLabel titulo = new JLabel("Destacados/Ofertas");

		inferiores.setLayout(new GridLayout(1, 2));

		img = new ImageIcon("recursos/img/deusto.jpg").getImage();
		resizedImage = img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);

		boton1 = new JButton(new ImageIcon(resizedImage));
		JButton boton2 = new JButton(new ImageIcon(resizedImage));
		JButton boton3 = new JButton("Productos");
		boton1.addActionListener(abrirProductos);
		boton2.addActionListener(abrirProductos);
		boton3.addActionListener(abrirProductos);
		superior.add(boton1);
		izquierdo.add(boton2);
		derecho.add(boton3);
		// inferiores.add(izquierdo);
		inferiores.add(derecho);
		add(superior);
		add(inferiores);

		setVisible(true);
		setSize(800, 600);

	}

}
