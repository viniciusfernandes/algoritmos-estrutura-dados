package br.com.viniciusfernandes.algoritmos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.viniciusfernandes.algoritmos.grafo.GrafoListaAdjacencia;
import br.com.viniciusfernandes.algoritmos.lista.List;
import br.com.viniciusfernandes.algoritmos.node.Node;

public class GrafoListaAdjacenciaTest {

	private GrafoListaAdjacencia<Integer> grafo;

	@Before
	public void inital() {
		grafo = new GrafoListaAdjacencia<Integer>();
		grafo.link(new Node<Integer>("0"), new Node<Integer>("1"), 1);
		grafo.link(new Node<Integer>("0"), new Node<Integer>("3"), 3);
		grafo.link(new Node<Integer>("0"), new Node<Integer>("4"), 10);

		grafo.link(new Node<Integer>("1"), new Node<Integer>("2"), 5);

		grafo.link(new Node<Integer>("2"), new Node<Integer>("4"), 1);

		grafo.link(new Node<Integer>("3"), new Node<Integer>("4"), 6);
		grafo.link(new Node<Integer>("3"), new Node<Integer>("2"), 2);
	}

	@Test
	public void testClear() {
		grafo.clear();
		assertTrue(grafo.size() == 0);
	}

	@Test
	public void testGrafoTrivial() {
		grafo.clear();
		grafo.link(new Node<Integer>("0"), new Node<Integer>("0"), 0);

		assertTrue(grafo.size() == 1);
	}

	@Test
	public void testLinkBidirecional() {

		grafo.clear();
		grafo.link(new Node<Integer>("0"), new Node<Integer>("1"), 1);
		grafo.link(new Node<Integer>("1"), new Node<Integer>("0"), 3);

		assertTrue(grafo.size() == 2);
	}

	@Test
	public void testLowCost() {
		assertEquals((Integer) 1, (Integer) grafo.lowCost("0", "1"));
		assertEquals((Integer) 3, (Integer) grafo.lowCost("0", "3"));
		assertEquals((Integer) 6, (Integer) grafo.lowCost("0", "4"));
		assertEquals((Integer) 5, (Integer) grafo.lowCost("0", "2"));
		assertEquals((Integer) 0, (Integer) grafo.lowCost("0", "0"));
	}

	@Test
	public void testNodeLinks() {
		final List<Node<Integer>> nodes = grafo.linkedNodesOf("0");
		assertTrue(nodes.size() == 3);
		int count = 0;
		String idNode = null;
		for (int i = 0; i < nodes.size(); i++) {
			idNode = nodes.get(i).id;
			if (idNode.equals("1") || idNode.equals("3") || idNode.equals("4")) {
				count++;
			}
		}
		assertTrue(count == 3);
	}

	@Test
	public void testSize() {
		assertTrue(grafo.size() == 5);
	}
}
