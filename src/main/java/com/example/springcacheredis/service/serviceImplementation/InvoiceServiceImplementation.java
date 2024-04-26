package com.example.springcacheredis.service.serviceImplementation;

import com.example.springcacheredis.entity.Invoice;
import com.example.springcacheredis.exception.InvoiceNotFoundException;
import com.example.springcacheredis.repository.InvoiceRepository;
import com.example.springcacheredis.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImplementation implements InvoiceService {


    private final InvoiceRepository invoiceRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public InvoiceServiceImplementation(InvoiceRepository invoiceRepository, ModelMapper modelMapper) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    /** THIS NOT MAKE ANY COMPILATION ERROR since it's different annotation*/
//    @CacheEvict(value = "Invoice")
//    @CachePut(value = "Invoice", key = "#id")
    @Caching(evict = @CacheEvict(value = "Invoices", allEntries = true), put = @CachePut(value = "Invoice", key = "#id", condition = "#newInvoice.id>=10", unless = "#newInvoice.id<=1000"))
    public Invoice updateInvoice(Long id, Invoice newInvoice) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not Found"));
        newInvoice.setId(id);
        if (newInvoice.getAmount() != null) invoice.setAmount(newInvoice.getAmount());

        if (newInvoice.getName() != null && !newInvoice.getName().isEmpty()) invoice.setName(newInvoice.getName());


        invoiceRepository.saveAndFlush(invoice);
        return invoice;

    }

    @Override
    /** this is for multiple caching*/
    @Caching(evict = {@CacheEvict(value = "Invoice"), @CacheEvict(value = "Invoice", key = "#id")})
//    @CacheEvict(value = "Invoice", key = "#id")
    public void deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not Found"));
        invoiceRepository.delete(invoice);

    }

    @Override
    @Cacheable(value = "Invoice", key = "#id", condition = "#result.id >= 0", unless = "#result.id <= 1000")
    public Invoice getOneInvoice(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not Found"));
    }

    @Override
    @Cacheable(value = "Invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    @Cacheable(value = "Invoices")
    public Page<Invoice> pageInvoices(int page, int pageSize) {
        return invoiceRepository.findAll(PageRequest.of(page, pageSize));
    }
}
