package br.com.viniciusfernandes.algoritmos.node;

import br.com.viniciusfernandes.algoritmos.lista.List;

public class LinkedNodeOLd<T> extends Node<T> {
	private final List<LinkedNodeOLd<T>> linked;

	public LinkedNodeOLd(String idNode) {
		super(idNode, null);
		linked = new List<>();
	}

	public LinkedNodeOLd(String idNode, T value) {
		super(idNode, value);
		linked = new List<>();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public LinkedNodeOLd<T> getLinked(int index) {
		return linked.get(index);
	}

	public List<LinkedNodeOLd<T>> getLinks() {
		return linked;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public LinkedNodeOLd<T> link(LinkedNodeOLd<T> node) {
		linked.add(node);
		return this;
	}

	public int size() {
		return linked.size();
	}

}