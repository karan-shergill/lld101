package lld_vendingmachine_v1.vendingMachineStates.concreteStates;

import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.factory.StateFactory;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineContext;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineState;

public class CancelState implements VendingMachineState {
    public CancelState() {
        System.out.println("Vending Machine is in CancelState - Processing cancellation...");
    }

    @Override
    public String getStateName() {
        return "CancelState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext vendingMachineContext) {
        // After cancellation processing is complete, return to idle state
        System.out.println("Cancellation processed. Returning to idle state.");
        return StateFactory.createState(StateType.IDLE);
    }
}
