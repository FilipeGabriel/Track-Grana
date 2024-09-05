package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.MonthlyContracts;
import io.filipegabriel.track_grana_api.entities.MonthlyExpenses;
import io.filipegabriel.track_grana_api.repositories.MonthlyContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonthlyContractsService {

    @Autowired
    private MonthlyContractsRepository repository;

//Get

    public MonthlyContracts findById(Long id){
        Optional<MonthlyContracts> monthlyContracts = repository.findById(id);
        return monthlyContracts.get();
    }

//Post

    public MonthlyContracts insert(){
        MonthlyContracts monthlyContracts = new MonthlyContracts();
        monthlyContracts.setTotalMonthlyContractsValue(0.0);
        repository.save(monthlyContracts);

        return monthlyContracts;
    }

    public MonthlyContracts insert(MonthlyContracts monthlyContracts){
        repository.save(monthlyContracts);
        return monthlyContracts;
    }

//Delete

    public void delete(Long id){
        repository.deleteById(id);
    }

}
