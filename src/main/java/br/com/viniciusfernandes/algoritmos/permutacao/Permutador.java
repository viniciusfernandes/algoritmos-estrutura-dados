package br.com.viniciusfernandes.algoritmos.permutacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutador {

	private List<int[]> permutacoes;

	public List<int[]> permutar(int[] arr) {
		permutacoes = new ArrayList<>();
		gerarArrayPermutado(arr, 0, arr.length - 1);
		return permutacoes;
	}

	private void gerarArrayPermutado(int[] arr, int inf, int sup) {
		if (inf == sup) {
			permutacoes.add(Arrays.copyOf(arr, arr.length));
		}
		else {
			for (int i = inf; i <= sup; i++) {
				permutar(arr, inf, i);
				gerarArrayPermutado(arr, inf + 1, sup);
				permutar(arr, inf, i);
			}
		}
	}

	private void permutar(int[] arr, int i, int j) {
		final int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}

	public static void main(String[] args) {
		final int[] arr = {
				1, 2, 3
		};
		final List<int[]> permutacoes = new Permutador().permutar(arr);
		String out = "";
		for (final int[] permt : permutacoes) {
			for (int i = 0; i < permt.length; i++) {
				out += permt[i] + " ";
			}

			// System.out.println(out);
			out = "";
		}
	}
}
