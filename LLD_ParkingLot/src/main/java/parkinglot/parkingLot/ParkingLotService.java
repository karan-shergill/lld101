package parkinglot.parkingLot;

import parkinglot.parkingSpotFactory.ParkingSpotType;
import parkinglot.parkingTicket.ParkingTicket;
import parkinglot.vehicleFactory.Vehicle;

/**
 * Interface for parking lot operations
 * Follows Interface Segregation Principle and Dependency Inversion Principle
 */
public interface ParkingLotService {
    
    /**
     * Parks a vehicle in the parking lot
     * @param vehicle The vehicle to park
     * @param entrance The entrance used
     * @return ParkingTicket if successful, null otherwise
     */
    ParkingTicket parkVehicle(Vehicle vehicle, Entrance entrance);
    
    /**
     * Processes vehicle exit from the parking lot
     * @param ticketNumber The parking ticket number
     * @param exit The exit used
     * @return The parking fee amount
     */
    double unparkVehicle(String ticketNumber, Exit exit);
    
    /**
     * Gets the number of available spots for a specific type
     * @param spotType The type of parking spot
     * @return Number of available spots
     */
    int getAvailableSpotCount(ParkingSpotType spotType);
    
    /**
     * Gets the number of active tickets
     * @return Number of active tickets
     */
    int getActiveTicketCount();
    
    /**
     * Gets the name of the parking lot
     * @return Parking lot name
     */
    String getName();
} 