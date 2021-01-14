package br.com.viniciusfernandes.algoritmos.hackerhank.euler201;

public class SubsetSum {
	private static int[] numbers;
	private static int sumSubsets;
	private static int last;
	private static int setSize;

	private static void toSum(int[] numbers, int setSize) {
		SubsetSum.numbers = numbers;
		SubsetSum.setSize = setSize;

		if (setSize == 1) {
			for (int i = 0; i < numbers.length; i++) {
				sumSubsets += numbers[i];
			}
		}
		else if (numbers.length == 1) {
			sumSubsets = numbers[0];
		}
		else {
			last = numbers.length - 1;
			toSumSubsets(numbers[0], 1, 0, 0);
		}
	}

	private static void toSumSubsets(int sum, int size, int index, int currt) {
		if (size == setSize - 1) {
			for (int i = index + 1; i < numbers.length; i++) {
				sumSubsets += sum + numbers[i];
			}
			currt++;
			index = currt;
			sum = numbers[currt];
			size = 1;

			if (currt == last) {
				return;
			}
		}

		size++;
		index++;
		sum += numbers[index];
		toSumSubsets(sum, size, index, currt);
	}

	public static void main(String... args) {
		int[] numbers = new int[] {
				1, 3, 6, 8, 10, 11
		};
		int setSize = 3;

		numbers = new int[] {
				1, 2, 3
		};
		setSize = 2;

		toSum(numbers, setSize);
		System.out.println("Sum subsets: " + sumSubsets);
	}
}
