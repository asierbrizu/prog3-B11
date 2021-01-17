package gui;

import javax.swing.*;
import bd.JDBC;
import clases.Producto;
import clases.Usuario;
import main.CambiarImagen;
import main.Inicio;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashSet;

public class VentanaPrincipal extends JFrame {

	static Usuario usuario = null;

	public static JMenuBar barra = new JMenuBar();
	public static JMenuItem registrarse = new JMenuItem("Registrarse");
	public static JMenuItem iniciarSesion = new JMenuItem("Iniciar sesión");
	public static JMenuItem inicio = new JMenuItem("Inicio");
	public static JMenuItem carrito = new JMenuItem("Carrito");
	public static JMenu miCuenta = new JMenu("Mi cuenta");
	public static JMenuItem pedidos = new JMenuItem("Pedidos");
	public static JMenuItem deseados = new JMenuItem("Lista de deseados");
	public static JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
	public static Thread transicion = new CambiarImagen();
	public static URL icono;
	public static Image img;
	public static Image resizedImage;
	public static JButton bProd;
	public static JPanel superior;

	public VentanaPrincipal() {
		Inicio.esVentanaDeseados = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Principal");
		setLayout(new GridLayout(2, 1));
		if (!transicion.isAlive()) {
			transicion = new CambiarImagen();
			transicion.start();
		}

		if (registrarse.getActionListeners().length == 0) {
			registrarse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					IniciarSesion.registrar("");
				}
			});
		}
		if (iniciarSesion.getActionListeners().length == 0) {
			iniciarSesion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					IniciarSesion.iniciar("");

				}
			});
		}
		if (cerrarSesion.getActionListeners().length == 0) {
			cerrarSesion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					barra.removeAll();
					barra.add(iniciarSesion);
					barra.add(registrarse);
					barra.add(carrito);
					barra.add(inicio);
					Inicio.ventana.validate();
					Inicio.ventana.repaint();
					Inicio.usuarioIniciado = "";
					Inicio.logger.info("Sesión cerrada.");
					Inicio.esVentanaDeseados = false;
					Inicio.ventana.dispose();
					transicion.interrupt();
					Inicio.ventana = new VentanaPrincipal();
				}
			});
		}
		if (inicio.getActionListeners().length == 0) {
			inicio.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Inicio.esVentanaDeseados = false;
					Inicio.ventana.dispose();
					transicion.interrupt();
					Inicio.ventana = new VentanaPrincipal();
				}
			});
		}

		if (carrito.getActionListeners().length == 0) {
			carrito.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (Inicio.mapaCesta.isEmpty()) {
						JOptionPane.showMessageDialog(Inicio.ventana, "No hay productos en el carrito",
								"No hay productos en el carrito", JOptionPane.INFORMATION_MESSAGE);
					} else {
						VentanaCarrito.mostrarCesta();
					}
				}
			});
		}

		if (deseados.getActionListeners().length == 0) {
			deseados.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!JDBC.productosDeseados(Inicio.usuarioIniciado).isEmpty()) {
						Inicio.esVentanaDeseados = true;
						transicion.interrupt();
						Inicio.ventana.dispose();
						Inicio.ventana = new VentanaProductos(
								new HashSet<Producto>(JDBC.productosDeseados(Inicio.usuarioIniciado)));
					} else {
						JOptionPane.showMessageDialog(Inicio.ventana, "No hay productos deseados",
								"No hay productos que mostrar", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}

		if (pedidos.getActionListeners().length == 0) {
			pedidos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (JDBC.tienePedidos(Inicio.usuarioIniciado)) {
						Inicio.esVentanaDeseados = false;
						transicion.interrupt();
						Inicio.ventana.dispose();
						Inicio.ventana = new VentanaPedidos();
					} else {
						JOptionPane.showMessageDialog(Inicio.ventana, "No hay pedidos", "No hay pedidos que mostrar",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}

		ActionListener abrirCamisetas = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transicion.interrupt();
				Inicio.ventana.dispose();
				Inicio.ventana = new VentanaProductos(Inicio.camisetasYPantalones);
			}
		};
		ActionListener abrirZapatos = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transicion.interrupt();
				Inicio.ventana.dispose();
				Inicio.ventana = new VentanaProductos(Inicio.zapatos);
			}
		};
		barra.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		iniciarSesion.setMaximumSize(new Dimension(120, 30));
		registrarse.setMaximumSize(new Dimension(120, 30));
		inicio.setMaximumSize(new Dimension(120, 30));
		carrito.setMaximumSize(new Dimension(120, 30));
		setJMenuBar(barra);
		miCuenta.add(pedidos);
		miCuenta.add(deseados);
		miCuenta.add(cerrarSesion);
		if (Inicio.usuarioIniciado == "") {
			barra.add(iniciarSesion);
			barra.add(registrarse);

		} else {
			barra.add(miCuenta);
		}
		barra.add(carrito);
		barra.add(inicio);
		superior = new JPanel();
		JPanel inferiores = new JPanel();
		JPanel izquierdo = new JPanel();
		JPanel derecho = new JPanel();

		inferiores.setLayout(new GridLayout(1, 2));

		icono = VentanaPrincipal.class.getResource(Inicio.imagenes + "img/anciano.png");
		img = new ImageIcon(icono).getImage();
		resizedImage = img.getScaledInstance((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 35 / 100,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 4 / 10, java.awt.Image.SCALE_SMOOTH);
		bProd = new JButton(new ImageIcon(resizedImage));
		bProd.setName("1");

		icono = VentanaPrincipal.class.getResource(Inicio.imagenes + "img/camisetas.png");
		img = new ImageIcon(icono).getImage();
		resizedImage = img.getScaledInstance((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 35 / 100,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 4 / 10, java.awt.Image.SCALE_SMOOTH);
		JButton bCamiseta = new JButton(new ImageIcon(resizedImage));
		bCamiseta = new JButton(new ImageIcon(resizedImage));

		icono = VentanaPrincipal.class.getResource(Inicio.imagenes + "img/zapatos.png");
		img = new ImageIcon(icono).getImage();
		resizedImage = img.getScaledInstance((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 35 / 100,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 4 / 10, java.awt.Image.SCALE_SMOOTH);
		JButton bZapato = new JButton(new ImageIcon(resizedImage));
		bZapato = new JButton(new ImageIcon(resizedImage));

		bProd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Seleccionar.mostrar(Inicio.usuarioIniciado, Inicio.mapaProducto.get(VentanaPrincipal.bProd.getName()));
			}
		});
		bCamiseta.addActionListener(abrirCamisetas);
		bZapato.addActionListener(abrirZapatos);
		superior.add(bProd);
		izquierdo.add(bCamiseta);
		derecho.add(bZapato);
		inferiores.add(izquierdo);
		inferiores.add(derecho);
		add(superior);
		add(inferiores);

		setVisible(true);
		setSize(1600, 1200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
