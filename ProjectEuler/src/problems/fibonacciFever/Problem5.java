package problems.fibonacciFever;

public class Problem5 {

	/**
	 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
	 *
	 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
	 */
	public static void main(String[] args) {
		int currentNum = 20;
		while(true) {
			//lowered number of comparisons as for example if number is divisible by 20, then it is also by
			//2, 4, 5, 10 so we don't need to individually check for 2, 4, 5, 10
			if (currentNum % 20 == 0 && currentNum % 19 == 0 && currentNum % 18 == 0 && currentNum % 17 == 0 &&
					currentNum % 16 == 0 && currentNum % 15 == 0 && currentNum % 14 == 0 && currentNum % 13 == 0 &&
					currentNum % 12 == 0 && currentNum % 11 == 0) {
				System.out.println("Smallest number: " + currentNum);
				break;
			}
			//number needs to be divisible by 20, so we can just increase by 20 each time
			currentNum += 20;
		}
	}

}
