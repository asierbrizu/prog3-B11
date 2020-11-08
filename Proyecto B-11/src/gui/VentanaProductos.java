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

public class VentanaProductos extends JFrame {
	int productoActual = 0;
	int hastaProducto = 4;
	JPanel panelCatalogo = new JPanel();

	private void cargarMas() {
		for (; productoActual <= hastaProducto; productoActual++) {
			// actual.add(productos[productoActual].getImagen());
			// actual.add(new JLabel("texto "+productoActual));
			panelCatalogo.add(new JLabel("imagen " + productoActual));
			panelCatalogo.add(new JLabel("texto " + productoActual));
			validate();
		}
	}

	public VentanaProductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Productos");
		JScrollPane scroll = new JScrollPane(panelCatalogo);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.getVerticalScrollBar().setUnitIncrement(16);

		panelCatalogo.setLayout(new GridLayout(0, 2));

		JButton verMas = new JButton("Ver mas.");

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
				VentanaPrincipal.ventana.dispose();
				VentanaPrincipal.ventana=new VentanaRegistro();
			}
		});
		iniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(VentanaPrincipal.ventana, VentanaPrincipal.usuarioYContraseña,"Iniciar sesión",JOptionPane.QUESTION_MESSAGE);
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

		cargarMas();
		panelCatalogo.add(verMas);

		verMas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hastaProducto += 5;
				panelCatalogo.remove(verMas);
				cargarMas();
				panelCatalogo.add(verMas);
			}
		});
		pack();
		setVisible(true);
		setSize(800, 600);
		add(scroll);

	}

	
}
