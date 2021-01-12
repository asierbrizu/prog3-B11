package gui;

import java.awt.ComponentOrientation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
import clases.Producto;
import main.CargarMas;
import main.Inicio;

public class VentanaProductos extends JFrame {

	public static Iterator<Producto> i;
	public static int productoActual = 0;
	public static int hastaProducto = 0;
	public static JPanel panelCatalogo = new JPanel();
	public static JScrollPane scroll;
	public static JButton verMas;

	public VentanaProductos(Set<Producto> productos) {
		productoActual=hastaProducto=0;
		if (Inicio.esVentanaDeseados) {
			hastaProducto=productos.size();
		}
		i = productos.iterator();
		panelCatalogo.removeAll();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Productos");
		scroll = new JScrollPane(panelCatalogo);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.getVerticalScrollBar().setUnitIncrement(16);

		panelCatalogo.setLayout(new GridLayout(0, 4));

		verMas = new JButton("Ver mas.");

		// Menú
		JMenuBar barra = VentanaPrincipal.barra;
		setJMenuBar(barra);
		if(!Inicio.esVentanaDeseados) {
		panelCatalogo.add(verMas);
		}
		verMas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CargarMas().run();
			}
		});
		new CargarMas().run();

		pack();
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		add(scroll);

	}

}
