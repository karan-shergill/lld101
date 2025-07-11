package parkinglot.parkingTicket;

import parkinglot.parkingLot.Entrance;
import parkinglot.parkingLot.Exit;
import parkinglot.parkingSpotFactory.ParkingSpot;
import parkinglot.vehicleFactory.Vehicle;

/**
 * Interface for ticket management operations
 * Follows Interface Segregation Principle
 */
public interface TicketService {
    
    /**
     * Creates a new parking ticket
     * @param vehicle The vehicle being parked
     * @param entrance The entrance used
     * @param parkingSpot The assigned parking spot
     * @return ParkingTicket
     */
    ParkingTicket createTicket(Vehicle vehicle, Entrance entrance, ParkingSpot parkingSpot);
    
    /**
     * Processes ticket for exit and calculates parking fee
     * @param ticketNumber The ticket number
     * @param exit The exit used
     * @return The parking fee amount
     */
    double processTicketForExit(String ticketNumber, Exit exit);
    
    /**
     * Gets a ticket by its number
     * @param ticketNumber The ticket number
     * @return ParkingTicket or null if not found
     */
    ParkingTicket getTicket(String ticketNumber);
    
    /**
     * Removes a ticket from the system
     * @param ticketNumber The ticket number to remove
     */
    void removeTicket(String ticketNumber);
    
    /**
     * Gets the count of active tickets
     * @return Number of active tickets
     */
    int getActiveTicketCount();
} 