package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.CamisetaYPantalon;
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
		usuario1 = new Usuario("asierbrizu@opendeusto.es", "ContraseñaFalsa1");
		usuario2 = new Usuario("miguelgarcia@outlook.com", "EstaTampocoExiste2");
		usuario3 = new Usuario("alvaroalvarez@gmail.com", "OroParecePlatanoEs");

		producto1 = new CamisetaYPantalon(1, "Camiseta Adidas", 25.0, 1.0, "Negro", "img/camiAdidas", false, Talla.L,
				"Poliester", true);
		producto2 = new Zapato(2, "Sandalias", 7.2, 0.85, "Marron", "img/sandalias", false, 38, "Blanda", true);
		producto3 = new Producto(3, "Gorro de Santa Claus", 7.1, 0.5, "Rojo", "img/santa", false);

	}

	@Test
	public void testCorreo() {
		usuario1.setCorreo("aa");
		usuario2.setCorreo("bb");
		usuario3.setCorreo("cc");
		assertEquals("aa", usuario1.getCorreo());
		assertEquals("bb", usuario2.getCorreo());
		assertEquals("cc", usuario3.getCorreo());
	}

}
