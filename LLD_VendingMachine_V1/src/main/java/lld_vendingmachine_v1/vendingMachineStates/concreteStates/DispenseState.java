package lld_vendingmachine_v1.vendingMachineStates.concreteStates;

import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.factory.StateFactory;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineContext;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineState;

public class DispenseState implements VendingMachineState {
    public DispenseState() {
        System.out.println("Vending Machine is in DispenseState");
    }

    @Override
    public String getStateName() {
        return "DispenseState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext vendingMachineContext) {
        // Dispense the selected product
        return StateFactory.createState(StateType.IDLE);
    }
}
