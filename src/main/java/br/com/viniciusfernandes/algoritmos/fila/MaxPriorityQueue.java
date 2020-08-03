package br.com.viniciusfernandes.algoritmos.fila;

public class MaxPriorityQueue extends PriorityQueue {

	public MaxPriorityQueue() {
		this(10);
	}

	public MaxPriorityQueue(int init) {
		super(init);
	}

	@Override
	public boolean isPrior(Pair a, Pair b) {
		return a.prior < b.prior;
	}

}
