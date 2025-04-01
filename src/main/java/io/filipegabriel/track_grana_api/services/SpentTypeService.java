package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.Account;
import io.filipegabriel.track_grana_api.entities.Invoice;
import io.filipegabriel.track_grana_api.entities.SpentType;
import io.filipegabriel.track_grana_api.repositories.AccountRepository;
import io.filipegabriel.track_grana_api.repositories.InvoiceRepository;
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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

//Get

    public List<SpentType> findAll(Long id){
        Account account = accountRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return account.getSpentTypes();
    }

    public SpentType findById(Long id){
        Optional<SpentType> spentType = repository.findById(id);
        return spentType.get();
    }

//Post

    public SpentType insert(SpentTypeDTO spentTypeDTO){
        SpentType spentType = new SpentType();
        Account account = accountRepository.findById(spentTypeDTO.getAccountId()).orElseThrow(NoSuchElementException::new);
        spentType.setName(spentTypeDTO.getName());
        spentType.setColor(spentTypeDTO.getColor());
        spentType.setTotalBankValue(spentTypeDTO.getTotalBankValue());
        spentType.setPaid(false);
        spentType.setAccount(account);

        if (!account.getInvoices().isEmpty()) {
            for (Invoice i : account.getInvoices()) {
                if (!i.getSpentTypes().contains(spentType)) {  // Evita adicionar duplicado
                    i.getSpentTypes().add(spentType);
                }
            }
        }

        account.getSpentTypes().add(spentType);

        repository.save(spentType);
        accountRepository.save(account);
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
        oldSpentType.setColor(newSpentType.getColor());
        oldSpentType.setPaid(newSpentType.getPaid());
    }

}
