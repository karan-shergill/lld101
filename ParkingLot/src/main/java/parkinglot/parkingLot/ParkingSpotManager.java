package parkinglot.parkingLot;

import parkinglot.parkingSpotFactory.ParkingSpot;
import parkinglot.parkingSpotFactory.ParkingSpotType;
import parkinglot.vehicleFactory.Vehicle;
import parkinglot.parkingStrategy.ParkingStrategy;

import java.util.List;

/**
 * Manages parking spot operations including assignment and freeing
 * Follows Single Responsibility Principle
 */
public class ParkingSpotManager {
    private List<ParkingFloor> parkingFloors;
    private ParkingStrategy parkingStrategy;

    public ParkingSpotManager(List<ParkingFloor> parkingFloors, ParkingStrategy parkingStrategy) {
        this.parkingFloors = parkingFloors;
        this.parkingStrategy = parkingStrategy;
    }

    /**
     * Finds an available parking spot for the given type
     * @param spotType The type of parking spot required
     * @return ParkingSpot if available, null otherwise
     */
    public ParkingSpot findAvailableSpot(ParkingSpotType spotType) {
        return parkingStrategy.getParkingSpot(spotType);
    }

    /**
     * Assigns a vehicle to a parking spot
     * @param vehicle The vehicle to assign
     * @param spot The parking spot to assign
     */
    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
        spot.assignVehicle(vehicle);
        
        // Update all floors to reflect the assignment
        for (ParkingFloor floor : parkingFloors) {
            if (floor.getParkingSpots().values().stream().anyMatch(spots -> spots.contains(spot))) {
                floor.assignVehicleToSpot(vehicle, spot);
                break;
            }
        }
        
        System.out.println("Vehicle " + vehicle.getLicenseNumber() + " assigned to spot " + spot.getNumber());
    }

    /**
     * Frees up a parking spot when a vehicle leaves
     * @param spot The parking spot to free
     */
    public void freeUpSpot(ParkingSpot spot) {
        Vehicle vehicle = spot.getVehicle();
        String vehicleLicense = vehicle != null ? vehicle.getLicenseNumber() : "Unknown";
        
        spot.removeVehicle(vehicle);
        
        // Update all floors to reflect the freed spot
        for (ParkingFloor floor : parkingFloors) {
            if (floor.getParkingSpots().values().stream().anyMatch(spots -> spots.contains(spot))) {
                floor.freeUpSpot(spot);
                break;
            }
        }
        
        System.out.println("Parking spot " + spot.getNumber() + " freed for vehicle " + vehicleLicense);
    }

    /**
     * Adds a parking floor to the system
     * @param floor The parking floor to add
     */
    public void addParkingFloor(ParkingFloor floor) {
        parkingFloors.add(floor);
    }

    /**
     * Gets the total number of available spots of a specific type
     * @param spotType The type of parking spot
     * @return Number of available spots
     */
    public int getAvailableSpotCount(ParkingSpotType spotType) {
        return parkingFloors.stream()
                .mapToInt(floor -> floor.getAvailableSpotCount(spotType))
                .sum();
    }

    /**
     * Gets all parking floors
     * @return List of parking floors
     */
    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }
} 