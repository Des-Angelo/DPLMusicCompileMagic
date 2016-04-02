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
public class TagStripper {
    private Scanner reader;
    private PrintWriter output;
    
    
    public TagStripper(String file)
    {
        try {
            reader = new Scanner(new File(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TagStripper.class.getName()).log(Level.SEVERE, null, ex);
        }//END SCANNER TRY-CATCH
        
        try {
            output = new PrintWriter(new File("stripped.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TagStripper.class.getName()).log(Level.SEVERE, null, ex);
        }//END PRINTWRITER TRY-CATCH
                
     
    }//END CONSTRUCTOR
    public void stripTagLine()
    {
    	System.out.println("-------------------Tag Stripper Started-------------------------------");
        String line = null;
        String stripped = null;
        while(reader.hasNext())
        {
            line = reader.nextLine();
            
        if(line.equals("-Start of File-"))
        {
            System.out.println("-Start of File- Stripped");
        }
        else
        if(line.equals("-Genre-"))
        {
            System.out.println("-Genre- Stripped");
            stripped = reader.nextLine();
            System.out.printf("%s %s\n",stripped, "Stripped");
        }
        else
        if(line.equals("-Title-"))
        {
            System.out.println("-Title- Stripped");
            stripped = reader.nextLine();
            System.out.printf("%s %s\n",stripped, "Stripped");
        }
        else
        if(line.equals("-Artiste-"))
        {
          System.out.println("-Artiste- Stripped");
          stripped = reader.nextLine();
          System.out.printf("%s %s\n",stripped, "Stripped");
        }
        else
        if(line.equals("-Verse-"))
        {
            System.out.println("-Verse- Stripped");
        }
        else
        if(line.equals("-Chorus-"))
        {
            System.out.println("-Chorus- Stripped");
        }
        else
        {
            output.println(line);
        }
        }//END LOOP
        
        output.close();
      
    }//END STRIPTAG
}
