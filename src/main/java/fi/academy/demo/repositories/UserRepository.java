package fi.academy.demo.repositories;

import fi.academy.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}
