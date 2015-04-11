package quicktweethero.tweet;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.NamedNodeMap;

public class Tweet {

    //Your Twitter App's Consumer Key
    private static String consumerKey = null;

    //Your Twitter App's Consumer Secret
    private static String consumerSecret = null;

    //Your Twitter Access Token
    private static String accessToken = null;

    //Your Twitter Access Token Secret
    private static String accessTokenSecret = null;

    //Your Twitter Objects
    private static TwitterFactory twitterFactory;
    private static Twitter twitter;

    public static void main(String[] args) throws IOException {

    }

    /**
     * Reads the Twitter API Configuration from the config.xml
     *
     * ToDo: Look, if xml is 'correct'
     */
    public static boolean readXML() {
        try {

            File fXmlFile = new File("cnf/config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("twitter");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    consumerKey = eElement.getElementsByTagName("consumerkey").item(0).getTextContent();
                    consumerSecret = eElement.getElementsByTagName("consumersecret").item(0).getTextContent();
                    accessToken = eElement.getElementsByTagName("accesstoken").item(0).getTextContent();
                    accessTokenSecret = eElement.getElementsByTagName("accesstokensecret").item(0).getTextContent();

                    if (consumerKey.isEmpty() || consumerSecret.isEmpty() || accessToken.isEmpty() || accessTokenSecret.isEmpty()) {
                        return false;
                    } else {
                        return true;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean newTweet(String tweet) {
        twitterFactory = new TwitterFactory();
        twitter = twitterFactory.getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        //Instantiate and initialize a new twitter status update
        StatusUpdate statusUpdate = new StatusUpdate(
                //your tweet or status message
                tweet);

        //tweet or update status
        try {
            Status status = twitter.updateStatus(statusUpdate);
            return true;
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(Tweet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean newMediaTweet(String tweet, String mediaTitle, String mediaURL) {
        twitterFactory = new TwitterFactory();
        twitter = twitterFactory.getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        //Instantiate and initialize a new twitter status update
        StatusUpdate statusUpdate = new StatusUpdate(tweet);
        //attach any media, if you want to
        try {
            statusUpdate.setMedia(mediaTitle, new URL(mediaURL).openStream());
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(Tweet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Tweet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tweet or update status
        try {
            Status status = twitter.updateStatus(statusUpdate);
            return true;
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(Tweet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void writeConfig(TextField consumerKey, PasswordField consumerSecret, TextField accessToken, PasswordField accessSecret) throws org.xml.sax.SAXException {
         
        try {
		String filepath = "cnf/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
                
                // Get the root element
		Node company = doc.getFirstChild();
 
		// Get the twitter element by tag name directly
		Node staff = doc.getElementsByTagName("twitter").item(0);
                
                NodeList list = staff.getChildNodes();

                for (int i = 0; i < list.getLength(); i++) {
 
                   Node node = list.item(i);
 
		   if ("consumerkey".equals(node.getNodeName())) {
			node.setTextContent(consumerKey.getText().trim());
		   }
                   if ("consumersecret".equals(node.getNodeName())) {
			node.setTextContent(consumerSecret.getText().trim());
		   }
                   if ("accesstoken".equals(node.getNodeName())) {
			node.setTextContent(accessToken.getText().trim());
		   }
                   if ("accesstokensecret".equals(node.getNodeName())) {
			node.setTextContent(accessSecret.getText().trim());
		   }
 
		}

 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
  
	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   }
    }

    public static String getConsumerKey() {
        return consumerKey;
    }

    public static void setConsumerKey(String consumerKey) {
        Tweet.consumerKey = consumerKey;
    }

    public static String getConsumerSecret() {
        return consumerSecret;
    }

    public static void setConsumerSecret(String consumerSecret) {
        Tweet.consumerSecret = consumerSecret;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        Tweet.accessToken = accessToken;
    }

    public static String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public static void setAccessTokenSecret(String accessTokenSecret) {
        Tweet.accessTokenSecret = accessTokenSecret;
    }

}
