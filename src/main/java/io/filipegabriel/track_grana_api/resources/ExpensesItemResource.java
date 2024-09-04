package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.ExpensesItem;
import io.filipegabriel.track_grana_api.resources.dto.ExpensesItemDTO;
import io.filipegabriel.track_grana_api.services.ExpensesItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/expenses-items")
public class ExpensesItemResource {

    @Autowired
    private ExpensesItemService service;

    @GetMapping
    private ResponseEntity<List<ExpensesItem>> findAll(){
        List<ExpensesItem> expensesItem = service.findAll();
        return ResponseEntity.ok().body(expensesItem);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ExpensesItem> findById(@PathVariable Long id){
        ExpensesItem expensesItem = service.findById(id);
        return ResponseEntity.ok().body(expensesItem);
    }

    @PostMapping
    private ResponseEntity<ExpensesItem> insert(@RequestBody ExpensesItemDTO expensesItemDTO){
        ExpensesItem newExpensesItem = service.insert(expensesItemDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newExpensesItem.getId()).toUri();
        return ResponseEntity.created(uri).body(newExpensesItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpensesItem> update(@PathVariable Long id, @RequestBody ExpensesItemDTO newExpensesItem){
        ExpensesItem expensesItem = service.update(id, newExpensesItem);
        return ResponseEntity.ok().body(expensesItem);
    }

}
