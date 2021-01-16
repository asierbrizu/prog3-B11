package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import bd.JDBC;
import clases.CamisetaYPantalon;
import clases.Producto;
import clases.Talla;
import clases.Zapato;
import main.Inicio;

public class Seleccionar {
	static JDialog seleccion;
	static boolean desea;

	public static void mostrar(String usuario, Producto producto) {
		desea = JDBC.loDesea(usuario, String.valueOf(producto.getId()));

		seleccion = new JDialog(Inicio.ventana, producto.getNombre(), true);
		seleccion.setLayout(new BorderLayout());
		JPanel info = new JPanel(new GridLayout(0, 2));

		URL icono = Inicio.ventana.getClass().getResource(Inicio.imagenes + producto.getImagen());
		Image img = new ImageIcon(icono).getImage();
		Image resizedImage = img.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
		JLabel imagen = new JLabel(new ImageIcon(resizedImage));
		seleccion.add(imagen, BorderLayout.WEST);

		JLabel nombre = new JLabel(producto.getNombre());
		JLabel precio = new JLabel(String.valueOf(producto.getPrecio() * producto.getDescuento()) + "€");
		JComboBox<Integer> numeroPie = new JComboBox<Integer>();
		JComboBox<Talla> talla = new JComboBox<Talla>();
		Integer[] numeros = { 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 };
		numeroPie.setModel(new DefaultComboBoxModel<Integer>(numeros));
		talla.setModel(new DefaultComboBoxModel<Talla>(Talla.values()));

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
		JSpinner cant = new JSpinner(spinnerModel);
		((DefaultEditor) cant.getEditor()).getTextField().setEditable(false);

		info.add(new JLabel("Nombre:"));
		info.add(nombre);
		info.add(new JLabel("Precio:"));
		info.add(precio);
		if (producto instanceof CamisetaYPantalon) {
			info.add(new JLabel("Talla:"));
			info.add(talla);
		} else {
			info.add(new JLabel("Numero:"));
			info.add(numeroPie);
		}
		info.add(new JLabel("Cantidad"));
		info.add(cant);

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
										Inicio.esVentanaDeseados = false;
										Inicio.ventana.dispose();
										VentanaPrincipal.transicion.interrupt();
										Inicio.ventana = new VentanaPrincipal();
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

		bConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean anyadido = false;
				Set<Producto> claves = Inicio.mapaCesta.keySet();

				Producto prod = new Producto();
				if (producto instanceof CamisetaYPantalon) {
					prod = new CamisetaYPantalon(producto.getId(), producto.getNombre(), producto.getPrecio(),
							producto.getDescuento(), producto.getColor(), producto.getImagen(),
							producto.isDescatalogado(), (Talla) talla.getSelectedItem(),
							((CamisetaYPantalon) producto).getMaterial(),
							((CamisetaYPantalon) producto).isEsCamiseta());
					for (Producto p : claves) {
						if (p instanceof CamisetaYPantalon) {
							if (((CamisetaYPantalon) p).equals(prod)) {
								int anterior = Inicio.mapaCesta.get(p);
								Inicio.mapaCesta.remove(p);
								Inicio.mapaCesta.put(p, anterior + (Integer) cant.getValue());
								Inicio.logger.info(p.getNombre() + " añadido a la cesta.");
								anyadido = true;
								break;
							}
						}

					}
				} else if (producto instanceof Zapato) {
					prod = new Zapato(producto.getId(), producto.getNombre(), producto.getPrecio(),
							producto.getDescuento(), producto.getColor(), producto.getImagen(),
							producto.isDescatalogado(), (Integer) numeroPie.getSelectedItem(),
							((Zapato) producto).getTipoSuela(), ((Zapato) producto).isTieneVelcro());
					for (Producto p : claves) {
						if (p instanceof Zapato) {
							if (((Zapato) p).equals(prod)) {
								int anterior = Inicio.mapaCesta.get(p);
								Inicio.mapaCesta.remove(p);
								Inicio.mapaCesta.put(p, anterior + (Integer) cant.getValue());
								Inicio.logger.info(p.getNombre() + " añadido a la cesta.");
								anyadido = true;
								break;
							}
						}

					}
				}
				if (!anyadido) {
					Inicio.mapaCesta.put(prod, (Integer) cant.getValue());
					Inicio.logger.info(prod.getNombre() + " añadido a la cesta.");
				}
				seleccion.dispose();
			}
		});

		if (Inicio.usuarioIniciado == "") {
			bDeseado.setEnabled(false);
		}

		JPanel botones = new JPanel(new FlowLayout());

		botones.add(bDeseado);
		botones.add(bConfirmar);
		seleccion.add(botones, BorderLayout.SOUTH);
		seleccion.setSize(530, 300);
		seleccion.setVisible(true);
		seleccion.setResizable(false);

	}

}
