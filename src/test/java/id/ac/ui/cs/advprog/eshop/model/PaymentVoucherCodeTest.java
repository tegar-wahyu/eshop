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

    @Test
    void testEmptyPaymentData() {
        PaymentVoucherCode payment = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testValidVoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        PaymentVoucherCode payment = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testLessThan16VoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC567");
        PaymentVoucherCode payment = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testMoreThan16VoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC56789");
        PaymentVoucherCode payment = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testNotStartWithESHOP() {
        this.paymentData.put("voucherCode", "XSHOP1234ABC5678");
        PaymentVoucherCode payment = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testNotContain8NumericalCharacters() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC567X");
        PaymentVoucherCode payment = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
}
