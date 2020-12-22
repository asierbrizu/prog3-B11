package clases;

import java.util.*;

public class Pedido {

	public static int idActual = 1;
	private int id;
	private HashMap<Producto, Integer> productos;
	private double importeBase;
	private double importeTotal;
	private Date fecha;

	public Pedido(HashMap<Producto, Integer> productos, double importeBase, double importeTotal) {
		super();
		this.id = idActual;
		idActual += 1;
		this.productos = productos;
		this.importeBase = importeBase;
		this.importeTotal = importeTotal;
		this.fecha = null;
	}

	public int getId() {
		return id;
	}

	public HashMap<Producto, Integer> getProductos() {
		return productos;
	}

	public void setProductos(HashMap<Producto, Integer> productos) {
		this.productos = productos;
	}

	public double getImporteBase() {
		return importeBase;
	}

	public void setImporteBase(double importeBase) {
		this.importeBase = importeBase;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", productos=" + productos + ", importeBase=" + importeBase + ", importeTotal="
				+ importeTotal + ", fecha=" + fecha + "]";
	}

}
