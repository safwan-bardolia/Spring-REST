package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	// autowire the customerService
	@Autowired
	private CustomerService customerService;
	

	// add mapping for GET /customers	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	// add mapping for GET /customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {			// bind path variable with method param
	
		Customer customer = customerService.getCustomer(customerId);
		
		if(customer == null) {
			throw new CustomerNotFoundException("customer id not found - " + customerId);
		}
		return customer;
	}
	
	// add mapping for POST /customers - add new customer
	@PostMapping("/customers")											// jackson will automatically convert request body from JSON to POJO
	public Customer addCustomer(@RequestBody Customer customer) {		// @RequestBody annotation binds that POJO(request body) to a method param

		customerService.saveCustomer(customer);
	
		// returning response,   
		return customer;
	}
	
	// add mapping for PUT /customers - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		
		customerService.saveCustomer(customer);

		// returning response,   		
		return customer;
	}
	
	// add mapping for DELETE /customers/{customerId} - delete existing customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {		// binding path variable to method param
		
		// first check if the customer exist?
		Customer customer = customerService.getCustomer(customerId);
		
		// throw exception if not exist
		if(customer == null) {
			throw new CustomerNotFoundException("customer id not found - " + customerId);
		}
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer with id - " + customerId;
	}
	
}
