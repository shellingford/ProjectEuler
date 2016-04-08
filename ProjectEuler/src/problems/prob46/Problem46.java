package problems.prob46;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.List;
import java.util.Set;

import problems.PrimeGenerator;

public class Problem46 {
	

	static List<Integer> lista = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		int result = Integer.MAX_VALUE;
		int count = 1024;
		
		List<Integer> lista2 = new ArrayList<Integer>();
		for(int i = 1; i < count; i++){
			lista2.add(2 * (int)Math.pow(i, 2));
		}
		
		long start = System.currentTimeMillis();
		Set<Integer> set = PrimeGenerator.computePrimesSet(100000, lista);
			for(int composite = 9; composite < 100000; composite=composite+2){
				if(set.contains(composite)) continue;
				boolean foundComposite = false;
				
				for(int k = 0; k < lista.size(); k++){
					if(lista.get(k) >= composite){
						break;
					}
					
					for(int s = 0; s < count; s++){
						if(lista2.get(s)+lista.get(k) > composite){
							break;
						}
						if(composite == lista.get(k) + lista2.get(s)){
							foundComposite = true;
							break;
						}
					}
					
					
					if(foundComposite) {
						break;
					}
				}
				
				
				if(!foundComposite) {
					result = composite;
					break;
				}
			}
		long duration = System.currentTimeMillis() - start;
		if(result == Integer.MAX_VALUE)
			result = -1;
		System.out.println("result: " + result + ", in " + (duration/(double)1000) + "s");
	}
	
	public static Set<Integer> read(String fFileName) throws IOException {
		Set<Integer> set = new HashSet<Integer>();
	    Scanner scanner = new Scanner(new FileInputStream(fFileName));

	    try {
	      while (scanner.hasNextLine()){
	    	  int br = Integer.parseInt(scanner.nextLine());
	    	  set.add(br);
	    	  lista.add(br);
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    return set;
	  }

}
