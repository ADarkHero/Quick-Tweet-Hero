/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicktweethero;

import java.awt.datatransfer.*;
import java.awt.Toolkit;
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
import javafx.scene.control.Label;
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
    private Button quickQuoteButton;
    @FXML
    private Button lastTweetButton;
    @FXML
    private Button lastRTButton;
    @FXML
    private Button copyButton;
    @FXML
    private Button pasteButton;
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
    @FXML
    private Label hashtags;
    @FXML
    private Label mentions;

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
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void quickQuote(ActionEvent event){
        textField.setText("\"" + textField.getText() + "\"");
        textField.positionCaret(textField.getLength()-1);
        updateCharcount();
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void lastTweet(ActionEvent event){
        textField.setText(textField.getText() + " #LastTweet");
        textField.positionCaret(textField.getLength());
        updateCharcount();
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void lastRT(ActionEvent event){
        textField.setText(textField.getText() + " #LastRT");
        textField.positionCaret(textField.getLength());
        updateCharcount();
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void copy(ActionEvent event){
        String myString = textField.getText();
        StringSelection stringSelection = new StringSelection (myString);
        Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
        clpbrd.setContents (stringSelection, null);
        
        copyButton.setText("Copied");

        //Reset Copy Button after 3 Seconds 
        Timeline timeline;
        EventHandler eh = new EventHandler() {

            @Override
            public void handle(Event t) {
                copyButton.setText("Copy");
            }
        };

        KeyFrame key = new KeyFrame(Duration.seconds(3), eh);

        timeline = new Timeline(key);
        timeline.play();
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void paste(ActionEvent event){
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = c.getContents(this);
        if (t == null)
            return;
        try {
            String text = textField.getText() + (String) t.getTransferData(DataFlavor.stringFlavor);
            textField.setText(text);
            textFieldFunctions();
            textField.positionCaret(textField.getLength());
        } catch (Exception e){
            e.printStackTrace();
        }    
    }

    /**
     * Functions, that should be executed, after the textField is updated.
     */
    @FXML
    private void textFieldFunctions(){
        updateCharcount();
        updateHashtags();
        updateMentions();
    }
    
    /**
     * 
     */
    @FXML
    private void updateHashtags(){
        String text = textField.getText();
        String tags = "";
        
        if(text.contains("#")){
            String words[] = text.split(" ");
            
            for (String word : words) {
                if (word.startsWith("#")) {
                    tags += word;
                }
            }
            
            tags = tags.replaceAll("#", "\r\n #");          //Add new line after tags
            tags = tags.substring(2);                       //Remove new line before first tag
            
            hashtags.setText(tags);
        }
        else{
            hashtags.setText("");
        }
    }
    
     /**
     * 
     */
    @FXML
    private void updateMentions(){
        String text = textField.getText();
        String ments = "";
        
        if(text.contains("@")){
            String words[] = text.split(" ");
            
            for (String word : words) {
                if (word.startsWith("@")) {
                    ments += word;
                }
            }
            
            ments = ments.replaceAll("@", "\r\n @");          //Add new line after tags
            ments = ments.substring(2);                       //Remove new line before first tag
            
            mentions.setText(ments);
        }
        else{
            mentions.setText("");
        }
    }
    
    /**
     * 
     */
    @FXML
    private void updateCharcount() {
        int length = textField.getLength();

        if (length > 140) {
            charCount.setTextFill(Color.web("#000000"));
            tweetButton.setDisable(true);
            charCount.setStyle("-fx-font-weight: bold; -fx-background-color: #007700;");
            charCount.setText("" + length);
        } else {
            charCount.setTextFill(Color.web("#FFFFFF"));
            tweetButton.setDisable(false);
            charCount.setStyle("-fx-font-weight: normal; -fx-background-color: #007700;");
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
                
                //Debug
                //System.out.println(keys[1]);

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
                if (keys[1].equals("H") && keys[0].equals("CONTROL")) {
                    helpButton.fire();
                    keys[1] = "";
                }

                //Send Tweet
                if (keys[1].equals("ENTER") && keys[0].equals("CONTROL")) {
                    tweetButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F2") || keys[1].equals("F2")) {
                    tweetButton.fire();
                    keys[1] = "";
                }

                //Delete textfield
                if (keys[1].equals("N") && keys[0].equals("CONTROL")) {
                    resetInputButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F5") || keys[1].equals("F5")) {
                    resetInputButton.fire();
                    keys[1] = "";
                }

                //Quick save
                if (keys[1].equals("S") && keys[0].equals("CONTROL")) {
                    saveButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F6") || keys[1].equals("F6")) {
                    saveButton.fire();
                    keys[1] = "";
                }

                //Not so quick save
                if (keys[1].equals("S") && keys[0].equals("ALT")) {
                    saveAsButton.fire();
                    keys[1] = "";
                }
                if (keys[0].equals("F7") || keys[1].equals("F7")) {
                    saveAsButton.fire();
                    keys[1] = "";
                }

                //Open file
                if (keys[1].equals("O") && keys[0].equals("CONTROL")) {
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
                
                //Copy
                if (keys[1].equals("C") && keys[0].equals("ALT")) {
                    copyButton.fire();
                    keys[1] = "";
                }
                
                //Paste
                if (keys[1].equals("V") && keys[0].equals("ALT")) {
                    pasteButton.fire();
                    keys[1] = "";
                }
                
                //Last Tweet
                if (keys[1].equals("DIGIT1") && keys[0].equals("CONTROL")) {
                    lastTweetButton.fire();
                    keys[1] = "";
                }
                
                //Quotate
                if (keys[1].equals("DIGIT2") && keys[0].equals("CONTROL")) {
                    quickQuoteButton.fire();
                    keys[1] = "";
                }

                //Shortcuts are not longer than 2 keys
                keys[0] = keys[1];

            }
        }
        );
    }

}
