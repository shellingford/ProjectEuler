package problems.probssub100;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import problems.common.SearchUtil;
import problems.common.Vertex;

public class Problem81 {

  /**
   * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by only moving to the
   * right and down, is indicated in bold red and is equal to 2427.
   *
   * Find the minimal path sum, in matrix.txt, a 31K text file containing a 80 by 80 matrix, from the top left to
   * the bottom right by only moving right and down.
   */
  public static void main(String[] args) throws FileNotFoundException {
    long start = System.currentTimeMillis();

    List<Vertex> nodes = processFile("files/p081_matrix.txt");
    SearchUtil.dijkstra(nodes.get(0));
    int distance = nodes.get(nodes.size() - 1).getDistance();

    System.out.println("Minimal path sum: " + distance);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static List<Vertex> processFile(String fileName) throws FileNotFoundException {
    List<Vertex> nodes = new ArrayList<>();
    Map<String, Vertex> nodesMap = new HashMap<>();

    try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
      int rowCounter = 0;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        readLine(line, rowCounter, nodesMap, nodes);
        rowCounter++;
      }
    }
    return nodes;
  }

  private static void readLine(String line, int rowCounter, Map<String, Vertex> nodesMap, List<Vertex> nodes) {
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
    vertex2.getNeighbours().add(vertex);
  }

}
