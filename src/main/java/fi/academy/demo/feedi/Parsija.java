package fi.academy.demo.feedi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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

//                    LocalDateTime nyt = LocalDateTime.now();
//                    Feedi nyky = new Feedi(nyt);
                    LocalDateTime aikaleima = LocalDateTime.parse(pvm, DateTimeFormatter.RFC_1123_DATE_TIME);
//                    Feedi julkaistu = new Feedi(aikaleima);
//                    int jotain = nyky.getAikaleima().minusMinutes(julkaistu.getAikaleima());
//                    System.out.println(jotain);


                    String kuva= "";
                    try {
//                        NodeList onkoKuvaa = eElement.getElementsByTagName("media:content");
//                        System.out.println(onkoKuvaa.getLength());
                        if (eElement.getElementsByTagName("media:content").getLength() == 0 && eElement.getElementsByTagName("media:thumbnail").getLength() == 0) {
                            kuva = "https://kuvat.uusisuomi.fi/sites/default/files/imagecache/artikkelikuva_std/kuvat/iso_karhu_125l.jpg";
                        } else if (eElement.getElementsByTagName("media:content").getLength() != 0 ) {
                            kuva = eElement
                                    .getElementsByTagName("media:content")
                                    .item(0)
                                    .getAttributes()
                                    .getNamedItem("url")
                                    .getNodeValue();

                        } else if (eElement.getElementsByTagName("media:thumbnail").getLength() != 0) {
                                kuva = eElement
                                    .getElementsByTagName("media:thumbnail")
                                    .item(0)
                                    .getAttributes()
                                    .getNamedItem("url")
                                    .getNodeValue();
                        }


                    } catch (NullPointerException npe) {
                        List<String> kuvalista = Arrays.asList("https://upload.wikimedia.org/wikipedia/commons/b/ba/III_olutpullo.jpg",
                                "https://www.vastavalo.net/albums/userpics/12899/normal_013juopottelu_MG_1803.jpg",
                                "https://media.riemurasia.net/albumit/mmedia/2v/2hw/ezt8/92343/663901211.jpg");
                        Random rand = new Random();
                        int n = rand.nextInt(3);
                        kuva = kuvalista.get(n);
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