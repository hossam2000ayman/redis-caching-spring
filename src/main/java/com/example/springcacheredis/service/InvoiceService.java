package com.example.springcacheredis.service;

import com.example.springcacheredis.entity.Invoice;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    Invoice updateInvoice(Long id,Invoice newInvoice);
    void deleteInvoice(Long id);
    Invoice getOneInvoice(Long id);
    List<Invoice> getAllInvoices();
    Page<Invoice> pageInvoices(int page, int pageSize);

}
