package problems.probssub100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import problems.common.PrimeGenerator;

public class Problem49 {

  /**
   * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual
   * in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are
   * permutations of one another.
   *
   * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property,
   * but there is one other 4-digit increasing sequence.
   *
   * What 12-digit number do you form by concatenating the three terms in this sequence?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    //only 4-digit primes
    List<Integer> primes = PrimeGenerator.computePrimes(10000).stream().filter(prime -> prime > 1000)
        .boxed().collect(Collectors.toList());

    String number = find12DigitNumber(primes);

    System.out.println("12-digit number: " + number);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static String find12DigitNumber(List<Integer> primes) {
    Set<Integer> primesSet = new HashSet<>(primes);
    Set<Integer> discardedPrimes = new HashSet<>();

    //remove first solution, as we are looking for another one
    List<Integer> primePermutations = generatePrimePermutations(1487, primesSet);
    discardedPrimes.addAll(primePermutations);

    for (Integer prime : primes) {
      if (discardedPrimes.contains(prime)) {
        continue;
      }
      primePermutations = generatePrimePermutations(prime, primesSet);
      //if there even aren't at least 3 prime 4-digit permutations then we can discard that prime and permutations
      if (primePermutations.size() < 3) {
        discardedPrimes.addAll(primePermutations);
      }
      else  {
        List<Integer> primesSeq = findPrimesSeq(primePermutations);

        //return first found solution (p1, p2, p3) as 12-digit string p1p2p3
        if (primesSeq.size() == 3) {
          StringBuilder b = new StringBuilder();
          primesSeq.stream().forEach(b::append);
          return b.toString();
        }
      }
    }

    return "";
  }

  private static List<Integer> generatePrimePermutations(int number, Set<Integer> primesSet) {
    return permutations(Integer.toString(number).chars().mapToObj(n -> n - 48).map(n -> n + "")
        .collect(Collectors.toList()), "").stream().mapToInt(s -> Integer.parseInt(s))
        .filter(i -> primesSet.contains(i)).boxed().sorted()
        .collect(Collectors.toList());
  }

  private static Set<String> permutations(List<String> chars, String digits) {
    if (chars.size() == 1) {
      return new HashSet<>(Arrays.asList(digits + chars.get(0)));
    }
    else {
      Set<String> numbers = new HashSet<>();
      for (String c : chars) {
        List<String> list = new ArrayList<>(chars);
        list.remove(c);
        numbers.addAll(permutations(list, digits + c));
      }
      return numbers;
    }
  }

  /**
   * Tries to find 3 primes from the list of prime permutations, p1, p2, p3, where difference in value
   * between p1 and p2 is the same as between p2 and p3.
   *
   * @param primePermutations list of prime permutations that are also primes
   * @return list of 3 primes if we can find them, otherwise empty list
   */
  private static List<Integer> findPrimesSeq(List<Integer> primePermutations) {
    for (int i = 0; i < primePermutations.size() - 2; i++) {
      for (int j = i + 1; j < primePermutations.size() - 1; j++) {
        int dist = primePermutations.get(j) - primePermutations.get(i);
        for (int k = j + 1; k < primePermutations.size(); k++) {
          if (dist == (primePermutations.get(k) - primePermutations.get(j))) {
            return Arrays.asList(primePermutations.get(i), primePermutations.get(j), primePermutations.get(k));
          }
        }
      }
    }
    return new ArrayList<>();
  }
}
