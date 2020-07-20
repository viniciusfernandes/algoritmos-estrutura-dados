package br.com.viniciusfernandes.algoritmos;

public class Tree {
	public static void main(String... c) {
		final Tree t = new Tree();
		t.add(8).add(3).add(1).add(6).add(4).add(7).add(10).add(14).add(13);

		t.remove(6);
		assert !"8 3 1 7 4 10 14 13".equals(t.toString());
	}

	private int length = 0;
	private Node root;

	public Tree add(int value) {
		final Node node = new Node(value);
		length++;
		if (root == null) {
			root = node;
			return this;
		}

		Node parent = null;
		Node current = root;
		while (true) {
			parent = current;
			if (current.value > node.value) {
				current = current.left;
			}
			else {
				current = current.right;
			}
			if (current == null) {
				break;
			}
		}
		if (parent.value == node.value) {
			return this;
		}
		else if (parent.value > node.value) {
			parent.left = node;
		}
		else {
			parent.right = node;
		}
		return this;
	}

	public void clear() {
		root = null;
		length = 0;
	}

	private void generateString(Node node, StringBuilder string) {
		if (node == null) {
			return;
		}
		string.append(node.value).append(" ");
		generateString(node.left, string);
		generateString(node.right, string);

	}

	public Tree remove(int value) {
		if (root == null) {
			return this;
		}
		Node parent = null;
		Node current = root;
		while (current != null) {
			if (current.value == value) {
				// Primeiro caso
				if (current.left == null && current.right == null) {
					if (parent == null) {
						root = null;
					}
					else if (parent.left == current) {
						parent.left = null;
					}
					else {
						parent.right = null;
					}

				}
				else if (current.left != null && current.right == null || current.left == null && current.right != null) {
					// verificando a remocao do root
					if (parent == null) {
						root.left = null;
						root.right = null;
						if (current.left != null) {
							root = current.left;
						}
						else {
							root = current.right;
						}
					}
					else if (parent.left == current) {
						if (current.left != null) {
							parent.left = current.left;
							current.left = null;
						}
						else {
							parent.left = current.right;
							current.right = null;
						}
					}
					else {
						if (current.left != null) {
							parent.right = current.left;
							current.left = null;
						}
						else {
							parent.right = current.right;
							current.right = null;
						}
					}
				}
				else if (current.left != null && current.right != null) {

					Node parentsmaller = current;
					Node smaller = current.right;
					Node next = current.right.left;

					// Recuperando o node com o menor valor, que eh o ultimo node a esquerda.
					while (next != null) {
						parentsmaller = smaller;
						smaller = next;
						next = next.left;
					}

					// Tratando o caso em que é a remocao do root e o filho a direita nao tem filho a esquerdo, logo
					// esse filho a direita sera o menor valor
					if (parent == null) {
						if (root.right == smaller) {
							smaller.left = root.left;
						}
						else {
							smaller.left = root.left;
							smaller.right = root.right;
						}
						root.left = null;
						root.right = null;
						root = smaller;
						parentsmaller.left = null;
					}
					else {
						// Adicionando o small node na arvore
						if (parent.left == current) {
							parent.left = smaller;
						}
						else {
							parent.right = smaller;
						}

						// Removendo a antiga posicao do smaller node na arvore.
						if (parentsmaller.left == smaller) {
							parentsmaller.left = null;
						}
						else {
							parentsmaller.right = null;
						}
						smaller.left = current.left;
						smaller.right = current.right;
						current.left = null;
						current.right = null;
					}
				}
				length--;
				break;
			}
			parent = current;
			current = current.value > value ? current.left : current.right;
		}

		return this;
	}

	public int size() {
		return length;
	}

	@Override
	public String toString() {
		final StringBuilder string = new StringBuilder();
		generateString(root, string);
		return string.substring(0, string.length() - 1);
	}
}
