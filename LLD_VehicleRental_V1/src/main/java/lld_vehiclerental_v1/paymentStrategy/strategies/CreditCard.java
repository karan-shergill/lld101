package lld_vehiclerental_v1.paymentStrategy.strategies;

import lld_vehiclerental_v1.paymentStrategy.Payment;

public class CreditCard implements Payment {
    private String cardNo;
    private int cvv;

    public CreditCard(String cardNo, int cvv) {
        this.cardNo = cardNo;
        this.cvv = cvv;
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("Payment of " + amount + " was mad via CC " + cardNo);
    }
}
