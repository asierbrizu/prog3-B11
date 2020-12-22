package gui;

import java.awt.ComponentOrientation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import clases.CamisetaYPantalon;
import main.CargarMas;
import main.Inicio;

public class VentanaProductos extends JFrame {

	public static Iterator<CamisetaYPantalon> i = Inicio.camisetasYPantalones.iterator();
	public static int productoActual = 0;
	public static int hastaProducto = 0;
	public static JPanel panelCatalogo = new JPanel();
	public static JScrollPane scroll;
	public static JButton verMas;

	public VentanaProductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Productos");
		scroll = new JScrollPane(panelCatalogo);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.getVerticalScrollBar().setUnitIncrement(16);

		panelCatalogo.setLayout(new GridLayout(0, 2));

		verMas = new JButton("Ver mas.");

		// Men�
		JMenuBar barra = VentanaPrincipal.barra;
		JMenuItem registrarse = VentanaPrincipal.registrarse;
		JMenuItem iniciarSesion = VentanaPrincipal.iniciarSesion;
		JMenu miCuenta = VentanaPrincipal.miCuenta;
		JMenuItem perfil = VentanaPrincipal.perfil;
		JMenuItem pedidos = VentanaPrincipal.pedidos;
		JMenuItem deseados = VentanaPrincipal.deseados;
		JMenuItem cerrarSesion = VentanaPrincipal.cerrarSesion;

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

		panelCatalogo.add(verMas);

		verMas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CargarMas().run();
			}
		});
		new CargarMas().run();

		pack();
		setVisible(true);
		setSize(800, 600);
		add(scroll);

	}

}
