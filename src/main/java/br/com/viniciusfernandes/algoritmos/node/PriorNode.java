package br.com.viniciusfernandes.algoritmos.node;

public class PriorNode extends Node {
	public final int prior;

	public PriorNode(String id, int value, int prior) {
		super(id, value);
		this.prior = prior;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}