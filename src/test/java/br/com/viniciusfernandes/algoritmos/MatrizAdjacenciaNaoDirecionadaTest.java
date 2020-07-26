package br.com.viniciusfernandes.algoritmos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.viniciusfernandes.algoritmos.grafo.MatrizAdjacenciaNaoDirecionada;

public class MatrizAdjacenciaNaoDirecionadaTest {

	private MatrizAdjacenciaNaoDirecionada matriz;

	@Before
	public void inital() {
		matriz = new MatrizAdjacenciaNaoDirecionada(5);
		matriz.add(1, 3).add(3, 5).add(2, 3).add(3, 4).add(4, 5);
	}

	@Test
	public void testAdicao() {
		String string = null;
		string = "0 0 1 0 0\n";
		string += "0 0 1 0 0\n";
		string += "1 1 0 1 1\n";
		string += "0 0 1 0 1\n";
		string += "0 0 1 1 0";
		assertEquals(string, matriz.toString());
	}

	@Test
	public void testAdjacetes() {
		assertEquals(new Integer[] {
				3
		}, matriz.getAdjacentes(1));
		assertEquals(new Integer[] {
				3
		}, matriz.getAdjacentes(2));
		assertEquals(new Integer[] {
				1, 2, 4, 5
		}, matriz.getAdjacentes(3));
		assertEquals(new Integer[] {
				3, 5
		}, matriz.getAdjacentes(4));
		assertEquals(new Integer[] {
				3, 4
		}, matriz.getAdjacentes(5));
	}

	@Test
	public void testVisiting() {
		matriz.visit();
	}

}
