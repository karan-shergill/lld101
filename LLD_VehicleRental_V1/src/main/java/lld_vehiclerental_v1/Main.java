package lld_vehiclerental_v1;

import lld_vehiclerental_v1.constants.*;
import lld_vehiclerental_v1.controller.VehicleRentalSystem;
import lld_vehiclerental_v1.model.*;
import lld_vehiclerental_v1.observer.*;
import lld_vehiclerental_v1.paymentStrategy.strategies.*;
import lld_vehiclerental_v1.rentStrategy.strategies.*;
import lld_vehiclerental_v1.vehicleFactory.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("==== Vehicle Rental System Demo ====\n");
        
        // Initialize the system
        VehicleRentalSystem rentalSystem = VehicleRentalSystem.getInstance();
            
            // Create locations
            Location delhiLocation = new Location("Connaught Place", "Delhi", "Delhi", "110001");
            Location mumbaiLocation = new Location("Bandra", "Mumbai", "Maharashtra", "400050");
            
            // Create rental stores
            RentalStore delhiStore = new RentalStore("DELHI001", "Delhi Central Store", delhiLocation);
            RentalStore mumbaiStore = new RentalStore("MUMBAI001", "Mumbai Bandra Store", mumbaiLocation);
            
            rentalSystem.addStore(delhiStore);
            rentalSystem.addStore(mumbaiStore);
            
            // Create vehicles using Factory pattern
            Vehicle sedan1 = VehicleFactory.createVehicle("Honda City", "DL01AB1234", VehicleType.SEDAN);
            Vehicle sedan2 = VehicleFactory.createVehicle("Maruti Swift", "DL02CD5678", VehicleType.SEDAN);
            Vehicle bike1 = VehicleFactory.createVehicle("Royal Enfield", "DL03EF9012", VehicleType.TWO_WHEELER);
            Vehicle traveler1 = VehicleFactory.createVehicle("Toyota Innova", "MH01GH3456", VehicleType.TRAVELER);
            
            // Add vehicles to stores
            delhiStore.addVehicleToStore(sedan1);
            delhiStore.addVehicleToStore(sedan2);
            delhiStore.addVehicleToStore(bike1);
            mumbaiStore.addVehicleToStore(traveler1);
            
            // Create users
            User user1 = new User("John Doe", "john@example.com", "9999999999");
            User user2 = new User("Jane Smith", "jane@example.com", "8888888888");
            
            rentalSystem.registerUser(user1);
            rentalSystem.registerUser(user2);
            
            System.out.println("✓ System initialized with stores, vehicles, and users\n");
            
            // Demonstrate reservation creation with Observer pattern
            System.out.println("==== Creating Reservation ====");
            
            LocalDate startDate = LocalDate.now().plusDays(1);
            LocalDate endDate = LocalDate.now().plusDays(3);
            
            Reservation reservation = rentalSystem.createReservation(
                user1.getId(), 
                "DL01AB1234", 
                PaymentStatus.PENDING,
                "DELHI001", 
                "DELHI001", 
                startDate, 
                endDate,
                new RentByDays() // Strategy pattern for rent calculation
            );
            
            if (reservation == null) {
                System.out.println("❌ Failed to create reservation");
                return;
            }
            
            // Add observers to reservation
            reservation.addObserver(new EmailNotificationObserver());
            reservation.addObserver(new SMSNotificationObserver());
            
            System.out.println("✓ Reservation created with ID: " + reservation.getId());
            System.out.println("✓ Total rent calculated: ₹" + reservation.getRent());
            System.out.println();
            
            // Demonstrate payment processing
            System.out.println("==== Processing Payment ====");
            
            boolean paymentSuccess = rentalSystem.processPayment(
                reservation.getId(), 
                new CreditCard("1234-5678-9012-3456", 123)
            );
            
            System.out.println("✓ Payment processed: " + (paymentSuccess ? "SUCCESS" : "FAILED"));
            System.out.println();
            
            // Demonstrate rental lifecycle
            System.out.println("==== Rental Lifecycle ====");
            
            rentalSystem.startRental(reservation.getId());
            System.out.println("✓ Rental started");
            
            rentalSystem.completeRental(reservation.getId());
            System.out.println("✓ Rental completed");
            System.out.println();
            
            // Create another reservation with different strategy
            System.out.println("==== Creating Hourly Rental ====");
            
            Reservation hourlyReservation = rentalSystem.createReservation(
                user2.getId(),
                "DL03EF9012",
                PaymentStatus.PENDING,
                "DELHI001",
                "DELHI001",
                startDate,
                startDate, // Same day rental
                new RentByTime() // Different pricing strategy
            );
            
            if (hourlyReservation == null) {
                System.out.println("❌ Failed to create hourly reservation");
                return;
            }
            
            hourlyReservation.addObserver(new EmailNotificationObserver());
            
            System.out.println("✓ Hourly reservation created with ID: " + hourlyReservation.getId());
            System.out.println("✓ Hourly rent calculated: ₹" + hourlyReservation.getRent());
            System.out.println();
            
            // Process payment with UPI
            boolean upiPayment = rentalSystem.processPayment(
                hourlyReservation.getId(),
                new UPI("user@paytm")
            );
            
            System.out.println("✓ UPI payment processed: " + (upiPayment ? "SUCCESS" : "FAILED"));
            System.out.println();
            
            // Cancel the hourly reservation
            rentalSystem.cancelReservation(hourlyReservation.getId());
            System.out.println("✓ Hourly reservation cancelled");
            System.out.println();
            
            System.out.println("==== Demo Completed Successfully! ====");
            System.out.println("\nDesign Patterns Demonstrated:");
            System.out.println("✓ Singleton Pattern - VehicleRentalSystem");
            System.out.println("✓ Factory Pattern - VehicleFactory");
            System.out.println("✓ Strategy Pattern - Payment & Rent strategies");
            System.out.println("✓ Observer Pattern - Reservation notifications");
    }
}