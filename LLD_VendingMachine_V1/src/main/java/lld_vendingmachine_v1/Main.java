package lld_vendingmachine_v1;

import lld_vendingmachine_v1.constants.Coin;
import lld_vendingmachine_v1.constants.ItemType;
import lld_vendingmachine_v1.modal.Item;
import lld_vendingmachine_v1.vendingMachineStates.VendingMachineContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    VENDING MACHINE DEMONSTRATION");
        System.out.println("========================================\n");

        // Initialize the vending machine
        VendingMachineContext vendingMachine = new VendingMachineContext();
        
        // Setup inventory
        setupInventory(vendingMachine);
        
        // Demo scenarios
        System.out.println("\n=== DEMO SCENARIOS ===");
        System.out.println("(Now with NEW CANCEL STATE!)\n");
        
        // Scenario 1: Successful purchase with exact money
        scenario1_SuccessfulPurchaseExactMoney(vendingMachine);
        
        // Scenario 2: Successful purchase with change
        scenario2_SuccessfulPurchaseWithChange(vendingMachine);
        
        // Scenario 3: Insufficient funds
        scenario3_InsufficientFunds(vendingMachine);
        
        // Scenario 4: Item not available
        scenario4_ItemNotAvailable(vendingMachine);
        
        // Scenario 5: Cancel transaction
        scenario5_CancelTransaction(vendingMachine);
        
        // Scenario 6: Try to cancel from invalid state
        scenario6_CancelFromInvalidState(vendingMachine);
        
        System.out.println("\n========================================");
        System.out.println("    DEMONSTRATION COMPLETED");
        System.out.println("========================================");
    }

    private static void setupInventory(VendingMachineContext vendingMachine) {
        System.out.println("--- Setting up Inventory ---");
        
        // Add items to the vending machine
        vendingMachine.addItem(new Item(ItemType.COKE, 15), 101);
        vendingMachine.addItem(new Item(ItemType.PEPSI, 20), 102);
        vendingMachine.addItem(new Item(ItemType.JUICE, 25), 103);
        vendingMachine.addItem(new Item(ItemType.COOKIES, 10), 104);
        vendingMachine.addItem(new Item(ItemType.CUP_CAKE, 30), 105);
        
        System.out.println("Inventory setup completed!\n");
    }

    private static void scenario1_SuccessfulPurchaseExactMoney(VendingMachineContext vendingMachine) {
        System.out.println("--- Scenario 1: Successful Purchase (Exact Money) ---");
        System.out.println("Purchasing COKE (₹15) with exact money...");
        
        vendingMachine.insertCoin(Coin.TEN_RUPEES);
        vendingMachine.insertCoin(Coin.FIVE_RUPEES);
        vendingMachine.selectItem(101); // COKE
        
        System.out.println("✓ Scenario 1 completed\n");
    }

    private static void scenario2_SuccessfulPurchaseWithChange(VendingMachineContext vendingMachine) {
        System.out.println("--- Scenario 2: Successful Purchase (With Change) ---");
        System.out.println("Purchasing COOKIES (₹10) with ₹20...");
        
        vendingMachine.insertCoin(Coin.TEN_RUPEES);
        vendingMachine.insertCoin(Coin.TEN_RUPEES);
        vendingMachine.selectItem(104); // COOKIES
        
        System.out.println("✓ Scenario 2 completed\n");
    }

    private static void scenario3_InsufficientFunds(VendingMachineContext vendingMachine) {
        System.out.println("--- Scenario 3: Insufficient Funds ---");
        System.out.println("Trying to purchase CUP_CAKE (₹30) with only ₹15...");
        
        vendingMachine.insertCoin(Coin.TEN_RUPEES);
        vendingMachine.insertCoin(Coin.FIVE_RUPEES);
        vendingMachine.selectItem(105); // CUP_CAKE
        
        // Cancel the transaction to clear coins
        vendingMachine.cancelTransaction();
        
        System.out.println("✓ Scenario 3 completed\n");
    }

    private static void scenario4_ItemNotAvailable(VendingMachineContext vendingMachine) {
        System.out.println("--- Scenario 4: Item Not Available ---");
        System.out.println("Trying to purchase item with invalid code 999...");
        
        vendingMachine.insertCoin(Coin.TEN_RUPEES);
        vendingMachine.insertCoin(Coin.TEN_RUPEES);
        vendingMachine.selectItem(999); // Invalid code
        
        // Cancel the transaction to clear coins
        vendingMachine.cancelTransaction();
        
        System.out.println("✓ Scenario 4 completed\n");
    }

    private static void scenario5_CancelTransaction(VendingMachineContext vendingMachine) {
        System.out.println("--- Scenario 5: Cancel Transaction (NEW CANCEL STATE) ---");
        System.out.println("Inserting money and then cancelling to see Cancel State in action...");
        
        vendingMachine.insertCoin(Coin.TEN_RUPEES);
        vendingMachine.insertCoin(Coin.FIVE_RUPEES);
        System.out.println("Current state: " + vendingMachine.getCurrentState().getStateName());
        
        System.out.println("Initiating cancellation...");
        vendingMachine.cancelTransaction();
        System.out.println("Final state: " + vendingMachine.getCurrentState().getStateName());
        
        System.out.println("✓ Scenario 5 completed - Cancel State demonstrated!\n");
    }

    private static void scenario6_CancelFromInvalidState(VendingMachineContext vendingMachine) {
        System.out.println("--- Scenario 6: Try Cancel from Invalid State ---");
        System.out.println("Attempting to cancel when no money is inserted (should fail)...");
        
        System.out.println("Current state: " + vendingMachine.getCurrentState().getStateName());
        System.out.println("Attempting cancellation...");
        vendingMachine.cancelTransaction();
        System.out.println("State after attempted cancel: " + vendingMachine.getCurrentState().getStateName());
        
        System.out.println("✓ Scenario 6 completed - Cancel validation demonstrated!\n");
    }
}