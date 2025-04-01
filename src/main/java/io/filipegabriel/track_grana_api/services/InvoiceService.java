package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.*;
import io.filipegabriel.track_grana_api.repositories.AccountRepository;
import io.filipegabriel.track_grana_api.repositories.InvoiceRepository;
import io.filipegabriel.track_grana_api.resources.dto.InvoiceDTO;
import io.filipegabriel.track_grana_api.resources.dto.MonthInvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Invoice> findAll(Long id) {
        Account account = accountService.findById(id);
        List<Invoice> invoices = new ArrayList<>();

        invoices.addAll(account.getInvoices());
        return invoices;
    }

    public List<Invoice> findAllInYear(Long id, String year){
        Account account = accountService.findById(id);
        List<Invoice> yearInvoices = new ArrayList<>();

        for (Invoice invoice : account.getInvoices()){
            if (invoice.getMonthInvoice().getMonthYear().getYear() == Integer.parseInt(year)){
                yearInvoices.add(invoice);
            }
        }
        if (!yearInvoices.isEmpty()){
            return yearInvoices;
        } else {
            throw new IllegalArgumentException("Não tem Faturas para o ano informado!");
        }
    }

    public Invoice findById(Long id){
        Optional<Invoice> invoice = repository.findById(id);
        return invoice.get();
    }

//Post

    public Invoice insert(Long id, MonthInvoiceDTO monthInvoiceDTO){
        Invoice invoice = new Invoice();
        MonthlyContracts monthlyContracts = monthlyContractsService.insert();
        MonthlyExpenses monthlyExpenses = monthlyExpensesService.insert();
        MonthInvoice monthInvoice = monthInvoiceService.insert(monthInvoiceDTO);
        Account account = accountService.findById(id);

        invoice.setTotalInvoiceValue(0.0);
        invoice.setTotalPaid(false);
        invoice.setMonthInvoice(monthInvoice);
        invoice.setMonthlyContracts(monthlyContracts);
        invoice.setMonthlyExpenses(monthlyExpenses);
        invoice.setAccount(account);

        for (Invoice i : account.getInvoices()){
            if (
                    monthInvoice.getMonthYear().getMonth() == i.getMonthInvoice().getMonthYear().getMonth() &&
                            monthInvoice.getMonthYear().getYear() == i.getMonthInvoice().getMonthYear().getYear()
            ){
                throw new IllegalArgumentException("Já existe uma fatura para esse período");
            }
        }

        if (!account.getSpentTypes().isEmpty()) {  // os ifs do invoiceservice e spenttypeservice estão funcionando, falta ajustar a regra para atrelar uma à outra que estão comentadas

            List<SpentType> spentTypes = new ArrayList<>();
            for (SpentType st : account.getSpentTypes()) {
                spentTypes.add(st);
                st.getInvoices().add(invoice); // Atualiza cada SpentType para incluir a Invoice
            }
            invoice.setSpentTypes(spentTypes); // Define os SpentTypes na Invoice

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
        } else {
            throw new IllegalArgumentException("Não há tipo de gasto cadastrado");
        }

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
