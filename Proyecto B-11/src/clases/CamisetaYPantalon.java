package clases;

public class CamisetaYPantalon extends Producto{

	private Talla talla;
	private String material;
	
	public CamisetaYPantalon(String nombre, double precio, double descuento, String color, String imagen, Talla talla,
			String material) {
		super(nombre, precio, descuento, color,imagen);
		this.talla = talla;
		this.material = material;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "CamisetaYPantalon [talla=" + talla + ", material=" + material + "]";
	}
	
	
	
}
