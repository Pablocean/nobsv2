package com.example.nobsv2.validators;

import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductValidatorTest {


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_with_empty_name_when_execute_then_throw_name_required_exception() {
        Product product =new Product();
        product.setId(1);
        product.setDescription(("Product Description which is at least 20 chars"));
        product.setPrice(9.99);

        assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));

    }

    @Test
    public void given_product_with_short_description_when_execute_then_throw_description_length_exception() {
        Product product =new Product();
        product.setId(1);
        product.setName("Product");
        product.setDescription(("short"));
        product.setPrice(9.99);

        assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
    }

    @Test
    public void given_product_with_negative_price_when_execute_then_throw_price_cannot_be_negative_exception() {
        Product product =new Product();
        product.setId(1);
        product.setName("Product");
        product.setDescription(("Product Description which is at least 20 chars"));
        product.setPrice(-9.99);

        assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
    }

    @Test
    public void given_product_with_null_price_when_execute_then_throw_price_cannot_be_negative_exception() {
        Product product =new Product();
        product.setId(1);
        product.setName("Product");
        product.setDescription(("Product Description which is at least 20 chars"));
        product.setPrice(null);

        assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
    }

    @Test
    public void given_valid_product_when_execute_then_no_exception_thrown() {
        Product product =new Product();
        product.setId(1);
        product.setName("Product");
        product.setDescription(("Product Description which is at least 20 chars"));
        product.setPrice(9.99);

        // No exception should be thrown
        ProductValidator.execute(product);
    }
}
