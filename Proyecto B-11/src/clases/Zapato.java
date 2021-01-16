package clases;

public class Zapato extends Producto {
	private int numero;
	private String tipoSuela;
	private boolean tieneVelcro;

	public Zapato(int id, String nombre, double precio, double descuento, String color, String imagen,
			boolean estaDescatalogado, int numero, String tipoSuela, boolean tieneVelcro) {
		super(id, nombre, precio, descuento, color, imagen, estaDescatalogado);
		this.numero = numero;
		this.tipoSuela = tipoSuela;
		this.tieneVelcro = tieneVelcro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipoSuela() {
		return tipoSuela;
	}

	public void setTipoSuela(String tipoSuela) {
		this.tipoSuela = tipoSuela;
	}

	public boolean isTieneVelcro() {
		return tieneVelcro;
	}

	public void setTieneVelcro(boolean tieneVelcro) {
		this.tieneVelcro = tieneVelcro;
	}

	@Override
	public String toString() {
		return "Zapato [numero=" + numero + ", tipoSuela=" + tipoSuela + ", tieneVelcro=" + tieneVelcro + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Zapato) {
			if (((Zapato) obj).getNombre().equals(this.getNombre()) && ((Zapato) obj).getNumero() == this.getNumero()) {
				return true;
			}
		}
		return false;
	}

}
