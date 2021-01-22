package br.com.viniciusfernandes.algoritmos.matriz;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	private final int[][] matrix;
	private final int[][] adjoint;
	private final int rows;
	private final int cols;

	public Matrix(int rows, int cols) {
		if (rows == 0 || cols == 0) {
			matrix = new int[0][];
			adjoint = new int[0][];
			this.rows = 0;
			this.cols = 0;
		}
		else {
			this.rows = rows;
			this.cols = cols;
			matrix = new int[rows][cols];
			adjoint = new int[cols][rows];
		}
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
		if (rows == 0 || cols == 0) {
			return "EMPTY";
		}
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
		final List<RemovedElements> removedRows = removeElements(rows);
		final List<RemovedElements> removedCols = removeElements(cols);

		for (final RemovedElements removedRow : removedRows) {
			for (final RemovedElements removedCol : removedCols) {
				submatrixes.add(remove(removedRow, removedCol));
			}
		}
		return submatrixes;
	}

	private Matrix remove(RemovedElements removedRows, RemovedElements removedCols) {
		final Matrix m = new Matrix(rows - removedRows.totalRemoved, cols - removedCols.totalRemoved);

		int mRow = 0;
		int mCol = 0;
		for (int row = 0; row < rows; row++) {
			if (removedRows.removed[row]) {
				continue;
			}

			mCol = 0;
			for (int col = 0; col < cols; col++) {
				if (removedCols.removed[col]) {
					continue;
				}
				m.set(mRow, mCol, matrix[row][col]);
				mCol++;
			}
			mRow++;
		}
		return m;
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

	private List<RemovedElements> removeElements(int totalElements) {
		final boolean[] removedElements = new boolean[totalElements];
		final List<RemovedElements> removedElementsBag = new ArrayList<>(50);

		int numToRemove = 0;
		int current = 0;
		final int MAX_TO_REMOVE = removedElements.length;
		while (numToRemove <= MAX_TO_REMOVE) {
			if (numToRemove == MAX_TO_REMOVE) {
				addRemovedElements(removedElements, removedElementsBag);
				return removedElementsBag;
			}
			if (numToRemove == 0) {
				addRemovedElements(removedElements, removedElementsBag);
			}

			else {
				current = numToRemove - 1;
				removeForward(removedElements, removedElementsBag, 0, current, numToRemove);
			}

			numToRemove++;
			reset(removedElements, numToRemove);
		}
		return removedElementsBag;
	}

	public void removeForward(boolean[] removedCols, List<RemovedElements> removedElementsBag, int first, int current,
			int numToRemove) {
		if (numToRemove == 1) {
			deafultRemove(removedCols, removedElementsBag);
			return;
		}

		if (first >= removedCols.length - numToRemove) {
			return;
		}

		final int last = removedCols.length - 1;
		int previous = current - 1;
		int next = previous - 1;
		while (true) {
			addRemovedElements(removedCols, removedElementsBag);
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

		removeForward(removedCols, removedElementsBag, next, current, numToRemove);
	}

	private void deafultRemove(boolean[] removedCols, List<RemovedElements> removedElementsBag) {
		for (int i = 0; i < removedCols.length; i++) {
			removedCols[i] = true;
			if (i > 0) {
				removedCols[i - 1] = false;
			}
			addRemovedElements(removedCols, removedElementsBag);
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

	private void addRemovedElements(boolean[] elements, List<RemovedElements> removedElementsBag) {
		final RemovedElements removedElements = new RemovedElements();
		final boolean[] copy = new boolean[elements.length];
		for (int i = 0; i < elements.length; i++) {
			if (elements[i]) {
				removedElements.totalRemoved++;
			}
			copy[i] = elements[i];
		}

		if (removedElements.totalRemoved >= elements.length) {
			return;
		}
		removedElements.removed = copy;
		removedElementsBag.add(removedElements);
	}

	public static void main(String[] args) {

		final Matrix m = new Matrix(2, 3);
		m.set(0, 0, 1);
		m.set(0, 1, 2);
		m.set(0, 2, 3);
		m.set(1, 0, 4);
		m.set(1, 1, 5);
		m.set(1, 2, 6);

		final List<Matrix> submatrixes = m.generateSubmatrixes();
		for (final Matrix submatrix : submatrixes) {
			System.out.println(submatrix.toString() + "\n");
		}
	}

	private class RemovedElements {
		int totalRemoved;
		boolean[] removed;
	}

}
