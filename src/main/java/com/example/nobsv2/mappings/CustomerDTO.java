package com.example.nobsv2.mappings;

import com.example.nobsv2.product.model.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class CustomerDTO {

    private Integer id;
    private String first_name;
    private String last_name;
    private Address address;

    public CustomerDTO(Customer costumer){
        this.id =costumer.getId();
        this.first_name = costumer.getFirst_name();
        this.last_name = costumer.getLast_name();
        this.address = costumer.getAddress();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
