package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bd.JDBC;
import main.CambiarImagen;
import main.Inicio;

public class IniciarSesion {

	public static void iniciar(String usu) {

		JPanel usuarioYContraseña = new JPanel(new BorderLayout(5, 5));

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
		username.setText(usu);
		password.setText("");
		int opcion = JOptionPane.showConfirmDialog(Inicio.ventana, usuarioYContraseña, "Iniciar sesión",
				JOptionPane.OK_CANCEL_OPTION);

		if (JOptionPane.OK_OPTION == opcion) {
			if (!username.getText().equals("")) {
				if (JDBC.comprobarUsuario(username.getText())) {
					if (JDBC.comprobarContrasenya(username.getText(), password.getText())) {
						Inicio.usuarioIniciado = username.getText();
						VentanaPrincipal.barra.removeAll();
						VentanaPrincipal.barra.add(VentanaPrincipal.miCuenta);
						VentanaPrincipal.barra.add(VentanaPrincipal.inicio);
						Inicio.ventana.validate();
						Inicio.ventana.repaint();
						CambiarImagen.productosDeseados = JDBC.productosDeseados(Inicio.usuarioIniciado);
						CambiarImagen.productos.clear();
						for (int i = 0; i < CambiarImagen.productosDeseados.size(); i++) {
							if (!CambiarImagen.productosDeseados.get(i).isDescatalogado()) {
								CambiarImagen.productos.add(CambiarImagen.productosDeseados.get(i));
							}
						}
						CambiarImagen.actual=0;
					} else {
						JOptionPane.showMessageDialog(Inicio.ventana, "Contraseña incorrecta", "Contraseña incorrecta",
								JOptionPane.ERROR_MESSAGE);
						iniciar(username.getText());
					}
				} else {
					JOptionPane.showMessageDialog(Inicio.ventana, "Usuario no encontrado", "Usuario desconocido",
							JOptionPane.ERROR_MESSAGE);
					iniciar(username.getText());
				}

			} else {
				JOptionPane.showMessageDialog(Inicio.ventana, "Introduzca un usuario", "Introduzca un usuario",
						JOptionPane.ERROR_MESSAGE);
				iniciar(username.getText());
			}
		}
		
	}

	public static void registrar() {
		JPanel registro = new JPanel(new BorderLayout());
		JPanel label = new JPanel(new GridLayout(0, 1));

		JLabel lContrasenya = new JLabel("Contraseña:");
		JLabel lConfirmar = new JLabel("Confirmar contraseña:");
		JLabel lCorreo = new JLabel("Correo electrónico:");

		label.add(lCorreo);
		label.add(lContrasenya);
		label.add(lConfirmar);
		registro.add(label, BorderLayout.WEST);

		JPanel texto = new JPanel(new GridLayout(0, 1));

		JPasswordField tConfirmar = new JPasswordField(20);
		JPasswordField tContrasenya = new JPasswordField(20);
		JTextField tCorreo = new JTextField(20);

		texto.add(tCorreo);
		texto.add(tContrasenya);
		texto.add(tConfirmar);
		registro.add(texto, BorderLayout.EAST);

		JButton bRegistrarse = new JButton("Registrarse");

		bRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String contra=new String(tContrasenya.getPassword());
				String confirmar=new String(tConfirmar.getPassword());
				if (tCorreo.getText().length() <= 30 && tCorreo.getText().trim().length() > 0) {
					if (contra.length() <= 50 && contra.trim().length()>0) {
						if (contra.equals(confirmar)) {
							JDBC.crearUsuario(tCorreo.getText(), contra);
							Inicio.ventana.dispose();
							Inicio.ventana = new VentanaPrincipal();
						} else {
							JOptionPane.showMessageDialog(Inicio.ventana, "Contraseñas no coinciden",
									"Las contraseñas deben coincidir.", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(Inicio.ventana, "Contraseña invalida",
								"La contraseña introducida no es valida.", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(Inicio.ventana, "Correo invalido",
							"El correo introducido no es valido.", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		registro.add(bRegistrarse, BorderLayout.SOUTH);
		JOptionPane.showOptionDialog(Inicio.ventana, registro, "Registrarse", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

	}
}
