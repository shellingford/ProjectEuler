package problems.probs1xx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem103 {

	private Set<SetNumber> setA = new HashSet<>();
	private Set<SetNumber> setB = new HashSet<>();
	private int numElems = 0;
	private int minSum = Integer.MAX_VALUE;

	/**
	 * Let S(A) represent the sum of elements in set A of size n. We shall call it a special sum set if for any two
	 * non-empty disjoint subsets, B and C, the following properties are true:
	 * 	- S(B) ≠ S(C); that is, sums of subsets cannot be equal.
	 * 	- If B contains more elements than C then S(B) > S(C).
	 *
	 * If S(A) is minimised for a given n, we shall call it an optimum special sum set. The first five optimum special sum
	 * sets are given below.
	 * 	n = 1: {1}
	 * 	n = 2: {1, 2}
	 * 	n = 3: {2, 3, 4}
	 * 	n = 4: {3, 5, 6, 7}
	 * 	n = 5: {6, 9, 11, 12, 13}
	 *
	 * It seems that for a given optimum set, A = {a1, a2, ... , an}, the next optimum set is of the form
	 * B = {b, a1+b, a2+b, ... ,an+b}, where b is the "middle" element on the previous row.
	 * By applying this "rule" we would expect the optimum set for n = 6 to be A = {11, 17, 20, 22, 23, 24}, with
	 * S(A) = 117. However, this is not the optimum set, as we have merely applied an algorithm to provide a near optimum
	 * set. The optimum set for n = 6 is A = {11, 18, 19, 20, 22, 25}, with S(A) = 115 and corresponding set
	 * string: 111819202225.
	 *
	 * Given that A is an optimum special sum set for n = 7, find its set string.
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		Problem103 prob = new Problem103();
		prob.run();

		System.out.println("Set string: " + prob.getSetString());
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

	public Problem103() {
		setA.add(new SetNumber(1, 19, 44));
		setA.add(new SetNumber(2, 20, 45));
		setA.add(new SetNumber(3, 21, 46));
		setA.add(new SetNumber(4 ,22, 47));

		setB.add(new SetNumber(5, 23, 48));
		setB.add(new SetNumber(6, 24, 49));
		setB.add(new SetNumber(7, 25, 50));

		numElems = setA.size() + setB.size();
	}

	public void run() {
		Set<SetNumber> copySetA = setA.stream().map( x -> x.copy()).collect(Collectors.toSet());
		Set<SetNumber> copySetB = setB.stream().map( x -> x.copy()).collect(Collectors.toSet());
		if (!checkCondition(copySetA, copySetB)) {
			List<SetNumber> allNums = new ArrayList<>(copySetA);
			allNums.addAll(copySetB);
			allNums.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));
			runCombinations(0, allNums, new HashSet<SetNumber>(), new HashSet<SetNumber>());
		}
	}

	public String getSetString() {
		List<SetNumber> allNums = new ArrayList<>(setA);
		allNums.addAll(setB);
		allNums.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));
		return allNums.stream().map(x -> x.getValue() + "").collect(Collectors.joining(""));
	}

	private boolean checkCondition(Set<SetNumber> setA, Set<SetNumber> setB) {
		if (setA.size() == setB.size()) {
			return subSumsAreOk(setA, setB);
		}
		else {
			int sumA = setA.stream().mapToInt(i -> i.getValue()).sum();
			int sumB = setB.stream().mapToInt(i -> i.getValue()).sum();

			return sumB < sumA && subSumsAreOk(setA, setB);
		}
	}

	private void runCombinations(int depth, List<SetNumber> allNums, Set<SetNumber> setA, Set<SetNumber> setB) {
		if (depth == numElems) {
			int sumA = setA.stream().mapToInt(i -> i.getValue()).sum();
			int sumB = setB.stream().mapToInt(i -> i.getValue()).sum();

			if ((sumA + sumB < minSum) && checkCondition(setA, setB)) {
				this.setA = setA.stream().map( x -> x.copy()).collect(Collectors.toSet());
				this.setB = setB.stream().map( x -> x.copy()).collect(Collectors.toSet());
				minSum = sumA + sumB;
			}
			return;
		}
		SetNumber number = allNums.get(depth);
		if (depth > (Math.ceil(numElems / 2d) - 1)) {
			setB.add(number);
		}
		else {
			setA.add(number);
		}
		runCombinations(depth + 1, allNums, setA, setB);
		while(number.canBeIncreased()) {
			changeMinMax(number, allNums);
			runCombinations(depth + 1, allNums, setA, setB);
		}
		if (depth > 2) {
			setB.remove(number);
		}
		else {
			setA.remove(number);
		}
	}

	private void changeMinMax(SetNumber changedNumber, List<SetNumber> numbers) {
		changedNumber.increaseValue();
		for (int i = changedNumber.getId(); i < numbers.size(); i++) {
			numbers.get(i).setValue(changedNumber.getValue() + (i - changedNumber.getId() + 1));
		}
	}

	private boolean subSumsAreOk(Set<SetNumber> setA, Set<SetNumber> setB) {
		List<SetNumber> setWithAllElems = new ArrayList<>(setA);
		setWithAllElems.addAll(setB);

		List<SetNumber> s = new ArrayList<>(setA);
		s.addAll(setB);

		for (int noElemsToSum = 2; noElemsToSum < numElems; noElemsToSum++) {
			if (!checkAllCombinationsA(setWithAllElems, new HashSet<SetNumber>(), noElemsToSum, 0, noElemsToSum,
					new HashSet<SetNumber>())) {
				return false;
			}
		}
		return true;
	}

	private boolean checkAllCombinationsA(List<SetNumber> allElems, Set<SetNumber> setB, int depth,
			int subSetSum, int maxDepth, Set<SetNumber> subSetA) {
		if (depth == 0) {
			for (int i = 2; i <= (numElems - maxDepth); i++) {
				boolean result = checkAllCombinationsB(subSetSum, subSetA, allElems, i, 0, new HashSet<SetNumber>());
				if (!result) {
					return false;
				}
			}
			return true;
		}
		for (SetNumber number : allElems) {
			if (!subSetA.contains(number)) {
				subSetA.add(number);
				boolean result = checkAllCombinationsA(allElems, setB, depth - 1, subSetSum + number.getValue(), maxDepth, subSetA);
				subSetA.remove(number);
				if (!result) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkAllCombinationsB(int subSetSumA, Set<SetNumber> setA, List<SetNumber> allElems, int depth,
			int subSetSum, Set<SetNumber> subSetB) {
		int maxInd = 0;
		for (SetNumber number : setA) {
			if (number.getValue() > maxInd) {
				maxInd = number.getValue();
			}
		}

		if (depth == 0) {
			if (setA.size() == subSetB.size()) {
				return subSetSumA != subSetSum;
			}
			else {
				if (setA.size() < subSetB.size()) {
					return subSetSumA < subSetSum;
				}
				else {
					return subSetSumA > subSetSum;
				}
			}
		}
		for (SetNumber number : allElems) {
			if (depth == 1) {
			}
			if (!setA.contains(number) && !subSetB.contains(number)) {
				subSetB.add(number);
				boolean result = checkAllCombinationsB(subSetSumA, setA, allElems, depth - 1, subSetSum + number.getValue(),
						subSetB);
				subSetB.remove(number);
				if (!result) {
					return false;
				}
			}
		}
		return subSetSumA != subSetSum;
	}

	public static class SetNumber {
		private int id;
		private int min;
		private int max;
		private int value;

		public SetNumber(int id, int min, int max) {
			this.id = id;
			this.min = min;
			this.max = max;
			this.value = min;
		}

		public boolean canBeIncreased() {
			return value < max;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public void increaseValue() {
			this.value++;
		}

		public int getMin() {
			return min;
		}

		public int getMax() {
			return max;
		}

		public int getId() {
			return id;
		}

		public SetNumber copy() {
			SetNumber copy = new SetNumber(id, min, max);
			copy.setValue(value);
			return copy;
		}

		@Override
		public String toString() {
			return value + "";
		}

		@Override
		public int hashCode() {
			return id;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SetNumber other = (SetNumber) obj;
			if (id != other.id)
				return false;
			return true;
		}
	}
}
