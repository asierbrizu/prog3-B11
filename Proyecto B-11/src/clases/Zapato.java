package clases;

public class Zapato extends Producto{
	private int numero;
	private String tipoSuela;
	private boolean tieneVelcro;
	public Zapato(String nombre, double precio, double descuento, String color, String imagen,int numero, String tipoSuela, boolean tieneVelcro) {
		super(nombre, precio, descuento, color,imagen);
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
	
	
}
