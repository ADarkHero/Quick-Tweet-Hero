package quicktweethero.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Save extends FileManager {

    /**
     * Saves the current text from the textField
     * 
     * @param text
     */
    public Save(String text)  {
        PrintWriter pWriter = null; 
        try { 
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(getFilePath()))); 
            pWriter.println(text); 
        } catch (IOException ioe) { 
            ioe.printStackTrace(); 
        } finally { 
            if (pWriter != null){ 
                pWriter.flush(); 
                pWriter.close(); 
            } 
        } 
    }
}


