package parkinglot_V1.paymentStrategy;

public class Payment {
    private PaymentStrategy paymentStrategy;

    public Payment(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean pay(int amount) {
        return this.paymentStrategy.pay(amount);
    }
}
