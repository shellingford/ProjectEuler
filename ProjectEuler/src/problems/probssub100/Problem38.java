package problems.probssub100;

public class Problem38 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long result = findPandigital(1000,10000,3);
		long duration = System.currentTimeMillis() - start;
		
		System.out.println("result: " + result + " in " + (duration/1000d) + "s");
	}
	
	private static long findPandigital(int min, int max, int count){
		long result = -1;
		
		for(int i = min; i < max; i++){
			String sum = "";
			int j = 1;
			for(j = 1; j <= count; j++){
				sum += i*j;
				if(sum.length() >= 9){
					break;
				}
			}
			if(check(sum+"")){
				long suma = Long.parseLong(sum);
				if(suma > result) result = suma;
			}
		}
		
		return result;
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

}
