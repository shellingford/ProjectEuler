package problems.probssub100;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Problem83 {

	private static List<Vertex> nodes;

	/**
	 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by moving left, right,
	 * up, and down, is indicated in bold red and is equal to 2297.
	 *
	 * Find the minimal path sum, in matrix.txt, a 31K text file containing a 80 by 80 matrix, from the top left to the
	 * bottom right by moving left, right, up, and down.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		long start = System.currentTimeMillis();

		processFile("files/p083_matrix.txt");
		dijkstra(nodes.get(0));
		int distance = nodes.get(nodes.size() - 1).getDistance();

		System.out.println("Minimal path sum: " + distance);
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

	private static void dijkstra(Vertex start) {
		start.setDistance(start.getWeight());
		PriorityQueue<Vertex> pq = new PriorityQueue<>(
				(a, b) -> Integer.compare(a.getDistance(), b.getDistance()));
		pq.offer(start);

		while(!pq.isEmpty()) {
			Vertex currentNode = pq.poll();
			currentNode.neighbours.forEach(neighbour -> {
				int newDistance = currentNode.getDistance() + neighbour.getWeight();
				if (newDistance < neighbour.getDistance()) {
					neighbour.setDistance(newDistance);
					pq.add(neighbour);
				}});
		}
	}

	private static void processFile(String fileName) throws FileNotFoundException {
		nodes = new ArrayList<>();
		Map<String, Vertex> nodesMap = new HashMap<>();

		try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
			int rowCounter = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				readLine(line, rowCounter, nodesMap);
				rowCounter++;
			}
		}
	}

	private static void readLine(String line, int rowCounter, Map<String, Vertex> nodesMap) {
		int[] row = Stream.of(line.split(",")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < row.length; i++) {
			Vertex vertex = new Vertex(rowCounter + "," + i, row[i]);
			if (rowCounter > 0) {
				Vertex rowPredecessor = nodesMap.get((rowCounter - 1) + "," + i);
				addNeighbours(vertex, rowPredecessor);
				if (i > 0) {
					Vertex predecessor = nodesMap.get(rowCounter + "," + (i - 1));
					addNeighbours(vertex, predecessor);
				}
			}
			else {
				if (i > 0) {
					Vertex predecessor = nodes.get(i - 1);
					addNeighbours(vertex, predecessor);
				}
			}
			nodesMap.put(rowCounter + "," + i, vertex);
			nodes.add(vertex);
		}
	}

	private static void addNeighbours(Vertex vertex, Vertex vertex2) {
		vertex.getNeighbours().add(vertex2);
		vertex2.getNeighbours().add(vertex);
	}

	public static class Vertex {
		private String name;
		private int weight;
		private int distance = Integer.MAX_VALUE;
		private Set<Vertex> neighbours = new HashSet<>();

		public Vertex(String name, int weight) {
			this.name = name;
			this.weight = weight;
		}

		public Vertex(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public int getWeight() {
			return weight;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}

		public Set<Vertex> getNeighbours() {
			return neighbours;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return name + ":" + weight;
		}
	}

}
