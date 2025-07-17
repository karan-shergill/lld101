package parkinglot.parkingLot;

import parkinglot.parkingSpotFactory.ParkingSpot;
import parkinglot.parkingSpotFactory.ParkingSpotType;
import parkinglot.parkingStrategy.ParkingStrategy;
import parkinglot.parkingRateStrategy.ParkingRateStrategy;
import parkinglot.parkingTicket.ParkingTicket;
import parkinglot.parkingTicket.TicketManager;
import parkinglot.parkingTicket.TicketService;
import parkinglot.vehicleFactory.Vehicle;

import java.util.*;

/**
 * Main ParkingLot class that coordinates parking operations
 * Follows Single Responsibility Principle by delegating specific operations to specialized managers
 * Implements ParkingLotService interface for better abstraction
 */
public class ParkingLot implements ParkingLotService {
    private String name;
    private List<Entrance> entrances;
    private List<Exit> exits;
    private TicketService ticketService;
    private ParkingSpotManager parkingSpotManager;

    public ParkingLot(String name, ParkingStrategy parkingStrategy, ParkingRateStrategy parkingRateStrategy) {
        this.name = name;
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
        this.ticketService = new TicketManager(parkingRateStrategy);
        this.parkingSpotManager = new ParkingSpotManager(new ArrayList<>(), parkingStrategy);
    }

    /**
     * Parks a vehicle in the parking lot
     * @param vehicle The vehicle to park
     * @param entrance The entrance used
     * @return ParkingTicket if successful, null otherwise
     */
    @Override
    public ParkingTicket parkVehicle(Vehicle vehicle, Entrance entrance) {
        if (vehicle == null) {
            System.out.println("Error: Vehicle cannot be null");
            return null;
        }

        ParkingSpotType requiredSpotType = vehicle.getRequiredSpotType();
        ParkingSpot availableSpot = parkingSpotManager.findAvailableSpot(requiredSpotType);
        
        if (availableSpot == null) {
            System.out.println("No parking spot available for vehicle: " + vehicle.getLicenseNumber());
            return null;
        }

        // Create ticket and assign spot
        ParkingTicket ticket = ticketService.createTicket(vehicle, entrance, availableSpot);
        parkingSpotManager.assignVehicleToSpot(vehicle, availableSpot);
        
        System.out.println("Vehicle " + vehicle.getLicenseNumber() + " parked at spot " + availableSpot.getNumber());
        return ticket;
    }

    /**
     * Processes vehicle exit from the parking lot
     * @param ticketNumber The parking ticket number
     * @param exit The exit used
     * @return The parking fee amount
     */
    @Override
    public double unparkVehicle(String ticketNumber, Exit exit) {
        if (ticketNumber == null || ticketNumber.trim().isEmpty()) {
            System.out.println("Error: Ticket number cannot be null or empty");
            return 0.0;
        }

        ParkingTicket ticket = ticketService.getTicket(ticketNumber);
        if (ticket == null) {
            System.out.println("Invalid ticket number: " + ticketNumber);
            return 0.0;
        }

        // Process exit and calculate fee
        double amount = ticketService.processTicketForExit(ticketNumber, exit);
        
        // Free up the parking spot
        parkingSpotManager.freeUpSpot(ticket.getParkingSpot());
        
        // Remove the ticket
        ticketService.removeTicket(ticketNumber);
        
        System.out.println("Vehicle " + ticket.getVehicle().getLicenseNumber() + " unparked. Amount: $" + amount);
        return amount;
    }

    /**
     * Adds a parking floor to the system
     * @param floor The parking floor to add
     */
    public void addFloor(ParkingFloor floor) {
        parkingSpotManager.addParkingFloor(floor);
    }

    /**
     * Adds an entrance to the parking lot
     * @param entrance The entrance to add
     */
    public void addEntrance(Entrance entrance) {
        entrances.add(entrance);
    }

    /**
     * Adds an exit to the parking lot
     * @param exit The exit to add
     */
    public void addExit(Exit exit) {
        exits.add(exit);
    }

    /**
     * Gets the name of the parking lot
     * @return Parking lot name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the number of available spots for a specific type
     * @param spotType The type of parking spot
     * @return Number of available spots
     */
    @Override
    public int getAvailableSpotCount(ParkingSpotType spotType) {
        return parkingSpotManager.getAvailableSpotCount(spotType);
    }

    /**
     * Gets the number of active tickets
     * @return Number of active tickets
     */
    @Override
    public int getActiveTicketCount() {
        return ticketService.getActiveTicketCount();
    }

    /**
     * Gets all entrances
     * @return List of entrances
     */
    public List<Entrance> getEntrances() {
        return new ArrayList<>(entrances);
    }

    /**
     * Gets all exits
     * @return List of exits
     */
    public List<Exit> getExits() {
        return new ArrayList<>(exits);
    }

    /**
     * Gets all parking floors
     * @return List of parking floors
     */
    public List<ParkingFloor> getParkingFloors() {
        return parkingSpotManager.getParkingFloors();
    }
}
