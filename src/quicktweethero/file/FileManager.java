package quicktweethero.file;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {

    private static String file;
    private static boolean set = true;

    public String getFilePath() {
        return file;
    }

    public void setFilePath(String file) {
        this.file = file;
    }
    
    /**
     * Generates a new Path, when the program is launched
     */
    public FileManager(){
        if(set){
            generateNewPath();
            
            set = false;
        }        
    }

    /**
     * Generates a new Path, if needed
     */
    public void generateNewPath() {
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss");
        Date date = new Date();
        file = "notes/"+dateFormat.format(date)+".qhn";
    }

}
