package fi.academy.demo.controllers;

import fi.academy.demo.entities.User;
import fi.academy.demo.repositories.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    private UserRepository userRepository;

    @Autowired
    public SecurityController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User rekisteroi(@RequestBody User uusi, Authentication authentication){
//        uusi.setId(3);

        uusi.setActive(1);
        userRepository.save(uusi);

        return uusi;
    }
}
