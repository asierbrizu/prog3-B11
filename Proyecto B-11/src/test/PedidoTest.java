package test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import clases.CamisetaYPantalon;
import clases.Pedido;
import clases.Producto;
import clases.Talla;
import clases.Zapato;

public class PedidoTest {

	Pedido pedido1;
	Pedido pedido2;
	Pedido pedido3;
	Producto producto1;
	Producto producto2;
	Producto producto3;

	@Before
	public void setUp() {
		pedido1 = new Pedido(1, new HashMap<Producto, Integer>(), 12.0, "correo1", "");
		pedido2 = new Pedido(2, new HashMap<Producto, Integer>(), 20, "correo2", "");
		pedido3 = new Pedido(3, new HashMap<Producto, Integer>(), 8, "correo3", "");

		producto1 = new CamisetaYPantalon(1, "Camiseta Adidas", 25.0, 1.0, "Negro", "img/camiAdidas", false, Talla.L,
				"Poliester", true);
		producto2 = new Zapato(2, "Sandalias", 7.2, 0.85, "Marron", "img/sandalias", false, 38, "Blanda", true);
		producto3 = new Producto(3, "Gorro de Santa Claus", 7.1, 0.5, "Rojo", "img/santa", false);

		pedido1.getProductos().put(producto1, 3);
		pedido1.getProductos().put(producto2, 1);
		pedido3.getProductos().put(producto3, 10);

	}

	@Test
	public void testId() {
		assertEquals(1, pedido1.getId());
		assertEquals(2, pedido2.getId());
		assertEquals(3, pedido3.getId());
	}

	@Test
	public void testProd() {

		assertFalse(pedido1.getProductos().get(producto2) == 3);
		assertTrue(pedido3.getProductos().containsKey(producto3));
		pedido2.getProductos().put(producto1, 4);
		pedido1.getProductos().clear();
		assertEquals(4, pedido2.getProductos().get(producto1), 0);
		assertFalse(pedido1.getProductos().containsKey(producto1));
		pedido1.setProductos(new HashMap<Producto, Integer>());

	}

	@Test
	public void testImporte() {
		pedido1.setImporteTotal(123.4);
		pedido2.setImporteTotal(456.7);
		pedido3.setImporteTotal(789.1);
		assertEquals(123.4, pedido1.getImporteTotal(), 0.0);
		assertEquals(456.7, pedido2.getImporteTotal(), 0.0);
		assertEquals(789.1, pedido3.getImporteTotal(), 0.0);
	}

	@Test
	public void testFecha() {
		String t1 = "";
		String t2 = "";
		String t3 = "";
		pedido1.setFecha(t1);
		pedido2.setFecha(t2);
		pedido3.setFecha(t3);
		assertEquals(t1, pedido1.getFecha());
		assertEquals(t2, pedido2.getFecha());
		assertEquals(t3, pedido3.getFecha());
	}

	@Test
	public void testCorreo() {
		pedido1.setCorreo("a");
		pedido2.setCorreo("b");
		pedido3.setCorreo("c");
		assertEquals("a", pedido1.getCorreo());
		assertEquals("b", pedido2.getCorreo());
		assertEquals("c", pedido3.getCorreo());
	}

}
