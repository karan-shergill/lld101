package lld_vendingmachine_v1.vendingMachineStates.concreteStates;

import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.factory.StateFactory;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineContext;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineState;

public class SelectionState implements VendingMachineState {
    public SelectionState() {
        System.out.println("Vending Machine is in SelectionState");
    }

    @Override
    public String getStateName() {
        return "SelectionState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext vendingMachineContext) {
        // If inventory has no items, transition to OutOfStock
        if (!vendingMachineContext.hasItems()) {
            return StateFactory.createState(StateType.OUT_OF_STOCK);
        }
        // If no money left, go back to idle
        if (!vendingMachineContext.hasCoins()) {
            return StateFactory.createState(StateType.IDLE);
        }
        // For simplicity, we'll transition to dispense state directly
        // In a more complex implementation, we'd track selected item code
        return new DispenseState();
    }
}
