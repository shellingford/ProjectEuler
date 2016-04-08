package problems.prob65;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem65 {

	private static class Razlomak{
		private BigInteger a;
		private BigInteger b;
		private Razlomak c;
		private Razlomak parent;
		
		public void setA(BigInteger a) {
			this.a = a;
		}

		public void setB(BigInteger b) {
			this.b = b;
		}

		public void setC(Razlomak c) {
			this.c = c;
		}

		public void setParent(Razlomak parent) {
			this.parent = parent;
		}

		public BigInteger getValue(){
			if(c != null) {
				if(c.isRazlomak()) c.calculate();
				BigInteger c1 = c.getA();
				BigInteger result = (a.multiply(c1)).add(b);
				return result;
			}
			else {
				return a.add(b);
			}
		}
		
		public void calculate(){
			if(c != null && !c.isRazlomak()){
				parent.b = c.getA();
				a = (a.multiply(c.getA())).add(b);
				b = BigInteger.valueOf(0);
			}
			else 
				if(c!=null){
					c.calculate();
					parent.b = c.getA();
					a = (a.multiply(c.getA())).add(b);
					b = BigInteger.valueOf(0);
				}
				else {
					parent.b = a.add(b);
				}
		}
		
		public BigInteger getA(){
			return a;
		}
		public boolean isRazlomak(){
			return c != null;
		}
	}
	
	public static List<BigInteger> koef = new ArrayList<BigInteger>();
	public static BigInteger a;
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		a = BigInteger.valueOf(2);
		koef.add(BigInteger.valueOf(1));
		int brojac = 1;
		for(int i = 1; i < 101; i = i + 3){
			koef.add(BigInteger.valueOf(2*brojac));
			koef.add(BigInteger.valueOf(1));
			koef.add(BigInteger.valueOf(1));
			brojac++;
		}
		testN(99);
		long duration = System.currentTimeMillis() - start;
		System.out.println("in " + (duration/1000d) + "s");
	}
	private static void testN(int n){
		Razlomak razlomak = new Razlomak();
		razlomak.setA(a);
		razlomak.setB(BigInteger.valueOf(1));
		List<Razlomak> lista = new ArrayList<Razlomak>();
		for(int i = 0; i < n; i++){
			Razlomak razlomakN = new Razlomak();
			razlomakN.setParent(i == 0 ? razlomak : lista.get(i-1));
			
			if(i==0){
				razlomak.setC(razlomakN);
			}
			else {
				lista.get(lista.size()-1).setC(razlomakN);
			}
			
			if(i == n-1){
				razlomakN.setA(koef.get(i));
			}
			else {
				razlomakN.setA(koef.get(i));
				razlomakN.setB(BigInteger.valueOf(1));
			}
			lista.add(razlomakN);
		}
		BigInteger d = razlomak.getValue();
		int result = calculate(d+"");
		System.out.println("Result: " + result);
	}
	
	private static int calculate(String broj){
		int result = 0;
		for(int i = 0; i < broj.length(); i++){
			if(Character.isDigit(broj.charAt(i))) result += (char)broj.charAt(i) - 48;
		}
		return result;
	}

}
