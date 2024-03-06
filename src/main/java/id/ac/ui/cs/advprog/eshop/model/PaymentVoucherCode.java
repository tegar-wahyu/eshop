package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

class PaymentVoucherCode extends Payment {
    public PaymentVoucherCode(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentVoucherCode(String id, String method, Map<String, String> paymentData, String status) {
        super(id, method, paymentData, status);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String voucherCode = paymentData.get("voucherCode");
        boolean isLengthValid = voucherCode.length() == 16;
        boolean isStartWithESHOP = voucherCode.startsWith("ESHOP");

        int countDigit = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            if (!Character.isDigit(voucherCode.charAt(i))) {
                countDigit++;
            }
        }

        if (countDigit == 8 && isLengthValid && isStartWithESHOP) {
            super.setStatus("SUCCESS");
        } else {
            super.setStatus("REJECTED");
        }

        super.setPaymentData(paymentData);
    }
}
