package fi.academy.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestKontrolleri {

    private TestiRepository testiRepository;

    public RestKontrolleri(@Autowired TestiRepository testiRepository) {
        this.testiRepository = testiRepository;
    }

    @GetMapping("/testi")
    public Iterable<TestiLista> haesanonnat(SpringDataWebProperties.Pageable pageable) {
        return testiRepository.findAll();
    }


}
