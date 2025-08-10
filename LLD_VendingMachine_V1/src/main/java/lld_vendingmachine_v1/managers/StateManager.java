package lld_vendingmachine_v1.managers;

import lld_vendingmachine_v1.vendingMachineStates.VendingMachineState;
import lld_vendingmachine_v1.factory.StateFactory;
import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.vendingMachineStates.concreteStates.IdleState;

// Manages state transitions using factory pattern
public class StateManager {
    private VendingMachineState currentState;

    public StateManager() {
        this.currentState = new IdleState(); // Initial state
        System.out.println("Initialized: " + currentState.getStateName());
    }

    // Transition to a new state using factory
    public void transitionTo(StateType stateType) {
        VendingMachineState newState = StateFactory.createState(stateType);
        this.currentState = newState;
        System.out.println("Transitioned to: " + currentState.getStateName());
    }

    // Get current state
    public VendingMachineState getCurrentState() {
        return currentState;
    }

    // Set current state directly (for complex transitions)
    public void setCurrentState(VendingMachineState state) {
        this.currentState = state;
        System.out.println("Current state: " + currentState.getStateName());
    }

    // Check if in specific state
    public boolean isInState(Class<? extends VendingMachineState> stateClass) {
        return stateClass.isInstance(currentState);
    }
}
