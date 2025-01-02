package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.Users;
import io.filipegabriel.track_grana_api.repositories.UserRepository;
import io.filipegabriel.track_grana_api.resources.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

//Get

    public Users findById(Long id){
        Optional<Users> user = repository.findById(id);
        return user.get();
    }

//Post

    public Users insert(UserDTO user){
        Users newUser = new Users();
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        newUser.setEmail(user.getEmail());
        newUser.setPassword(encryptedPassword);
        newUser.setRegistrationDate(LocalDateTime.now());

        repository.save(newUser);

        return newUser;
    }

//Put

    public Users update(Long id, UserDTO newUser){
        Users oldUser = repository.findById(id).orElseThrow(NoSuchElementException::new);
        updateUser(oldUser, newUser);

        repository.save(oldUser);
        return oldUser;
    }

    public void updateUser(Users oldUser, UserDTO newUser){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(newUser.getPassword(), oldUser.getPassword())) {
            oldUser.setPassword(passwordEncoder.encode(newUser.getNewPassword()));
        } else {
            throw new IllegalArgumentException("Senha incorreta! Tente novamente.");
        }
    }
}
