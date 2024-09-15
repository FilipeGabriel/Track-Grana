package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.Account;
import io.filipegabriel.track_grana_api.entities.User;
import io.filipegabriel.track_grana_api.repositories.AccountRepository;
import io.filipegabriel.track_grana_api.repositories.UserRepository;
import io.filipegabriel.track_grana_api.resources.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserRepository userRepository;

//Get

    public Account findById(Long id){
        Account user = repository.findById(id).orElseThrow(NoSuchElementException::new);;
        user.getInvoices().sort(Comparator.comparing(invoices -> invoices.getMonthInvoice().getMonthYear()));       //Makes invoices appear in order by invoice date
        return user;
    }

//Post

    public Account insert(AccountDTO account){
        Account newAccount = new Account();
        User user = userRepository.findById(account.getUserId()).orElseThrow(NoSuchElementException::new);

        LocalDate birthDate = LocalDate.parse(account.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        newAccount.setName(account.getName());
        newAccount.setCpf(account.getCpf());
        newAccount.setTelephone(account.getTelephone());
        newAccount.setBirthDate(birthDate);
        newAccount.setUser(user);

        user.setAccount(newAccount);

        repository.save(newAccount);
        userRepository.save(user);

        return newAccount;
    }

//Delete

    public void delete(Long id){
        repository.deleteById(id);
    }

//Put

    public Account update(Long id, AccountDTO newAccount){
        Account oldAccount = repository.findById(id).orElseThrow(NoSuchElementException::new);
        updateAccount(oldAccount, newAccount);

        repository.save(oldAccount);
        return oldAccount;
    }

    public void updateAccount(Account oldAccount, AccountDTO newAccount){
        LocalDate birthDate = LocalDate.parse(newAccount.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        oldAccount.setName(newAccount.getName());
        oldAccount.setTelephone(newAccount.getTelephone());
        oldAccount.setBirthDate(birthDate);
    }

}
