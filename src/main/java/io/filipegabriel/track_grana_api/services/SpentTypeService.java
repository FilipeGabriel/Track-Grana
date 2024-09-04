package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.SpentType;
import io.filipegabriel.track_grana_api.repositories.SpentTypeRepository;
import io.filipegabriel.track_grana_api.resources.dto.SpentTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SpentTypeService {

    @Autowired
    private SpentTypeRepository repository;

//Get

    public List<SpentType> findAll(){
        return repository.findAll();
    }

    public SpentType findById(Long id){
        Optional<SpentType> spentType = repository.findById(id);
        return spentType.get();
    }

//Post

    public SpentType insert(SpentTypeDTO spentTypeDTO){
        SpentType spentType = new SpentType();
        spentType.setName(spentTypeDTO.getName());
        spentType.setTotalBankValue(spentTypeDTO.getTotalBankValue());
        spentType.setPaid(false);

        repository.save(spentType);
        return spentType;
    }

//Delete

    public void delete(Long id){
        repository.deleteById(id);
    }

//Put

    public SpentType update(Long id, SpentTypeDTO newSpentType){
        SpentType oldSpentType = repository.findById(id).orElseThrow(NoSuchElementException::new);
        updateSpentType(oldSpentType, newSpentType);
        return repository.save(oldSpentType);
    }

    public void updateSpentType(SpentType oldSpentType, SpentTypeDTO newSpentType){
        oldSpentType.setName(newSpentType.getName());
        oldSpentType.setPaid(newSpentType.getPaid());
    }

}
