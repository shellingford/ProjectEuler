package problems.prob3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import problems.PrimeGenerator;

public class Problem3 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		double num = 600851475143d;
		long start = System.currentTimeMillis();
		boolean end = false;
		
		List<Integer> list = PrimeGenerator.computePrimesList(800000);
			for(int j = list.size() - 1; j >= 0; j--){
				if(num % list.get(j) == 0){
					System.out.println(list.get(j));
					end = true;
					break;
				}
			if(end) break;
		}
		
		long duration = System.currentTimeMillis() - start;
		
		System.out.println((duration/(double)1000) + "s");

	}
	
	public static List<Integer> read(String fFileName) throws IOException {
		List<Integer> list = new ArrayList<Integer>();
	    Scanner scanner = new Scanner(new FileInputStream(fFileName));

		double max = 775147;
	    try {
	      while (scanner.hasNextLine()){
	    	  int br = Integer.parseInt(scanner.nextLine());
	    	  if(br < max) list.add(br);
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    return list;
	  }

}
