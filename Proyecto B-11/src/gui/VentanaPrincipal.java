package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
	public static JFrame ventana;
	public static JPanel usuarioYContraseña = new JPanel(new BorderLayout(5, 5));
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Principal");
		setLayout(new GridLayout(2, 1));

		// Menú
		JMenuBar barra = new JMenuBar();
		JMenuItem registrarse = new JMenuItem("Registrarse");
		JMenuItem iniciarSesion = new JMenuItem("Iniciar sesión");
		JMenu miCuenta = new JMenu("Mi cuenta");
		JMenuItem perfil = new JMenuItem("Perfil");
		JMenuItem pedidos = new JMenuItem("Pedidos");
		JMenuItem deseados = new JMenuItem("Lista de deseados");
		JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");

		//Inicio sesión
		
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Usuario", SwingConstants.RIGHT));
	    label.add(new JLabel("Contraseña", SwingConstants.RIGHT));
	    usuarioYContraseña.add(label, BorderLayout.WEST);
	    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField username = new JTextField();
	    controls.add(username);
	    JPasswordField password = new JPasswordField();
	    controls.add(password);
	    usuarioYContraseña.add(controls, BorderLayout.CENTER);
		
		registrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
				ventana=new VentanaRegistro();
			}
		});
		iniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(ventana, usuarioYContraseña,"Iniciar sesión",JOptionPane.QUESTION_MESSAGE);
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
		ActionListener abrirProductos=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
				ventana=new VentanaProductos();
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

		JPanel superior = new JPanel();
		JPanel inferiores = new JPanel();
		JPanel izquierdo = new JPanel();
		JPanel derecho = new JPanel();
		JLabel titulo = new JLabel("Destacados/Ofertas");

		//superior.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

		inferiores.setLayout(new GridLayout(1, 2));

		
		Image img = new ImageIcon("img/deusto.jpg").getImage();
		Image resizedImage = img.getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH);
		JButton boton1=new JButton(new ImageIcon(resizedImage));
		JButton boton2=new JButton(new ImageIcon(resizedImage));
		JButton boton3=new JButton(new ImageIcon(resizedImage));
		boton1.addActionListener(abrirProductos);
		boton2.addActionListener(abrirProductos);
		boton3.addActionListener(abrirProductos);
		superior.add(boton1);
		izquierdo.add(boton2);
		derecho.add(boton3);
		inferiores.add(izquierdo);
		inferiores.add(derecho);
		add(superior);
		add(inferiores);

		setVisible(true);
		setSize(800, 600);

	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ventana=new VentanaPrincipal();
			}

		});
	}
	

}
