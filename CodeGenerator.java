package dplmusiccompilemagic;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CodeGenerator {
	
	public static void toBinary(String asciiText) throws IOException{
		
		String line = null;
        StringBuffer value = new StringBuffer();
		try (Scanner scan = new Scanner(new FileReader(asciiText))){		
                    try (FileWriter f = new FileWriter("Binary File.txt")) {
                        while (scan.hasNext()){
                            
                            line = scan.nextLine();
                            value.append(line);                            
                        }
                        
                        byte[] bytes = value.toString().getBytes();
                        for (byte b : bytes){
                        	
/*                        	System.out.println("c: " + (char) b + " -> "
                        			+ Integer.toBinaryString(b).substring(2));*/
                        	
                        	f.write(Integer.toBinaryString(b).substring(2));                        	
                        } 
                        System.out.println("----------------------------Code Generator Complete----------------------------");
                        System.out.println("'Binary File.txt' created");
                    }		
		}
	}
}
	

