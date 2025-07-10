package parkinglot_V1;

import parkinglot_V1.parkingLot.*;
import parkinglot_V1.parkingSpotFactory.*;
import parkinglot_V1.parkingTicket.ParkingTicket;
import parkinglot_V1.vehicleFactory.*;
import parkinglot_V1.parkingStrategy.NearestParking;
import parkinglot_V1.parkingRateStrategy.BasicParkingRate;
import parkinglot_V1.paymentStrategy.CashPayment;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Parking Lot System Demo ===");
        
        // Create parking floors
        ParkingFloor floor1 = new ParkingFloor("Floor-1");
        ParkingFloor floor2 = new ParkingFloor("Floor-2");
        
        // Add parking spots to floors
        addSpotsToFloor(floor1, "1");
        addSpotsToFloor(floor2, "2");
        
        // Create parking lot
        List<ParkingFloor> floors = List.of(floor1, floor2);
        ParkingLotService parkingLotService = new ParkingLot("CityCenter Mall",
                                             new NearestParking(floors), 
                                             new BasicParkingRate());
        
        // Cast to concrete class for floor management operations
        ParkingLot parkingLot = (ParkingLot) parkingLotService;
        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);
        
        // Create entrance and exit with parking lot service references
        Entrance entrance1 = new Entrance("ENT-1", parkingLotService);
        Exit exit1 = new Exit("EXT-1", parkingLotService);
        
        parkingLot.addEntrance(entrance1);
        parkingLot.addExit(exit1);
        
        // Test parking system
        testParkingSystem(entrance1, exit1);
    }
    
    private static void addSpotsToFloor(ParkingFloor floor, String floorNumber) {
        // Add different types of spots
        for (int i = 1; i <= 5; i++) {
            floor.addParkingSpot(ParkingSpotFactory.getParkingSpotByType(
                floorNumber + "-C" + i, ParkingSpotType.COMPACT_SPOT));
        }
        
        for (int i = 1; i <= 3; i++) {
            floor.addParkingSpot(ParkingSpotFactory.getParkingSpotByType(
                floorNumber + "-L" + i, ParkingSpotType.LARGE_SPOT));
        }
        
        for (int i = 1; i <= 10; i++) {
            floor.addParkingSpot(ParkingSpotFactory.getParkingSpotByType(
                floorNumber + "-TW" + i, ParkingSpotType.TWO_WHEELER_SPOT));
        }
    }
    
    private static void testParkingSystem(Entrance entrance, Exit exit) {
        System.out.println("\n--- Testing Parking System ---");
        
        // Create vehicles without parking tickets (following new design)
        Vehicle car1 = VehicleFactory.getVehicleOfType("CAR-001", VehicleType.CAR);
        Vehicle bike1 = VehicleFactory.getVehicleOfType("BIKE-001", VehicleType.TWO_WHEELER);
        Vehicle van1 = VehicleFactory.getVehicleOfType("VAN-001", VehicleType.VAN);
        
        // Park vehicles using entrance (proper delegation)
        System.out.println("\n--- Vehicle Entry ---");
        ParkingTicket ticket1 = entrance.provideTicket(car1);
        ParkingTicket ticket2 = entrance.provideTicket(bike1);
        ParkingTicket ticket3 = entrance.provideTicket(van1);
        
        // Display parking status
        System.out.println("\n--- Parking Status ---");
        if (ticket1 != null) {
            System.out.println("Car parked with ticket: " + ticket1.getNumber());
        }
        if (ticket2 != null) {
            System.out.println("Bike parked with ticket: " + ticket2.getNumber());
        }
        if (ticket3 != null) {
            System.out.println("Van parked with ticket: " + ticket3.getNumber());
        }
        
        // Wait a bit (simulate parking duration)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Exit vehicles using exit with payment processing (proper delegation)
        System.out.println("\n--- Vehicle Exit ---");
        if (ticket1 != null) {
            boolean paymentSuccess = exit.processPayment(ticket1.getNumber(), new CashPayment());
            if (paymentSuccess) {
                System.out.println("Car successfully exited and paid");
            }
        }
        if (ticket2 != null) {
            boolean paymentSuccess = exit.processPayment(ticket2.getNumber(), new CashPayment());
            if (paymentSuccess) {
                System.out.println("Bike successfully exited and paid");
            }
        }
        if (ticket3 != null) {
            boolean paymentSuccess = exit.processPayment(ticket3.getNumber(), new CashPayment());
            if (paymentSuccess) {
                System.out.println("Van successfully exited and paid");
            }
        }
        
        System.out.println("\n--- Demo Complete ---");
    }
}