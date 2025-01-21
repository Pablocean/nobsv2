package com.example.nobsv2.services;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.UpdateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_execute_then_update_product_and_return_dto() {
        Product updatedProduct =new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Product Name");
        updatedProduct.setDescription(("Product Description which is at least 20 chars"));
        updatedProduct.setPrice(9.99);

        Product existingProduct =new Product();
        existingProduct.setId(1);
        existingProduct.setName("Product Name");
        existingProduct.setDescription(("Product Description which is at least 20 chars"));
        existingProduct.setPrice(9.99);

        UpdateProductCommand command = new UpdateProductCommand(1, updatedProduct);

        when(productRepository.findById(command.getId())).thenReturn(Optional.of(existingProduct));

        ResponseEntity<ProductDTO> response = updateProductService.execute(command);

        assertEquals(ResponseEntity.ok(new ProductDTO(existingProduct)), response);

    }

    @Test
    public void given_product_does_not_exist_when_execute_then_throw_product_not_found_exception() {
        Product updatedProduct =new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Product Name");
        updatedProduct.setDescription(("Product Description which is at least 20 chars"));
        updatedProduct.setPrice(9.99);

        UpdateProductCommand command = new UpdateProductCommand(1, updatedProduct);

        when(productRepository.findById(command.getId())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> updateProductService.execute(command));

        verify(productRepository,times(1)).findById(command.getId());
    }
}
