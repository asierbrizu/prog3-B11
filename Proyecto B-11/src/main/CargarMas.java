package main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import clases.CamisetaYPantalon;
import gui.Seleccionar;
import gui.VentanaProductos;

public class CargarMas extends Thread {

	ActionListener listener = new ActionListener() {

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
			CamisetaYPantalon c;
			while (VentanaProductos.i.hasNext()) {
				c = VentanaProductos.i.next();
				if (!c.isDescatalogado()) {

					URL icono = Inicio.ventana.getClass().getResource("/" + c.getImagen());

					JButton iconito = new JButton(new ImageIcon(icono));
					iconito.setName(String.valueOf(c.getId()));
					iconito.addActionListener(listener);
					VentanaProductos.panelCatalogo.add(iconito);
					break;
				}
			}

			VentanaProductos.panelCatalogo.validate();
			VentanaProductos.scroll.revalidate();
		}
		VentanaProductos.panelCatalogo.add(VentanaProductos.verMas);

	}
}
