package com.github.sanchezih.dla.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sanchezih.dla.shopping.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {
}