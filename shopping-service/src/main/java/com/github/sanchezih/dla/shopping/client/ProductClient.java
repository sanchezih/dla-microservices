package com.github.sanchezih.dla.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sanchezih.dla.shopping.model.Product;

@FeignClient(name = "product-service", path = "/products")
public interface ProductClient {

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

	@PutMapping(value = "/{id}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable Long id,
			@RequestParam(name = "quantity", required = true) Double quantity);
}
