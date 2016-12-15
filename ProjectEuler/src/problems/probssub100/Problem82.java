package problems.probssub100;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import problems.common.SearchUtil;
import problems.common.Vertex;

public class Problem82 {

  /**
   * The minimal path sum in the 5 by 5 matrix below, by starting in any cell in the left column and finishing in any
   * cell in the right column, and only moving up, down, and right, is indicated in red and bold; the sum is equal to
   * 994.
   *
   * Find the minimal path sum, in matrix.txt, a 31K text file containing a 80 by 80 matrix, from the left column to
   * the right column.
   */
  public static void main(String[] args) throws FileNotFoundException {
    long start = System.currentTimeMillis();

    int dimension = 80;
    Map<String, Vertex> nodesMap = processFile("files/p082_matrix.txt");

    int minDistance = Integer.MAX_VALUE;
    for (int i = 0; i < dimension; i++) {
      Vertex v = nodesMap.get(i + ",0");
      SearchUtil.dijkstra(v);
      for (int j = 0; j < dimension; j++) {
        minDistance = Math.min(minDistance, nodesMap.get(j + "," + (dimension - 1)).getDistance());
      }
    }

    System.out.println("Minimal path sum: " + minDistance);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static Map<String, Vertex> processFile(String fileName) throws FileNotFoundException {
    Map<String, Vertex> nodesMap = new HashMap<>();
    try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
      int rowCounter = 0;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        readLine(line, rowCounter, nodesMap);
        rowCounter++;
      }
    }
    return nodesMap;
  }

  private static void readLine(String line, int rowCounter, Map<String, Vertex> nodesMap) {
    int[] row = Stream.of(line.split(",")).mapToInt(Integer::parseInt).toArray();
    for(int i = 0; i < row.length; i++) {
      Vertex vertex = new Vertex(rowCounter + "," + i, row[i], Integer.MAX_VALUE);
      if (rowCounter > 0) {
        Vertex rowPredecessor = nodesMap.get((rowCounter - 1) + "," + i);
        addNeighboursForBoth(vertex, rowPredecessor);
        if (i > 0) {
          Vertex predecessor = nodesMap.get(rowCounter + "," + (i - 1));
          addNeighboursForBoth(vertex, predecessor);
        }
      }
      else {
        if (i > 0) {
          Vertex predecessor = nodesMap.get(rowCounter + "," + (i - 1));
          addNeighbours(vertex, predecessor);
        }
      }
      nodesMap.put(rowCounter + "," + i, vertex);
    }
  }

  private static void addNeighbours(Vertex vertex, Vertex vertex2) {
    vertex2.getNeighbours().add(vertex);
  }

  private static void addNeighboursForBoth(Vertex vertex, Vertex vertex2) {
    vertex.getNeighbours().add(vertex2);
    vertex2.getNeighbours().add(vertex);
  }

}
