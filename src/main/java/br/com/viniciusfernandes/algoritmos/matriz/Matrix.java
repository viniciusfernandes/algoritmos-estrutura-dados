package br.com.viniciusfernandes.algoritmos.matriz;

import java.util.ArrayList;
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

	public List<Matrix> generateSubmatrixes() {
		final List<Matrix> submatrixes = new ArrayList<>(50);
		return null;
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

	public void removeElements(boolean[] removedCols) {
		int numToRemove = 0;
		int current = 0;
		final int MAX_TO_REMOVE = removedCols.length;
		while (numToRemove <= MAX_TO_REMOVE) {
			if (numToRemove == MAX_TO_REMOVE) {
				print(removedCols);
				return;
			}
			if (numToRemove == 0) {
				print(removedCols);
			}

			else {
				current = numToRemove - 1;
				removeForward(removedCols, 0, current, numToRemove);
			}

			numToRemove++;
			reset(removedCols, numToRemove);
		}
	}

	public void removeForward(boolean[] removedCols, int first, int current, int numToRemove) {
		if (numToRemove == 1) {
			deafultRemove(removedCols);
			return;
		}

		if (first >= removedCols.length - numToRemove) {
			return;
		}

		final int last = removedCols.length - 1;
		int previous = current - 1;
		int next = previous - 1;
		while (true) {
			print(removedCols);
			if (current >= last && previous >= current - 1) {
				break;
			}
			if (current < last) {
				removedCols[current] = false;
				removedCols[++current] = true;
			}
			else {
				previous++;
				current = previous + 1;
				shift(removedCols, current, numToRemove);
			}
		}
		if (next < 0) {
			return;
		}
		removedCols[next] = false;
		next++;
		previous = next + 1;
		current = previous + 1;
		removedCols[next] = removedCols[previous] = removedCols[current] = true;
		for (int i = current; i <= last; i++) {
			removedCols[i] = false;
		}

		removeForward(removedCols, next, current, numToRemove);
	}

	private void deafultRemove(boolean[] removedCols) {
		for (int i = 0; i < removedCols.length; i++) {
			removedCols[i] = true;
			if (i > 0) {
				removedCols[i - 1] = false;
			}
			print(removedCols);
		}
	}

	private void reset(boolean[] removedCols, int numToRemove) {
		final int last = numToRemove - 1;
		for (int i = 0; i < removedCols.length; i++) {
			removedCols[i] = i <= last;
		}
	}

	private void shift(boolean[] removedCols, int current, int numToRemove) {
		final int last = current;
		final int init = current - numToRemove + 1;
		for (int i = 0; i < removedCols.length; i++) {
			removedCols[i] = i >= init && i <= last;

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
