package com.example.nobsv2.services;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_when_execute_then_save_product_and_return_created_dto() {
        Product product =new Product();
        product.setName("Product Name");
        product.setDescription(("Product Description which is at least 20 chars"));
        product.setPrice(9.99);

        Product savedProduct =new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription(("Product Description which is at least 20 chars"));
        product.setPrice(9.99);


        when(productRepository.save(product)).thenReturn(savedProduct);

        ResponseEntity<ProductDTO> response = createProductService.execute(product);


        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct)), response);
        verify(productRepository,times(1)).save(product);
    }
}
