package problems.probssub100;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Problem11 {

  /**
   * What is the greatest product of four adjacent numbers in the same direction (up, down, left, right,
   * or diagonally) in the 20×20 grid?
   *
   * @throws FileNotFoundException
   */
  public static void main(String[] args) throws FileNotFoundException {
    long start = System.currentTimeMillis();

    int[][] matrix = readmatrix("files/p011_matrix.txt");
    int max = Math.max(Math.max(maxHorizontal(matrix), maxVertical(matrix)),
        Math.max(maxDiagonalRight(matrix), maxDiagonalLeft(matrix)));

    System.out.println("Greatest product of adjacent numbers in the matrix: " + max);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static int[][] readmatrix(String fileName) throws FileNotFoundException {
    int[][] matrix = new int[20][20];
    try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
      int rowCounter = 0;
      while (scanner.hasNextLine()) {
        String[] lineNumbers = scanner.nextLine().split(" ");
        final int row = rowCounter;
        IntStream.range(0, 20).forEach(i -> matrix[row][i] = Integer.parseInt(lineNumbers[i]));
        rowCounter++;
      }
    }
    return matrix;
  }

  private static int maxHorizontal(int[][] matrix) {
    return IntStream.range(0, 16).map(r ->
            IntStream.range(0, 16).map(i ->
              IntStream.range(0, 3).reduce(1, (v, k) -> v * matrix[r][i + k])).
            max().getAsInt()).
          max().getAsInt();
  }

  private static int maxVertical(int[][] matrix) {
    return IntStream.range(0, 16).map(r ->
             IntStream.range(0, 16).map(i ->
               IntStream.range(0, 3).reduce(1, (v, k) -> v * matrix[i + k][r])).
             max().getAsInt()).
           max().getAsInt();
  }

  private static int maxDiagonalRight(int[][] matrix) {
    int max = 0;
    for (int i = 0; i <= 16; i++) {
      for (int j = 0; j < 16 - i; j++) {
        int product = 1;
        for (int k = 0; k < 4; k++) {
          product *= matrix[i + k][j + k];
        }
        max = Math.max(max, product);
      }
    }
    for (int j = 1; j <= 16; j++) {
      for (int i = 1; i < 16 - j; i++) {
        int product = 1;
        for (int k = 0; k < 4; k++) {
          product *= matrix[i + k][j + k];
        }
        max = Math.max(max, product);
      }
    }
    return max;
  }

  private static int maxDiagonalLeft(int[][] matrix) {
    int max = 0;
    for (int i = 0; i <= 16; i++) {
      for (int j = 19; j >= i + 3; j--) {
        int product = 1;
        for (int k = 0; k < 4; k++) {
          product *= matrix[i + k + (19 - j)][j - k];
        }
        max = Math.max(max, product);
      }
    }
    for (int j = 18; j >= 3; j--) {
      for (int i = 0; i <= j - 3; i++) {
        int product = 1;
        for (int k = 0; k < 4; k++) {
          product *= matrix[i + k][j - i - k];
        }
        max = Math.max(max, product);
      }
    }
    return max;
  }

}
