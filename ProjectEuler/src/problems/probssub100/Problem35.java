package problems.probssub100;

import java.io.IOException;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

import problems.PrimeGenerator;

public class Problem35 {

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		Set<Integer> list = new HashSet<Integer>();
		Set<Integer> list2 = new HashSet<Integer>();
		list.addAll(PrimeGenerator.computePrimesList(1000000));

		for(Integer broj : list){
			if(!list2.contains(broj)){
				check(list, list2, broj);
			}
		}

		long duration = System.currentTimeMillis() - start;

		System.out.println("result: " + list2.size() + ", in " + (duration/(double)1000) + "s");
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

	private static void check(Set<Integer> list, Set<Integer> list2, int num){
		String s = num + "";
		int b = num;
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < s.length() - 1; i++){
			int a = (b % 10) * (int)Math.pow(10, s.length()-1) + (b/10);
			if(list.contains(a)){
				set.add(a);
				b = a;
				continue;
			}
			else return;
		}
		set.add(num);
		list2.addAll(set);
	}
}
