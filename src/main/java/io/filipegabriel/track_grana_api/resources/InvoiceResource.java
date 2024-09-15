package io.filipegabriel.track_grana_api.resources;

import io.filipegabriel.track_grana_api.entities.Invoice;
import io.filipegabriel.track_grana_api.resources.dto.MonthInvoiceDTO;
import io.filipegabriel.track_grana_api.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/invoices")
public class InvoiceResource {

    @Autowired
    private InvoiceService service;

//Get

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable Long id){
        Invoice invoice = service.findById(id);
        return ResponseEntity.ok().body(invoice);
    }

//Post

    @PostMapping("/{id}")
    public ResponseEntity<Invoice> insertNextInvoices(@PathVariable Long id, @RequestBody MonthInvoiceDTO monthInvoiceDTO){
        Invoice invoice = service.insert(id, monthInvoiceDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(uri).body(invoice);
    }

//Put

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(@PathVariable Long id, @RequestBody Invoice newInvoice){
        Invoice invoice = service.update(id, newInvoice);
        return ResponseEntity.ok().body(invoice);
    }


}
