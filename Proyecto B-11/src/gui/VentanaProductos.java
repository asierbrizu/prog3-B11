package gui;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import main.CargarMas;
import main.Inicio;

public class VentanaProductos extends JFrame {
	
	public static void verMas() {
		hastaProducto += 5;
		panelCatalogo.remove(verMas);
		for (; productoActual <= hastaProducto; productoActual++) {
			// actual.add(productos[productoActual].getImagen());
			// actual.add(new JLabel("texto "+productoActual));
			panelCatalogo.add(new JLabel("imagen " + productoActual));
			panelCatalogo.add(new JLabel("texto " + productoActual));
			//VentanaProductos.validate();
			panelCatalogo.validate();
			scroll.revalidate();
		}
		panelCatalogo.add(verMas);
	}
	
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

		// Menú
		JMenuBar barra = new JMenuBar();
		JMenuItem registrarse = new JMenuItem("Registrarse");
		JMenuItem iniciarSesion = new JMenuItem("Iniciar sesión");
		JMenu miCuenta = new JMenu("Mi cuenta");
		JMenuItem perfil = new JMenuItem("Perfil");
		JMenuItem pedidos = new JMenuItem("Pedidos");
		JMenuItem deseados = new JMenuItem("Lista de deseados");
		JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");

		// Mas adelante eso será distinto para que sea igual en todas las ventanas
		
		registrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio.ventana.dispose();
				Inicio.ventana=new VentanaRegistro();
			}
		});
		iniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Inicio.ventana, VentanaPrincipal.usuarioYContraseña,"Iniciar sesión",JOptionPane.QUESTION_MESSAGE);
				barra.removeAll();
				barra.add(miCuenta);
				validate();
				repaint();
				
			}
		});
		cerrarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				barra.removeAll();
				barra.add(iniciarSesion);
				barra.add(registrarse);
				validate();
				repaint();
			}
		});
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
				verMas();
			}
		});
		verMas();
		pack();
		setVisible(true);
		setSize(800, 600);
		add(scroll);

	}

	
}
