package problems.common;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimeGenerator {

	public static List<Integer> computePrimesList(int limit){
		final BitSet primes = new BitSet();
		primes.set(0, false);
		primes.set(1, false);
		primes.set(2, limit, true);
		for (int i = 0; i * i < limit; i++) {
			if (primes.get(i)) {
				for (int j = i * i; j < limit; j += i) {
					primes.clear(j);
				}
			}
		}
		List<Integer> lista = new ArrayList<Integer>();
		int index = 0;
		int value = -1;
		while (true) {
			value = primes.nextSetBit(index);
			if (value == -1) {
				break;
			}
			lista.add(value);
			index = value+1;
		}
		return lista;
	}

	public static Set<Integer> computePrimesSet(int limit){
		final BitSet primes = new BitSet();
		primes.set(0, false);
		primes.set(1, false);
		primes.set(2, limit, true);
		for (int i = 0; i * i < limit; i++) {
			if (primes.get(i))
			{
				for (int j = i * i; j < limit; j += i)
				{
					primes.clear(j);
				}
			}
		}
		Set<Integer> set = new HashSet<Integer>();
		int index = 0;
		int value = -1;
		while(true) {
			value = primes.nextSetBit(index);
			if(value == -1){
				break;
			}
			set.add(value);
			index = value+1;
		}
		return set;
	}

	public static Set<Integer> computePrimesSet(int limit, List<Integer> lista) {
		if (lista == null) lista = new ArrayList<Integer>();
		final BitSet primes = new BitSet();
		primes.set(0, false);
		primes.set(1, false);
		primes.set(2, limit, true);
		for (int i = 0; i * i < limit; i++) {
			if (primes.get(i)) {
				for (int j = i * i; j < limit; j += i) {
					primes.clear(j);
				}
			}
		}
		Set<Integer> set = new HashSet<Integer>();
		int index = 0;
		int value = -1;
		while(true) {
			value = primes.nextSetBit(index);
			if (value == -1) {
				break;
			}
			set.add(value);
			lista.add(value);
			index = value+1;
		}
		return set;
	}

	public static int findNumberOfPrimes(int limit) {
		int primeCounter = 0;

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
		int index = 0;
		int value = -1;
		while(true){
			value = primes.nextSetBit(index);
			if(value == -1){
				break;
			}
			index = value+1;
			primeCounter++;
		}

		return primeCounter;
	}

	public static BitSet computePrimes(int limit) {
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
}
