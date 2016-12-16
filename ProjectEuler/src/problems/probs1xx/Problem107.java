package problems.probs1xx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import problems.common.Vertex;

public class Problem107 {

  private static final int dimension = 40;

  /**
   * Using network.txt, a 6K text file containing a network with forty vertices, and given in matrix form,
   * find the maximum saving which can be achieved by removing redundant edges whilst ensuring that the
   * network remains connected.
   */
  public static void main(String[] args) throws FileNotFoundException {
    long start = System.currentTimeMillis();

    List<Edge> edges = processFile("files/p107_network.txt");
    int totalWeight = edges.stream().map(e -> e.getWeight()).reduce(0, (sum, w) -> sum + w);

    Set<Edge> minSpanningTree = kruskal(edges);
    int minWeight = minSpanningTree.stream().map(e -> e.getWeight()).reduce(0, (sum, w) -> sum + w);

    int maxSaving = totalWeight - minWeight;

    System.out.println("Maximum saving: " + maxSaving);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static List<Edge> processFile(String fileName) throws FileNotFoundException {
    Map<String, Vertex> nodesMap = new HashMap<>();
    IntStream.range(0, dimension).forEach(i -> nodesMap.put(i + "", new Vertex(i + "", -1, -1)));
    List<Edge> edges = new ArrayList<Edge>();
    try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
      int rowCounter = 0;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (rowCounter > 0) {
          readLine(line, rowCounter, nodesMap, edges);
        }
        rowCounter++;
      }
    }
    return edges;
  }

  private static void readLine(String line, int rowCounter, Map<String, Vertex> nodesMap, List<Edge> edges) {
    List<Integer> weights = Stream.of(line.split(",")).map(s -> mapStrToInt(s)).collect(Collectors.toList());
    Vertex current = nodesMap.get(rowCounter + "");
    //we don't need to go through the whole matrix but just one half
    for (int i = 0; i < rowCounter; i++) {
      if (weights.get(i) > 0) {
        Vertex to = nodesMap.get(i + "");
        Edge edge = new Edge(current, to, weights.get(i));
        edges.add(edge);
      }
    }
  }

  private static int mapStrToInt(String str) {
    if ("-".equals(str)) {
      return -1;
    }
    else {
      return Integer.parseInt(str);
    }
  }

  /**
   * We are using Kruskal's algorithm to generate minimum-spanning tree.
   *
   * @param edges list of all edges
   * @return minimum-spanning tree edges
   */
  private static Set<Edge> kruskal(List<Edge> edges) {
    DisjointSet disjointSet = new DisjointSet();
    Collections.sort(edges);

    for (Edge edge : edges) {
      disjointSet.add(edge);
    }

    return disjointSet.getEdges();
  }

  private static class Edge implements Comparable<Edge> {
    private Vertex from;
    private Vertex to;
    private int weight;

    public Edge(Vertex from, Vertex to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    public Vertex getFrom() {
      return from;
    }

    public Vertex getTo() {
      return to;
    }

    public int getWeight() {
      return weight;
    }

    @Override
    public int compareTo(Edge other) {
      return Integer.compare(getWeight(), other.getWeight());
    }
  }

  private static class DisjointSet {
    private Map<Vertex, Set<Vertex>> data = new HashMap<>();
    private Set<Edge> edges = new HashSet<>();

    /**
     * Add new edge if it doesn't create a cycle.
     *
     * @param edge edge that might be added to the disjoint set
     */
    public void add(Edge edge) {
      if (!hasCycle(edge)) {
        edges.add(edge);
        addVerticesToMap(edge);
      }
    }

    /**
     * Add edge to the disjoint set and update map of vertexes with their neighbours
     * from the disjoint set.
     *
     * @param edge edge that will be added
     */
    private void addVerticesToMap(Edge edge) {
      Vertex from = edge.getFrom();
      Vertex to = edge.getTo();
      addToMap(from, to);
      addToMap(to, from);
    }

    private void addToMap(Vertex key, Vertex neighbour) {
      if (data.containsKey(key)) {
        data.get(key).add(neighbour);
      }
      else {
        Set<Vertex> set = new HashSet<>();
        set.add(neighbour);
        data.put(key, set);
      }
    }

    /**
     * Essentially uses bfs to find if there exists a cycle between two vertices of
     * the new edge.
     *
     * @param edge edge for which we are looking a cycle
     * @return true if there exists a cycle. false otherwise
     */
    private boolean hasCycle(Edge edge) {
      //if any of two vertices of the edge is not already in the disjoint set then there can't be a cycle either
      if (!data.containsKey(edge.getFrom()) || !data.containsKey(edge.getTo())) {
        return false;
      }

      Set<Vertex> fromNeighbours = data.get(edge.getFrom());
      for (Vertex neighbour : fromNeighbours) {
        boolean foundCycle = false;
        if (!neighbour.equals(edge.getTo())) {
          Set<Vertex> set = new HashSet<>();
          set.add(neighbour);
          foundCycle = findCycleWithBfs(neighbour, edge.getTo(), set);
        }
        if (foundCycle) {
          return true;
        }
      }

      return false;
    }

    private boolean findCycleWithBfs(Vertex current, Vertex to, Set<Vertex> ignoreSet) {
      Set<Vertex> neighbours = data.get(current);
      for (Vertex neighbour : neighbours) {
        if (ignoreSet.contains(neighbour)) {
          continue;
        }
        if (neighbour.equals(to)) {
          return true;
        }
        Set<Vertex> newIgnoreSet = new HashSet<>(ignoreSet);
        newIgnoreSet.add(neighbour);
        boolean foundCycle = findCycleWithBfs(neighbour, to, newIgnoreSet);
        if (foundCycle) {
          return true;
        }
      }
      return false;
    }

    public Set<Edge> getEdges() {
      return edges;
    }

  }
}
