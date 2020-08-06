package br.com.viniciusfernandes.algoritmos.sort;

import br.com.viniciusfernandes.algoritmos.lista.List;

public class InsertionSorter<T extends Comparable<T>> {
	public void sort(List<T> list) {
		T a = null;
		T b = null;
		final int max = list.size() - 1;
		for (int i = 0; i < max; i++) {
			a = list.get(i);
			b = list.get(i + 1);
			if (a.compareTo(b) > 0) {
				list.replace(a, i + 1).replace(b, i);
				for (int k = i; k > 0; k--) {
					a = list.get(k - 1);
					b = list.get(k);
					if (a.compareTo(b) > 0) {
						list.replace(a, k).replace(b, k - 1);
						continue;
					}
					break;
				}
			}
		}
	}
}
