package com.github.sanchezih.dla.shopping.service;

import java.util.List;

import com.github.sanchezih.dla.shopping.entity.Invoice;

public interface InvoiceService {
	public List<Invoice> findInvoiceAll();

	public Invoice createInvoice(Invoice invoice);

	public Invoice updateInvoice(Invoice invoice);

	public Invoice deleteInvoice(Invoice invoice);

	public Invoice getInvoice(Long id);
}