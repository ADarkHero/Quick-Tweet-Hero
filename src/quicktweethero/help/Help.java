package quicktweethero.help;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class Help {
    
    public Help() throws IOException{
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        
        Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
        Scene newScene = new Scene(root);
        
        newStage.setScene(newScene);
        newStage.setTitle("Help");
        newStage.initStyle(StageStyle.UNDECORATED);
        
        newStage.show();
    }

}
