package parkinglot.vehicleFactory;

import parkinglot.parkingTicket.ParkingTicket;
import parkinglot.parkingSpotFactory.ParkingSpotType;

public abstract class Vehicle {
    private String licenseNumber;
    private VehicleType vehicleType;
    private ParkingTicket parkingTicket;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
        this.parkingTicket = null; // Will be set when vehicle is parked
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }
    
    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }
    
    /**
     * Abstract method to determine the required parking spot type for this vehicle
     * Each vehicle type must implement this method according to its requirements
     * @return ParkingSpotType required for this vehicle
     */
    public abstract ParkingSpotType getRequiredSpotType();
}
