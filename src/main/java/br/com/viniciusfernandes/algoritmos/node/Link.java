package br.com.viniciusfernandes.algoritmos.node;

public class Link<T> {
	final Integer cost;
	final Node<T> nodeA;
	final Node<T> nodeB;

	public Link(Node<T> nodeA, Node<T> nodeB) {
		this(nodeA, nodeB, null);
	}

	public Link(Node<T> nodeA, Node<T> nodeB, Integer cost) {
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.cost = cost;
	}

}
