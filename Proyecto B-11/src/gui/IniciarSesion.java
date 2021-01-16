package gui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
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

		JPanel usuarioYContrase�a = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Correo electr�nico", SwingConstants.RIGHT));
		label.add(new JLabel("Contrase�a", SwingConstants.RIGHT));
		usuarioYContrase�a.add(label, BorderLayout.WEST);
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField tUsuario = new JTextField();
		controls.add(tUsuario);
		JPasswordField tContrasenya = new JPasswordField();
		controls.add(tContrasenya);
		usuarioYContrase�a.add(controls, BorderLayout.CENTER);
		tUsuario.setText(usu);
		tContrasenya.setText("");
		usuarioYContrase�a.setPreferredSize(new Dimension(300, 50));
		int opcion = JOptionPane.showConfirmDialog(Inicio.ventana, usuarioYContrase�a, "Iniciar sesi�n",
				JOptionPane.OK_CANCEL_OPTION);

		if (JOptionPane.OK_OPTION == opcion) {
			if (!tUsuario.getText().equals("")) {
				if (JDBC.comprobarUsuario(tUsuario.getText())) {
					if (JDBC.comprobarContrasenya(tUsuario.getText(), String.valueOf(tContrasenya.getPassword()))) {
						Inicio.usuarioIniciado = tUsuario.getText();
						Inicio.logger.info(Inicio.usuarioIniciado + " ha iniciado sesi�n.");
						VentanaPrincipal.barra.removeAll();
						VentanaPrincipal.barra.add(VentanaPrincipal.miCuenta);
						VentanaPrincipal.barra.add(VentanaPrincipal.inicio);
						Inicio.ventana.validate();
						Inicio.ventana.repaint();
						CambiarImagen.productosDeseados = JDBC.productosDeseados(Inicio.usuarioIniciado);
						CambiarImagen.productos.clear();
						Inicio.esVentanaDeseados = false;
						Inicio.ventana.dispose();
						VentanaPrincipal.transicion.interrupt();
						Inicio.ventana = new VentanaPrincipal();
						for (int i = 0; i < CambiarImagen.productosDeseados.size(); i++) {
							if (!CambiarImagen.productosDeseados.get(i).isDescatalogado()) {
								CambiarImagen.productos.add(CambiarImagen.productosDeseados.get(i));
							}
						}
						CambiarImagen.actual = 0;
					} else {
						JOptionPane.showMessageDialog(Inicio.ventana, "Contrase�a incorrecta", "Contrase�a incorrecta",
								JOptionPane.ERROR_MESSAGE);
						iniciar(tUsuario.getText());
					}
				} else {
					JOptionPane.showMessageDialog(Inicio.ventana, "Usuario no encontrado", "Usuario desconocido",
							JOptionPane.ERROR_MESSAGE);
					iniciar(tUsuario.getText());
				}

			} else {
				JOptionPane.showMessageDialog(Inicio.ventana, "Introduzca un usuario", "Introduzca un usuario",
						JOptionPane.ERROR_MESSAGE);
				iniciar(tUsuario.getText());
			}
		}

	}

	public static void registrar(String usu) {

		JPanel usuarioYContrase�a = new JPanel(new BorderLayout(5, 5));
		usuarioYContrase�a.setPreferredSize(new Dimension(350, 80));
		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));

		label.add(new JLabel("Correo electr�nico", SwingConstants.RIGHT));
		label.add(new JLabel("Contrase�a", SwingConstants.RIGHT));
		label.add(new JLabel("Confirmar contrase�a", SwingConstants.RIGHT));
		usuarioYContrase�a.add(label, BorderLayout.WEST);
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField tUsuario = new JTextField();
		controls.add(tUsuario);
		JPasswordField tContrasenya = new JPasswordField();
		controls.add(tContrasenya);
		JPasswordField tConfirmar = new JPasswordField();
		controls.add(tConfirmar);
		usuarioYContrase�a.add(controls, BorderLayout.CENTER);

		tUsuario.setText(usu);
		int opcion = JOptionPane.showConfirmDialog(Inicio.ventana, usuarioYContrase�a, "Registrarse",
				JOptionPane.OK_CANCEL_OPTION);

		if (JOptionPane.OK_OPTION == opcion) {
			String contra = new String(tContrasenya.getPassword());
			String confirmar = new String(tConfirmar.getPassword());
			if (tUsuario.getText().length() <= 30 && tUsuario.getText().trim().length() > 0) {
				if (contra.length() <= 50 && contra.trim().length() > 0) {
					if (contra.equals(confirmar)) {
						JDBC.crearUsuario(tUsuario.getText(), contra);

					} else {
						JOptionPane.showMessageDialog(Inicio.ventana, "Contrase�as no coinciden",
								"Las contrase�as deben coincidir.", JOptionPane.ERROR_MESSAGE);
						registrar(tUsuario.getText());
					}
				} else {
					JOptionPane.showMessageDialog(Inicio.ventana, "Contrase�a invalida",
							"La contrase�a introducida no es valida.", JOptionPane.ERROR_MESSAGE);
					registrar(tUsuario.getText());
				}
			} else {
				JOptionPane.showMessageDialog(Inicio.ventana, "Correo invalido", "El correo introducido no es valido.",
						JOptionPane.ERROR_MESSAGE);
				registrar(tUsuario.getText());
			}

		}

	}

}
