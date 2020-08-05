package br.com.viniciusfernandes.algoritmos.sort;

import br.com.viniciusfernandes.algoritmos.lista.List;

public class SelectionSorter<T extends Comparable<T>> {
	public void sort(List<T> list) {
		T a = null;
		T b = null;
		T current = null;
		final int length = list.size();
		int currIndex = 0;
		int minIndex = -1;
		while (currIndex < length) {
			a = list.get(currIndex);

			for (int j = currIndex; j < length; j++) {
				b = list.get(j);
				if (a.compareTo(b) > 0) {
					minIndex = j;
					a = list.get(j);
				}
			}
			if (minIndex > 0) {
				current = list.get(currIndex);
				list.replace(a, currIndex);
				list.replace(current, minIndex);
			}
			minIndex = -1;
			currIndex++;
		}
	}
}
