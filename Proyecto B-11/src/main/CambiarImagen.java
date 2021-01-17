package main;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

		if (Inicio.usuarioIniciado != "" && !JDBC.productosDeseados(Inicio.usuarioIniciado).isEmpty()) {
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

		while (!VentanaPrincipal.transicion.isInterrupted()) {
			int maximo = productos.size();
			if (actual == maximo) {
				actual = 0;
			}

			try {

				try {
					Thread.sleep(Integer.valueOf(Inicio.milisegundos));
				} catch (Exception e) {
					Thread.sleep(5000);

				}

				String ruta = Inicio.imagenes
						+ Inicio.mapaProducto.get(String.valueOf(productos.get(actual).getId())).getImagen();

				URL icono = Inicio.ventana.getClass().getResource(ruta);
				Image img = new ImageIcon(icono).getImage();
				Image resizedImage = img.getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()*4/10, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()*4/10, java.awt.Image.SCALE_SMOOTH);
				VentanaPrincipal.bProd = new JButton(new ImageIcon(resizedImage));
				VentanaPrincipal.bProd.setName(String.valueOf(productos.get(actual).getId()));
				VentanaPrincipal.bProd.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Seleccionar.mostrar(Inicio.usuarioIniciado,
								Inicio.mapaProducto.get(VentanaPrincipal.bProd.getName()));
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
				Inicio.logger.severe("Error al cambiar la imagen de la pagina principal.");
			}
		}
	}

}
