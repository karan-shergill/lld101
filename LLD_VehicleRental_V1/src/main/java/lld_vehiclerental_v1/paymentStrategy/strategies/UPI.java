package lld_vehiclerental_v1.paymentStrategy.strategies;

import lld_vehiclerental_v1.paymentStrategy.Payment;

public class UPI implements Payment {
    private String upiId;

    public UPI(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("Payment of " + amount + " was mad via UPI ID " + upiId);
    }
}
