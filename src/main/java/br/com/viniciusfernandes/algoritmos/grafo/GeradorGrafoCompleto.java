package br.com.viniciusfernandes.algoritmos.grafo;

public class GeradorGrafoCompleto {
	public static <T> GrafoListaAdjacencia<T> gerar(int size) {
		final GrafoListaAdjacencia<T> grafo = new GrafoListaAdjacencia<>();
		String id1 = null;
		String id2 = null;
		final int max = size * 10;
		for (int j = 0; j < size; j++) {
			id1 = String.valueOf(j);
			for (int i = 0; i < size; i++) {
				id2 = String.valueOf(i);
				if (i == j || grafo.isLinked(id1, id2)) {
					continue;
				}
				grafo.symLink(id1, id2, (int) (max * Math.random()));
			}
		}
		return grafo;
	}

	public static void main(String[] args) {
		final GrafoListaAdjacencia<Void> grafo = GeradorGrafoCompleto.gerar(4);
		System.out.println(grafo.toString());

		System.out.println("Custo: " + grafo.cost("0", "3"));
	}
}
