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

	public List<T> add(T obj, int index) {
		if (index < 0 || index > current) {
			throw new IllegalArgumentException("O indice deve estar entre 0 e " + current);
		}

		current++;
		if (current >= last) {
			resize();
		}

		final Object[] copy = new Object[elements.length];
		for (int i = 0; i < elements.length; i++) {
			if (i < index) {
				copy[i] = elements[i];
				continue;
			}
			else if (i == index) {
				copy[index] = obj;
				continue;
			}
			copy[i] = elements[i - 1];
		}
		elements = copy;
		return this;
	}

	public void clear() {
		elements = new Object[elements.length];
		current = -1;
	}

	public List<T> copy() {
		final int length = size();
		final List<T> cp = new List<>(length);
		for (int i = 0; i < length; i++) {
			cp.add(get(i));
		}
		return cp;
	}

	public T get(int index) {
		return (T) elements[index];
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public T remove(int index) {
		if (index < 0 || index > current) {
			throw new IllegalArgumentException("O indice " + index + " nao existe e nao pode ser removido da lista");
		}
		final Object removed = elements[index];

		final int length = elements.length - 1;
		for (int i = index; i < length; i++) {
			elements[i] = elements[i + 1];
		}
		elements[elements.length - 1] = null;
		current--;
		return (T) removed;
	}

	public List<T> replace(T obj, int index) {
		if (index < 0 || index > current) {
			throw new IllegalArgumentException("O indice deve estar entre 0 e " + current);
		}
		elements[index] = obj;
		return this;
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
