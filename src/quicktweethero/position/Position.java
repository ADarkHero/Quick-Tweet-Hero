package quicktweethero.position;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Writes and reads current Position into/from config.xml
 *
 * @author ADarkHero
 */
public class Position {

    /**
     * Writes position to config.
     *
     * @param xPosition X-Position
     * @param yPosition Y-Position
     */
    public void setPosition(double xPosition, double yPosition) {
        try {
            String filepath = "cnf/config.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node company = doc.getFirstChild();

            // Get the twitter element by tag name directly
            Node staff = doc.getElementsByTagName("position").item(0);

            NodeList list = staff.getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if ("x".equals(node.getNodeName())) {
                    node.setTextContent(xPosition + "");
                }
                if ("y".equals(node.getNodeName())) {
                    node.setTextContent(yPosition + "");
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
        } catch (SAXException ex) {
            Logger.getLogger(Position.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public double getXPosition() {
        String x = "";
        
         try {
            File fXmlFile = new File("cnf/config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("position");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    x = eElement.getElementsByTagName("x").item(0).getTextContent();

                    if (x.isEmpty()) {
                        x = "-9001";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.parseDouble(x);
    }

    public double getYPosition() {
        String y = "";
        
         try {
            File fXmlFile = new File("cnf/config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("position");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    y = eElement.getElementsByTagName("y").item(0).getTextContent();

                    if (y.isEmpty()) {
                        y = "-9001";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.parseDouble(y);
    }
}
