import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;



public class Main {
    public static void main(String[] args) throws IOException, NotValidAutosarFileException, ParserConfigurationException, SAXException {

        try {
            System.out.print("PLease pass the file : ");
            Scanner input = new Scanner(System.in);
            String x = input.next();
            if (!x.endsWith(".arxml")) {
                throw new NotValidAutosarFileException();
            }


            File inputFile = new File(x);
            if (inputFile.length() == 0) {
                throw new EmptyAutosarFileException("File is empty");
            }
            String y = x.replaceFirst(".arxml", "");
            File outputXmlFile = new File(y + "_mod.arxml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            // get all CONTAINER elements
            ArrayList<Element> containers = new ArrayList<>();
            for (int i = 0; i < doc.getDocumentElement().getElementsByTagName("CONTAINER").getLength(); i++) {
                containers.add((Element)doc.getDocumentElement().getElementsByTagName("CONTAINER").item(i));
            }

            // sort the containers by their SHORT-NAME values
            Collections.sort(containers, new Comparator<Element>() {
                @Override
                public int compare(Element e1, Element e2) {
                    return e1.getElementsByTagName("SHORT-NAME").item(0).getTextContent().compareTo(e2.getElementsByTagName("SHORT-NAME").item(0).getTextContent());
                }
            });

            // create a new output document
            Document outputDoc = dBuilder.newDocument();
            /*
                     I'm tried to make a newline before <AUTOSAR> But I failed
            Node newLine = outputDoc.createTextNode("\n");
            outputDoc.appendChild(newLine);
             */
            outputDoc.setXmlStandalone(true);
            Text newlinee = outputDoc.createTextNode("\n");

            Element autosar = outputDoc.createElement("AUTOSAR");
            autosar.appendChild(newlinee);
            outputDoc.appendChild(autosar);

            // add the sorted containers to the output document
            for (Element container : containers) {
                Text newline = outputDoc.createTextNode("\n");
                autosar.appendChild(outputDoc.importNode(container, true));
                autosar.appendChild(autosar.appendChild(newline));
            }

            // write the output XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(outputDoc);
            StreamResult result = new StreamResult(outputXmlFile);
            transformer.transform(source, result);

            System.out.println("Output written to " + outputXmlFile);
        } catch (EmptyAutosarFileException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
