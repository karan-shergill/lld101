package lld_vendingmachine_v1.vendingMachineStates.concreteStates;

import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.factory.StateFactory;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineContext;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineState;

public class IdleState implements VendingMachineState {
    public IdleState() {
        System.out.println("Vending Machine is in IdleState");
    }

    @Override
    public String getStateName() {
        return "IdleState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext vendingMachineContext) {
        // Check if inventory has items
        if (!vendingMachineContext.hasItems()) {
            return StateFactory.createState(StateType.OUT_OF_STOCK);
        }
        // If money has been inserted, transition to HasMoneyState
        if (vendingMachineContext.hasCoins()) {
            return StateFactory.createState(StateType.HAS_MONEY);
        }
        // Otherwise, remain in idle state
        return this;
    }
}
