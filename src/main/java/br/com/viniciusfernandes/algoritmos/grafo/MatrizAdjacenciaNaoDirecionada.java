package br.com.viniciusfernandes.algoritmos.grafo;

import java.util.ArrayList;
import java.util.List;

public class MatrizAdjacenciaNaoDirecionada {
	private final int[][] nodes;

	public MatrizAdjacenciaNaoDirecionada(int size) {
		nodes = new int[size][size];
	}

	public MatrizAdjacenciaNaoDirecionada add(int a, int b) {
		if (a > 5 || b > 5) {
			throw new IllegalArgumentException("Os parametros a, b devem ser menores do que 6");
		}
		nodes[a - 1][b - 1] = nodes[b - 1][a - 1] = 1;
		return this;
	}

	public Integer[] getAdjacentes(int i) {
		if (i <= 0 || i > 5) {
			throw new IllegalArgumentException("O parametro i deve ser estar entre 1 e 5");
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
		if (i > 5) {
			throw new IllegalArgumentException("Os parametros i devem ser menor do que 6");
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
}
