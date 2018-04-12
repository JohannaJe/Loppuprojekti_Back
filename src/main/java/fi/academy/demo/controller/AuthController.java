package fi.academy.demo.controller;

import fi.academy.demo.exception.AppException;
import fi.academy.demo.model.Role;
import fi.academy.demo.model.RoleName;
import fi.academy.demo.model.User;
import fi.academy.demo.payload.*;
import fi.academy.demo.repository.RoleRepository;
import fi.academy.demo.repository.UserRepository;
import fi.academy.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        String muokattavaData = signUpRequest.getData();
        int eka = muokattavaData.indexOf("[");
        String likanenKikka = muokattavaData.substring(eka);
        likanenKikka = likanenKikka.replace("[", "");
        int vika = likanenKikka.indexOf("]");
        likanenKikka = likanenKikka.replace("\"", " ");
        likanenKikka = likanenKikka.substring(0, vika);


        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), likanenKikka) ;

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserData(@Valid @RequestBody UpdateRequest updateRequest) {

        // Creating user's account
        String muokattavaData = updateRequest.getData();
        int eka = muokattavaData.indexOf("[");
        String likanenKikka = muokattavaData.substring(eka);
        likanenKikka = likanenKikka.replace("[", "");
        int vika = likanenKikka.indexOf("]");
        likanenKikka = likanenKikka.replace("\"", " ");
        likanenKikka = likanenKikka.substring(0, vika);

        Optional<User> haeKäyttäjä = userRepository.findByUsername(updateRequest.getUsername());
        haeKäyttäjä.get().setData(likanenKikka);
        userRepository.save(haeKäyttäjä.get());


//        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
//                signUpRequest.getEmail(), signUpRequest.getPassword(), likanenKikka) ;
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(Collections.singleton(userRole));
////
//        User result = userRepository.save(user);
////
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/users/{username}")
//                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.ok().build();
    }

}
