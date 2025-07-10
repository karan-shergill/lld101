package parkinglot_V1.parkingLot;

import parkinglot_V1.paymentStrategy.Payment;
import parkinglot_V1.paymentStrategy.PaymentStrategy;

/**
 * Exit class that handles vehicle exit from the parking lot
 * Follows Dependency Inversion Principle by depending on ParkingLotService interface
 */
public class Exit {
    private final String id;
    private ParkingLotService parkingLotService;
    
    public Exit(String id) {
        this.id = id;
    }
    
    public Exit(String id, ParkingLotService parkingLotService) {
        this.id = id;
        this.parkingLotService = parkingLotService;
    }
    
    public String getId() {
        return id;
    }
    
    public ParkingLotService getParkingLotService() {
        return parkingLotService;
    }
    
    public void setParkingLotService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }
    
    /**
     * Processes vehicle exit and calculates parking fee
     * @param ticketNumber The parking ticket number
     * @return The parking fee amount
     */
    public double processExit(String ticketNumber) {
        if (parkingLotService == null) {
            System.out.println("Error: No parking lot service associated with this exit");
            return 0.0;
        }
        
        if (ticketNumber == null || ticketNumber.trim().isEmpty()) {
            System.out.println("Error: Ticket number cannot be null or empty");
            return 0.0;
        }
        
        System.out.println("Processing exit for ticket: " + ticketNumber + " at exit: " + this.id);
        return parkingLotService.unparkVehicle(ticketNumber, this);
    }
    
    /**
     * Processes payment for parking fee
     * @param ticketNumber The parking ticket number
     * @param paymentStrategy The payment strategy to use
     * @return true if payment successful, false otherwise
     */
    public boolean processPayment(String ticketNumber, PaymentStrategy paymentStrategy) {
        if (paymentStrategy == null) {
            System.out.println("Error: Payment strategy cannot be null");
            return false;
        }
        
        double amount = processExit(ticketNumber);
        if (amount <= 0) {
            System.out.println("Error: Invalid parking fee amount");
            return false;
        }
        
        Payment payment = new Payment(paymentStrategy);
        int amountInCents = (int) (amount * 100); // Convert to cents for payment processing
        
        if (payment.pay(amountInCents)) {
            System.out.println("Payment successful for ticket: " + ticketNumber + ", Amount: $" + amount);
            return true;
        } else {
            System.out.println("Payment failed for ticket: " + ticketNumber);
            return false;
        }
    }
}
