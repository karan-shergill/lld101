package parkinglot_V1.parkingSpotFactory;

import parkinglot_V1.vehicleFactory.Vehicle;

public abstract class ParkingSpot {
    private String number;
    private boolean isFree;
    private ParkingSpotType parkingSpotType;
    private Vehicle vehicle;

    public ParkingSpot(String number, ParkingSpotType parkingSpotType) {
        this.number = number;
        this.parkingSpotType = parkingSpotType;
    }

    public ParkingSpot(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isFree = false;
        return true;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        this.vehicle = null;
        this.isFree = true;
        return true;
    }
}
