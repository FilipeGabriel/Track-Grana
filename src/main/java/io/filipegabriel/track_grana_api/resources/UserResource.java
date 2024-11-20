package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.Users;
import io.filipegabriel.track_grana_api.resources.dto.UserDTO;
import io.filipegabriel.track_grana_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/v1/api/users")
public class UserResource {

    @Autowired
    private UserService service;

//Get

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id){
        Users user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

//Post

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO user){
        Users newUser = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//Put

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody UserDTO newUser){
        Users user = service.update(id, newUser);
        return ResponseEntity.ok().body(user);
    }

}
