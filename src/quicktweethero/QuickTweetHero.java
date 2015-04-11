/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicktweethero;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import quicktweethero.position.Position;

/**
 *
 * @author ADarkHero
 */
public class QuickTweetHero extends Application {
    
    private double xOffset = 0;
    private double yOffset = 0;
    private Position p;

    @Override
    public void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("QuickTweetHero.fxml"));
        //Transparent
        root.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        
        //Don't show titlebar
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        

        stage.setScene(scene);
        
        //Set title
        stage.setTitle("QuickTweetHero");
        
        
        p = new Position();
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
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
                p.setPosition(event.getScreenX() - xOffset, event.getScreenY() - yOffset);
            }
        });
        
        
        double x = p.getXPosition();
        double y = p.getYPosition();
        
        //-9001 -> Position wasn't set
        if(x != -9001){
            stage.setX(x);
        }
        if(y != -9001){
            stage.setY(y);
        }
        
        //Show UI
        stage.show();

    }
    

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
