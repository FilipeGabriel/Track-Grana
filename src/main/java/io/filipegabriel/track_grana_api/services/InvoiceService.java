package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.*;
import io.filipegabriel.track_grana_api.repositories.AccountRepository;
import io.filipegabriel.track_grana_api.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MonthInvoiceService monthInvoiceService;

    @Autowired
    private MonthlyContractsService monthlyContractsService;

    @Autowired
    private MonthlyExpensesService monthlyExpensesService;

    @Autowired
    private AccountService accountService;

//Get

    public Invoice findById(Long id){
        Optional<Invoice> invoice = repository.findById(id);
        return invoice.get();
    }

    public List<Invoice> findAll(){
        return repository.findAll();
    }

//Post

    public Invoice insertFirstInvoice(Long id){
        Invoice invoice = new Invoice();
        MonthlyContracts monthlyContracts = monthlyContractsService.insert();
        MonthlyExpenses monthlyExpenses = monthlyExpensesService.insert();
        MonthInvoice monthInvoice = monthInvoiceService.insertFirstTime();
        Account account = accountService.findById(id);

        invoice.setTotalInvoiceValue(0.0);
        invoice.setTotalPaid(false);
        invoice.setMonthInvoice(monthInvoice);
        invoice.setMonthlyContracts(monthlyContracts);
        invoice.setMonthlyExpenses(monthlyExpenses);
        invoice.setAccount(account);

        repository.save(invoice);

        monthInvoice.setInvoice(invoice);
        monthlyContracts.setInvoice(invoice);
        monthlyExpenses.setInvoice(invoice);
        account.getInvoices().add(invoice);

        monthInvoiceService.insert(monthInvoice);
        monthlyContractsService.insert(monthlyContracts);
        monthlyExpensesService.insert(monthlyExpenses);
        accountRepository.save(account);

        return invoice;
    }

//Put

    public Invoice update(Long id, Invoice newInvoice){
        Invoice oldInvoice = repository.findById(id).orElseThrow(NoSuchElementException::new);
        updateInvoice(oldInvoice, newInvoice);
        return repository.save(oldInvoice);
    }

    public void updateInvoice(Invoice oldInvoice, Invoice newInvoice){
        oldInvoice.setTotalPaid(newInvoice.getTotalPaid());
    }

}
