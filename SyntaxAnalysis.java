package dplmusiccompilemagic;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class SyntaxAnalysis {
    private Scanner fRead;
    
    public void openFile(String lex)
    {
        try
        {
        	System.out.println("-------------------Syntax Analysis Started-------------------------------");
        	System.out.println("Reading file... (" + lex + ")");
            fRead = new Scanner(new File(lex));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Error: File could not be found");
        }
        catch(Exception e)
        {
            System.out.println("An error ocurred.");
        }
    }//END OPENFILE
    
    public void readFile()
    {
        String line = null;
        int startCheck = 0;
        int genreCheck = 0;
        int titleCheck = 0;
        int artisteCheck = 0;
        int verseCheck = 0;
        int chorusCheck = 0;
        
        while(fRead.hasNext())
        {
            line = fRead.nextLine();
            if(line.equals("-Start of File-"))
                startCheck = 1;
            if(line.equals("-Genre-"))
                genreCheck = 1;
            if(line.equals("-Title-"))
                titleCheck = 1;
            if(line.equals("-Artiste-"))
                artisteCheck = 1;
            if(line.equals("-Verse-"))
                verseCheck = 1;
            if(line.equals("-Chorus-"))
                chorusCheck = 1;
           
        }
        
        if(startCheck == 0)
        {
            System.err.println("Error: No Start of File Tag found.");
            System.exit(0);
        }
        if(genreCheck == 0)
        {
            System.err.println("Error: No Genre Tag found.");
            System.exit(0);
        }
        if(titleCheck == 0)
        {
            System.err.println("Error: No Title Tag found.");
            System.exit(0);
        }
        if(artisteCheck == 0)
        {
            System.err.println("Error: No Artiste Tag found.");
            System.exit(0);
        }
        if(verseCheck == 0)
        {
            System.err.println("Error: No Verse Tag found.");
            System.exit(0);
        }
        if(chorusCheck == 0)
        {
            System.err.println("Error: No Chorus Tag found.");
            System.exit(0);
        }
        
    }//END READFILE
    
    public void closeFile()
    {
    	System.out.println("Closing file...");
        fRead.close();
    }
    
    
    
}
