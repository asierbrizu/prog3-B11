package main;

import java.awt.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import bd.JDBC;
import clases.Producto;
import gui.VentanaPrincipal;

public class CambiarImagen extends Thread {

	@Override
	public void run() {
		int actual = 0;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		if (Inicio.usuarioIniciado != null) {
			List<Producto> productosDeseados = JDBC.productosDeseados(Inicio.usuarioIniciado);
			for (int i = 0; i < productosDeseados.size(); i++) {
				if (!productosDeseados.get(i).isDescatalogado()) {
					productos.add(productosDeseados.get(i));
				}
			}
		} else {

			List<Producto> todosLosProductos = new ArrayList<Producto>(Inicio.mapaProducto.values());

			for (int i = 0; i < todosLosProductos.size(); i++) {
				if (!todosLosProductos.get(i).isDescatalogado()) {
					productos.add(todosLosProductos.get(i));
				}
			}

		}

		while (true) {
			int maximo = productos.size();
			if (actual == maximo) {
				actual = 0;
			}
			try {
				Thread.sleep(3000);
				String ruta = "/" + Inicio.mapaProducto.get(String.valueOf(productos.get(actual).getId())).getImagen();

				URL icono = Inicio.ventana.getClass().getResource(ruta);
				Image img = new ImageIcon(icono).getImage();
				Image resizedImage = img.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
				VentanaPrincipal.boton1 = new JButton(new ImageIcon(resizedImage));

				VentanaPrincipal.superior.removeAll();
				VentanaPrincipal.superior.add(VentanaPrincipal.boton1);
				Inicio.ventana.revalidate();
				Inicio.ventana.validate();
				Inicio.ventana.repaint();
				actual += 1;
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (NullPointerException n) {
			}

		}
	}

}
