package br.com.viniciusfernandes.algoritmos.node;

import java.util.HashMap;
import java.util.Map;

import br.com.viniciusfernandes.algoritmos.lista.List;

public class LinkedNode<T> extends Node<T> {
	private final Map<String, Integer> costs;
	private final List<Node<T>> linkedNodes;
	private final List<Link<T>> links;

	public LinkedNode(String id) {
		this(id, null);
	}

	public LinkedNode(String id, T value) {
		super(id, value);
		linkedNodes = new List<>();
		links = new List<>();
		costs = new HashMap<>();
	}

	public Integer costLink(String idNode) {
		return costs.get(idNode);
	}

	public String getIdLinkedNode(int index) {
		return linkedNodes.get(index).id;
	}

	public Node<T> getLinkedNode(int index) {
		return linkedNodes.get(index);
	}

	public List<Link<T>> getLinks() {
		return links;
	}

	public boolean isLinked(String idNode) {
		return costs.containsKey(idNode);
	}

	public LinkedNode<T> link(Node<T> node, Integer cost) {
		linkedNodes.add(node);
		links.add(new Link<>(this, node, cost));
		costs.put(node.id, cost);
		return this;
	}

	public List<Node<T>> linkedNodes() {
		return linkedNodes;
	}

	public int size() {
		return linkedNodes.size();
	}

}