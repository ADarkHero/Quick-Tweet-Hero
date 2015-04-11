package quicktweethero.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Open extends FileManager {
    
    /**
     * The user can choose a file to open.
     */
    public Open(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        chooser.setFileFilter(new FileNameExtensionFilter("QuickHero Notes", "qhn"));
        chooser.setDialogTitle("Specify a file to load"); 
        chooser.setCurrentDirectory(new File("./notes"));
        
        //Displays FileChooser
        int returnValue = chooser.showOpenDialog(null);
        
        //Sets saving path, if User selected a file
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            setFilePath(chooser.getSelectedFile().getPath());
        }
    }
    
    /**
     * Reads file content
     * 
     * @return File as text 
     */
    public String getText() throws FileNotFoundException{
        String text = "";
        
        text = new Scanner(new File(getFilePath())).useDelimiter("\\Z").next();
        
        return text;
    }

}
