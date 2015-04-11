/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicktweethero;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import quicktweethero.cnf.Config;
import quicktweethero.file.FileManager;
import quicktweethero.file.Save;
import quicktweethero.file.SaveAs;
import quicktweethero.file.Open;
import quicktweethero.help.Help;
import quicktweethero.tweet.Tweet;

/**
 *
 * @author ADarkHero
 */
public class QuickTweetHeroController implements Initializable {

    @FXML
    private TextArea textField;
    @FXML
    private Button tweetButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button saveAsButton;
    @FXML
    private Button viewButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button configButton;
    @FXML
    private Button resetInputButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button moveButton;
    @FXML
    private Button charCount;
    @FXML
    private ImageView saveButtonImage;
    @FXML
    private ImageView saveAsButtonImage;
    @FXML
    private ImageView viewButtonImage;
    @FXML
    private ImageView helpButtonImage;
    @FXML
    private ImageView exitButtonImage;
    @FXML
    private ImageView configButtonImage;
    @FXML
    private ImageView moveButtonImage;
    @FXML
    private ImageView resetInputImage;

    private static Tweet tw;
    private static String tweet = null;
    private static String mediaTitle = null;
    private static String mediaURL = null;
    private static String[] keys = new String[2];
    
 
    /**
     * Sends the inputted tweet
     *
     * @param event Mouse click/Keyboard shortcut
     */
    @FXML
    private void sendTweet(ActionEvent event) {
        //New Tweet Object
        tw = new Tweet();

        textField.requestFocus();                       //Focuses Textfield

        //Did it work?
        boolean work = tw.newTweet(textField.getText());

        //Success/Failure message
        JFrame frame = null;

        if (work) {
            tweetButton.setStyle("-fx-base: #007700;");
            tweetButton.setText("Tweet sent!");
            textField.clear();
        } else {
            tweetButton.setStyle("-fx-base: #770000;");
            tweetButton.setText("FAILURE!");
        }

        //Reset Tweet Button after 3 Seconds 
        Timeline timeline;
        EventHandler eh = new EventHandler() {

            @Override
            public void handle(Event t) {
                tweetButton.setStyle("");
                tweetButton.setText("Send Tweet");
            }
        };

        KeyFrame key = new KeyFrame(Duration.seconds(5), eh);

        timeline = new Timeline(key);
        timeline.play();

    }

    /**
     *
     * @param event
     */
    @FXML
    private void resetInput(ActionEvent event) {
        FileManager fm = new FileManager();
        if (!textField.getText().equals("")) {
            fm.generateNewPath();
            saveButton.fire();                      //Quick Saves Input
        }
        fm.generateNewPath();                   //Generates new Path after Quicksave
        textField.clear();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void exit(ActionEvent event) {
        if (!textField.getText().equals("")) {
            int quit = JOptionPane.showOptionDialog(null, "Do you really want to quit?", "Quit?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null,
                    new String[]{"Quit and save", "Quit without saving", "Cancel"}, "Cancel");

            if (quit == 0) {
                saveButton.fire();
                System.exit(0);
            } else if (quit == 1) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    /**
     * Saves the inputted text
     *
     * @param event Mouse click/Keyboard shortcut
     */
    @FXML
    private void quickSave(ActionEvent event) {
        Image image = new Image("file:img/SaveAs-50.png");
        saveButtonImage.setImage(image);
        Save s = new Save(textField.getText());

        //Reset Save Button after 3 Seconds 
        Timeline timeline;
        EventHandler eh = new EventHandler() {

            @Override
            public void handle(Event t) {
                saveButtonImage.setImage(new Image("file:img/Save-50.png"));
            }
        };

        KeyFrame key = new KeyFrame(Duration.seconds(3), eh);

        timeline = new Timeline(key);
        timeline.play();
    }

    /**
     * Saves the inputted text
     *
     * @param event Mouse click/Keyboard shortcut
     */
    @FXML
    private void saveAs(ActionEvent event) {
        SaveAs s = new SaveAs(textField.getText());
    }

    /**
     * Views past notes
     *
     * @param event Mouse click/Keyboard shortcut
     */
    @FXML
    private void viewFile(ActionEvent event) throws FileNotFoundException {
        Open o = new Open();
        textField.setText(o.getText());
    }

    /**
     * Helps you! *^*
     *
     * @param event Mouse click/Keyboard shortcut
     */
    @FXML
    private void showHelp(ActionEvent event) throws IOException {
        Help h = new Help();
    }

    /**
     * Helps you to config stuff! *^*
     *
     * @param event Mouse click/Keyboard shortcut
     */
    @FXML
    private void showConfig(ActionEvent event) throws IOException {
        Config c = new Config();
    }

    @FXML
    private void updateCharcount() {
        int length = textField.getLength();

        if (length > 140) {
            charCount.setTextFill(Color.web("#FF0000"));
            tweetButton.setDisable(true);
            charCount.setStyle("-fx-font-weight: bold;");
            charCount.setText("" + length);
        } else {
            charCount.setTextFill(Color.web("#FFFFFF"));
            tweetButton.setDisable(false);
            charCount.setStyle("-fx-font-weight: normal;");
            charCount.setText("" + length + " / 140");
        }

    }
    

    /**
     * Initializes the UI
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Read .xml config
        boolean twitterConfig = Tweet.readXML();
             
        //If something is not configurated
        if(!twitterConfig){
            configButton.setStyle("-fx-background-color: #770000;");
        }

        FileManager fm = new FileManager();

        keys[0] = "";
        keys[1] = "";

        //Keyboard Shortcuts
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                //Track keyboard shortcuts
                keys[1] = t.getCode().toString();

                //Exit Program
                if (keys[0].equals("ESCAPE") || keys[1].equals("ESCAPE")) {
                    exitButton.fire();
                    keys[1] = "";
                }

                //Halp me!
                if (keys[0].equals("F1") || keys[1].equals("F1")) {
                    helpButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("H") && keys[1].equals("CONTROL") || keys[1].equals("H") && keys[0].equals("CONTROL")) {
                    helpButton.fire();
                    keys[1] = "";
                }

                //Send Tweet
                if (keys[0].equals("ENTER") && keys[1].equals("CONTROL") || keys[1].equals("ENTER") && keys[0].equals("CONTROL")) {
                    tweetButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F2") || keys[1].equals("F2")) {
                    tweetButton.fire();
                    keys[1] = "";
                }

                //Delete textfield
                if (keys[0].equals("N") && keys[1].equals("CONTROL") || keys[1].equals("N") && keys[0].equals("CONTROL")) {
                    resetInputButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F5") || keys[1].equals("F5")) {
                    resetInputButton.fire();
                    keys[1] = "";
                }

                //Quick save
                if (keys[0].equals("S") && keys[1].equals("CONTROL") || keys[1].equals("S") && keys[0].equals("CONTROL")) {
                    saveButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F6") || keys[1].equals("F6")) {
                    saveButton.fire();
                    keys[1] = "";
                }

                //Not so quick save
                if (keys[0].equals("S") && keys[1].equals("ALT") || keys[1].equals("S") && keys[0].equals("ALT")) {
                    saveAsButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F7") || keys[1].equals("F7")) {
                    saveAsButton.fire();
                    keys[1] = "";
                }

                //Open file
                if (keys[0].equals("O") && keys[1].equals("CONTROL") || keys[1].equals("O") && keys[0].equals("CONTROL")) {
                    viewButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F8") || keys[1].equals("F8")) {
                    viewButton.fire();
                    keys[1] = "";
                }
                
                //Configuration
                if (keys[0].equals("F12") || keys[1].equals("F12")) {
                    configButton.fire();
                    keys[1] = "";
                }

                //Shortcuts are not longer than 2 keys
                keys[0] = keys[1];

            }
        }
        );
    }

}
