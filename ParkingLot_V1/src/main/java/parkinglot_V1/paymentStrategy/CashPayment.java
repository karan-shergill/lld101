package parkinglot_V1.paymentStrategy;

public class CashPayment implements PaymentStrategy{
    @Override
    public boolean pay(int amount) {
        System.out.println("Amount " + amount + " payed via Cash");
        return true;
    }
}
