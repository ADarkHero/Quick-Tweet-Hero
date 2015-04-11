package quicktweethero.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;

public class SaveAs extends FileManager {

    /**
     * The user can choose a file to save.
     * 
     * @param text Text to save
     */
    public SaveAs(String text) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./notes"));
        chooser.setDialogTitle("Specify a file to save");
        
        int userSelection = chooser.showSaveDialog(null);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            setFilePath(fileToSave.getAbsolutePath());
            Save(text);
        }
    }
    
    /**
     * Writes Text into a file
     * 
     * @param text Text to save
     */
    private void Save(String text){
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
