package com.example.nobsv2.services;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.SearchProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SearchProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductService searchProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_products_service_return_products_dto(){

        List<Product> products = getProducts();

        when(productRepository.findByNameContaining("Product1")).thenReturn(products);

        //When
        ResponseEntity<List<ProductDTO>> response = searchProductService.execute("Product1");

        //Then
        List<ProductDTO> productsDto = products
                .stream()
                .map(ProductDTO::new)
                .toList();

        assertEquals(ResponseEntity.ok(productsDto),response);
        verify(productRepository,times(1)).findByNameContaining("Product1");
    }

    private static List<Product> getProducts() {
        Product product1 =new Product();
        product1.setId(1);
        product1.setName("Product1 Name");
        product1.setDescription(("Product1 Description which is at least 20 chars"));
        product1.setPrice(9.99);

        Product product2 =new Product();
        product2.setId(2);
        product2.setName("Product2 Name");
        product2.setDescription(("Product2 Description which is at least 20 chars"));
        product2.setPrice(10.99);


        return Arrays.asList(product1, product2);
    }
}
