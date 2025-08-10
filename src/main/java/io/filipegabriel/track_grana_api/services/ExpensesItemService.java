package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.Invoice;
import io.filipegabriel.track_grana_api.entities.MonthlyExpenses;
import io.filipegabriel.track_grana_api.entities.SpentType;
import io.filipegabriel.track_grana_api.entities.ExpensesItem;
import io.filipegabriel.track_grana_api.repositories.MonthlyExpensesRepository;
import io.filipegabriel.track_grana_api.repositories.SpentTypeRepository;
import io.filipegabriel.track_grana_api.repositories.ExpensesItemRepository;
import io.filipegabriel.track_grana_api.resources.dto.ExpensesItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExpensesItemService {

    @Autowired
    private ExpensesItemRepository repository;

    @Autowired
    private SpentTypeRepository spentTypeRepository;

    @Autowired
    private MonthlyExpensesRepository monthlyExpensesRepository;

//Get

    public List<ExpensesItem> findAll(){
        return repository.findAll();
    }

    public ExpensesItem findById(Long id){
        Optional<ExpensesItem> expensesItem = repository.findById(id);
        return expensesItem.get();
    }

//Post

    public ExpensesItem insert(ExpensesItemDTO expensesItemDTO){
        ExpensesItem expensesItem = new ExpensesItem();
        SpentType spentType = spentTypeRepository.findById(expensesItemDTO.getSpentTypeId()).orElseThrow(NoSuchElementException::new);
        MonthlyExpenses monthlyExpenses = monthlyExpensesRepository.findById(expensesItemDTO.getMonthlyExpensesId()).orElseThrow(NoSuchElementException::new);
        Invoice invoice = monthlyExpenses.getInvoice();

        expensesItem.setDescription(expensesItemDTO.getDescription());
        expensesItem.setInstallment(expensesItemDTO.getInstallment());
        expensesItem.setItemValue(expensesItemDTO.getItemValue());
        expensesItem.setSpentType(spentType);
        expensesItem.setMonthlyExpenses(monthlyExpenses);

        repository.save(expensesItem);

        spentType.getExpensesItems().add(expensesItem);
        monthlyExpenses.getExpensesItems().add(expensesItem);

        Double expensesValue = monthlyExpenses.getTotalMonthlyExpensesValue() + expensesItem.getItemValue();
        monthlyExpenses.setTotalMonthlyExpensesValue(expensesValue);

        invoice.setTotalInvoiceValue(invoice.getMonthlyExpenses().getTotalMonthlyExpensesValue() + spentType.getAccount().getMonthlyContracts().getTotalMonthlyContractsValue());

        spentTypeRepository.save(spentType);
        monthlyExpensesRepository.save(monthlyExpenses);

        return expensesItem;
    }

//Delete

    public void delete(Long id){
        repository.deleteById(id);
    }

//Put

    public ExpensesItem update(Long id, ExpensesItemDTO newExpensesItem){
        ExpensesItem oldExpensesItem = repository.findById(id).orElseThrow(NoSuchElementException::new);
        updateMonthlyItem(oldExpensesItem, newExpensesItem);
        return repository.save(oldExpensesItem);
    }

    public void updateMonthlyItem(ExpensesItem oldExpensesItem, ExpensesItemDTO newExpensesItem){
        oldExpensesItem.setDescription(newExpensesItem.getDescription());
        oldExpensesItem.setItemValue(newExpensesItem.getItemValue());
        SpentType newSpentType = spentTypeRepository.findById(newExpensesItem.getSpentTypeId())
                .orElseThrow(NoSuchElementException::new);
        oldExpensesItem.setSpentType(newSpentType);
    }

}
