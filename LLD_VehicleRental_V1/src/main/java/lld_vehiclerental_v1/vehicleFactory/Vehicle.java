package lld_vehiclerental_v1.vehicleFactory;

import lld_vehiclerental_v1.constants.VehicleStatus;
import lld_vehiclerental_v1.constants.VehicleType;

public abstract class Vehicle {
    private String name;
    private String registrationNo;
    private VehicleStatus vehicleStatus;
    private VehicleType vehicleType;
    private int capacity;

    public Vehicle(String name, String vehicleNo) {
        this.name = name;
        this.registrationNo = vehicleNo;
        this.vehicleStatus = VehicleStatus.AVAILABLE;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }
}
