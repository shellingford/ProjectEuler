package problems.prob89;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem89 {

	static Map<Integer, Integer> mapa = new HashMap<Integer, Integer>();
	static Map<Integer, String> mapa2 = new HashMap<Integer, String>();
	static Map<Integer, String> mapa3 = new HashMap<Integer, String>();
	static int len = 0;
	public static void main(String[] args) throws IOException {
		String roman1k = "roman-1k.txt";
		String romans = "roman.txt";
		String dir = "C:\\Users\\Renn\\Desktop\\programming\\Euler project\\";
		
		long start = System.currentTimeMillis();
		read1k(dir+roman1k);
//		makeRomans();
		read(dir+romans);
		long duration = System.currentTimeMillis()-start;
		System.out.println("Result: " + len + " in " + (duration/1000d) + "s");
		
		
//		for(Integer key : mapa2.keySet()){
//			if(!mapa2.get(key).equals(mapa3.get(key))){
//				System.out.println(mapa2.get(key) + "  ==  " + mapa3.get(key) + "  ==> " + key);
//			}
//		}

//		convert("MMMMCMLXXXXVIII");
	}
	
	private static void read(String fFileName) throws IOException {
	    Scanner scanner = new Scanner(new FileInputStream(fFileName));

	    try {
	      while (scanner.hasNextLine()){
	    	  convert(scanner.nextLine());
	      }
	    }
	    finally{
	      scanner.close();
	    }
	  }
	
	private static void convert(String roman){
		int number = 0;
		int maxLen = roman.length();
		String s = roman;
		if(roman.contains("IV")){
			number += 4;
			roman = roman.replace("IV", "");
		}
		if(roman.contains("IX")){
			number += 9;
			roman = roman.replace("IX", "");
		}
		
		if(roman.contains("XL")){
			number += 40;
			roman = roman.replace("XL", "");
		}
		if(roman.contains("XC")){
			number += 90;
			roman = roman.replace("XC", "");
		}
		
		if(roman.contains("CD")){
			number += 400;
			roman = roman.replace("CD", "");
		}
		if(roman.contains("CM")){
			number += 900;
			roman = roman.replace("CM", "");
		}

		char[] str = roman.toCharArray();
		
		number += 1*noOccurances(str, 'I');
		
		number += 5*noOccurances(str, 'V');
		
		number += 10*noOccurances(str, 'X');
		
		number += 50*noOccurances(str, 'L');
		
		number += 1000*noOccurances(str, 'M');
		
		number += 500*noOccurances(str, 'D');
		number += 100*noOccurances(str, 'C');
		
//		System.out.println(roman + " => " + number);
		try{
			len += Math.abs(maxLen - mapa.get(number));
		}catch(Exception e){
			System.out.println("EEEEEEERRRRRRRRRROOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRRRRRR");
			System.out.println(s + "("+roman+") ==> " + number);
			System.exit(0);
		}
	}
	
	private static int noOccurances(char[] str, char s){
//		if(s == 'M') System.out.println("noOccurances: " + Arrays.toString(str));
		int count = 0;
		for(int i = 0; i < str.length; i++){
			if(str[i] == s){
				count++;
			}
		}
		return count;
	}
	
	private static void read1k(String fFileName) throws IOException {
	    Scanner scanner = new Scanner(new FileInputStream(fFileName));
	    int brojac = 0;
	    try {
	      while (scanner.hasNextLine()){
	    	  brojac++;
	    	  String romanNumber = scanner.nextLine();
	    	  int len = romanNumber.length();
	    	  mapa.put(brojac, len);
	    	  mapa.put(brojac+1000, len+1);
	    	  mapa.put(brojac+2000, len+2);
	    	  mapa.put(brojac+3000, len+3);
	    	  mapa.put(brojac+4000, len+4);
	    	  

	    	  mapa2.put(brojac, romanNumber);
	    	  mapa2.put(brojac+1000, "M"+romanNumber);
	    	  mapa2.put(brojac+2000, "MM"+romanNumber);
	    	  mapa2.put(brojac+3000, "MMM"+romanNumber);
	    	  mapa2.put(brojac+4000, "MMMM"+romanNumber);
	      }
	    }
	    finally{
	      scanner.close();
	    }
//	    System.out.println("added: " + brojac);
	  }
	
	@SuppressWarnings("unused")
	private static void makeRomans(){
		String[] s = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",""};
		String[] s1 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String[] s2 = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 10; j++){
				for(int k = 0; k < 10; k++){
					for(int r=0; r < 10; r++){
						String roman = "";
						if(i == 1){
							roman = "M";
						}
						if(i == 2){
							roman = "MM";
						}
						if(i == 3){
							roman = "MMM";
						}
						if(i == 4){
							roman = "MMMM";
						}
						int number = j*100 + k*10  + i*1000;
						if(r < 9){
							number += (r+1)*1;
						}
						if(number == 4850){
							System.out.println("j="+j+"("+s2[j]+"), k="+k+"("+s1[k]+"), r="+r+"("+s[r]+")");
						}
						if(j > 0){
							roman += s2[j];
						}
						if(k > 0){
							roman += s1[k];
						}
						roman += s[r];
						mapa3.put(number, roman);
					}
				}
			}
		}
	}

}
