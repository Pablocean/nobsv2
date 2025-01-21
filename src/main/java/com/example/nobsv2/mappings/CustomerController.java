package com.example.nobsv2.mappings;

import com.example.nobsv2.product.services.GetCustomerService;
import com.example.nobsv2.product.services.GetProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final GetCustomerService getCustomerService;

    public CustomerController(GetCustomerService getCustomerService) {
        this.getCustomerService = getCustomerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id){
        return getCustomerService.execute(id);
    }
}
