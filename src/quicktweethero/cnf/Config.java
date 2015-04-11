package quicktweethero.cnf;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Config {
    
    private double xOffset = 0;
    private double yOffset = 0;

    public Config() throws IOException{
        final Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        
        Parent root = FXMLLoader.load(getClass().getResource("Config.fxml"));
        Scene newScene = new Scene(root);
        
        newStage.setScene(newScene);
        newStage.setTitle("Configuration");
        newStage.initStyle(StageStyle.UNDECORATED);
        
        //Make draggable
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newStage.setX(event.getScreenX() - xOffset);
                newStage.setY(event.getScreenY() - yOffset);
            }
        });
        
        newStage.show();
    }

}
