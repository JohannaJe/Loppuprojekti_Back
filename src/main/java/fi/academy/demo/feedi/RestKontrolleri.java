package fi.academy.demo.feedi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestKontrolleri {


    @GetMapping("/testi")
    public List<Feedi> naytaLista(@RequestParam(name = "name", required = false) String[] feednameList) {
        List feediLista = new ArrayList();
        Parsija parsija = new Parsija();
        String feedname;
        List feediListaCNN;
        List feediListaIS;
        List feediListaBBC;
        List feediListaHS;
        List omaFeediLista;
        System.out.println(Arrays.toString(feednameList));

        for (int i = 0; i < feednameList.length; i++) {
            feedname = feednameList[i];

            if(feedname.contains("www") || feedname.contains("http")){
                String feedUrl = feedname;
                omaFeediLista= parsija.parsiFeedit(feedUrl);
                feediLista.addAll(omaFeediLista);
            }
            // tähän tulee tarvittaessa .rss ja .xml urlit suodattava if hässäkkä

            if (feedname.contains("CNN")) {
                if (feedname.contains("latest")) {
                    feediListaCNN = parsija.parsiFeedit("http://rss.cnn.com/rss/cnn_latest.rss");
                    feediLista.addAll(feediListaCNN);
                } else if (feedname.contains("top")) {
                    feediListaCNN = parsija.parsiFeedit("http://rss.cnn.com/rss/edition.rss");
                    feediLista.addAll(feediListaCNN);
                } else if (feedname.contains("world")) {
                    feediListaCNN = parsija.parsiFeedit("http://rss.cnn.com/rss/edition_world.rss");
                    feediLista.addAll(feediListaCNN);
                } else if (feedname.contains("science")) {
                    feediListaCNN = parsija.parsiFeedit("http://rss.cnn.com/rss/edition_space.rss");
                    feediLista.addAll(feediListaCNN);
                } else if (feedname.contains("entertainment")) {
                    feediListaCNN = parsija.parsiFeedit("http://rss.cnn.com/rss/edition_entertainment.rss");
                    feediLista.addAll(feediListaCNN);
                } else if (feedname.contains("sport")) {
                    feediListaCNN = parsija.parsiFeedit("http://rss.cnn.com/rss/edition_sport.rss");
                    feediLista.addAll(feediListaCNN);
                }

            }


            if (feedname.contains("IS")) {
                if (feedname.contains("tuoreimmat")) {
                    feediListaIS = parsija.parsiFeedit("https://www.is.fi/rss/tuoreimmat.xml");
                    feediLista.addAll(feediListaIS);
                } else if (feedname.contains("kotimaa")) {
                    feediListaIS = parsija.parsiFeedit("https://www.is.fi/rss/kotimaa.xml");
                    feediLista.addAll(feediListaIS);
                } else if (feedname.contains("ulkomaat")) {
                    feediListaIS = parsija.parsiFeedit("https://www.is.fi/rss/ulkomaat.xml");
                    feediLista.addAll(feediListaIS);
                } else if (feedname.contains("talous")) {
                    feediListaIS = parsija.parsiFeedit("https://www.is.fi/rss/taloussanomat.xml");
                    feediLista.addAll(feediListaIS);
                } else if (feedname.contains("viihde")) {
                    feediListaIS = parsija.parsiFeedit("https://www.is.fi/rss/viihde.xml");
                    feediLista.addAll(feediListaIS);
                } else if (feedname.contains("musiikki")) {
                    feediListaIS = parsija.parsiFeedit("https://www.is.fi/rss/musiikki.xml");
                    feediLista.addAll(feediListaIS);
                } else if (feedname.contains("urheilu")) {
                    feediListaIS = parsija.parsiFeedit("https://www.is.fi/rss/urheilu.xml");
                    feediLista.addAll(feediListaIS);
                }

            }

            if (feedname.contains("HS")) {
                if (feedname.contains("tuoreimmat")) {
                    feediListaHS = parsija.parsiFeedit("https://www.hs.fi/rss/tuoreimmat.xml");
                    feediLista.addAll(feediListaHS);
                } else if (feedname.contains("kotimaa")) {
                    feediListaHS = parsija.parsiFeedit("https://www.hs.fi/rss/kotimaa.xml");
                    feediLista.addAll(feediListaHS);
                } else if (feedname.contains("ulkomaat")) {
                    feediListaHS = parsija.parsiFeedit("https://www.hs.fi/rss/ulkomaat.xml");
                    feediLista.addAll(feediListaHS);
                } else if (feedname.contains("talous")) {
                    feediListaHS = parsija.parsiFeedit("https://www.hs.fi/rss/talous.xml");
                    feediLista.addAll(feediListaHS);
                } else if (feedname.contains("politiikka")) {
                    feediListaHS = parsija.parsiFeedit("https://www.hs.fi/rss/politiikka.xml");
                    feediLista.addAll(feediListaHS);
                } else if (feedname.contains("urheilu")) {
                    feediListaHS = parsija.parsiFeedit("https://www.hs.fi/rss/urheilu.xml");
                    feediLista.addAll(feediListaHS);
                }
            }
//              vanha versio if lauseesta
//            if("BBC".equals(feedname)){
//                List feedilistaBBC=new ArrayList();
//                feediLista.addAll(feedilistaBBC);
//            }
            if (feedname.contains("BBC")) {
                if (feedname.contains("world")) {
                    feediListaBBC = parsija.parsiFeedit("http://feeds.bbci.co.uk/news/world/rss.xml");
                    feediLista.addAll(feediListaBBC);
                } else if (feedname.contains("business")) {
                    feediListaBBC = parsija.parsiFeedit("http://feeds.bbci.co.uk/news/business/rss.xml");
                    feediLista.addAll(feediListaBBC);
                } else if (feedname.contains("politics")) {
                    feediListaBBC = parsija.parsiFeedit("http://feeds.bbci.co.uk/news/politics/rss.xml");
                    feediLista.addAll(feediListaBBC);
                } else if (feedname.contains("health")) {
                    feediListaBBC = parsija.parsiFeedit("http://feeds.bbci.co.uk/news/health/rss.xml");
                    feediLista.addAll(feediListaBBC);
                }
            }


        }

        Collections.sort(feediLista, new FeediOlioComparator());


        return feediLista;
    }


}
