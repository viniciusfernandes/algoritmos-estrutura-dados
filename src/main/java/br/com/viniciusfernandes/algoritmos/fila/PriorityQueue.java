package br.com.viniciusfernandes.algoritmos.fila;

import br.com.viniciusfernandes.algoritmos.lista.List;

public class PriorityQueue {
	private class Node {
		String label;
		int prior;

		public Node(String label, int prior) {
			this.label = label;
			this.prior = prior;
		}
	}

	private final List<Node> queue;

	public PriorityQueue() {
		this(10);
	}

	public PriorityQueue(int init) {
		queue = new List<>(init);
	}

}
