package br.com.viniciusfernandes.algoritmos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.viniciusfernandes.algoritmos.arvore.BinaryTree;

public class ArvoreBinariaTest {

	private BinaryTree tree;

	@Before
	public void inital() {
		tree = new BinaryTree();
		tree.add(8).add(3).add(1).add(6).add(4).add(7).add(10).add(14).add(13);
	}

	@Test
	public void testAdicao() {
		assertEquals("8 3 1 6 4 7 10 14 13", tree.toString());
		assertEquals(Integer.valueOf(9), (Integer) tree.size());
	}

	@Test
	public void testAdicaoERemocaoNodeFilhoDaRaiz() {
		tree.add(9);
		assertEquals("8 3 1 6 4 7 10 9 14 13", tree.toString());
		assertEquals(Integer.valueOf(10), (Integer) tree.size());

		tree.remove(8);
		assertEquals("9 3 1 6 4 7 10 14 13", tree.toString());
		assertEquals(Integer.valueOf(9), (Integer) tree.size());
	}

	@Test
	public void testRemocaoNodeComDoisFilhos() {
		tree.remove(6);
		assertEquals("8 3 1 7 4 10 14 13", tree.toString());
		assertEquals(Integer.valueOf(8), (Integer) tree.size());
	}

	@Test
	public void testRemocaoNodeComUnicoFilho() {
		tree.remove(14);
		assertEquals("8 3 1 6 4 7 10 13", tree.toString());
		assertEquals(Integer.valueOf(8), (Integer) tree.size());
	}

	@Test
	public void testRemocaoNodeFilhoDaRaiz() {
		tree.remove(10);
		assertEquals("8 3 1 6 4 7 14 13", tree.toString());
		assertEquals(Integer.valueOf(8), (Integer) tree.size());
	}

	@Test
	public void testRemocaoNodeFilhoDaRaiz_2() {
		tree.remove(3);
		assertEquals("8 4 1 6 7 10 14 13", tree.toString());
		assertEquals(Integer.valueOf(8), (Integer) tree.size());
	}

	@Test
	public void testRemocaoNodeFolha() {
		tree.remove(1);
		assertEquals("8 3 6 4 7 10 14 13", tree.toString());
		assertEquals(Integer.valueOf(8), (Integer) tree.size());

		tree.remove(13);
		assertEquals("8 3 6 4 7 10 14", tree.toString());
		assertEquals(Integer.valueOf(7), (Integer) tree.size());

		tree.remove(4);
		assertEquals("8 3 6 7 10 14", tree.toString());
		assertEquals(Integer.valueOf(6), (Integer) tree.size());
	}

	@Test
	public void testRemocaoRaiz() {
		tree.remove(8);
		assertEquals("10 3 1 6 4 7 14 13", tree.toString());
		assertEquals(Integer.valueOf(8), (Integer) tree.size());
	}

}
