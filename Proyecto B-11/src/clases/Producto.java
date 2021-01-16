package clases;

public class Producto {

	private int id;
	private String nombre;
	private double precio;
	private double descuento;
	private String color;
	private String imagen;
	private boolean estaDescatalogado;

	public Producto() {
		super();
		this.id = 0;
		this.nombre = "";
		this.precio = 0.0;
		this.descuento = 0.0;
		this.color = "";
		this.imagen = "";
		this.estaDescatalogado = true;
	}

	public Producto(int id, String nombre, double precio, double descuento, String color, String imagen,
			boolean estaDescatalogado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.descuento = descuento;
		this.color = color;
		this.imagen = imagen;
		this.estaDescatalogado = estaDescatalogado;
	}

	public int getId() {
		return id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isDescatalogado() {
		return estaDescatalogado;
	}

	public void setDescatalogado(boolean descatalogado) {
		this.estaDescatalogado = descatalogado;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", descuento=" + descuento + ", color=" + color
				+ ", imagen=" + imagen + "]";
	}

}
