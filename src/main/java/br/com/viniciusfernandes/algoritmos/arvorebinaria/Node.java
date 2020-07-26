package br.com.viniciusfernandes.algoritmos.arvorebinaria;

public class Node {
	public Node left;
	public Node right;
	int value;

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "{value:" + value + ", letft:" + (left != null ? left.value : null) + ", right:" + (right != null ? right.value : null) + "}";
	}
}
