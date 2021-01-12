package gui;

import javax.swing.*;

import bd.JDBC;
import clases.Producto;
import clases.Usuario;
import main.CambiarImagen;
import main.CargarMas;
import main.Inicio;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class VentanaPrincipal extends JFrame {

	static Usuario usuario = null;

	public static JMenuBar barra=new JMenuBar();
	public static JMenuItem registrarse = new JMenuItem("Registrarse");
	public static JMenuItem iniciarSesion = new JMenuItem("Iniciar sesión");
	public static JMenuItem inicio=new JMenuItem("Inicio");
	public static JMenu miCuenta = new JMenu("Mi cuenta");
	public static JMenuItem perfil = new JMenuItem("Perfil");
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
		Inicio.esVentanaDeseados=false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Principal");
		setLayout(new GridLayout(2, 1));
		transicion=new CambiarImagen();
		transicion.start();
		if (registrarse.getActionListeners().length==0) {
			registrarse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					IniciarSesion.registrar();
				}
			});
		}
		if (iniciarSesion.getActionListeners().length==0) {
			iniciarSesion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					IniciarSesion.iniciar("");
					Inicio.esVentanaDeseados=false;
					Inicio.ventana.dispose();
					transicion.interrupt();
					Inicio.ventana=new VentanaPrincipal();
				}
			});
		}
		if (cerrarSesion.getActionListeners().length==0) {
			cerrarSesion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					barra.removeAll();
					barra.add(iniciarSesion);
					barra.add(registrarse);
					barra.add(inicio);
					Inicio.ventana.validate();
					Inicio.ventana.repaint();
					Inicio.usuarioIniciado = "";
					Inicio.esVentanaDeseados=false;
					Inicio.ventana.dispose();
					transicion.interrupt();
					Inicio.ventana=new VentanaPrincipal();
				}
			});
		}
		if(inicio.getActionListeners().length==0) {
			inicio.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					Inicio.esVentanaDeseados=false;
					Inicio.ventana.dispose();
					transicion.interrupt();
					Inicio.ventana=new VentanaPrincipal();
				}
			});
		}

		if(deseados.getActionListeners().length==0) {
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
						JOptionPane.showMessageDialog(Inicio.ventana, "No hay productos deseados", "No hay productos que mostrar",
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
		ActionListener abrirZapatos= new ActionListener() {

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
		setJMenuBar(barra);
		miCuenta.add(perfil);
		miCuenta.add(pedidos);
		miCuenta.add(deseados);
		miCuenta.add(cerrarSesion);
		if(Inicio.usuarioIniciado=="") {
			barra.add(iniciarSesion);
			barra.add(registrarse);
				
		}else {
			barra.add(miCuenta);
		}
		barra.add(inicio);
		
		superior = new JPanel();
		JPanel inferiores = new JPanel();
		JPanel izquierdo = new JPanel();
		JPanel derecho = new JPanel();
		JLabel titulo = new JLabel("Destacados/Ofertas");

		inferiores.setLayout(new GridLayout(1, 2));

		img = new ImageIcon("recursos/img/anciano.png").getImage();
		resizedImage = img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);

		bProd = new JButton(new ImageIcon(resizedImage));
		bProd.setName("1");
		JButton bCamiseta = new JButton("Camisetas");
		JButton bZapato = new JButton("Zapatos");
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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
