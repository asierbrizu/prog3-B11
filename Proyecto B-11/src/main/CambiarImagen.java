package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import gui.Seleccionar;
import gui.VentanaPrincipal;

public class CambiarImagen extends Thread {
	public static int actual = 0;
	public static ArrayList<Producto> productos = new ArrayList<Producto>();
	public static List<Producto> productosDeseados;
	public static List<Producto> todosLosProductos;
	@Override
	public void run() {
		
		if (Inicio.usuarioIniciado != ""&&!JDBC.productosDeseados(Inicio.usuarioIniciado).isEmpty()) {
			productosDeseados = JDBC.productosDeseados(Inicio.usuarioIniciado);
			for (int i = 0; i < productosDeseados.size(); i++) {
				if (!productosDeseados.get(i).isDescatalogado()) {
					productos.add(productosDeseados.get(i));
				}
			}
		} else {

			todosLosProductos = new ArrayList<Producto>(Inicio.mapaProducto.values());
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
				VentanaPrincipal.bProd = new JButton(new ImageIcon(resizedImage));
				VentanaPrincipal.bProd.setName(String.valueOf(productos.get(actual).getId()));
				VentanaPrincipal.bProd.addActionListener(new ActionListener() {		
					@Override
					public void actionPerformed(ActionEvent e) {
						Seleccionar.mostrar(Inicio.usuarioIniciado, Inicio.mapaProducto.get(VentanaPrincipal.bProd.getName()));
					}
				});
				VentanaPrincipal.superior.removeAll();
				VentanaPrincipal.superior.add(VentanaPrincipal.bProd);
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
