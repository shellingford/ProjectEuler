package problems.probssub100;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Problem79 {

  private static Map<Integer, PassNum> passNums = new HashMap<>();

  /**
   * A common security method used for online banking is to ask the user for three random characters from a passcode.
   * For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply
   * would be: 317.
   *
   * The text file, keylog.txt, contains fifty successful login attempts.
   *
   * Given that the three characters are always asked for in order, analyse the file so as to determine the shortest
   * possible secret passcode of unknown length.
   */
  public static void main(String[] args) throws FileNotFoundException {
    long start = System.currentTimeMillis();
    processFile("files/p079_keylog.txt");

    System.out.println("Shortest possible secret pass:");
    for (Map.Entry<Integer, PassNum> entry : passNums.entrySet()) {
      if (entry.getValue().isFirstNum()) {
        entry.getValue().print("");
      }
    }
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static void processFile(String fileName) throws FileNotFoundException {
    Set<String> lines = new HashSet<>(); //used to ignore duplicates
    try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!lines.contains(line)) {
          lines.add(line);
          processLine(line);
        }
      }
    }
  }

  private static void processLine(String line) {
    int[] numbers = line.chars().map(x -> x - 48).toArray();
    PassNum first = getPassNum(numbers[0]);
    PassNum second = getPassNum(numbers[1]);
    PassNum third = getPassNum(numbers[2]);

    first.addAfter(second);
    second.addBefore(first);
    second.addAfter(third);
    third.addBefore(second);

    removeDuplicateConnections();
  }

  private static void removeDuplicateConnections() {
    for (PassNum passNum : passNums.values()) {
      passNum.removeDuplicateConnections();
    }
  }

  private static PassNum getPassNum(int value) {
    if (passNums.containsKey(value)) {
      return passNums.get(value);
    }
    else {
      PassNum passNum = new PassNum(value);
      passNums.put(value, passNum);
      return passNum;
    }
  }

  public static class PassNum {
    private Set<PassNum> somewhereBefore = new HashSet<>();
    private Set<PassNum> somewhereAfter = new HashSet<>();
    private int value;

    public PassNum(int value) {
      this.value = value;
    }

    public Set<PassNum> getSomewhereBefore() {
      return somewhereBefore;
    }

    public void addBefore(PassNum passNumBefore) {
      if (!isBefore(passNumBefore)) {
        somewhereBefore.add(passNumBefore);
      }
    }

    public boolean isBefore(PassNum passNum) {
      if (somewhereBefore.contains(passNum)) {
        return true;
      }
      else {
        for (PassNum afterNum : somewhereBefore) {
          if (afterNum.isBefore(passNum)) {
            return true;
          }
        }
        return false;
      }
    }

    public Set<PassNum> getSomewhereAfter() {
      return somewhereAfter;
    }

    public void addAfter(PassNum passNumAfter) {
      if (!isAfter(passNumAfter)) {
        somewhereAfter.add(passNumAfter);
      }
    }

    public boolean isAfter(PassNum passNum) {
      if (somewhereAfter.contains(passNum)) {
        return true;
      }
      else {
        for (PassNum afterNum : somewhereAfter) {
          if (afterNum.isAfter(passNum)) {
            return true;
          }
        }
        return false;
      }
    }

    public int getValue() {
      return value;
    }

    public void removeDuplicateConnections() {
      if (!somewhereAfter.isEmpty()) {
        Set<PassNum> passNumsAfterAfters = new HashSet<>();
        for (PassNum passNum : somewhereAfter) {
          passNumsAfterAfters.addAll(passNum.getSomewhereAfter());
        }
        for (PassNum passNum : passNumsAfterAfters) {
          if (somewhereAfter.contains(passNum)) {
            passNum.removeBefore(this);
            somewhereAfter.remove(passNum);
          }
        }
      }
    }

    private void removeBefore(PassNum passNum) {
      somewhereBefore.remove(passNum);
    }

    public boolean isFirstNum() {
      return somewhereBefore.isEmpty();
    }

    public boolean isLastNum() {
      return somewhereAfter.isEmpty();
    }

    public void print(String p) {
      if (isLastNum()) {
        System.out.println(p + value);
      }
      else {
        for (PassNum num : somewhereAfter) {
          num.print(p + value);
        }
      }
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + value;
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
      PassNum other = (PassNum) obj;
      if (value != other.value)
        return false;
      return true;
    }
  }
}
