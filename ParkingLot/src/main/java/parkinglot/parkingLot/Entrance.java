package parkinglot.parkingLot;

import parkinglot.parkingTicket.ParkingTicket;
import parkinglot.vehicleFactory.Vehicle;

/**
 * Entrance class that handles vehicle entry into the parking lot
 * Follows Dependency Inversion Principle by depending on ParkingLotService interface
 */
public class Entrance {
    private final String id;
    private ParkingLotService parkingLotService;
    
    public Entrance(String id) {
        this.id = id;
    }
    
    public Entrance(String id, ParkingLotService parkingLotService) {
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
     * Provides a parking ticket for a vehicle entering through this entrance
     * @param vehicle The vehicle requesting a parking spot
     * @return ParkingTicket if successful, null if no parking spot available
     */
    public ParkingTicket provideTicket(Vehicle vehicle) {
        if (parkingLotService == null) {
            System.out.println("Error: No parking lot service associated with this entrance");
            return null;
        }
        
        if (vehicle == null) {
            System.out.println("Error: Vehicle cannot be null");
            return null;
        }
        
        System.out.println("Providing ticket for vehicle: " + vehicle.getLicenseNumber() + " at entrance: " + this.id);
        return parkingLotService.parkVehicle(vehicle, this);
    }
}
