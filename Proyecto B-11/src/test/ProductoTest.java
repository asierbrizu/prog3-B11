package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.CamisetaYPantalon;
import clases.Producto;
import clases.Talla;
import clases.Zapato;

public class ProductoTest {
	Producto producto1;
	Producto producto2;
	Producto producto3;
	Producto producto4;

	@Before
	public void setUp() {
		producto1 = new CamisetaYPantalon(1, "Camiseta Adidas", 25.0, 1.0, "Negro", "img/camiAdidas", false, Talla.L,
				"Poliester", true);
		producto2 = new Zapato(2, "Sandalias", 7.2, 0.85, "Marron", "img/sandalias", false, 38, "Blanda", true);
		producto3 = new Producto(3, "Gorro de Santa Claus", 7.1, 0.5, "Rojo", "img/santa", false);
		producto4 = new Producto();
	}

	@Test
	public void testId() {
		assertEquals(1, producto1.getId());
		assertEquals(2, producto2.getId());
		assertEquals(3, producto3.getId());
	}

	@Test
	public void testImagen() {
		producto1.setImagen("aa");
		producto2.setImagen("bb");
		producto3.setImagen("cc");
		assertEquals("aa", producto1.getImagen());
		assertEquals("bb", producto2.getImagen());
		assertEquals("cc", producto3.getImagen());
	}

	@Test
	public void testNombre() {
		producto1.setNombre("aa");
		producto2.setNombre("bb");
		producto3.setNombre("cc");
		assertEquals("aa", producto1.getNombre());
		assertEquals("bb", producto2.getNombre());
		assertEquals("cc", producto3.getNombre());
	}

	@Test
	public void testPrecioFinal() {
		producto1.setPrecio(12);
		producto2.setPrecio(23);
		producto3.setPrecio(34);
		producto1.setDescuento(0.25);
		producto2.setDescuento(1);
		producto3.setDescuento(0.5);
		assertEquals(3.0, producto1.getPrecio() * producto1.getDescuento(), 0.0);
		assertEquals(23.0, producto2.getPrecio() * producto2.getDescuento(), 0.0);
		assertEquals(17.0, producto3.getPrecio() * producto3.getDescuento(), 0.0);
	}

	@Test
	public void testColor() {
		producto1.setColor("aa");
		producto2.setColor("bb");
		producto3.setColor("cc");
		assertEquals("aa", producto1.getColor());
		assertEquals("bb", producto2.getColor());
		assertEquals("cc", producto3.getColor());
	}

	@Test
	public void testDescatalogar() {
		producto1.setDescatalogado(true);
		producto3.setDescatalogado(true);
		assertTrue(producto1.isDescatalogado());
		assertFalse(producto2.isDescatalogado());
		assertTrue(producto3.isDescatalogado());
	}

	@Test
	public void testTalla() {
		((CamisetaYPantalon) producto1).setTalla(Talla.L);
		((CamisetaYPantalon) producto1).setMaterial("Algodon");
		((CamisetaYPantalon) producto1).setEsCamiseta(false);
		assertEquals("L", ((CamisetaYPantalon) producto1).getTalla().letra());
		assertEquals(52, ((CamisetaYPantalon) producto1).getTalla().numeroEU());
		assertEquals(8, ((CamisetaYPantalon) producto1).getTalla().numeroUSA());
		assertEquals("Algodon", ((CamisetaYPantalon) producto1).getMaterial());
		assertEquals(false, ((CamisetaYPantalon) producto1).isEsCamiseta());
	}

	@Test
	public void testNumero() {

		((Zapato) producto2).setNumero(2);
		assertEquals(2, ((Zapato) producto2).getNumero());

	}

	@Test
	public void testSuela() {
		((Zapato) producto2).setTipoSuela("aa");
		assertEquals("aa", ((Zapato) producto2).getTipoSuela());
	}

	@Test
	public void testVelcro() {
		((Zapato) producto2).setTieneVelcro(true);
		assertTrue(((Zapato) producto2).isTieneVelcro());
	}
}
