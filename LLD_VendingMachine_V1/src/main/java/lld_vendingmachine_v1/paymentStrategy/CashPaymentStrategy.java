package lld_vendingmachine_v1.paymentStrategy;

import lld_vendingmachine_v1.constants.Coin;
import lld_vendingmachine_v1.changeDispenser.ChangeDispenser;

import java.util.List;

// Concrete strategy for cash payment processing
public class CashPaymentStrategy implements PaymentStrategy {
    private ChangeDispenser changeDispenser;

    public CashPaymentStrategy() {
        this.changeDispenser = new ChangeDispenser();
    }

    @Override
    public boolean processPayment(int amount) {
        // For cash payments, any positive amount is valid
        return amount > 0;
    }

    @Override
    public List<Coin> calculateChange(int changeAmount) {
        return changeDispenser.dispenseChange(changeAmount);
    }

    @Override
    public boolean canProcess(int amount) {
        // Cash can process any positive amount
        return amount >= 0;
    }
}
