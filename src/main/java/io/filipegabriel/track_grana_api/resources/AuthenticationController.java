package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.config.security.TokenService;
import io.filipegabriel.track_grana_api.entities.Users;
import io.filipegabriel.track_grana_api.repositories.UserRepository;
import io.filipegabriel.track_grana_api.resources.records.AuthenticationDTO;
import io.filipegabriel.track_grana_api.resources.records.LoginResponseDTO;
import io.filipegabriel.track_grana_api.resources.records.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        Users user = (Users) userRepository.findByEmail(data.email());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());
        var userId = user.getId();

        return ResponseEntity.ok(new LoginResponseDTO(token, userId));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        if (userRepository.existsByEmail(data.email())) {
            throw new VerifyError("Já existe uma conta cadastrada com o Email informado.");
        }
        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users();

        newUser.setEmail(data.email());
        newUser.setPassword(encryptedPassword);
        newUser.setRegistrationDate(LocalDateTime.now());

        userRepository.save(newUser);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

}
