package com.example.nobsv2.services;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.services.DeleteProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_execute_then_delete_product_and_return_no_content() {
        //Given
        Product product =new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription(("Product Description which is at least 20 chars"));
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ResponseEntity<Void> response = deleteProductService.execute(1);

        assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT).build(), response);
        verify(productRepository,times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_execute_then_throw_product_not_found_exception() {

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // Verificamos que se lanza la excepción
        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(1));
        verify(productRepository,times(1)).findById(1);
    }
}
