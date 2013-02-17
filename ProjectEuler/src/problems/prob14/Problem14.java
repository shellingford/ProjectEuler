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
		Map<Long, Integer> mapa = new HashMap<Long, Integer>();
		for(int i = 10; i < 1000000; i++){
			int brojac = 1;
			long current = i;
			while(current != 1){
				brojac++;
				if(mapa.containsKey(current)){
					brojac += mapa.get(current);
					break;
				}
				if(current % 2 == 0){
					current = current / 2;
				}
				else {
					current = current * 3 + 1;
				}
			}
			mapa.put((long)i, brojac);
			if(brojac > maxChainLength){
				maxChainLength = brojac;
				startingNumber = i;
			}
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("starting number: " + startingNumber + "("+maxChainLength+"), in " + (duration/(double)1000) + "s");
	}
	
}
