package parkinglot.parkingLot;

import parkinglot.parkingSpotFactory.ParkingSpot;
import parkinglot.parkingSpotFactory.ParkingSpotType;
import parkinglot.vehicleFactory.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingFloor {
    private String name;
    private ParkingDisplayBoard parkingDisplayBoard;
    private HashMap<ParkingSpotType, List<ParkingSpot>> parkingSpot;

    public ParkingFloor(String name) {
        this.name = name;
        parkingDisplayBoard = new ParkingDisplayBoard(name);
        parkingSpot = new HashMap<>();
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot.computeIfAbsent(parkingSpot.getParkingSpotType(), k -> new ArrayList<>()).add(parkingSpot);
        this.parkingDisplayBoard.addFreeParkingSpot(parkingSpot);
        parkingSpot.setFree(true); // Initialize as free
    }

    public ParkingSpot getAvailableSpot(ParkingSpotType parkingSpotType) {
        List<ParkingSpot> spots = parkingSpot.get(parkingSpotType);
        if (spots != null) {
            return spots.stream().filter(ParkingSpot::isFree).findFirst().orElse(null);
        }
        return null;
    }

    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot parkingSpot) {
        parkingSpot.assignVehicle(vehicle);
        this.parkingDisplayBoard.removeFreeParkingSpot(parkingSpot);
        parkingSpot.setFree(false);
    }

    /**
     * Frees up a parking spot when a vehicle leaves
     * @param parkingSpot The parking spot to free up
     */
    public void freeUpSpot(ParkingSpot parkingSpot) {
        parkingSpot.removeVehicle(parkingSpot.getVehicle());
        this.parkingDisplayBoard.addFreeParkingSpot(parkingSpot);
        parkingSpot.setFree(true);
    }

    /**
     * Gets the count of available spots for a specific type
     * @param parkingSpotType The type of parking spot
     * @return The count of available spots
     */
    public int getAvailableSpotCount(ParkingSpotType parkingSpotType) {
        List<ParkingSpot> spots = parkingSpot.get(parkingSpotType);
        if (spots != null) {
            return (int) spots.stream().filter(ParkingSpot::isFree).count();
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public ParkingDisplayBoard getParkingDisplayBoard() {
        return parkingDisplayBoard;
    }

    public HashMap<ParkingSpotType, List<ParkingSpot>> getParkingSpots() {
        return parkingSpot;
    }
}
