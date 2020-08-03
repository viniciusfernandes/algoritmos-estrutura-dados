package br.com.viniciusfernandes.algoritmos.fila;

public class MinPriorityQueue<T> extends PriorityQueue<T> {

	public MinPriorityQueue() {
		this(10);
	}

	public MinPriorityQueue(int init) {
		super(init);
	}

	@Override
	boolean isPrior(Pair a, Pair b) {
		return a.prior > b.prior;
	}

}
