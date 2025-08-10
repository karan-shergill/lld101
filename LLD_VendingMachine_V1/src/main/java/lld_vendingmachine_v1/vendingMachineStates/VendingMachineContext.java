package lld_vendingmachine_v1.vendingMachineStates;

import lld_vendingmachine_v1.constants.Coin;
import lld_vendingmachine_v1.constants.StateType;
import lld_vendingmachine_v1.modal.Item;
import lld_vendingmachine_v1.managers.PaymentManager;
import lld_vendingmachine_v1.managers.InventoryManager;
import lld_vendingmachine_v1.managers.StateManager;
import lld_vendingmachine_v1.managers.TransactionManager;
import lld_vendingmachine_v1.vendingMachineStates.concreteStates.HasMoneyState;
import lld_vendingmachine_v1.vendingMachineStates.concreteStates.IdleState;
import lld_vendingmachine_v1.vendingMachineStates.concreteStates.SelectionState;

// Context class that maintains state and handles transitions in the vending machine
public class VendingMachineContext {
    private StateManager stateManager;
    private PaymentManager paymentManager;
    private InventoryManager inventoryManager;
    private TransactionManager transactionManager;

    // Constructor to initialize the vending machine with managers
    public VendingMachineContext() {
        this.stateManager = new StateManager();
        this.paymentManager = new PaymentManager();
        this.inventoryManager = new InventoryManager(10); // 10 slots
        this.transactionManager = new TransactionManager();
        System.out.println("Vending Machine initialized with all managers");
    }

    // Gets the current state of the vending machine
    public VendingMachineState getCurrentState() {
        return stateManager.getCurrentState();
    }

    // Handles the insertion of a coin
    public void insertCoin(Coin coin) {
        if (stateManager.isInState(IdleState.class) || stateManager.isInState(HasMoneyState.class)) {
            paymentManager.addCoin(coin);
            if (stateManager.isInState(IdleState.class)) {
                stateManager.transitionTo(StateType.HAS_MONEY);
            }
        } else {
            System.out.println("Cannot insert coin in " + getCurrentState().getStateName());
        }
    }

    // Handles the product selection process
    public void selectItem(int itemCode) {
        if (stateManager.isInState(HasMoneyState.class)) {
            stateManager.transitionTo(StateType.SELECTION);
            
            // Check if out of stock
            if (!inventoryManager.hasItems()) {
                stateManager.transitionTo(StateType.OUT_OF_STOCK);
                System.out.println("Machine is out of stock");
                return;
            }

            // Process the transaction
            boolean success = transactionManager.processTransaction(itemCode, paymentManager, inventoryManager);
            
            if (success) {
                stateManager.transitionTo(StateType.DISPENSE);
                stateManager.transitionTo(StateType.IDLE); // Return to idle after dispensing
            } else {
                stateManager.transitionTo(StateType.HAS_MONEY); // Return to has money state if transaction failed
            }
        } else {
            System.out.println("Product selection can only be done in HasMoney state");
        }
    }

    // Updates the inventory by adding a new item
    public void addItem(Item item, int codeNumber) {
        if (stateManager.isInState(IdleState.class)) {
            inventoryManager.addItem(item, codeNumber);
        } else {
            System.out.println("Inventory can only be updated in Idle state");
        }
    }

    // Cancel current transaction and return money
    public void cancelTransaction() {
        // Only allow cancellation if money has been inserted or in selection state
        if (stateManager.isInState(HasMoneyState.class) || 
            stateManager.isInState(SelectionState.class)) {
            
            System.out.println("Cancellation requested from: " + getCurrentState().getStateName());
            
            // First transition to cancel state for processing
            stateManager.transitionTo(StateType.CANCEL);
            
            // Process the cancellation
            transactionManager.cancelTransaction(paymentManager);
            
            // Then return to idle state
            stateManager.transitionTo(StateType.IDLE);
        } else {
            System.out.println("Cannot cancel transaction in " + getCurrentState().getStateName() + " state");
        }
    }

    // Legacy methods for backward compatibility (delegated to managers)
    public boolean hasItems() {
        return inventoryManager.hasItems();
    }

    public boolean hasCoins() {
        return paymentManager.hasCoins();
    }
}
