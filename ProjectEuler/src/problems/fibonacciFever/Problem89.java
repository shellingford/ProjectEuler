package problems.fibonacciFever;

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
		String roman1k = "files/roman-1k.txt";
		String romans = "files/roman.txt";

		long start = System.currentTimeMillis();
		read1k(roman1k);
		read(romans);
		long duration = System.currentTimeMillis()-start;
		System.out.println("Result: " + len + " in " + (duration/1000d) + "s");
	}

	private static void read(String fFileName) throws IOException {
	    try (Scanner scanner = new Scanner(new FileInputStream(fFileName))) {
	      while (scanner.hasNextLine()){
	    	  convert(scanner.nextLine());
	      }

	    }
	  }

	private static void convert(String roman){
		int number = 0;
		int maxLen = roman.length();
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

		try{
			len += Math.abs(maxLen - mapa.get(number));
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}

	private static int noOccurances(char[] str, char s){
		int count = 0;
		for(int i = 0; i < str.length; i++){
			if(str[i] == s){
				count++;
			}
		}
		return count;
	}

	private static void read1k(String fileName) throws IOException {
	    try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
	      int brojac = 0;
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
	  }

}
