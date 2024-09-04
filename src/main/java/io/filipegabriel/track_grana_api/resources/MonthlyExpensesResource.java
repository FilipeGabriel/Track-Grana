package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.MonthlyExpenses;
import io.filipegabriel.track_grana_api.services.MonthlyExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/api/monthly-expenses")
public class MonthlyExpensesResource {

    @Autowired
    private MonthlyExpensesService service;

    @GetMapping("/{id}")
    private ResponseEntity<MonthlyExpenses> findById(@PathVariable Long id){
        MonthlyExpenses monthlyExpenses = service.findById(id);
        return ResponseEntity.ok().body(monthlyExpenses);
    }

    @PostMapping
    private ResponseEntity<MonthlyExpenses> insert(){
        MonthlyExpenses monthlyExpenses = service.insert();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(monthlyExpenses.getId()).toUri();
        return ResponseEntity.created(uri).body(monthlyExpenses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
