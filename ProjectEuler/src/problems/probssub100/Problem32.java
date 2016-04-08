package problems.probssub100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem32 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Set<Integer> set = new HashSet<Integer>();
		List<Integer> lista = new ArrayList<Integer>();
		for(int k = 1; k < 2000; k++){
			for(int i = 1; i < 2000; i++){
				if(k%10==0 || i%10==0){
					continue;
				}
				int a = i * k;
				String n = a + ""+k+""+i;
				if(n.length() != 9){
					continue;
				}
				if(check(n)){
					if(!set.contains(a)){
						set.add(a);
						lista.add(a);
					}
				}
			}
		}
		
		long duration = System.currentTimeMillis() - start;
		
		System.out.println("Duration: " + (duration / 1000d) + "s");
		long sum = 0;
		for(Integer i : lista){
			sum += i;
		}
		System.out.println("Result: " + sum);

	}
	
	public static boolean check(String str){
		for(int i = 1; i < 10; i++){
			if(str.length() == 0){
				return false;
			}
			if(str.indexOf(i+"") < 0){
				return false;
			}
			if(str.indexOf(i+"") != str.lastIndexOf(i+"")){
				return false;
			}
			else {
				str = str.replace(i+"", "");
			}
		}
		return str.length() == 0;
	}
	
	public static boolean check(String a, String b){
		for(int i = 0; i < a.length(); i++){
			if(b.contains(a.charAt(i)+"")){
				return false;
			}
		}
		for(int i = 1; i < 10; i++){
			if(a.indexOf(i+"") != a.lastIndexOf(i+"")){
				return false;
			}
			if(b.indexOf(i+"") != b.lastIndexOf(i+"")){
				return false;
			}
		}
		return true;
	}

}
