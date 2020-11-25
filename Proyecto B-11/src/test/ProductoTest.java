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
	
	@Before
	public void setUp() {
		producto1 = new CamisetaYPantalon("Camiseta Adidas",25.0,1.0,"Negro","img/camiAdidas",Talla.L,"Poliester");
		producto2 = new Zapato("Sandalias",7.2,0.85,"Marron","img/sandalias",38,"Blanda",true);
		producto3 = new Producto("Gorro de Santa Claus",7.1,0.5,"Rojo","img/santa");
	}
	
	@Test
	public void testNombre() {
		assertEquals("Camiseta Adidas", producto1.getNombre());
		assertEquals("Sandalias", producto2.getNombre());
		assertEquals("Gorro de Santa Claus", producto3.getNombre());
	}
	@Test
	public void testPrecioFinal() {
		assertEquals(25.0, producto1.getPrecio()*producto1.getDescuento(),0.0);
		assertEquals(6.12, producto2.getPrecio()*producto2.getDescuento(),0.0);
		assertEquals(3.55, producto3.getPrecio()*producto3.getDescuento(),0.0);
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
		assertEquals("L", ((CamisetaYPantalon) producto1).getTalla().letra());
		assertEquals(52, ((CamisetaYPantalon) producto1).getTalla().numeroEU());
		assertEquals(8, ((CamisetaYPantalon) producto1).getTalla().numeroUSA());

	}

}
