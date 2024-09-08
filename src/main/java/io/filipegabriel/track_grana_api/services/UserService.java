package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.User;
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

    public User findById(Long id){
        Optional<User> user = repository.findById(id);
        return user.get();
    }

//Post

    public User insert(UserDTO user){
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setRegistrationDate(LocalDateTime.now());

        repository.save(newUser);

        return newUser;
    }

//Put

    public User update(Long id, UserDTO newUser){
        User oldUser = repository.findById(id).orElseThrow(NoSuchElementException::new);
        updateUser(oldUser, newUser);

        repository.save(oldUser);
        return oldUser;
    }

    public void updateUser(User oldUser, UserDTO newUser){
        oldUser.setPassword(newUser.getPassword());
        oldUser.setEmail(newUser.getEmail());
    }

}
