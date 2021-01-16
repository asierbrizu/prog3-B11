package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import clases.Producto;
import main.CargarMas;
import main.Inicio;

public class VentanaProductos extends JFrame {

	public static Iterator<Producto> i;
	public static int productoActual = 0;
	public static int hastaProducto = 0;
	public static JPanel panelCatalogo = new JPanel();
	public static JScrollPane scroll;
	public static JButton verMas;

	public VentanaProductos(Set<Producto> productos) {
		productoActual = hastaProducto = 0;
		if (Inicio.esVentanaDeseados) {
			hastaProducto = productos.size();
		}
		i = productos.iterator();
		panelCatalogo.removeAll();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Productos");
		scroll = new JScrollPane(panelCatalogo);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setBounds(0, 0, 800, 600);
		scroll.setViewportView(panelCatalogo);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		try {
			panelCatalogo.setLayout(new GridLayout(0, Integer.valueOf(Inicio.columnas)));
		} catch (Exception e) {
			panelCatalogo.setLayout(new GridLayout(0, 4));
			Inicio.logger.log(Level.INFO,
					"No se ha podido cargar la cantidad de columnas del fichero de configuración.");
		}

		verMas = new JButton("Ver mas.");

		// Menú
		JMenuBar barra = VentanaPrincipal.barra;
		setJMenuBar(barra);
		if (!Inicio.esVentanaDeseados) {
			panelCatalogo.add(verMas);
		}
		verMas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CargarMas().run();
			}
		});
		new CargarMas().run();

		pack();
		setVisible(true);
		setSize(1600, 1200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		add(scroll);

	}

}
