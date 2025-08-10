package io.filipegabriel.track_grana_api.services;

import io.filipegabriel.track_grana_api.entities.ContractItem;
import io.filipegabriel.track_grana_api.entities.Invoice;
import io.filipegabriel.track_grana_api.entities.MonthlyContracts;
import io.filipegabriel.track_grana_api.entities.SpentType;
import io.filipegabriel.track_grana_api.repositories.ContractItemRepository;
import io.filipegabriel.track_grana_api.repositories.InvoiceRepository;
import io.filipegabriel.track_grana_api.repositories.MonthlyContractsRepository;
import io.filipegabriel.track_grana_api.repositories.SpentTypeRepository;
import io.filipegabriel.track_grana_api.resources.dto.ContractItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContractItemService {

    @Autowired
    private ContractItemRepository repository;

    @Autowired
    private SpentTypeRepository spentTypeRepository;

    @Autowired
    private MonthlyContractsRepository monthlyContractsRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

//Get

    public List<ContractItem> findAll(){
        return repository.findAll();
    }

    public ContractItem findById(Long id){
        Optional<ContractItem> contractItem = repository.findById(id);
        return contractItem.get();
    }

//Post

    public ContractItem insert(ContractItemDTO contractItemDTO){
        ContractItem contractItem = new ContractItem();
        SpentType spentType = spentTypeRepository.findById(contractItemDTO.getSpentTypeId()).orElseThrow(NoSuchElementException::new);
        MonthlyContracts monthlyContracts = monthlyContractsRepository.findById(contractItemDTO.getMonthlyContractsId()).orElseThrow(NoSuchElementException::new);
        Invoice invoice = invoiceRepository.findById(contractItemDTO.getInvoiceId()).orElseThrow(NoSuchElementException::new);      //Lembrar de adicionar o id do invoice atual no post da requisição

        LocalDate contractItemDate = LocalDate.parse(contractItemDTO.getEndDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        contractItem.setDescription(contractItemDTO.getDescription());
        contractItem.setItemValue(contractItemDTO.getItemValue());
        contractItem.setEndDate(contractItemDate);
        contractItem.setPaid(false);
        contractItem.setSpentType(spentType);
        contractItem.setMonthlyContracts(monthlyContracts);

        repository.save(contractItem);

        spentType.getContractItems().add(contractItem);
        monthlyContracts.getContractItems().add(contractItem);

        Double itemValue = monthlyContracts.getTotalMonthlyContractsValue() + contractItem.getItemValue();
        monthlyContracts.setTotalMonthlyContractsValue(itemValue);

        invoice.setTotalInvoiceValue(spentType.getAccount().getMonthlyContracts().getTotalMonthlyContractsValue() + invoice.getMonthlyExpenses().getTotalMonthlyExpensesValue());

        spentTypeRepository.save(spentType);
        monthlyContractsRepository.save(monthlyContracts);

        return contractItem;
    }

//Delete

    public void delete(Long id){
        repository.deleteById(id);
    }

//Put

    public ContractItem update(Long id, ContractItemDTO newContractItem){
        ContractItem oldContractItem = repository.findById(id).orElseThrow(NoSuchElementException::new);
        updateContractItem(oldContractItem, newContractItem);
        return repository.save(oldContractItem);
    }

    public void updateContractItem(ContractItem oldContractItem, ContractItemDTO newContractItem){
        LocalDate contractItemDate = LocalDate.parse(newContractItem.getEndDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        oldContractItem.setDescription(newContractItem.getDescription());
        oldContractItem.setItemValue(newContractItem.getItemValue());
        oldContractItem.setEndDate(contractItemDate);
        SpentType newSpentType = spentTypeRepository.findById(newContractItem.getSpentTypeId())
                .orElseThrow(NoSuchElementException::new);
        oldContractItem.setSpentType(newSpentType);
    }

}
