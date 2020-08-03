package br.com.viniciusfernandes.algoritmos;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.viniciusfernandes.algoritmos.fila.MaxPriorityQueue;

public class PriorityQueueTest {

	private MaxPriorityQueue queue;

	@Before
	public void inital() {
		queue = new MaxPriorityQueue(5);
		queue.push("B", 2).push("A", 1).push("Z", 26).push("H", 8);
	}

	@Test
	public void testAdicao() {
		assertTrue(queue.size() == 4);

		queue.push("WW", 1000);
		assertTrue(queue.size() == 5);
	}

	@Test
	public void testAdicaoDoVazio() {
		queue.clear();
		queue.push("K", 12);
		assertTrue(queue.size() == 1);
	}

	@Test
	public void testLimpeza() {
		queue.clear();
		assertTrue(queue.size() == 0);
	}

	@Test
	public void testOrnecacaoERemocao() {
		assertTrue(queue.pop().id.equals("Z"));
		assertTrue(queue.pop().id.equals("H"));
		assertTrue(queue.pop().id.equals("B"));
		assertTrue(queue.pop().id.equals("A"));

		assertTrue(queue.size() == 0);
	}

}
