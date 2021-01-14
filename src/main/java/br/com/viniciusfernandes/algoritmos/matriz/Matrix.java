package br.com.viniciusfernandes.algoritmos.matriz;

import java.util.List;

public class Matrix {
	private final int[][] matrix;
	private final int[][] adjoint;
	private final int rows;
	private final int cols;

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		matrix = new int[rows][cols];
		adjoint = new int[cols][rows];
	}

	public void set(int row, int col, int value) {
		matrix[row][col] = value;
		adjoint[col][row] = value;
	}

	public int get(int row, int col) {
		return matrix[row][col];
	}

	public Matrix sum(Matrix other) {
		if (other.rows != rows && other.cols != cols) {
			throw new IllegalArgumentException("The matrix to sum is not compatible");
		}
		final Matrix m = new Matrix(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				m.set(i, j, matrix[i][j] + other.matrix[i][j]);
			}
		}
		return m;
	}

	@Override
	public String toString() {
		final StringBuilder s = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				s.append(matrix[i][j]);
				if (j <= cols - 2) {
					s.append(" ");
				}
			}
			if (i <= rows - 2) {
				s.append("\n");
			}
		}

		return s.toString();
	}

	public Matrix remove(int removedRow, int removedCol) {
		final int newRows = removedRow < 0 ? rows : rows - 1;
		final int newCols = removedCol < 0 ? cols : cols - 1;
		final Matrix m = new Matrix(newRows, newCols);

		for (int countRow = 0, r = 0; countRow < rows; countRow++) {
			if (countRow == removedRow) {
				continue;
			}

			for (int countCol = 0, c = 0; countCol < cols; countCol++) {
				if (countCol == removedCol) {
					continue;
				}
				m.set(r, c, matrix[countRow][countCol]);
				c++;
			}
			r++;
		}
		return m;
	}

	public Matrix removeRow(int removedRow) {
		return remove(removedRow, -1);
	}

	public Matrix removeCol(int removedCol) {
		return remove(-1, removedCol);
	}

	private List<int[]> generateAllPermutations() {
		final int[] rowsIndexes = new int[rows];
		final int[] colsIndexes = new int[cols];

		for (int i = 0; i < rows; i++) {
			rowsIndexes[i] = i;
		}
		for (int i = 0; i < cols; i++) {
			colsIndexes[i] = i;
		}

		return null;
	}

	public void removeElements(boolean[] removedElements) {
		int numToRemove = 0;
		int first = 0;
		int current = 0;
		int next = 0;
		int lastToRemove = 0;
		int stop = 0;
		while (++numToRemove <= removedElements.length) {
			first = 0;
			current = numToRemove - 1;
			next = current == 0 ? 0 : current - 1;
			lastToRemove = removedElements.length - 1;
			stop = removedElements.length - numToRemove;

			reset(removedElements, first, numToRemove);
			print(removedElements);
			while (true) {

				if (first == stop) {
					break;
				}
				if (current >= lastToRemove) {
					if (numToRemove == 1 || next < 0) {
						break;
					}
					if (next == first) {
						first++;
						current = first + numToRemove - 1;
						next = current - 1;
						shift(removedElements, first, numToRemove);
						print(removedElements);
						continue;
					}
					else {
						lastToRemove--;
						current = next;
						next--;
					}
				}
				else {
					// Removing elements
					current++;
					removedElements[current] = true;
					removedElements[current - 1] = false;
					print(removedElements);

				}
			}
		}
	}

	private void reset(boolean[] elements, int first, int numToRemove) {
		final int last = numToRemove - 1;
		for (int i = first; i < elements.length; i++) {
			elements[i] = i <= last;
		}
	}

	private void shift(boolean[] elements, int first, int numToRemove) {
		final int last = first + numToRemove - 1;
		for (int i = 0; i < elements.length; i++) {
			elements[i] = i >= first && i <= last;
		}
	}

	private void print(boolean[] elements) {
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {

		final Matrix m = new Matrix(2, 3);
		m.set(0, 0, 1);
		m.set(0, 1, 2);
		m.set(0, 2, 2);
		m.set(1, 0, 3);
		m.set(1, 1, 4);
		m.set(1, 2, 2);

		final boolean[] b = {
				false, false, false, false
		};
		m.removeElements(b);
	}

}
