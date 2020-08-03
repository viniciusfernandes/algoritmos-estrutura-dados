package br.com.viniciusfernandes.algoritmos.fila;

import br.com.viniciusfernandes.algoritmos.lista.List;
import br.com.viniciusfernandes.algoritmos.node.Node;

public abstract class PriorityQueue<T> {

	class Pair {
		final Node<T> node;
		final int prior;

		public Pair(Node<T> node, int prior) {
			this.node = node;
			this.prior = prior;
		}

	}

	private final List<Pair> queue;

	public PriorityQueue() {
		this(10);
	}

	public PriorityQueue(int init) {
		queue = new List<>(init);
	}

	public PriorityQueue<T> clear() {
		queue.clear();
		return this;
	}

	abstract boolean isPrior(Pair a, Pair b);

	public Node<T> pop() {
		return queue.remove(0).node;
	}

	public PriorityQueue<T> push(Node<T> node, int prior) {
		final Pair pNode = new Pair(node, prior);
		return push(pNode);
	}

	private PriorityQueue<T> push(Pair pair) {

		final int size = queue.size();
		if (size == 0) {
			queue.add(pair);
		}
		else {
			boolean ok = false;
			for (int i = 0; i < size; i++) {
				if (ok = isPrior(queue.get(i), pair)) {
					queue.add(pair, i);
					break;
				}
			}
			if (!ok) {
				queue.add(pair);
			}
		}
		return this;
	}

	public PriorityQueue<T> push(String nodeId, int prior) {
		final Pair pair = new Pair(new Node(nodeId), prior);
		return push(pair);
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
