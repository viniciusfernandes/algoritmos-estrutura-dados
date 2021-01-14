package br.com.viniciusfernandes.algoritmos.node;

public class Node<T> {
	public final String id;
	public T value;

	public Node(int id) {
		this(String.valueOf(id), null);
	}

	public Node(String id) {
		this(id, null);
	}

	public Node(String id, T value) {
		this.id = id;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		return id != null && obj instanceof Node && id.equals(((Node) obj).id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		final StringBuilder s = new StringBuilder();
		s.append("{id: ").append(id).append(", value: ").append(value).append("}");
		return s.toString();
	}
}