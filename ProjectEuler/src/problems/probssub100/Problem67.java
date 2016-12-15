package problems.probssub100;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import problems.common.SearchUtil;
import problems.common.Vertex;

public class Problem67 {

  /**
   * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum
   * total from top to bottom is 23.
   *        3
   *       7 4
   *      2 4 6
   *     8 5 9 3
   *
   * That is, 3 + 7 + 4 + 9 = 23.
   *
   * Find the maximum total from top to bottom in triangle.txt, a 15K text file containing a triangle with
   * one-hundred rows.
   */
  public static void main(String[] args) throws FileNotFoundException {
    long start = System.currentTimeMillis();

    Map<String, Vertex> nodesMap = processFile("files/p067_triangle.txt");
    SearchUtil.reverseDijkstra(nodesMap.get("0,0"));

    int dimension = 100;
    int maxDistance = 0;
    for (int j = 0; j < dimension; j++) {
      maxDistance = Math.max(maxDistance, nodesMap.get((dimension - 1) + "," + j).getDistance());
    }

    System.out.println("Maximal path sum: " + maxDistance);
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
    int[] row = Stream.of(line.split(" ")).mapToInt(Integer::parseInt).toArray();
    for(int i = 0; i < row.length; i++) {
      Vertex vertex = new Vertex(rowCounter + "," + i, row[i], 0);
      if (rowCounter > 0) {
        if (i == 0) {
          Vertex rowPredecessor = nodesMap.get((rowCounter - 1) + "," + i);
          rowPredecessor.getNeighbours().add(vertex);
        }
        else if (i == row.length - 1) {
          Vertex rowPredecessor = nodesMap.get((rowCounter - 1) + "," + (i - 1));
          rowPredecessor.getNeighbours().add(vertex);
        }
        else {
          Vertex rowPredecessor1 = nodesMap.get((rowCounter - 1) + "," + (i - 1));
          rowPredecessor1.getNeighbours().add(vertex);
          Vertex rowPredecessor2 = nodesMap.get((rowCounter - 1) + "," + i);
          rowPredecessor2.getNeighbours().add(vertex);
        }
      }
      nodesMap.put(rowCounter + "," + i, vertex);
    }
  }

}
