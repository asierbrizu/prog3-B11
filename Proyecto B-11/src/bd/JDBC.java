package bd;

import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JOptionPane;
import clases.CamisetaYPantalon;
import clases.Pedido;
import clases.Producto;
import clases.Talla;
import clases.Usuario;
import clases.Zapato;
import main.Inicio;

public class JDBC {

	public static void importar() {
		try {
			Class.forName("org.sqlite.JDBC");
			Inicio.logger.info("JDBC cargado.");
		} catch (ClassNotFoundException e) {
			Inicio.logger.severe("No se ha podido cargar el driver de la base de datos.");
		}
	}

	public static void conectarBD() {
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM camiseta");

			while (rs.next()) {
				int id = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				double precio = rs.getDouble("precio");
				double descuento = rs.getDouble("descuento");
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
				double precio = rs.getDouble("precio");
				double descuento = rs.getDouble("descuento");
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

			Inicio.logger.info("Productos cargados.");
			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al cargar los productos de la BD.");
		}
	}

	public static boolean comprobarUsuario(String correo) {
		boolean existe = false;
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

			while (rs.next()) {
				if (rs.getString("correo").equals(correo)) {
					existe = true;
				}
			}

			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al comprobar la existecia del usuario " + correo + ".");
		}
		return existe;
	}

	public static boolean comprobarContrasenya(String correo, String contrasenya) {
		boolean coincide = false;
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

			while (rs.next()) {
				if (rs.getString("correo").equals(correo) && rs.getString("contrasenya").equals(contrasenya)) {
					coincide = true;
				}
			}

			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al comprobar la contraseña del usuario " + correo + ".");
		}
		return coincide;
	}

	public static void crearUsuario(String usu, String contra) {
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
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
			} else {
				JOptionPane.showMessageDialog(Inicio.ventana, "Usuario ya existe", "El usuario ya existe",
						JOptionPane.ERROR_MESSAGE);
			}
			Inicio.logger.info("Usuario " + usu + " creado.");
			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al crear el usuario " + usu + ".");
		}
	}

	public static boolean loDesea(String usu, String id) {
		boolean desea = false;
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deseados WHERE correo=?;");
			stmt.setString(1, usu);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("idCamiseta") == null) {
					if (rs.getString("idZapatos").equals(id)) {
						desea = true;
					}
				} else {
					if (rs.getString("idCamiseta").equals(id)) {
						desea = true;
					}
				}
			}

			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al determinar si el usuario " + usu + " tiene el producto ID:" + id
					+ " en su lista de deseados.");
		}
		return desea;
	}

	public static void anyadirDeseado(String usu, String id) {
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO deseados VALUES(?,?,?)");
			if (Inicio.mapaProducto.get(id) instanceof CamisetaYPantalon) {
				stmt.setString(1, id);
				stmt.setString(3, usu);
			} else {
				stmt.setString(2, id);
				stmt.setString(3, usu);
			}
			stmt.execute();
			Inicio.logger.info("Producto ID:" + id + " añadida a la lista de deseados de " + usu + ".");
			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al añadir el producto ID:" + id + " a la lista de deseados de " + usu + ".");
		}
	}

	public static void eliminarDeseados(String usu, String id) {
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			PreparedStatement stmt;
			if (Inicio.mapaProducto.get(id) instanceof CamisetaYPantalon) {
				stmt = conn.prepareStatement("DELETE FROM deseados WHERE correo=? AND idCamiseta=?");
			} else {
				stmt = conn.prepareStatement("DELETE FROM deseados WHERE correo=? AND idZapatos=?");
			}
			stmt.setString(1, usu);
			stmt.setString(2, id);
			stmt.execute();
			Inicio.logger.info("Producto ID:" + id + " eliminado de la lista de deseados de " + usu + ".");
			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al eliminar el producto ID:" + id + " de la lista de deseados de " + usu + ".");
		}
	}

	public static ArrayList<Producto> productosDeseados(String usuario) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deseados WHERE correo=?;");
			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("idCamiseta") == null) {
					productos.add(Inicio.mapaProducto.get(String.valueOf(rs.getInt("idZapatos"))));
				} else {
					productos.add(Inicio.mapaProducto.get(String.valueOf(rs.getInt("idCamiseta"))));
				}
			}

			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al obtener la lista de productos deseados de " + usuario + ".");
		}
		return productos;
	}

	public static void tramitarPedido() {
		Connection conn;
		try {
			double importeTotal = 0.0;
			Set<Producto> claves = Inicio.mapaCesta.keySet();

			for (Producto producto : claves) {
				importeTotal += producto.getPrecio() * producto.getDescuento() * Inicio.mapaCesta.get(producto);
			}

			conn = DriverManager.getConnection(Inicio.database);
			Statement stmt;
			PreparedStatement pstmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM pedido;");
			int maximo = 0;

			while (rs.next()) {
				if (maximo < rs.getInt("id")) {
					maximo = rs.getInt("id");
				}
			}

			int id = 1 + maximo;
			pstmt = conn.prepareStatement("INSERT INTO pedido VALUES(?,?,?,?);");
			pstmt.setInt(1, id);
			pstmt.setDouble(2, importeTotal);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(3, df.format(new Timestamp(System.currentTimeMillis())));
			pstmt.setString(4, Inicio.usuarioIniciado);
			pstmt.execute();
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter(Inicio.pedidos + "_" + id + ".txt"));
			buffWriter.write("ID Pedido:" + id + " | Importe total: " + importeTotal + " | Usuario: "
					+ Inicio.usuarioIniciado + ".");
			buffWriter.newLine();
			buffWriter.newLine();
			buffWriter.write("Productos:");
			buffWriter.newLine();
			buffWriter.newLine();

			for (Producto producto : claves) {
				pstmt = conn.prepareStatement("INSERT INTO productospedido VALUES(?,?,?,?,?,?)");
				if (producto instanceof CamisetaYPantalon) {
					buffWriter.write(
							"   ID: " + id + ", " + " talla: " + ((CamisetaYPantalon) producto).getTalla().toString()
									+ " -> " + Inicio.mapaCesta.get(producto) + " unidades.");
					pstmt.setInt(1, producto.getId());
					pstmt.setString(6, ((CamisetaYPantalon) producto).getTalla().toString());
				} else if (producto instanceof Zapato) {
					buffWriter.write("   ID: " + id + ", " + " número: " + ((Zapato) producto).getNumero() + " -> "
							+ Inicio.mapaCesta.get(producto) + " unidades.");
					pstmt.setInt(2, producto.getId());
					pstmt.setInt(5, ((Zapato) producto).getNumero());
				}
				pstmt.setInt(3, id);
				pstmt.setInt(4, Inicio.mapaCesta.get(producto));
				pstmt.execute();
				buffWriter.newLine();
			}

			Inicio.mapaCesta.clear();
			Inicio.logger.info("Pedido " + id + " tramitado.");
			buffWriter.close();
			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al tramitar el pedido.");
		} catch (FileNotFoundException e) {
			Inicio.logger.severe("No se pudo encontrar el fichero.");
		} catch (IOException e) {
			Inicio.logger.severe("Hay problemas al escribir.");
		}
	}

	public static ArrayList<Pedido> obtenerPedidos(String correo) {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pedido WHERE correo=?;");
			stmt.setString(1, correo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PreparedStatement productosPedido = conn
						.prepareStatement("SELECT * FROM productospedido WHERE idPe=?;");
				productosPedido.setString(1, rs.getString("id"));
				ResultSet productos = productosPedido.executeQuery();
				HashMap<Producto, Integer> mapaProductos = new HashMap<Producto, Integer>();
				while (productos.next()) {
					if (productos.getString("idCamiseta") == null) {
						Producto producto = obtenerProducto(Integer.valueOf(productos.getString("idZapatos")), 1);
						((Zapato) producto).setNumero(productos.getInt("numero"));
						mapaProductos.put(producto, Integer.valueOf(productos.getInt("cant")));
					} else {
						Producto producto = obtenerProducto(Integer.valueOf(productos.getString("idCamiseta")), 0);
						((CamisetaYPantalon) producto).setTalla(Talla.valueOf(productos.getString("talla")));
						mapaProductos.put(producto, Integer.valueOf(productos.getInt("cant")));
					}
				}
				pedidos.add(new Pedido(Integer.valueOf(rs.getString("id")), mapaProductos, rs.getDouble("importeTotal"),
						correo, rs.getString("fecha")));
			}

		} catch (SQLException e) {
			Inicio.logger.severe("Error al obtener los pedidos de " + correo + ".");
		}
		return pedidos;
	}

	public static boolean tienePedidos(String usu) {
		try {
			Connection conn = DriverManager.getConnection(Inicio.database);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM pedido");

			while (rs.next()) {
				if (rs.getString("correo").equals(usu)) {
					return true;
				}
			}

			conn.close();
		} catch (SQLException e) {
			Inicio.logger.severe("Error al intentar determinar si el usuario " + usu + " ha realizado algun pedido.");
		}
		return false;
	}

	// 0 Si es Camiseta o pantalon, 1 si es Zapato, -1 si no ninguno de ellos.
	public static Producto obtenerProducto(int id, int tipoProd) {
		Connection conn;
		ResultSet rs;
		Producto prod = new Producto();
		try {
			conn = DriverManager.getConnection(Inicio.database);
			PreparedStatement stmt;
			switch (tipoProd) {
			case 0: {
				stmt = conn.prepareStatement("SELECT * FROM camiseta WHERE idProducto=?");
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				rs.next();
				prod = new CamisetaYPantalon(id, rs.getString("nombre"), rs.getDouble("precio"),
						rs.getDouble("descuento"), rs.getString("color"), rs.getString("imagen"),
						rs.getBoolean("descatalogado"), Talla.valueOf(rs.getString("talla")), rs.getString("material"),
						rs.getBoolean("esCamiseta"));
				break;
			}
			case 1: {
				stmt = conn.prepareStatement("SELECT * FROM zapatos WHERE idProducto=?");
				stmt.setString(1, String.valueOf(id));
				rs = stmt.executeQuery();
				rs.next();
				prod = new Zapato(id, rs.getString("nombre"), rs.getDouble("precio"), rs.getDouble("descuento"),
						rs.getString("color"), rs.getString("imagen"), rs.getBoolean("descatalogado"),
						rs.getInt("numero"), rs.getString("tipoSuela"), rs.getBoolean("velcro"));
				break;
			}
			default:
				break;
			}
		} catch (SQLException e) {
			Inicio.logger.severe("Error al intentar obtener el producto " + id + ".");
		}
		return prod;
	}
}
