package parkinglot.paymentStrategy;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String pin;

    public CreditCardPayment(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    @Override
    public boolean pay(int amount) {
        System.out.println("Amount " + amount + " payed via CC " + this.cardNumber);
        return true;
    }
}
