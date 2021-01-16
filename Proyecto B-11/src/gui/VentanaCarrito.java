package gui;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import bd.JDBC;
import clases.CamisetaYPantalon;
import clases.Producto;
import clases.Zapato;
import main.Inicio;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class VentanaCarrito {

	public static JDialog cesta;

	public static void mostrarCesta() {
		cesta = new JDialog(Inicio.ventana, "Carrito de la compra", true);
		String[] columnNames = { "Nombre", "Precio", "Color", "Tamaño", "Caracteristicas", "Cantidad", "Borrar" };

		Object[][] data = new Object[Inicio.mapaCesta.size()][7];
		Set<Producto> claves = Inicio.mapaCesta.keySet();
		int i = 0;
		for (Producto p : claves) {
			data[i][0] = p.getNombre();
			data[i][1] = p.getPrecio() * p.getDescuento() + "€";
			data[i][2] = p.getColor();

			if (p instanceof CamisetaYPantalon) {
				data[i][3] = ((CamisetaYPantalon) p).getTalla().toString();
			} else if (p instanceof Zapato) {
				data[i][3] = ((Zapato) p).getNumero();
			}

			if (p instanceof CamisetaYPantalon) {
				data[i][4] = ((CamisetaYPantalon) p).getMaterial();
			} else if (p instanceof Zapato) {
				data[i][4] = ((Zapato) p).getTipoSuela();
			}

			data[i][5] = Inicio.mapaCesta.get(p);

			data[i][6] = "Eliminar";
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);

		cesta.add(scrollPane, BorderLayout.CENTER);

		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());

				for (Producto p : claves) {
					if (p instanceof CamisetaYPantalon) {
						if (p.getNombre().equals(model.getValueAt(modelRow, 0)) && ((CamisetaYPantalon) p).getTalla()
								.toString().equals(model.getValueAt(modelRow, 3))) {
							Inicio.mapaCesta.remove(p);
							Inicio.logger.info(p.getNombre() + " eliminado del carrito.");
							break;
						}
					} else if (p instanceof Zapato) {
						if (p.getNombre().equals(model.getValueAt(modelRow, 0)) && String
								.valueOf(((Zapato) p).getNumero()).equals(model.getValueAt(modelRow, 3).toString())) {
							Inicio.mapaCesta.remove(p);
							Inicio.logger.info(p.getNombre() + " eliminado del carrito.");
							break;
						}
					}

				}
				((DefaultTableModel) table.getModel()).removeRow(modelRow);

				if (Inicio.mapaCesta.isEmpty()) {
					cesta.dispose();
				}
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 6);

		JButton tramitar = new JButton("Guardar pedido");
		tramitar.setEnabled(Inicio.usuarioIniciado != "");
		tramitar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDBC.tramitarPedido();
				cesta.dispose();
			}
		});
		cesta.add(tramitar, BorderLayout.SOUTH);

		cesta.setSize(1000, 500);
		cesta.setVisible(true);
		cesta.setResizable(false);

	}

}