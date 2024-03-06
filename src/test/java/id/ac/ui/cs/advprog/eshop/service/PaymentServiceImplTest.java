package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    OrderRepository orderRepository;
    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        List <Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudrajat");
        orders.add(order1);

        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();
        Map<String, String> paymentData1 = Map.of("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = Map.of("bankName", "MANDIRI",
                "referenceCode", "1234567890");
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                "BANK_TRANSFER", paymentData2);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.addPayment(orders.get(1),
                "VOUCHER_CODE", Map.of("voucherCode", "ESHOP1234ABC5678"));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testSetSuccessStatus() {
        Payment payment = payments.get(1);
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.setStatus(payment, "SUCCESS");
        Order updatedOrder = orderRepository.findById(payment.getId());

        verify(orderRepository, times(1)).findById(payment.getId());
        verify(orderRepository, times(1)).save(order);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", updatedOrder.getStatus());
    }

    @Test
    void testSetFailedStatus() {
        Payment payment = payments.get(1);
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.setStatus(payment, "REJECTED");
        Order updatedOrder = orderRepository.findById(payment.getId());

        verify(orderRepository, times(1)).findById(payment.getId());
        verify(orderRepository, times(1)).save(order);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", updatedOrder.getStatus());
    }
    @Test
    void testGetPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(payments, result);
        assertEquals(payments.size(), result.size());
    }
}