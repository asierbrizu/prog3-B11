package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bd.JDBC;
import clases.Producto;
import clases.Talla;
import clases.Usuario;
import main.Inicio;

public class Seleccionar {
	static JDialog seleccion;
	static boolean desea;

	public static void mostrar(String usuario, Producto producto) {
		desea = JDBC.loDesea(usuario, String.valueOf(producto.getId()));

		seleccion = new JDialog(Inicio.ventana, producto.getNombre(), true);
		seleccion.setLayout(new BorderLayout());
		JPanel info = new JPanel(new GridLayout(0, 1));

		URL icono = Inicio.ventana.getClass().getResource("/" + producto.getImagen());
		Image img = new ImageIcon(icono).getImage();
		Image resizedImage = img.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
		JLabel imagen = new JLabel(new ImageIcon(resizedImage));
		seleccion.add(imagen, BorderLayout.WEST);

		JLabel nombre = new JLabel(producto.getNombre());
		JLabel precio = new JLabel(String.valueOf(producto.getPrecio() * producto.getDescuento()));
		JComboBox<Talla> talla = new JComboBox<Talla>();
		talla.setModel(new DefaultComboBoxModel<Talla>(Talla.values()));

		info.add(new JLabel("Nombre:"));
		info.add(nombre, BorderLayout.NORTH);
		info.add(new JLabel("Precio:"));
		info.add(precio, BorderLayout.WEST);
		info.add(new JLabel("Talla:"));
		info.add(talla, BorderLayout.CENTER);

		seleccion.add(info, BorderLayout.CENTER);

		JButton bDeseado;
		if (desea && Inicio.usuarioIniciado != "") {
			bDeseado = new JButton("Eliminar de deseados");
		} else {
			bDeseado = new JButton("Añadir a deseados.");
		}
		JButton bConfirmar = new JButton("Añadir a la cesta");

		bDeseado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (desea) {
					JDBC.eliminarDeseados(usuario, String.valueOf(producto.getId()));
					bDeseado.setText("Añadir a deseados");
					if (Inicio.esVentanaDeseados) {
						for (Component c : VentanaProductos.panelCatalogo.getComponents()) {
							if (c instanceof JButton && c != null) {
								if (c.getName().equals(String.valueOf(producto.getId()))) {
									VentanaProductos.panelCatalogo.remove(c);
									VentanaProductos.panelCatalogo.repaint();
									VentanaProductos.panelCatalogo.validate();
									VentanaProductos.panelCatalogo.revalidate();
									seleccion.dispose();
									if (JDBC.productosDeseados(Inicio.usuarioIniciado).isEmpty()) {
										Inicio.esVentanaDeseados=false;
										Inicio.ventana.dispose();
										VentanaPrincipal.transicion.interrupt();
										Inicio.ventana=new VentanaPrincipal();
									}
								}
							}
						}
					}
				} else {
					JDBC.anyadirDeseado(usuario, String.valueOf(producto.getId()));
					bDeseado.setText("Eliminar de deseados");
				}
				desea = JDBC.loDesea(usuario, String.valueOf(producto.getId()));
			}
		});
		if (Inicio.usuarioIniciado == "") {
			bDeseado.setEnabled(false);
		}

		JPanel botones = new JPanel(new FlowLayout());

		botones.add(bDeseado);
		botones.add(bConfirmar);
		seleccion.add(botones, BorderLayout.SOUTH);
		seleccion.setSize(400, 300);
		seleccion.setVisible(true);
		seleccion.setResizable(false);

	}

}
