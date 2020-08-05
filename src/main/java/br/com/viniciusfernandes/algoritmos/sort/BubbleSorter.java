package br.com.viniciusfernandes.algoritmos.sort;

import br.com.viniciusfernandes.algoritmos.lista.List;

public class BubbleSorter<T extends Comparable<T>> {
	public void sort(List<T> list) {
		T a = null;
		T b = null;
		int max = list.size() - 1;
		while (max > 0) {
			for (int j = 0; j < max; j++) {
				a = list.get(j);
				b = list.get(j + 1);
				if (a.compareTo(b) > 0) {
					list.replace(a, j + 1);
					list.replace(b, j);
				}
			}
			max--;
		}
	}
}
