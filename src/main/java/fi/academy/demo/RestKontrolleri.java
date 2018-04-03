package fi.academy.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestKontrolleri {


//    public void  kayta() {
//        Lukija rssOlio = new Lukija();
//        System.out.println(rssOlio.haeOtsikko("https://www.hs.fi/rss/urheilu.xml"));
//        System.out.println(rssOlio.haeLinkki("https://www.hs.fi/rss/urheilu.xml"));
//        System.out.println(rssOlio.haeAika("https://www.hs.fi/rss/urheilu.xml"));
//        Map<List, List> map = yhdistaListatMapiksi (otsikot, linkit);
//        System.out.println(map);
// }


    @GetMapping("/testi")
//    public String haesanonnat() {
//        return "Friedrich Nietsche: Jumala on kuollut, Jumala: Friedrich Nietsche on kuollut, Chuck Norris: Friedrich Nietsche ja Jumala on kuollut, Horst Schimanski: Minä olen Jumala";
//    }

    public List<Feedi> naytaLista() {
        Lukija nnn = new Lukija();
        List a= nnn.koostaLista("https://www.hs.fi/rss/urheilu.xml");
//        List<String> lista = new ArrayList();
//        lista.add("Friedrich Nietsche: Jumala on kuollut");
//        lista.add("Jumala: Friedrich Nietsche on kuollut");
//        lista.add("Chuck Norris: Friedrich Nietsche ja Jumala on kuollut");
//        lista.add("Horst Schimanski: Minä olen Jumala");
        return a;
    }

}
