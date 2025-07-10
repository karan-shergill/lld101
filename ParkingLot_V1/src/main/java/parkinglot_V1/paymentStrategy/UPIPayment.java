package parkinglot_V1.paymentStrategy;

public class UPIPayment implements PaymentStrategy{
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean pay(int amount) {
        System.out.println("Amount " + amount + " payed via UPI ID " + this.upiId);
        return true;
    }
}
