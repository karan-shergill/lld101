package lld_vendingmachine_v1.vendingMachineStates.concreteStates;

import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.factory.StateFactory;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineContext;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineState;

public class OutOfStockState implements VendingMachineState {
    public OutOfStockState() {
        System.out.println("Vending Machine is in OutOfStockState");
    }

    @Override
    public String getStateName() {
        return "OutOfStockState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext vendingMachineContext) {
        // If inventory has items again, return to idle state
        if (vendingMachineContext.hasItems()) {
            return StateFactory.createState(StateType.IDLE);
        }

        // Otherwise, remain in out of stock state
        return this;
    }
}
