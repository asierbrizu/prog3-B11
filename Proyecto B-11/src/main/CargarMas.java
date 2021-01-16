package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import clases.Producto;
import gui.Seleccionar;
import gui.VentanaProductos;

public class CargarMas extends Thread {

	public static ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Seleccionar.mostrar(Inicio.usuarioIniciado, Inicio.mapaProducto.get(((JButton) e.getSource()).getName()));
		}
	};

	@Override
	public void run() {
		VentanaProductos.hastaProducto += 5;
		VentanaProductos.panelCatalogo.remove(VentanaProductos.verMas);
		for (; VentanaProductos.productoActual <= VentanaProductos.hastaProducto
				&& VentanaProductos.i.hasNext(); VentanaProductos.productoActual++) {
			Producto c;
			while (VentanaProductos.i.hasNext()) {
				c = VentanaProductos.i.next();
				if (!c.isDescatalogado()) {

					URL icono = Inicio.ventana.getClass().getResource(Inicio.imagenes + c.getImagen());
					Image img = new ImageIcon(icono).getImage();
					Image resizedImage = img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);

					JButton iconito = new JButton(new ImageIcon(resizedImage));
					iconito.setName(String.valueOf(c.getId()));
					iconito.addActionListener(listener);
					VentanaProductos.panelCatalogo.add(iconito);
					break;
				}
			}

			VentanaProductos.panelCatalogo.validate();
			VentanaProductos.scroll.revalidate();
		}
		if (!Inicio.esVentanaDeseados) {
			VentanaProductos.panelCatalogo.add(VentanaProductos.verMas);
		}
	}
}
