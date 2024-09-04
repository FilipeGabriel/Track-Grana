package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.ContractItem;
import io.filipegabriel.track_grana_api.resources.dto.ContractItemDTO;
import io.filipegabriel.track_grana_api.services.ContractItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/contract-items")
public class ContractItemResource {

    @Autowired
    private ContractItemService service;

    @GetMapping
    private ResponseEntity<List<ContractItem>> findAll(){
        List<ContractItem> contractItem = service.findAll();
        return ResponseEntity.ok().body(contractItem);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ContractItem> findById(@PathVariable Long id){
        ContractItem contractItem = service.findById(id);
        return ResponseEntity.ok().body(contractItem);
    }

    @PostMapping
    private ResponseEntity<ContractItem> insert(@RequestBody ContractItemDTO contractItemDTO){
        ContractItem newContractItem = service.insert(contractItemDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newContractItem.getId()).toUri();
        return ResponseEntity.created(uri).body(newContractItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractItem> update(@PathVariable Long id, @RequestBody ContractItemDTO newContractItem){
        ContractItem contractItem = service.update(id, newContractItem);
        return ResponseEntity.ok().body(contractItem);
    }

}
