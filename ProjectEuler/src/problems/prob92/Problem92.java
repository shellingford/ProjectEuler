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
			List<Long> lista = new ArrayList<Long>();
			long number = i;
			if(number < 568) lista.add(number);
			while(true){
				if(number == 1){
					break;
				}
				if(number == 89){
					set.addAll(lista);
					if(i == 567){
						counter = set.size();
					}
					if(i > 567){
						if(lista.size() > 1){
							System.out.println("num: " + i);
							for(Long l : lista){
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
				if(set.contains(number)){
					set.addAll(lista);
					if(i == 567){
						counter = set.size();
					}
					if(i > 567){
						if(lista.size() > 1){
							System.out.println("num: " + i);
							for(Long l : lista){
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
				lista.add(number);
			}
		}
		long duration = System.currentTimeMillis() - start;
		long b = 1;
		System.out.println(set.contains(b));
		long a = 89;
		System.out.println(set.contains(a));
		System.out.println("result: " + counter + " in " + (duration/1000d) + "s");
	}
	
	@SuppressWarnings("unused")
	private static void print(List<Long> lista){
		for(int i = 0; i < lista.size(); i++){
			if(i == lista.size() - 1){
				System.out.print(lista.get(i));
			}
			else System.out.print(lista.get(i) + " -> ");
		}
		System.out.println();
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
