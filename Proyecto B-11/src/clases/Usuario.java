package clases;

import java.util.*;

public class Usuario {

	private String correo;
	private String contrasenya;
	private HashSet<Pedido> pedidos;
	private ArrayList<Producto> cesta;
	private HashMap<String, ArrayList<Producto>> listas;

	public Usuario(String correo, String contrasenya) {
		super();
		this.correo = correo;
		this.contrasenya = contrasenya;
	}

	public Usuario(String correo, String contrasenya, HashSet<Pedido> pedidos) {
		super();
		this.correo = correo;
		this.contrasenya = contrasenya;
		this.pedidos = pedidos;
		this.cesta = new ArrayList<Producto>();
		this.listas = new HashMap<String, ArrayList<Producto>>();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public HashSet<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(HashSet<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public HashMap<String, ArrayList<Producto>> getListas() {
		return listas;
	}

	public void setListas(HashMap<String, ArrayList<Producto>> listas) {
		this.listas = listas;
	}

	public ArrayList<Producto> getCesta() {
		return cesta;
	}

	public void setCesta(ArrayList<Producto> cesta) {
		this.cesta = cesta;
	}

	@Override
	public String toString() {
		return "Usuario [correo=" + correo + ", pedidos=" + pedidos + "]";
	}

	public void darseDeBaja() {
		// Borrar al usuario de la Base de Datos.
	}

	public void anyadirALista(String nombre, Producto inicial) {
		if (!this.listas.containsKey(nombre)) {
			this.listas.put(nombre, new ArrayList<Producto>());
		}
		this.listas.get(nombre).add(inicial);
	}

}
