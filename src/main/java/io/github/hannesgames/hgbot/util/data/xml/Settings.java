package io.github.hannesgames.hgbot.util.data.xml;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


public class Settings {
    private String file = "settings.xml"; //filename
    private String path = "";

    public void read() throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile = new File(path + "/" + file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("settings");
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //get this with - element.getElementsByTagName("auth").item(0).getTextContent();

            }
        }
    }

    public void save() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        //root element
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("settings");
        doc.appendChild(rootElement);

        //secret elements
        Element secrets = doc.createElement("secrets");
        rootElement.appendChild(secrets);

        //secret attributes
        Attr attr = doc.createAttribute("secrets");
        attr.setValue("1");
        rootElement.appendChild(secrets);

        //2OAUTH
        rootElement.setAttribute("auth", "1213434bdfbfd"); //test 2oauth token TODO: REAL TOKEN USING

        //save to xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path + "/" + file));

        transformer.transform(source, result);
    }
}