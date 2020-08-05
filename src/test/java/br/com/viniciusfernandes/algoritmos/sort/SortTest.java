package br.com.viniciusfernandes.algoritmos.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.viniciusfernandes.algoritmos.lista.List;

public class SortTest {
	private final BubbleSorter<Integer> bubble = new BubbleSorter<>();
	private List<Integer> lista;

	private final SelectionSorter<Integer> selection = new SelectionSorter<>();
	private final Integer[] sortedArray = new Integer[] {
			1, 2, 3, 5, 7, 31
	};

	@Before
	public void inital() {
		lista = new List<>(2);
		lista.add(2).add(31).add(5).add(7).add(1).add(3);
	}

	@Test
	public void testBubbleSort() {
		bubble.sort(lista);
		assertEquals(sortedArray, lista.toArray());
	}

	@Test
	public void testBubbleSortTrivial() {
		final List<Integer> lista = new List<>();
		lista.add(1).add(2).add(3).add(5).add(7).add(31);

		bubble.sort(lista);
		assertEquals(sortedArray, lista.toArray());
	}

	@Test
	public void testSelectionSort() {
		selection.sort(lista);
		assertEquals(sortedArray, lista.toArray());
	}

	@Test
	public void testSelectionSortTrivial() {
		final List<Integer> lista = new List<>();
		lista.add(1).add(2).add(3).add(5).add(7).add(31);

		selection.sort(lista);
		assertEquals(sortedArray, lista.toArray());
	}

	@Test
	public void testSelectionSortTrivial2() {
		final List<Integer> lista = new List<>();
		lista.add(1).add(2).add(32).add(5).add(7).add(31);
		final Integer[] sortedArray = new Integer[] {
				1, 2, 5, 7, 31, 32
		};
		selection.sort(lista);
		assertEquals(sortedArray, lista.toArray());
	}
}
