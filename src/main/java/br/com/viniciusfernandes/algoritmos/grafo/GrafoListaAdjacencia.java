package br.com.viniciusfernandes.algoritmos.grafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.com.viniciusfernandes.algoritmos.fila.MinPriorityQueue;
import br.com.viniciusfernandes.algoritmos.fila.PriorityQueue;
import br.com.viniciusfernandes.algoritmos.lista.List;
import br.com.viniciusfernandes.algoritmos.node.LinkedNode;
import br.com.viniciusfernandes.algoritmos.node.Node;

public class GrafoListaAdjacencia<T> {

	final Map<String, Integer> costMap = new HashMap<>();

	private boolean foundCycle;

	private final Map<String, LinkedNode<T>> table = new HashMap<>();

	public GrafoListaAdjacencia<T> clear() {
		table.clear();
		return this;
	}

	public LinkedNode<T> get(String id) {
		return table.get(id);
	}

	public boolean hasCycle() {
		final Set<String> visited = new HashSet<>();
		foundCycle = false;
		for (final Entry<String, LinkedNode<T>> e : table.entrySet()) {
			hasCycle(e.getValue(), visited);
			if (foundCycle) {
				return true;
			}
			visited.clear();
		}

		return false;
	}

	private void hasCycle(LinkedNode<T> node, Set<String> visited) {
		if (foundCycle) {
			return;
		}

		if (!visited.contains(node.id)) {
			visited.add(node.id);
			for (int i = 0; i < node.size(); i++) {
				hasCycle(get(node.getIdLinkedNode(i)), visited);
			}
		}
		else {
			foundCycle = true;
		}
	}

	public GrafoListaAdjacencia<T> link(Node<T> from, Node<T> to, Integer cost) {
		LinkedNode<T> nodeFrom = table.get(from.id);
		LinkedNode<T> nodeTo = table.get(to.id);
		if (nodeFrom == null) {
			nodeFrom = new LinkedNode<>(from.id, from.value);
			table.put(from.id, nodeFrom);
		}

		if (nodeTo == null) {
			nodeTo = new LinkedNode<>(to.id, to.value);
			table.put(nodeTo.id, nodeTo);
		}

		nodeFrom.link(nodeTo, cost);
		return this;
	}

	public List<Node<T>> linkedNodesOf(String idNode) {
		final LinkedNode<T> n = table.get(idNode);
		if (n == null) {
			return new List<>(0);
		}
		return n.linkedNodes();
	}

	public int lowCost(String from, String to) {
		lowCostPath(from, to);

		return costMap.get(to);
	}

	public List<Node<T>> lowCostPath(String from, String to) {
		final List<Node<T>> path = new List<>();

		LinkedNode<T> fromNode = table.get(from);

		final PriorityQueue<Integer> priorQueue = new MinPriorityQueue<>();
		priorQueue.push(from, 0);
		costMap.put(from, 0);

		Integer fromCost = 0;
		Integer toCost = 0;
		Integer linkCost = 0;
		Integer cost = 0;
		String idNodeFrom = null;
		String idNodeTo = null;
		while (priorQueue.size() > 0) {
			idNodeFrom = priorQueue.pop().id;
			fromNode = table.get(idNodeFrom);
			path.add(fromNode);

			for (int i = 0; i < fromNode.size(); i++) {
				idNodeTo = fromNode.getLinkedNode(i).id;
				linkCost = fromNode.costLink(idNodeTo);

				fromCost = costMap.get(idNodeFrom);
				toCost = costMap.get(idNodeTo);
				cost = fromCost + linkCost;
				if (toCost == null || toCost > cost) {
					toCost = cost;
					costMap.put(idNodeTo, toCost);
					priorQueue.push(idNodeTo, linkCost);
				}
			}
		}
		return path;
	}

	public int size() {
		return table.size();
	}

}
