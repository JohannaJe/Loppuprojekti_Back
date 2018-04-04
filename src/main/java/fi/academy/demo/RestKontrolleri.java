package fi.academy.demo;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestKontrolleri {





    @GetMapping("/testi")
    public List<Feedi> naytaLista() {
        Lukija lukija = new Lukija();
        List feediListaHS= lukija.koostaLista("https://www.hs.fi/rss/urheilu.xml");
        List feediListaIS= lukija.koostaLista("https://www.is.fi/rss/musiikki.xml");
        List feediLista = new ArrayList();
        feediLista.addAll(feediListaHS);
        feediLista.addAll(feediListaIS);
        Collections.sort(feediLista, new FeediOlioComparator());

        return feediLista;
    }

}
