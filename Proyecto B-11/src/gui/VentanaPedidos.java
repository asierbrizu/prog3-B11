package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bd.JDBC;
import clases.CamisetaYPantalon;
import clases.Pedido;
import clases.Producto;
import clases.Zapato;
import main.Inicio;

public class VentanaPedidos extends JFrame {

	class ModeloPedidos extends DefaultListModel<Pedido> {

		public ModeloPedidos(Collection<Pedido> pedidos) {
			add(pedidos);
		}

		public void add(Collection<Pedido> pedidos) {
			for (Pedido p : pedidos) {
				addElement(p);
			}
		}

	}

	public VentanaPedidos() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Pedidos");

		JPanel centro = new JPanel(new GridLayout(0, 3));
		JScrollPane scroll = new JScrollPane(centro);
		ArrayList<Pedido> pedidos = JDBC.obtenerPedidos(Inicio.usuarioIniciado);
		add(scroll, BorderLayout.CENTER);
		ModeloPedidos modelo = new ModeloPedidos(pedidos);
		JList<Pedido> lista = new JList<Pedido>(modelo);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setFont(new Font("Arial", Font.BOLD, 20));
		add(lista, BorderLayout.WEST);
		JMenuBar barra = VentanaPrincipal.barra;
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		setJMenuBar(barra);
		setVisible(true);
		setSize(1600, 1200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		lista.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				centro.removeAll();
				Pedido pedido = lista.getSelectedValue();
				Set<Producto> productos = pedido.getProductos().keySet();

				for (Producto p : productos) {
					JPanel panel = new JPanel(new BorderLayout());
					URL icono = Inicio.ventana.getClass().getResource(Inicio.imagenes + p.getImagen());
					Image img = new ImageIcon(icono).getImage();
					Image resizedImage = img.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);

					panel.add(new JLabel(new ImageIcon(resizedImage)), BorderLayout.WEST);
					JPanel textos = new JPanel(new BorderLayout());
					JPanel izquierda = new JPanel(new GridLayout(0, 1));
					JPanel derecha = new JPanel(new GridLayout(0, 1));
					izquierda.add(new JLabel("Nombre:"));
					derecha.add(new JLabel(p.getNombre()));

					if (p instanceof CamisetaYPantalon) {
						izquierda.add(new JLabel("Talla:"));
						derecha.add(new JLabel(((CamisetaYPantalon) p).getTalla().toString()));
					} else if (p instanceof Zapato) {
						izquierda.add(new JLabel("Numero:"));
						derecha.add(new JLabel(String.valueOf(((Zapato) p).getNumero())));
					}
					izquierda.add(new JLabel("Precio unidad:         "));
					derecha.add(new JLabel(String.valueOf(p.getPrecio() * p.getDescuento()) + "€"));

					izquierda.add(new JLabel("Cantidad: "));
					derecha.add(new JLabel(String.valueOf(pedido.getProductos().get(p))));

					izquierda.add(new JLabel("Subtotal: "));
					Double precio = p.getPrecio() * p.getDescuento() * pedido.getProductos().get(p);
					derecha.add(new JLabel(String.valueOf(new DecimalFormat("#.##").format(precio)) + "€"));

					textos.add(izquierda, BorderLayout.WEST);
					textos.add(derecha, BorderLayout.CENTER);
					panel.add(textos, BorderLayout.CENTER);
					panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
					centro.add(panel);

				}
				int restantes = 0;
				while (restantes + productos.size() < 7) {
					centro.add(new JPanel());
					restantes++;
				}

				repaint();
				validate();
				revalidate();
			}
		});

	}
}
