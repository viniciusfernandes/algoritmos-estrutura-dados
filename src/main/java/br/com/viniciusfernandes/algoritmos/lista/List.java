package br.com.viniciusfernandes.algoritmos.lista;

public class List<T> {
	private int current;
	private Object[] elements;
	private int last;

	public List() {
		this(10);
	}

	public List(int size) {
		elements = new Object[size];
		current = -1;
		last = size - 1;
	}

	public List<T> add(T obj) {
		current++;
		elements[current] = obj;
		if (current >= last) {
			resize();
		}
		return this;
	}

	public void clear() {
		elements = new Object[elements.length];
		current = -1;
	}

	public Object get(int index) {
		return elements[index];
	}

	public void remove(int index) {
		if (index < 0 || index > current) {
			throw new IllegalArgumentException("O indice " + index + " nao existe e nao pode ser removido da lista");
		}
		final int max = elements.length - 1;
		for (int i = index; i < max; i++) {
			elements[i] = elements[i + 1];
		}
		elements[elements.length - 1] = null;
		current--;
	}

	private void resize() {
		final Object[] copy = new Object[elements.length * 2];
		for (int i = 0; i < elements.length; i++) {
			copy[i] = elements[i];
		}
		elements = copy;
		last = elements.length - 1;
	}

	public int size() {
		return current + 1;
	}

	public Object[] toArray() {
		final Object[] copy = new Object[current + 1];
		for (int i = 0; i <= current; i++) {
			copy[i] = elements[i];
		}
		return copy;
	}
}
