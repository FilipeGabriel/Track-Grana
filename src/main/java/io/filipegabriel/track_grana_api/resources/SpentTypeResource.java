package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.SpentType;
import io.filipegabriel.track_grana_api.resources.dto.SpentTypeDTO;
import io.filipegabriel.track_grana_api.services.SpentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/spents-type")
public class SpentTypeResource {

    @Autowired
    private SpentTypeService service;

    @GetMapping
    private ResponseEntity<List<SpentType>> findAll(){
        List<SpentType> spentType = service.findAll();
        return ResponseEntity.ok().body(spentType);
    }

    @GetMapping("/{id}")
    private ResponseEntity<SpentType> findById(@PathVariable Long id){
        SpentType spentType = service.findById(id);
        return ResponseEntity.ok().body(spentType);
    }

    @PostMapping
    private ResponseEntity<SpentType> insert(@RequestBody SpentTypeDTO spentTypeDTO){
        SpentType newSpentType = service.insert(spentTypeDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSpentType.getId()).toUri();
        return ResponseEntity.created(uri).body(newSpentType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpentType> update(@PathVariable Long id, @RequestBody SpentTypeDTO newSpentType){
        SpentType spentType = service.update(id, newSpentType);
        return ResponseEntity.ok().body(spentType);
    }


}
