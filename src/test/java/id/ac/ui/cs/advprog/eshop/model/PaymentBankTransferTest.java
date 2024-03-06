package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentBankTransferTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testEmptyPaymentData() {
        PaymentBankTransfer payment = new PaymentBankTransfer("13652556-012a-4c07-b546-54eb1396d79b",
                "BANK_TRANSFER", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testValidPaymentData() {
        this.paymentData.put("bankName", "MANDIRI");
        this.paymentData.put("referenceCode", "1234567890");
        PaymentBankTransfer payment = new PaymentBankTransfer("13652556-012a-4c07-b546-54eb1396d79b",
                "BANK_TRANSFER", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testEmptyBankName() {
        this.paymentData.put("bankName", "");
        this.paymentData.put("referenceCode", "1234567890");
        PaymentBankTransfer payment = new PaymentBankTransfer("13652556-012a-4c07-b546-54eb1396d79b",
                "BANK_TRANSFER", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testEmptyReferenceCode() {
        this.paymentData.put("bankName", "MANDIRI");
        this.paymentData.put("referenceCode", "");
        PaymentBankTransfer payment = new PaymentBankTransfer("13652556-012a-4c07-b546-54eb1396d79b",
                "BANK_TRANSFER", this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
}