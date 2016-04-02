package dplmusiccompilemagic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SemanticAnalysis {
	
	public static boolean alphaCheck(String s) throws FileNotFoundException {

		System.out.println("--------------------------Running Semantic Check----------------------------------");
		String line;
		Scanner scan = new Scanner(new FileReader(s));

		while (scan.hasNextLine()) {

			line = scan.nextLine();
			char[] chars = line.toCharArray();

			for (char c : chars) {
				if (!isAlpha(c)) {
					System.err.println("Error: Not Alphanumeric");
					System.exit(1);
					scan.close();
					return false;
				}

			}
		}
		scan.close();
		System.out.println("Semenatic Check Complete: File Validated");
		return true;
	}
	
	public static boolean isAlpha(char name){
		return Character.toString(name).matches("[a-zA-Z ,']+");
	}
}


