package fi.academy.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Parsija {

    List<Feedi> feediLista = new ArrayList<>();

    public List parsiFeedit() {

        try {
            String urli = "https://www.hs.fi/rss/urheilu.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new URL(urli).openStream());


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(urli);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("item");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String otsikko = eElement
                            .getElementsByTagName("title")
                            .item(0)
                            .getTextContent();
                    String linkki = eElement
                            .getElementsByTagName("link")
                            .item(0)
                            .getTextContent();
                    String pvm = eElement
                            .getElementsByTagName("pubDate")
                            .item(0)
                            .getTextContent();
                    LocalDateTime aikaleima = LocalDateTime.parse(pvm, DateTimeFormatter.RFC_1123_DATE_TIME);

                    feediLista.add(new Feedi(otsikko, linkki, aikaleima));



//                    System.out.println("Student roll no : "
//                            + eElement.getAttribute("rollno"));
//                    System.out.println("First Name : "
//                            + eElement
//                            .getElementsByTagName("item")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Last Name : "
//                            + eElement
//                            .getElementsByTagName("title")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Nick Name : "
//                            + eElement
//                            .getElementsByTagName("nickname")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Marks : "
//                            + eElement
//                            .getElementsByTagName("marks")
//                            .item(0)
//                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return feediLista;
    }
}