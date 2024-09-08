package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.Account;
import io.filipegabriel.track_grana_api.resources.dto.AccountDTO;
import io.filipegabriel.track_grana_api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/api/accounts")
public class AccountResource {

    @Autowired
    private AccountService service;

//Get

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id){
        Account account = service.findById(id);
        return ResponseEntity.ok().body(account);
    }

//Post

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody AccountDTO account){
        Account newAccount = service.insert(account);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAccount.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//Delete

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

//Put

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody AccountDTO newAccount){
        Account account = service.update(id, newAccount);
        return ResponseEntity.ok().body(account);
    }

}
