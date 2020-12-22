package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;

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
		pedido1 = new Pedido(new HashMap<Producto, Integer>(), 12, 10);
		pedido2 = new Pedido(new HashMap<Producto, Integer>(), 20, 14);
		pedido3 = new Pedido(new HashMap<Producto, Integer>(), 8, 6);

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
		assertEquals(4, pedido1.getId());
		assertEquals(5, pedido2.getId());
		assertEquals(6, pedido3.getId());
	}

	@Test
	public void testProd() {
		assertTrue(pedido1.getProductos().get(producto1) == 3);
		assertFalse(pedido1.getProductos().get(producto2) == 3);
		assertTrue(pedido3.getProductos().containsKey(producto3));
		pedido2.getProductos().put(producto1, 4);
		pedido1.getProductos().clear();
		assertEquals(4, pedido2.getProductos().get(producto1), 0);
		assertFalse(pedido1.getProductos().containsKey(producto1));
	}

}
