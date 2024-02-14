package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HomePageControllerTest {
    @BeforeEach
    void setUp() {
    }

    @InjectMocks
    HomePageController homePageController;

    @Test
    void testHomePage() {
        String viewName = homePageController.homePage();
        assertEquals("HomePage", viewName);
    }
}
