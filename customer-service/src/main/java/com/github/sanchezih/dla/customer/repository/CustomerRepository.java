package com.github.sanchezih.dla.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sanchezih.dla.customer.entity.Customer;
import com.github.sanchezih.dla.customer.entity.Region;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer findByNumberID(String numberID);

	public List<Customer> findByLastName(String lastName);

	public List<Customer> findByRegion(Region region);
}