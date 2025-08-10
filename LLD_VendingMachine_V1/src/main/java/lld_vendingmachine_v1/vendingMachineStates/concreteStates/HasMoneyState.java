package lld_vendingmachine_v1.vendingMachineStates.concreteStates;

import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.factory.StateFactory;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineContext;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineState;

public class HasMoneyState implements VendingMachineState {
    public HasMoneyState() {
        System.out.println("Vending Machine is in HasMoneyState");
    }

    @Override
    public String getStateName() {
        return "HasMoneyState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext vendingMachineContext) {
        if (!vendingMachineContext.hasItems()) {
            return StateFactory.createState(StateType.OUT_OF_STOCK);
        }
        if (!vendingMachineContext.hasCoins()) {
            return StateFactory.createState(StateType.IDLE);
        }
        // Transition to SelectionState if user starts product selection
        if (vendingMachineContext.getCurrentState() instanceof HasMoneyState) {
            return StateFactory.createState(StateType.SELECTION);
        }
        return this;
    }
}
