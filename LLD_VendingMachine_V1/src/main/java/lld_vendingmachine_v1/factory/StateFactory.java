package lld_vendingmachine_v1.factory;

import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineState;
import lld_vendingmachine_v1.vendingMachineStates.concreteStates.*;

// Factory class for creating different states of the vending machine
public class StateFactory {

    // Factory method to create states based on StateType
    public static VendingMachineState createState(StateType stateType) {
        switch (stateType) {
            case IDLE:
                return new IdleState();
            case HAS_MONEY:
                return new HasMoneyState();
            case SELECTION:
                return new SelectionState();
            case DISPENSE:
                return new DispenseState();
            case OUT_OF_STOCK:
                return new OutOfStockState();
            case CANCEL:
                return new CancelState();
            default:
                throw new IllegalArgumentException("Unknown state type: " + stateType);
        }
    }
}
