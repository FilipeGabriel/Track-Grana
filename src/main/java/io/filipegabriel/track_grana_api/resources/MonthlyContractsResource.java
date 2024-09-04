package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.MonthlyContracts;
import io.filipegabriel.track_grana_api.services.MonthlyContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/api/monthly-contracts")
public class MonthlyContractsResource {

    @Autowired
    private MonthlyContractsService service;

    @GetMapping("/{id}")
    private ResponseEntity<MonthlyContracts> findById(@PathVariable Long id){
        MonthlyContracts monthlyContracts = service.findById(id);
        return ResponseEntity.ok().body(monthlyContracts);
    }

    @PostMapping
    private ResponseEntity<MonthlyContracts> insert(){
        MonthlyContracts monthlyContracts = service.insert();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(monthlyContracts.getId()).toUri();
        return ResponseEntity.created(uri).body(monthlyContracts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
