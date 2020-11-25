package test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import clases.CamisetaYPantalon;
import clases.Pedido;
import clases.Producto;
import clases.Talla;
import clases.Usuario;
import clases.Zapato;


public class UsuarioTest {

	Usuario usuario1;
	Usuario usuario2;
	Usuario usuario3;
	Producto producto1;
	Producto producto2;
	Producto producto3;
	
	@Before
	public void setUp() {
		usuario1 = new Usuario("asierbrizu@opendeusto.es","ContraseñaFalsa1",new HashSet<Pedido>());
		usuario2 = new Usuario("miguelgarcia@outlook.com","EstaTampocoExiste2", new HashSet<Pedido>());
		usuario3 = new Usuario("alvaroalvarez@gmail.com","OroParecePlatanoEs",new HashSet<Pedido>());
		
		producto1 = new CamisetaYPantalon("Camiseta Adidas",25.0,1.0,"Negro","img/camiAdidas",Talla.L,"Poliester");
		producto2 = new Zapato("Sandalias",7.2,0.85,"Marron","img/sandalias",38,"Blanda",true);
		producto3 = new Producto("Gorro de Santa Claus",7.1,0.5,"Rojo","img/santa");
		
		usuario1.getCesta().add(producto1);
		usuario1.getCesta().add(producto3);
	}
	
	@Test
	public void testCorreo() {
		assertEquals("asierbrizu@opendeusto.es", usuario1.getCorreo());
		assertEquals("miguelgarcia@outlook.com", usuario2.getCorreo());
		assertEquals("alvaroalvarez@gmail.com", usuario3.getCorreo());
	}
	
	@Test
	public void testCesta() {
		assertTrue(usuario1.getCesta().contains(producto1));
		assertFalse(usuario1.getCesta().contains(producto2));
		assertTrue(usuario1.getCesta().contains(producto3));
		assertFalse(usuario2.getCesta().contains(producto1));
		usuario3.getCesta().add(producto3);
		usuario1.getCesta().remove(producto1);
		assertFalse(usuario1.getCesta().contains(producto1));
		assertTrue(usuario3.getCesta().contains(producto3));
	}
	
	
}
