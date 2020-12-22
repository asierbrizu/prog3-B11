package clases;

public class CamisetaYPantalon extends Producto {

	private Talla talla;
	private String material;
	private boolean esCamiseta;

	public CamisetaYPantalon(int id, String nombre, double precio, double descuento, String color, String imagen,
			boolean estaDescatalogado, Talla talla, String material, boolean esCamiseta) {
		super(id, nombre, precio, descuento, color, imagen, estaDescatalogado);
		this.talla = talla;
		this.material = material;
		this.esCamiseta = esCamiseta;
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

	public boolean isEsCamiseta() {
		return esCamiseta;
	}

	public void setEsCamiseta(boolean esCamiseta) {
		this.esCamiseta = esCamiseta;
	}

	@Override
	public String toString() {
		return "CamisetaYPantalon [talla=" + talla + ", material=" + material + ", esCamiseta=" + esCamiseta + "]";
	}

}
