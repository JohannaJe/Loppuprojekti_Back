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


    public List parsiFeedit(String urli) {
        List<Feedi> feediLista = new ArrayList<>();

        try {


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(urli);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

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

                    String kuva;

                    try {
                        kuva = eElement
                                .getElementsByTagName("media:content")
                                .item(0)
                                .getAttributes()
                                .getNamedItem("url")
                                .getNodeValue();
                    } catch (NullPointerException npe) {
                        kuva = "https://kuvat.uusisuomi.fi/sites/default/files/imagecache/artikkelikuva_std/kuvat/iso_karhu_125l.jpg";
                    }


                    feediLista.add(new Feedi(otsikko, linkki, aikaleima, kuva));


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return feediLista;
    }
}