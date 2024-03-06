package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentVoucherCodeTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }
//    Voucher Code sub-feature will fill the Map<String, String> paymentData parameter when creating new Payment using this key-value pairs:
//            “voucherCode”: the voucher code
//    The payment status will automatically be “SUCCESS” if the voucher code follows these rules:
//    The voucher code must be 16 characters long, and
//    The voucher code must be started with “ESHOP”, and
//    The voucher code must contain 8 numerical characters.
//    Valid voucher code example is: “ESHOP1234ABC5678”. If the voucher code is invalid, the payment status will automatically be “REJECTED”.

    @Test
    void testEmptyPaymentData() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testValidVoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testLessThan16VoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC567");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testMoreThan16VoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC56789");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testNotStartWithESHOP() {
        this.paymentData.put("voucherCode", "XSHOP1234ABC5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testNotContain8NumericalCharacters() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC567X");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
}
