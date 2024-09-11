package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.MonthInvoice;
import io.filipegabriel.track_grana_api.entities.MonthlyContracts;
import io.filipegabriel.track_grana_api.repositories.MonthInvoiceRepository;
import io.filipegabriel.track_grana_api.resources.dto.MonthInvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MonthInvoiceService {

    @Autowired
    private MonthInvoiceRepository repository;

//Get

    public MonthInvoice findById(Long id){
        Optional<MonthInvoice> monthInvoice = repository.findById(id);
        return monthInvoice.get();
    }

    public List<MonthInvoice> findAll(){
        return repository.findAll();
    }

//Post

    public MonthInvoice insertFirstTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = LocalDate.now().format(formatter);
        LocalDate monthYear = LocalDate.parse(formattedDate, formatter);

        Month month = monthYear.getMonth();
        String monthName = month.name();

        MonthInvoice monthInvoice = new MonthInvoice();
        monthInvoice.setMonthName(monthName);
        monthInvoice.setMonthYear(monthYear);

        return repository.save(monthInvoice);
    }

    public MonthInvoice insert(MonthInvoiceDTO monthInvoiceDTO){
        LocalDate monthYear = LocalDate.parse(monthInvoiceDTO.getMonthYear(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Month month = monthYear.getMonth();
        String monthName = month.name();

        MonthInvoice monthInvoice = new MonthInvoice();
        monthInvoice.setMonthName(monthName);
        monthInvoice.setMonthYear(monthYear);

        return repository.save(monthInvoice);
    }

    public MonthInvoice insert(MonthInvoice monthInvoice){
        repository.save(monthInvoice);
        return monthInvoice;
    }

}
