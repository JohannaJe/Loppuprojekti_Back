package fi.academy.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestKontrolleri {


    @GetMapping("/testi")
    public String haesanonnat() {
        return "Friedrich Nietsche: Jumala on kuollut, Jumala: Friedrich Nietsche on kuollut, Chuck Norris: Friedrich Nietsche ja Jumala on kuollut, Horst Schimanski: Minä olen Jumala";
    }


}
