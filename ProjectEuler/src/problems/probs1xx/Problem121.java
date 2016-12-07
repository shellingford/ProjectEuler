package problems.probs1xx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class Problem121 {

  private static final int noDiscs = 15;
  private static int noSelected = 8;

  /**
   * A bag contains one red disc and one blue disc. In a game of chance a player takes a disc at random and its
   * colour is noted. After each turn the disc is returned to the bag, an extra red disc is added, and another
   * disc is taken at random.
   *
   * The player pays £1 to play and wins if they have taken more blue discs than red discs at the end of the game.
   *
   * If the game is played for four turns, the probability of a player winning is exactly 11/120, and so the maximum
   * prize fund the banker should allocate for winning in this game would be £10 before they would expect to incur a
   * loss. Note that any payout will be a whole number of pounds and also includes the original £1 paid to play the
   * game, so in the example given the player actually wins £9.
   *
   * Find the maximum prize fund that should be allocated to a single game in which fifteen turns are played.
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    int maxFund = findMaxFund();

    System.out.println("Max prize fund: " + maxFund);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * First we need to calculate probability of selecting more blue discs than reds, n/d, and then max
   * prize fund is calculated as Math.floor(d/n).
   *
   * @return max prize fund
   */
  private static int findMaxFund() {
    List<Integer> availableNumbers = IntStream.range(1, noDiscs + 1).boxed().collect(Collectors.toList());

    //sum of all probabilities when there are more blue discs, so if we have 15 turns, we need to sum probabilities
    //for when we select from 8 to 15 blue ones
    long sum = 0;
    for (int k = noSelected; k <= noDiscs; k++) {
      List<List<Integer>> permutations = new ArrayList<>();
      permutations(availableNumbers, new ArrayList<>(), permutations, k);

      for (List<Integer> permutation : permutations) {
        List<Integer> remainingNumbers = new ArrayList<>(availableNumbers);
        remainingNumbers.removeAll(permutation);
        sum += remainingNumbers.stream().reduce(1, (s, i) -> i * s);
      }
    }

    long d = LongStream.range(2, noDiscs + 2).reduce(1L, (p, i) -> i * p);
    int maxFund = (int)Math.floor(d / sum);
    return maxFund;
  }

  /**
   * Creates all possible permutations of the size permutationSize out of all numbers within
   * the list of all available numbers. Order within a permutation is not important and duplicate
   * permutations are ignored.
   *
   * @param availableNumbers all available numbers for current recursion
   * @param selectedNumbers all selected numbers so far for a current permutation
   * @param permutations list of all permutations
   * @param permutationSize max permutation size
   */
  private static void permutations(List<Integer> availableNumbers, List<Integer> selectedNumbers,
      List<List<Integer>> permutations, int permutationSize) {
    for (Integer number : availableNumbers) {
      List<Integer> list = availableNumbers.stream().filter(i -> i > number).collect(Collectors.toList());
      selectedNumbers.add(number);

      if (selectedNumbers.size() == permutationSize) {
        permutations.add(new ArrayList<>(selectedNumbers));
      }
      else {
        permutations(list, new ArrayList<>(selectedNumbers), permutations, permutationSize);
      }

      selectedNumbers.remove(number);
    }
  }

}
