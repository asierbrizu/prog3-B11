package main;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import bd.JDBC;
import clases.Producto;
import clases.Usuario;
import gui.VentanaPrincipal;

public class Inicio {
	public static Map<String, Producto> mapaProducto = new HashMap<String, Producto>();
	public static String usuarioIniciado = "";
	public static JFrame ventana;
	public static Thread cargarMas;
	public static Set<Producto> camisetasYPantalones = new HashSet<Producto>();
	public static Set<Producto> zapatos = new HashSet<Producto>();
	public static Set<Usuario> usuarios = new HashSet<Usuario>();
	public static boolean esVentanaDeseados = false;
	public static Map<Producto, Integer> mapaCesta = new HashMap<Producto, Integer>();
	public static Logger logger = Logger
			.getLogger(Inicio.class.getName() + " " + new Timestamp(System.currentTimeMillis()));
	public static String database;
	public static String milisegundos;
	public static String columnas;
	public static String imagenes;
	public static String pedidos;

	public static void main(String[] args) {
		File directory = new File("pedidos");
		if (!directory.exists()) {
			directory.mkdir();

		}
		directory = new File("logs");
		if (!directory.exists()) {
			directory.mkdir();

		}
		directory = new File("config");
		if (!directory.exists()) {
			directory.mkdir();
			BufferedWriter buffWriter;
			try {
				buffWriter = new BufferedWriter(new FileWriter("config/config.properties"));
				buffWriter.write("database=jdbc:sqlite:tienda.db");
				buffWriter.newLine();
				buffWriter.write("milisegundos=8000");
				buffWriter.newLine();
				buffWriter.write("columnas=4");
				buffWriter.newLine();
				buffWriter.write("imagenes=/");
				buffWriter.newLine();
				buffWriter.write("pedidos=pedidos/pedido");
				buffWriter.close();

				buffWriter = new BufferedWriter(new FileWriter("config/logger.properties"));
				buffWriter.write("handlers = java.util.logging.FileHandler, java.util.logging.ConsoleHandler");
				buffWriter.newLine();
				buffWriter.write(".level = ALL");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.ConsoleHandler.level = WARNING");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.FileHandler.pattern = logs/logger.log");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.FileHandler.level = INFO");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.FileHandler.append = true");
				buffWriter.close();

			} catch (IOException e) {
			}

		}

		try (FileInputStream fis = new FileInputStream("config/logger.properties")) {
			LogManager.getLogManager().readConfiguration(fis);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error al leer el fichero de configuración del logger.");
		}

		try (FileReader reader = new FileReader("config/config.properties")) {
			Properties properties = new Properties();
			properties.load(reader);

			database = properties.getProperty("database");
			milisegundos = properties.getProperty("milisegundos");
			columnas = properties.getProperty("columnas");
			imagenes = properties.getProperty("imagenes");
			pedidos = properties.getProperty("pedidos");

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error al leer el fichero de configuración.");
		}

		JDBC.importar();
		JDBC.conectarBD();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				logger.info("Programa iniciado");
				ventana = new VentanaPrincipal();
			}

		});

	}

}
