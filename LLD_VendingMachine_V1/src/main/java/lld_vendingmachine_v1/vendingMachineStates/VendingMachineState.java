package lld_vendingmachine_v1.vendingMachineStates;

// Interface defining the common methods for all states
public interface VendingMachineState {
    String getStateName();
    // Method to handle state transitions
    VendingMachineState next(VendingMachineContext vendingMachineContext);
}
