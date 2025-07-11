package parkinglot.parkingTicket;

import parkinglot.parkingLot.Entrance;
import parkinglot.parkingLot.Exit;
import parkinglot.parkingSpotFactory.ParkingSpot;
import parkinglot.vehicleFactory.Vehicle;
import parkinglot.enums.DurationType;
import parkinglot.parkingRateStrategy.ParkingRateStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages parking tickets and related operations
 * Follows Single Responsibility Principle
 * Implements TicketService interface for better abstraction
 */
public class TicketManager implements TicketService {
    private Map<String, ParkingTicket> tickets;
    private ParkingRateStrategy parkingRateStrategy;

    public TicketManager(ParkingRateStrategy parkingRateStrategy) {
        this.tickets = new HashMap<>();
        this.parkingRateStrategy = parkingRateStrategy;
    }

    /**
     * Creates a new parking ticket
     * @param vehicle The vehicle being parked
     * @param entrance The entrance used
     * @param parkingSpot The assigned parking spot
     * @return ParkingTicket
     */
    @Override
    public ParkingTicket createTicket(Vehicle vehicle, Entrance entrance, ParkingSpot parkingSpot) {
        String ticketNumber = generateTicketNumber();
        ParkingTicket ticket = new ParkingTicket(ticketNumber, vehicle, entrance, parkingSpot);
        
        // Set the parking ticket on the vehicle
        vehicle.setParkingTicket(ticket);
        
        // Store the ticket
        tickets.put(ticketNumber, ticket);
        
        System.out.println("Ticket created: " + ticketNumber + " for vehicle: " + vehicle.getLicenseNumber());
        return ticket;
    }

    /**
     * Processes ticket for exit and calculates parking fee
     * @param ticketNumber The ticket number
     * @param exit The exit used
     * @return The parking fee amount
     */
    @Override
    public double processTicketForExit(String ticketNumber, Exit exit) {
        ParkingTicket ticket = tickets.get(ticketNumber);
        if (ticket == null) {
            System.out.println("Invalid ticket number: " + ticketNumber);
            return 0.0;
        }

        ticket.setExitTimeStamp(new Date());
        ticket.setExit(exit);
        
        long durationInMillis = ticket.getExitTimeStamp().getTime() - ticket.getEntryTimeStamp().getTime();
        int durationInHours = (int) Math.ceil(durationInMillis / (1000.0 * 60 * 60));
        
        double amount = parkingRateStrategy.calculateParkingFee(
            ticket.getVehicle().getVehicleType(), 
            durationInHours, 
            DurationType.HOUR
        );
        
        ticket.setAmount(amount);
        
        System.out.println("Ticket processed for exit: " + ticketNumber + ", Amount: $" + amount);
        return amount;
    }

    /**
     * Removes a ticket from the system
     * @param ticketNumber The ticket number to remove
     */
    @Override
    public void removeTicket(String ticketNumber) {
        tickets.remove(ticketNumber);
    }

    /**
     * Gets a ticket by its number
     * @param ticketNumber The ticket number
     * @return ParkingTicket or null if not found
     */
    @Override
    public ParkingTicket getTicket(String ticketNumber) {
        return tickets.get(ticketNumber);
    }

    /**
     * Generates a unique ticket number
     * @return Unique ticket number
     */
    private String generateTicketNumber() {
        return "TKT-" + System.currentTimeMillis();
    }

    /**
     * Gets the count of active tickets
     * @return Number of active tickets
     */
    @Override
    public int getActiveTicketCount() {
        return tickets.size();
    }
} 