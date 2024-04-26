package com.example.springcacheredis.controller;

import com.example.springcacheredis.entity.Invoice;
import com.example.springcacheredis.service.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;

    }

    @PostMapping("save")
    public Invoice saveInvoice(@RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @PutMapping("update/{id}")
    public Invoice updateInvoice(@PathVariable Long id, @RequestBody Invoice newInvoice) {
        return invoiceService.updateInvoice(id, newInvoice);
    }

    @DeleteMapping("delete/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }

    @GetMapping("get/{id}")
    public Invoice getOneInvoice(@PathVariable Long id) {
        return invoiceService.getOneInvoice(id);
    }

    @GetMapping("get")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("page")
    public Page<Invoice> pageInvoices(@RequestParam(defaultValue = "0",required = false) int page, @RequestParam(defaultValue = "50",required = false) int pageSize) {
        return invoiceService.pageInvoices(page, pageSize);
    }
}
