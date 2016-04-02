package dplmusiccompilemagic;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class LexicalAnalysis {
    
    private Scanner fRead;
    private PrintWriter output;
    
    public LexicalAnalysis()
    {
        try {
            output = new PrintWriter(new File("lexical.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LexicalAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void openFile(String txtFile){
    
        try
        {
        	System.out.println("-------------------Lexical Analysis Started-------------------------------");
        	System.out.println("Reading file... (" + txtFile + ")");
            fRead = new Scanner(new File(txtFile));
        }
        catch(Exception e)
        {
            System.out.println("This file does not exist");
        }
        
    }//END OPENFILE
    
    public void readFile(){
        String lyric = null;
        
        if(fRead.hasNext())//START OF LINE CHECK
        {
            lyric = fRead.nextLine();
            if(lyric.equals("*start"))
            {
                output.println("-Start of File-");
                System.out.println("-Start of File-");
            }
            else
            {
                System.err.println("Error Detected: No start of line specified.");
                System.exit(0);
            }
        }//END START OF LINE CHECK
        
        if(fRead.hasNext())//CHECK FOR GENRE
        {
            lyric = fRead.nextLine();
            if(lyric.equals("#"))
            {
                lyric = fRead.nextLine();
                output.println("-Genre-");
                output.println(lyric);
                System.out.printf("%s %s \n",lyric, "-Genre-");
            }
            else
            {
                System.err.println("Error Detected: No genre specified");
                System.exit(0);
            }
        }//END GENRE CHECK
        
        if(fRead.hasNext())//CHECK FOR TITLE
        {
            lyric = fRead.nextLine();
            if(lyric.equals("&"))
            {
                lyric = fRead.nextLine();
                output.println("-Title-");
                output.println(lyric);
                System.out.printf("%s %s \n",lyric, "-Title-");
            }
            else
            {
                System.err.println("Error Detected: No title specified");
                System.exit(0);
            }
        }//END TITLE CHECK
        
        if(fRead.hasNext())//CHECK FOR AUTHOR
        {
            lyric = fRead.nextLine();
            if(lyric.equals("*"))
            {
                lyric = fRead.nextLine();
                output.println("-Artiste-");
                output.println(lyric);
                System.out.printf("%s %s \n", lyric, "-Artiste-");
            }
            else
            {
                System.err.println("Error Detected: No author specified");
                System.exit(0);
            }
        }//END AUTHOR CHECK
        
        while(fRead.hasNext())//PARSES EACH VERSE/CHORUS ALONG WITH LYRICS
        {
            lyric = fRead.nextLine();
            if(lyric.equals("v#"))
            {
                output.println("-Verse-");
                System.out.println("-Verse-");
            }
            else if(lyric.equals("c#"))
            {
                output.println("-Chorus-");
                System.out.println("-Chorus-");
            }
            else
            {
                output.println(lyric);
                System.out.printf("%s \n",lyric);
            }
        }//END WHILE LOOP
            
    }//END READFILE
    
    public void closeFile()
    {
        fRead.close();
        output.close();
    }
    
}//END CLASS
