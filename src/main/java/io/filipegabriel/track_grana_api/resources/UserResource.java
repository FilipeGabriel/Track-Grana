package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.User;
import io.filipegabriel.track_grana_api.resources.dto.UserDTO;
import io.filipegabriel.track_grana_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/api/users")
public class UserResource {

    @Autowired
    private UserService service;

//Get

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

//Post

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO user){
        User newUser = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//Put

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO newUser){
        User user = service.update(id, newUser);
        return ResponseEntity.ok().body(user);
    }

}
