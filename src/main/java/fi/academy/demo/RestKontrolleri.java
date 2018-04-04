package fi.academy.demo;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api")
public class RestKontrolleri {





    @GetMapping("/testi")
    public List<Feedi> naytaLista() {
        Lukija lukija = new Lukija();
        List feediLista= lukija.koostaLista("https://www.hs.fi/rss/urheilu.xml");
        return feediLista;
    }

}
