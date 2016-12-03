package problems.probssub100;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import problems.common.PrimeGenerator;

public class Problem46 {

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		int result = Integer.MAX_VALUE;
		int count = 1024;
		List<Integer> lista = new ArrayList<Integer>();

		List<Integer> lista2 = new ArrayList<Integer>();
		for(int i = 1; i < count; i++){
			lista2.add(2 * (int)Math.pow(i, 2));
		}

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

}
