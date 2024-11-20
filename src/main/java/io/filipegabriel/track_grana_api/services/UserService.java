package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.Users;
import io.filipegabriel.track_grana_api.repositories.UserRepository;
import io.filipegabriel.track_grana_api.resources.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
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
        oldUser.setPassword(newUser.getPassword());
        oldUser.setEmail(newUser.getEmail());
    }

}
