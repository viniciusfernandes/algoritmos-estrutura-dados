package br.com.viniciusfernandes.algoritmos.grafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MatrizAdjacenciaNaoDirecionada {
	private final int[][] nodes;
	private Queue<Integer> queue = null;

	private final int size;

	private boolean visitados[] = null;

	public MatrizAdjacenciaNaoDirecionada(int size) {
		nodes = new int[size][size];
		this.size = size;
	}

	public MatrizAdjacenciaNaoDirecionada add(int a, int b) {
		if (a < 0 || a > size || b < 0 || b > size) {
			throw new IllegalArgumentException("Os parametros a, b devem estar entre 1 e " + size);
		}
		nodes[a - 1][b - 1] = nodes[b - 1][a - 1] = 1;
		return this;
	}

	public Integer[] getAdjacentes(int i) {
		if (i <= 0 || i > size) {
			throw new IllegalArgumentException("O parametro i deve ser estar entre 1 e " + size);
		}
		i--;
		final List<Integer> adj = new ArrayList<>(nodes[i].length);
		for (int j = 0; j < nodes[i].length; j++) {
			if (nodes[i][j] == 1) {
				adj.add(j + 1);
			}
		}
		return adj.toArray(new Integer[] {});
	}

	public boolean isAdjecente(int a, int b) {
		return nodes[a - 1][b - 1] == 1;
	}

	@Override
	public String toString() {
		final StringBuilder s = new StringBuilder();
		final int max = nodes.length;
		final int last = nodes.length - 1;
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				s.append(nodes[i][j]);
				if (j < last) {
					s.append(" ");
				}
			}
			if (i < last) {
				s.append("\n");
			}
		}
		return s.toString();
	}

	public String toString(int i) {
		if (i <= 0 || i > size) {
			throw new IllegalArgumentException("O parametro i deve ser estar entre 1 e " + size);
		}
		final StringBuilder s = new StringBuilder();
		final int last = nodes[i].length - 1;
		for (int j = 0; j < nodes[i].length; j++) {
			s.append(nodes[i][j]);
			if (j < last) {
				s.append(" ");
			}
		}
		return s.toString();
	}

	public void visitInDepth() {
		visitInDepth(3);
	}

	private void visitInDepth(int i) {
		if (visitados == null) {
			if (i <= 0 || i > size) {
				throw new IllegalArgumentException("O parametro i deve ser estar entre 1 e " + size);
			}
			visitados = new boolean[nodes.length];
			i--;
		}
		visitados[i] = true;
		System.out.println("Visitado: " + (i + 1));
		for (int j = 0; j < size; j++) {
			if (nodes[i][j] == 1 && !visitados[j]) {
				visitInDepth(j);
			}
		}
	}

	public void visitInLarge() {
		visitInLarge(1);
	}

	private void visitInLarge(int i) {

		if (queue == null) {
			if (i <= 0 || i > size) {
				throw new IllegalArgumentException("O parametro i deve ser estar entre 1 e " + size);
			}
			queue = new LinkedList<>();
			queue.add(--i);

			visitados = new boolean[size];
			visitados[i] = true;
		}

		System.out.println("Visitado: " + (i + 1));
		while (queue.size() > 0) {
			i = queue.peek();
			for (int j = 0; j < nodes[i].length; j++) {
				if (nodes[i][j] == 1 && !visitados[j]) {
					visitados[j] = true;
					queue.add(j);
					System.out.println("Visitado: " + (j + 1));
				}
			}
			queue.poll();
		}
	}
}
