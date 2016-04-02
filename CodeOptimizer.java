package dplmusiccompilemagic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CodeOptimizer {
    
    public static void redundanceCheck(String file)
    {
        try {
            Scanner scan = new Scanner(new File(file));
            PrintWriter writer = new PrintWriter(new File("Optimize1.txt"));
            
            String[] lyric = new String[10];
            int chorusFlag = 0;
            
            while(scan.hasNext())//SCANS THROUGH FILE AND STRIPS OUT REPEATED CHORUS
        {
            String line = scan.nextLine();
            
            if(line.equals("-Chorus-") && chorusFlag == 0)
            {
                writer.println(line);
                int cnt = 0;
                while(!line.equals("-Verse-"))//BUILDS ARRAY WITH CHORUS LYRICS
                {
                    line = scan.nextLine();
                    if(line.equals("-Verse-"))
                    writer.println(line);
                    else
                    {
                        lyric[cnt] = line;
                        writer.println(line);
                    }
                    cnt++;
                    
                }//END INNER LOOP
                chorusFlag = 1; 
            }//END IF
            else if(line.equals("-Chorus-") && chorusFlag == 1)
            {
                writer.println(line);
                int cnt = 0;
                
                while(!line.equals("-Verse-"))//CHECKS FOR REPEATED CHORUS
                {
                    line = scan.nextLine();
                    if(!line.equals(lyric[cnt]))
                    {
                        writer.println(line);
                    }
                    cnt++;
                }//END INNER LOOP
            }//END IF
            else writer.println(line);
        }//END WHILE LOOP
            
            scan.close();
            writer.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodeOptimizer.class.getName()).log(Level.SEVERE, null, ex);
        }//END TRY-CATCH 
        
    }//END REDUNDANCE CHECK
	
	public static String optimize(String pathname) throws IOException{
				
		Scanner scan = new Scanner(new FileReader(pathname));
		FileWriter fw = new FileWriter("Optimize2.txt");
		String line = null;
		
		while(scan.hasNextLine()){
			
			line = scan.nextLine();
			if(line.trim().length() > 0){
				line = line.trim().replaceAll("\\s+", "") + " ";
				}
				else{
					line = line.trim().replaceAll("\\s", "");
					line = line + "\n";
				}
				fw.write(line);
				}
		scan.close();
		fw.close();
		return line;
		}
	public static String optimizeAscii(String pathname) throws IOException{
		
		Scanner scan = new Scanner(new FileReader(pathname));
		FileWriter fw = new FileWriter("Optimized ASCII.txt");
		String line = null;
		
		while(scan.hasNextLine()){
			
			line = scan.nextLine();
			if(line.trim().length() > 0){
				line = line.trim().replaceAll("\\s+", "") + " ";
				}
				else{
					line = line.trim().replaceAll("\\s", "");
					line = line + "\n";
				}
				fw.write(line);
				}
		scan.close();
		fw.close();
		return line;
		}
}
