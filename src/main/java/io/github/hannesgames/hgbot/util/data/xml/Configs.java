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

public class Configs {
    public static String mitDabei, twitchAcc, multi;
    //TODO:JAXB angucken und ändern
    private String file = "config.xml"; //filename
    private String path = "";
    File f = new File(path + "" + file);

    public void read() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        if (f.exists()) {
            File fXmlFile = new File(path + "/" + file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("config");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    //get this with - element.getElementsByTagName("auth").item(0).getTextContent();
                    mitDabei = element.getElementsByTagName("mitDabei").item(0).getTextContent();
                    twitchAcc = element.getElementsByTagName("accountName").item(0).getTextContent();
                    multi = element.getElementsByTagName("multi").item(0).getTextContent();
                }
            }
        } else {
            System.out.println("");
        }
    }

    private void save() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        //root element
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("config");
        doc.appendChild(rootElement);

        //commands elements
        Element cmd = doc.createElement("commands");
        rootElement.appendChild(cmd);

        //chatOutputForCMD attributes
        Attr attr = doc.createAttribute("chatOutputForCMD");
        attr.setValue("1");
        rootElement.appendChild(cmd);

        //mitDabei
        rootElement.setAttribute("mitDabei", "niemand");

        //multi
        rootElement.setAttribute("multi", "Es gibt keinen Multi-stream");

        //accountName
        rootElement.setAttribute("accountName", "hannesgamestest");

        //save to xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(f);

        transformer.transform(source, result);
        try {
            read();
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
