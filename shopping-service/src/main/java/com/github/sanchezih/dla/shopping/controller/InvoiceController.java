package com.github.sanchezih.dla.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sanchezih.dla.shopping.entity.Invoice;
import com.github.sanchezih.dla.shopping.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	/**
	 * Retrieve All Invoices
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<Invoice>> listAllInvoices() {
		List<Invoice> invoices = invoiceService.findInvoiceAll();
		if (invoices.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(invoices);
	}

	/**
	 * Retrieve Single Invoice
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {
		log.info("Fetching Invoice with id {}", id);
		Invoice invoice = invoiceService.getInvoice(id);
		if (null == invoice) {
			log.error("Invoice with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(invoice);
	}

	/**
	 * Create a Invoice
	 * 
	 * @param invoice
	 * @param result
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) {
		log.info("Creating Invoice : {}", invoice);
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		}
		Invoice invoiceDB = invoiceService.createInvoice(invoice);

		return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDB);
	}

	/**
	 * Update a Invoice
	 * 
	 * @param id
	 * @param invoice
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
		log.info("Updating Invoice with id {}", id);

		invoice.setId(id);
		Invoice currentInvoice = invoiceService.updateInvoice(invoice);

		if (currentInvoice == null) {
			log.error("Unable to update. Invoice with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(currentInvoice);
	}

	/**
	 * Delete a Invoice
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
		log.info("Fetching & Deleting Invoice with id {}", id);

		Invoice invoice = invoiceService.getInvoice(id);
		if (invoice == null) {
			log.error("Unable to delete. Invoice with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		invoice = invoiceService.deleteInvoice(invoice);
		return ResponseEntity.ok(invoice);
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private String formatMessage(BindingResult result) {
		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;

		}).collect(Collectors.toList());
		ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}