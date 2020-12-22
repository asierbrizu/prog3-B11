package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases.Producto;
import clases.Talla;
import clases.Usuario;
import main.Inicio;

public class Seleccionar {
	static JDialog seleccion;

	public static void mostrar(Usuario usuario, Producto producto) {
		seleccion = new JDialog(Inicio.ventana, producto.getNombre(), true);
		seleccion.setLayout(new GridLayout(2, 2));
		JPanel info = new JPanel(new BorderLayout());

		URL icono = Inicio.ventana.getClass().getResource("/" + producto.getImagen());
		Image img = new ImageIcon(icono).getImage();
		Image resizedImage = img.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
		JLabel imagen = new JLabel(new ImageIcon(resizedImage));
		seleccion.add(imagen);

		JLabel nombre = new JLabel(producto.getNombre());
		JLabel precio = new JLabel(String.valueOf(producto.getPrecio() * producto.getDescuento()));
		JComboBox<Talla> talla = new JComboBox<Talla>();
		talla.setModel(new DefaultComboBoxModel(Talla.values()));
		info.add(nombre, BorderLayout.NORTH);
		info.add(precio, BorderLayout.WEST);
		info.add(talla, BorderLayout.EAST);

		seleccion.add(info);

		JButton bDeseado = new JButton("Añadir a deseados.");
		JButton bConfirmar = new JButton("Añadir a la cesta");

		seleccion.add(bDeseado);
		seleccion.add(bConfirmar);

		seleccion.setSize(800, 600);
		seleccion.setVisible(true);
		seleccion.setResizable(false);

	}

}
