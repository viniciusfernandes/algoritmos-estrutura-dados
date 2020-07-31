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

		@Override
		public String toString() {
			final StringBuilder s = new StringBuilder();
			s.append("{label: ").append(label).append(", prior: ").append(prior).append("}");
			return s.toString();
		}
	}

	private final List<Node> queue;

	public PriorityQueue() {
		this(10);
	}

	public PriorityQueue(int init) {
		queue = new List<>(init);
	}

	public PriorityQueue clear() {
		queue.clear();
		return this;
	}

	public String pop() {
		return queue.remove(0).label;
	}

	public PriorityQueue push(String label, int prior) {
		final Node node = new Node(label, prior);
		final int size = queue.size();
		if (size == 0) {
			queue.add(node);
		}
		else {
			boolean ok = false;
			for (int i = 0; i < size; i++) {
				if (ok = queue.get(i).prior < prior) {
					queue.add(node, i);
					break;
				}
			}
			if (!ok) {
				queue.add(node);
			}
		}
		return this;
	}

	public int size() {
		return queue.size();
	}

	@Override
	public String toString() {
		final StringBuilder s = new StringBuilder();
		final int length = queue.size();
		final int last = length - 1;
		s.append("[");
		for (int i = 0; i < length; i++) {
			s.append(queue.get(i).toString());
			if (i < last) {
				s.append(", ");
			}
		}
		s.append("]");
		return s.toString();
	}
}
