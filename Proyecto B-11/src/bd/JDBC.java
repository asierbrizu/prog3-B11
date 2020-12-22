package bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import clases.CamisetaYPantalon;
import clases.Producto;
import clases.Talla;
import clases.Usuario;
import clases.Zapato;
import main.Inicio;

public class JDBC {
	public static void importar() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
	}

	public static void conectarBD() {
		try {

			Connection conn = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM camiseta");
			while (rs.next()) {

				int id = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				float precio = rs.getFloat("precio");
				float descuento = rs.getFloat("descuento");
				String color = rs.getString("color");
				String imagen = rs.getString("imagen");
				boolean descatalogado = (rs.getInt("descatalogado") == 1);
				Talla talla = Talla.valueOf(rs.getString("talla"));
				String material = rs.getString("material");
				boolean esCamiseta = (rs.getInt("esCamiseta") == 1);
				Producto camiseta = new CamisetaYPantalon(id, nombre, precio, descuento, color, imagen, descatalogado,
						talla, material, esCamiseta);
				Inicio.camisetasYPantalones.add((CamisetaYPantalon) camiseta);
				Inicio.mapaProducto.put(String.valueOf(rs.getInt("idProducto")), camiseta);
			}
			rs = stmt.executeQuery("SELECT * FROM zapatos");
			while (rs.next()) {

				int id = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				float precio = rs.getFloat("precio");
				float descuento = rs.getFloat("descuento");
				String color = rs.getString("color");
				String imagen = rs.getString("imagen");
				boolean descatalogado = (rs.getInt("descatalogado") == 1);
				int numero = rs.getInt("numero");
				String suela = rs.getString("tipoSuela");
				boolean velcro = (rs.getInt("velcro") == 1);
				Producto zapato = new Zapato(id, nombre, precio, descuento, color, imagen, descatalogado, numero, suela,
						velcro);
				Inicio.zapatos.add((Zapato) zapato);
				Inicio.mapaProducto.put(String.valueOf(rs.getInt("idProducto")), zapato);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL");
		}
	}

	public static boolean comprobarUsuario(String correo) {
		boolean existe = false;
		try {

			Connection conn = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			while (rs.next()) {
				if (rs.getString("correo").equals(correo)) {
					existe = true;
				}
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL");
		}
		return existe;
	}

	public static boolean comprobarContrasenya(String correo, String contrasenya) {
		boolean coincide = false;
		try {

			Connection conn = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			while (rs.next()) {
				if (rs.getString("correo").equals(correo) && rs.getString("contrasenya").equals(contrasenya)) {
					coincide = true;
				}
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL");
		}
		return coincide;
	}

	public static void cargarUsuarios() {

		try {

			Connection conn = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getString("correo"), rs.getString("contrasenya"));
				Inicio.usuarios.add(usuario);
				Inicio.mapaUsuario.put(usuario.getCorreo(), usuario);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL");
		}

	}

	public static void crearUsuario(String usu, String contra) {

		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS conteo FROM usuario WHERE correo=?;");
			stmt.setString(1, usu);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			if (rs.getInt("conteo") == 0) {
				stmt = conn.prepareStatement("INSERT INTO usuario VALUES(?,?)");
				JOptionPane.showMessageDialog(Inicio.ventana, "Cuenta creada", "El usuario ha sido creado con éxito",
						JOptionPane.INFORMATION_MESSAGE);
				stmt.setString(1, usu);
				stmt.setString(2, contra);
				stmt.execute();
				Usuario usuario = new Usuario(usu, contra);
				Inicio.usuarios.add(usuario);
				Inicio.mapaUsuario.put(usuario.getCorreo(), usuario);
			} else {
				JOptionPane.showMessageDialog(Inicio.ventana, "Usuario ya existe", "El usuario ya existe",
						JOptionPane.ERROR_MESSAGE);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL");
		}

	}

	public static boolean loDesea(Usuario usu, String id) {
		boolean desea = false;
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deseados WHERE correo=?;");
			stmt.setString(1, usu.getCorreo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("idCamiseta").isEmpty()) {
					if (rs.getString("idZapatos").equals(id)) {
						desea = true;
					}
				} else {
					if (rs.getString("idCamiseta").equals(id)) {
						desea = true;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
		}
		return desea;
	}

	public static ArrayList<Producto> productosDeseados(Usuario usuario) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deseados WHERE correo=?;");
			stmt.setString(1, usuario.getCorreo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("idCamiseta").isEmpty()) {
					productos.add(Inicio.mapaProducto.get(rs.getInt("idZapatos")));
				} else {
					productos.add(Inicio.mapaProducto.get(rs.getInt("idCamiseta")));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
		}
		return productos;
	}
}
