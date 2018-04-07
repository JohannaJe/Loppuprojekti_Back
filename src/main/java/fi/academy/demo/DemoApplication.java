package fi.academy.demo;

import fi.academy.demo.model.Role;
import fi.academy.demo.model.RoleName;
import fi.academy.demo.model.User;
import fi.academy.demo.repository.RoleRepository;
import fi.academy.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner luoKayttaja(RoleRepository RoleRepo, UserRepository UserRepo) {
        return (args -> {

            //Pakollinen databasealustus
            Role userRooli = new Role(RoleName.ROLE_USER);
            Role adminRooli = new Role(RoleName.ROLE_ADMIN);
            RoleRepo.save(userRooli);
            RoleRepo.save(adminRooli);
            //Pakollinen databasealustus päättyy

            //Alustetaan testikäyttäjä
            User testikayttaja = new User("Teppo Testi", "testitunnus", "teppo@testaaja.fi", passwordEncoder.encode("salasana"));
            UserRepo.save(testikayttaja);


        });
    }

}
