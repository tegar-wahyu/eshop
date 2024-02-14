package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;
    Product product = new Product();

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        when(productService.create(product)).thenReturn(product);
        String viewName = productController.createProductPost(product);

        assertEquals("redirect:list", viewName);
    }

    @Test
    void testProductListPage() {
        when(productService.findAll()).thenReturn(null);
        String viewName = productController.productListPage(model);

        assertEquals("ListProduct", viewName);
    }

    @Test
    void testDeleteProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product);
        String viewName = productController.deleteProduct(productId);

        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPage() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product);
        String viewName = productController.editProductPage(productId, model);

        assertEquals("EditProduct", viewName);
    }

    @Test
    void testEditProductPost() {
        String viewName = productController.editProductPost(product);

        assertEquals("redirect:list", viewName);
    }
}