package com.github.sanchezih.dla.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sanchezih.dla.shopping.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	public List<Invoice> findByCustomerId(Long customerId);

	public Invoice findByNumberInvoice(String numberInvoice);
}