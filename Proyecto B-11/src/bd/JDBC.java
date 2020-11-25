package bd;

import java.sql.*;

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

			Connection conn = DriverManager.getConnection("tienda.db");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO usuario VALUES('asasasa','assddfadad')");
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL");
		}
	}
}
