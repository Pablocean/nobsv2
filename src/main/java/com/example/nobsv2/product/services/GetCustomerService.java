package com.example.nobsv2.product.services;

import com.example.nobsv2.Query;
import com.example.nobsv2.exceptions.CostumerNotFoundException;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.mappings.Customer;
import com.example.nobsv2.mappings.CustomerDTO;
import com.example.nobsv2.mappings.CustomerRepository;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCustomerService implements Query<Integer, CustomerDTO> {

    private final CustomerRepository customerRepository;

    public GetCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<CustomerDTO> execute(Integer input) {
        Optional<Customer> customerOptional = customerRepository.findById(input);

        if(customerOptional.isPresent()){
            return ResponseEntity.ok(new CustomerDTO(customerOptional.get()));
        }

        throw new CostumerNotFoundException();
    }
}
