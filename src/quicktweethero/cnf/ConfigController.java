/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicktweethero.cnf;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import quicktweethero.QuickTweetHeroController;
import quicktweethero.tweet.Tweet;

/**
 * FXML Controller class
 *
 * @author ADarkHero
 */
public class ConfigController implements Initializable {
    @FXML
    private Button updateConfigButton;
    @FXML
    private Button closeConfigButton;
    @FXML
    private TextField consumerKey;
    @FXML
    private TextField accessToken;
    @FXML
    private PasswordField consumerSecret;
    @FXML
    private PasswordField accessSecret;
    @FXML
    private Hyperlink twitterDevLink;  

    @FXML
    private void updateConfig(ActionEvent event) throws SAXException {
        Tweet tw = new Tweet();
        tw.writeConfig(consumerKey, consumerSecret, accessToken, accessSecret);
        tw.readXML();
        closeConfigButton.fire();
    }

    @FXML
    private void closeConfig(ActionEvent event) {        
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void openTwitterDev(ActionEvent event) throws IOException, URISyntaxException{
         String url = "https://apps.twitter.com/";

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Reads current config
        Tweet tw = new Tweet();
        consumerKey.setText(tw.getConsumerKey());
        consumerSecret.setText(tw.getConsumerSecret());
        accessToken.setText(tw.getAccessToken());
        accessSecret.setText(tw.getAccessTokenSecret());
    }  
    
}
