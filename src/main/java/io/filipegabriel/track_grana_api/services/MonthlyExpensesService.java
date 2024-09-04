package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.MonthlyExpenses;
import io.filipegabriel.track_grana_api.repositories.MonthlyExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonthlyExpensesService {

    @Autowired
    private MonthlyExpensesRepository repository;

//Get

    public MonthlyExpenses findById(Long id){
        Optional<MonthlyExpenses> monthlySpent = repository.findById(id);
        return monthlySpent.get();
    }

//Post

    public MonthlyExpenses insert(){
        MonthlyExpenses monthlyExpenses = new MonthlyExpenses();
        monthlyExpenses.setTotalMonthlyExpensesValue(0.0);
        repository.save(monthlyExpenses);

        return monthlyExpenses;
    }

//Delete

    public void delete(Long id){
        repository.deleteById(id);
    }

}
