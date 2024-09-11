package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.MonthInvoice;
import io.filipegabriel.track_grana_api.resources.dto.MonthInvoiceDTO;
import io.filipegabriel.track_grana_api.services.MonthInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/month-invoices")
public class MonthInvoiceResource {

    @Autowired
    private MonthInvoiceService service;

//Get

    @GetMapping("/{id}")
    public ResponseEntity<MonthInvoice> findById(@PathVariable Long id){
        MonthInvoice monthInvoice = service.findById(id);
        return ResponseEntity.ok().body(monthInvoice);
    }

    @GetMapping
    public ResponseEntity<List<MonthInvoice>> findAll(){
        List<MonthInvoice> monthInvoices = service.findAll();
        return ResponseEntity.ok().body(monthInvoices);
    }

//Post

    @PostMapping("/first-time")
    public ResponseEntity<MonthInvoice> insertFirstTime(){
        MonthInvoice monthInvoice = service.insertFirstTime();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(monthInvoice.getId()).toUri();
        return ResponseEntity.created(uri).body(monthInvoice);
    }

    @PostMapping
    public ResponseEntity<MonthInvoice> insert(@RequestBody MonthInvoiceDTO monthInvoiceDTO){
        MonthInvoice monthInvoice = service.insert(monthInvoiceDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(monthInvoice.getId()).toUri();
        return ResponseEntity.created(uri).body(monthInvoice);
    }

}
