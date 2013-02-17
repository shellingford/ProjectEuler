package problems.prob35;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

import problems.PrimeGenerator;

public class Problem35 {

	static int brojac = 0;
	public static void main(String[] args) throws IOException {
//		String[] fileNames = {"primes-to-100k.txt","primes-to-200k.txt","primes-to-300k.txt","primes-to-400k.txt","primes-to-500k.txt","primes-to-600k.txt",
//				"primes-to-700k.txt","primes-to-800k.txt","primes-to-900k.txt","primes-to-1000k.txt"};
//		String dir = "C:\\Users\\Renn\\Desktop\\programming\\primes\\";
		
		long start = System.currentTimeMillis();
		Set<Integer> lista = new HashSet<Integer>();
		Set<Integer> lista2 = new HashSet<Integer>();
		
//		for(int i = 0; i < fileNames.length; i++){
//			 lista.addAll(read(dir+fileNames[i]));
//		}

		lista.addAll(PrimeGenerator.computePrimesList(1000000));
		
//		System.out.println(lista.size());
//		BitSet lista3 = computePrimes(1000000);
//		System.out.println(lista3.size());
		
		for(Integer broj : lista){
			if(!lista2.contains(broj)){
				check(lista, lista2, broj);
			}
		}
		
		long duration = System.currentTimeMillis() - start;
		
		System.out.println("result: " + lista2.size() + ", in " + (duration/(double)1000) + "s");
	}
	
	public static BitSet computePrimes(int limit)
	{
	    final BitSet primes = new BitSet();
	    primes.set(0, false);
	    primes.set(1, false);
	    primes.set(2, limit, true);
	    for (int i = 0; i * i < limit; i++)
	    {
	        if (primes.get(i))
	        {
	            for (int j = i * i; j < limit; j += i)
	            {
	                primes.clear(j);
	            }
	        }
	    }
	    return primes;
	}
	
	private static void check(Set<Integer> lista, Set<Integer> lista2, int broj){
		String s = broj + "";
		int b = broj;
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < s.length() - 1; i++){
			int a = (b % 10) * (int)Math.pow(10, s.length()-1) + (b/10);
			if(lista.contains(a)){
				set.add(a);
				b = a;
				continue;
			}
			else return;
		}
		brojac += set.size() + 1;
		set.add(broj);
		System.out.println(set);
		lista2.addAll(set);
	}
	
	public static Set<Integer> read(String fFileName) throws IOException {
		Set<Integer> lista = new HashSet<Integer>();
	    Scanner scanner = new Scanner(new FileInputStream(fFileName));

	    try {
	      while (scanner.hasNextLine()){
	    	  int br = Integer.parseInt(scanner.nextLine());
	    	  lista.add(br);
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    System.out.println("adding " + lista.size() + " elements! " + fFileName);
	    return lista;
	  }

}
