package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setup() {
    }

    Product initiateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        return product;
    }

    @Test
    void testCreateAndFind() {
        Product product = initiateProduct();

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = initiateProduct();
        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = initiateProduct();

        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setProductName("Sampo Cap Usep");
        editedProduct.setProductQuantity(50);
        productRepository.edit(editedProduct);

        product = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(editedProduct.getProductId(), product.getProductId());
        assertEquals(editedProduct.getProductName(), product.getProductName());
        assertEquals(editedProduct.getProductQuantity(), product.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = initiateProduct();

        Product deletedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productRepository.delete(deletedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testNegativeQuantity() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(-100);
        productRepository.create(product);

        Product checkProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(0, checkProduct.getProductQuantity());
    }

    @Test
    void testEditNegativeQuantity() {
        Product product = initiateProduct();

        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setProductName("Sampo Cap Usep");
        editedProduct.setProductQuantity(-50);
        productRepository.edit(editedProduct);

        product = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(0, product.getProductQuantity());
    }

    @Test
    void testDeleteNonexistentProduct() {
        Product product = initiateProduct();

        Product deletedProduct = new Product();
        deletedProduct.setProductId("eb558e9f");
        deletedProduct.setProductName("Sampo Cap Usep");
        deletedProduct.setProductQuantity(50);
        productRepository.delete(deletedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
    }

    @Test
    void testEditNonexistentProduct() {
        Product product = initiateProduct();

        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f");
        editedProduct.setProductName("Sampo Cap Bango");
        editedProduct.setProductQuantity(-5);
        Product uneditedProduct = productRepository.edit(editedProduct);

        assertNull(uneditedProduct);
    }

    @Test
    void testFindByIdNonexistentProduct() {
        Product product = initiateProduct();

        Product nonexistentProduct = productRepository.findById("eb558e9f");
        assertNull(nonexistentProduct);
    }
}