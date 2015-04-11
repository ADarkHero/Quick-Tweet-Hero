package quicktweethero.cnf;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Config {

    public Config() throws IOException{
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        
        Parent root = FXMLLoader.load(getClass().getResource("Config.fxml"));
        Scene newScene = new Scene(root);
        
        newStage.setScene(newScene);
        newStage.setTitle("Configuration");
        newStage.initStyle(StageStyle.UNDECORATED);
        
        newStage.show();
    }

}
