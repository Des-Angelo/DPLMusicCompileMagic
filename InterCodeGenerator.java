package dplmusiccompilemagic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class InterCodeGenerator {

	public static String readFile(String pathname){
		
		System.out.println("---------------------------Intermediate Code Generator-------------------------------");
		System.out.println("Producing ASCII values.......");
		String line = null;
		try {
			Scanner scan = new Scanner(new FileReader(pathname));
			FileWriter f = new FileWriter("ASCII Version.txt");
			while (scan.hasNextLine()) {
				
				line = scan.nextLine();
				String splits = split(line);
				System.out.println(splits);
				String Ascii = " " + toAscii(splits);
				System.out.println(Ascii);
							
				String[] array = Ascii.split("  ");
//				System.out.println(Arrays.toString(array));
				f.write(Arrays.toString(array).replaceAll("\\[","").replaceAll("\\]", ""));
			
			}
			f.close();
			scan.close();
			System.out.println("---------------------Intermediate Code Generator Complete-------------------");
			System.out.println("'ASCII Version.txt' created");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public static String toAscii(String s){
		
		StringBuilder sb = new StringBuilder();
		long asciiInt = 0;
		for (int i = 0; i< s.length(); i++){
			char c = s.charAt(i);
			asciiInt = (int)c;
//			System.out.println(c + "=" + asciiInt);
			sb.append(asciiInt);
		}
//		return Long.parseLong(sb.toString());
		return sb.toString();
	}
	
	public static String split(String str){
		
		String sTrimed = str.trim();
		String[] parts = sTrimed.split("");
		String br = Arrays.toString(parts).replaceAll("\\s+","").replaceAll("\\[","").replaceAll("\\]", "").replaceAll("\\,", "");
		String line = br;
		
		return line;
	}
}

