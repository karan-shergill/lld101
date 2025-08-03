package lld_vehiclerental_v1.paymentStrategy;

public class PaymentProcessor {
    public boolean processPayment(double amount, Payment paymentStrategy) {
        paymentStrategy.makePayment(amount);
        return true; // Assume payment is successful for simplicity
    }
}
