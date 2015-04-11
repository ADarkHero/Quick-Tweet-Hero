package quicktweethero.help;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class Help {
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    public Help() throws IOException{
        final Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        
        Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
        Scene newScene = new Scene(root);
        
        newStage.setScene(newScene);
        newStage.setTitle("Help");
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
