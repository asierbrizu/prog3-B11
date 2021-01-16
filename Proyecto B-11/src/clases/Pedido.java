package clases;

import java.sql.Timestamp;
import java.util.*;

public class Pedido {

	private int id;
	private HashMap<Producto, Integer> productos;
	private double importeTotal;
	private Timestamp fecha;
	private String correo;

	public Pedido(int id, HashMap<Producto, Integer> productos, double importeTotal, String correo, Timestamp fecha) {
		super();
		this.id = id;
		this.productos = productos;
		this.importeTotal = importeTotal;
		this.fecha = fecha;
		this.correo = correo;
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

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Fecha: " + fecha + " | Importe total: " + importeTotal + "�";
	}

}
