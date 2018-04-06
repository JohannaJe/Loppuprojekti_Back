package fi.academy.demo;


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
    public List<Feedi> naytaLista(@RequestParam(name="name", required = false) String [] feednameList) {
        List feediLista = new ArrayList();
        Parsija parsija = new Parsija();
        String feedname;
        System.out.println(Arrays.toString(feednameList));

        for (int i = 0; i < feednameList.length; i++) {
            feedname = feednameList[i];


            if ("IS".equals(feedname)) {
                List feediListaIS = parsija.parsiFeedit("https://www.is.fi/rss/kotimaa.xml");
                feediLista.addAll(feediListaIS);
            }

            if ("HS".equals(feedname)) {
                List feediListaHS = parsija.parsiFeedit("https://www.hs.fi/rss/urheilu.xml");
                feediLista.addAll(feediListaHS);
            }

            if ("BBC".equals(feedname)) {
                List feediListaBBC = parsija.parsiFeedit("http://feeds.bbci.co.uk/news/world/rss.xml");
                feediLista.addAll(feediListaBBC);
            }

            if ("CNN".equals(feedname)) {
                List feediListaCNN = parsija.parsiFeedit("http://rss.cnn.com/rss/edition.xml");
                feediLista.addAll(feediListaCNN);
            }
        }

        Collections.sort(feediLista, new FeediOlioComparator());


        return feediLista;
    }



}
