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
//		String[] fileNames = {"primes-to-100k.txt","primes-to-200k.txt","primes-to-300k.txt","primes-to-400k.txt","primes-to-500k.txt","primes-to-600k.txt","primes-to-700k.txt","primes-to-800k.txt"};
//		String dir = "C:\\Users\\Renn\\Desktop\\primes\\";
		double broj = 600851475143d;
		
		long start = System.currentTimeMillis();
		boolean end = false;
		
//		for(int i = 0; i < fileNames.length; i++){
//			List<Integer> lista = read(dir+fileNames[i]);
		List<Integer> lista = PrimeGenerator.computePrimesList(800000);
			for(int j = lista.size() - 1; j >= 0; j--){
				if(broj % lista.get(j) == 0){
					System.out.println(lista.get(j));
					end = true;
					break;
				}
//			}
			if(end) break;
		}
		
		long duration = System.currentTimeMillis() - start;
		
		System.out.println((duration/(double)1000) + "s");

	}
	
	public static List<Integer> read(String fFileName) throws IOException {
		List<Integer> lista = new ArrayList<Integer>();
	    Scanner scanner = new Scanner(new FileInputStream(fFileName));

		double max = 775147;
	    try {
	      while (scanner.hasNextLine()){
	    	  int br = Integer.parseInt(scanner.nextLine());
	    	  if(br < max) lista.add(br);
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    return lista;
	  }

}
