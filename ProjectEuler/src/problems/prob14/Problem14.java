package problems.prob14;

import java.util.HashMap;
import java.util.Map;

public class Problem14 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int maxChainLength = 0;
		int startingNumber = 0;
		long start = System.currentTimeMillis();
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		for(int i = 10; i < 1000000; i++){
			int counter = 1;
			long current = i;
			while(current != 1){
				counter++;
				if(map.containsKey(current)){
					counter += map.get(current);
					break;
				}
				if(current % 2 == 0){
					current = current / 2;
				}
				else {
					current = current * 3 + 1;
				}
			}
			map.put((long)i, counter);
			if(counter > maxChainLength){
				maxChainLength = counter;
				startingNumber = i;
			}
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("starting number: " + startingNumber + "("+maxChainLength+"), in " + (duration/(double)1000) + "s");
	}
	
}
