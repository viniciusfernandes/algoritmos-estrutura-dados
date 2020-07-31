package br.com.viniciusfernandes.algoritmos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.viniciusfernandes.algoritmos.lista.List;

public class ListaTest {

	private List<Integer> lista;

	@Before
	public void inital() {
		lista = new List<>(2);
		lista.add(1).add(3).add(7).add(5).add(31);
	}

	@Test
	public void testAdicao() {
		assertEquals(new Object[] {
				1, 3, 7, 5, 31
		}, lista.toArray());

		assertTrue(lista.size() == 5);
	}

	@Test
	public void testAdicaoPosicional() {
		lista.add(4, 2);
		assertTrue(lista.size() == 6);
		assertEquals(new Object[] {
				1, 3, 4, 7, 5, 31
		}, lista.toArray());

		assertTrue(lista.size() == 6);

	}

	@Test
	public void testAdicaoPosicionalNoComeco() {
		lista.add(4, 0);
		assertTrue(lista.size() == 6);
		assertEquals(new Object[] {
				4, 1, 3, 7, 5, 31
		}, lista.toArray());

		assertTrue(lista.size() == 6);
	}

	@Test
	public void testAdicaoPosicionalNoFim() {
		lista.add(4);
		assertTrue(lista.size() == 6);
		assertEquals(new Object[] {
				1, 3, 7, 5, 31, 4
		}, lista.toArray());

		assertTrue(lista.size() == 6);
	}

	@Test
	public void testLimpeza() {
		lista.clear();
		assertTrue(lista.size() == 0);
		lista.add(1);
		assertTrue(lista.size() == 1);
	}

	@Test
	public void testRemocao() {
		lista.remove(4);
		assertEquals(new Object[] {
				1, 3, 7, 5
		}, lista.toArray());

		assertTrue(lista.size() == 4);

		lista.remove(3);
		assertEquals(new Object[] {
				1, 3, 7
		}, lista.toArray());
		assertTrue(lista.size() == 3);

		lista.remove(2);
		assertEquals(new Object[] {
				1, 3
		}, lista.toArray());
		assertTrue(lista.size() == 2);

		lista.remove(1);
		assertEquals(new Object[] {
				1
		}, lista.toArray());

		lista.remove(0);
		assertEquals(new Object[0], lista.toArray());
		assertTrue(lista.size() == 0);

	}

}
