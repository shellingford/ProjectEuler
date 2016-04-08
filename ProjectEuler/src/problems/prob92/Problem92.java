package problems.prob92;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem92 {

	static Set<Long> set = new HashSet<Long>();
	
	public static void main(String[] args) {
		long counter = 0;
		long start = System.currentTimeMillis();
		for(long i = 2; i < 10000000; i++){
			List<Long> list = new ArrayList<Long>();
			long number = i;
			if(number < 568) list.add(number);
			while(true){
				if(number == 1){
					break;
				}
				if(number == 89){
					set.addAll(list);
					if(i == 567){
						counter = set.size();
					}
					if(i > 567){
						if(list.size() > 1){
							for(Long l : list){
								if(!set.contains(l)){
									set.add(l);
									counter++;
								}
							}
						}
						else counter++;
					}
					break;
				}
				if(set.contains(number)){
					set.addAll(list);
					if(i == 567){
						counter = set.size();
					}
					if(i > 567){
						if(list.size() > 1){
							System.out.println("num: " + i);
							for(Long l : list){
								System.out.println("\t"+l);
								if(!set.contains(l)){
									set.add(l);
									counter++;
								}
							}
						}
						else counter++;
					}
					break;
				}
				number = (long)calculate(number);
				list.add(number);
			}
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("result: " + counter + " in " + (duration/1000d) + "s");
	}
	
	private static int calculate(long number){
		int result = 0;
		
		while(true){
			if(number < 10){
				result += number*number;
				break;
			}
			else {
				long res = number % 10;
				result += res*res;
				number = number / 10;
			}
		}
		
		return result;
	}

}
