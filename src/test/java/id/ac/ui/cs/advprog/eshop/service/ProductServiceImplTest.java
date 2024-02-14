package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    Product initiateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        return product;
    }

    @Test
    void testCreateProduct() {
        Product product = initiateProduct();
        when(productRepository.create(product)).thenReturn(product);

        productService.create(product);

        assertNotNull(product);
    }

    @Test
    void testDeleteProduct() {
        Product product = initiateProduct();
        productService.delete(product);

        verify(productRepository).delete(product);
    }

    @Test
    void testEditProduct() {
        Product product = initiateProduct();
        when(productRepository.edit(product)).thenReturn(product);

        Product editedProduct = productService.edit(product);

        assertEquals(product, editedProduct);
    }

    @Test
    void testFindAllProduct() {
        Product product = initiateProduct();

        when(productRepository.findAll()).thenReturn(List.of(product).iterator());
        Iterator<Product> productIterator = productService.findAll().iterator();

        assertTrue(productIterator.hasNext());
    }

    @Test
    void testFindByIdProduct() {
        Product product = initiateProduct();
        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        Product foundProduct = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(product, foundProduct);
    }
}
