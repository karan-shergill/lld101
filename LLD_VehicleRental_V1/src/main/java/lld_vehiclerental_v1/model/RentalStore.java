package lld_vehiclerental_v1.model;

import lld_vehiclerental_v1.constants.VehicleStatus;
import lld_vehiclerental_v1.vehicleFactory.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalStore {
    private String name;
    private String storeId;
    private Location address;
    // vehicle that are available for booking(registrationNo: Vehicle)
    private Map<String,Vehicle> availableVehiclesAtStore;

    public RentalStore(String storeId, String name, Location address) {
        this.storeId = storeId;
        this.name = name;
        this.address = address;
        this.availableVehiclesAtStore = new HashMap<>();
    }

    public void addVehicleToStore(Vehicle vehicle) {
        availableVehiclesAtStore.put(vehicle.getRegistrationNo(), vehicle);
    }

    public boolean removeVehicleFromStore(Vehicle vehicle) {
        if (!availableVehiclesAtStore.containsKey(vehicle.getRegistrationNo())) {
            System.out.println("This vehicle is not present at store " + this.name);
            return false;
        }
        availableVehiclesAtStore.remove(vehicle.getRegistrationNo());
        return true;
    }

    public boolean isVehicleAvailable(String vehicleRegistrationNumber, LocalDate startDate, LocalDate endDate) {
        Vehicle vehicle = availableVehiclesAtStore.get(vehicleRegistrationNumber);
        return vehicle != null
                && vehicle.getVehicleStatus() == VehicleStatus.AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public String getStoreId() {
        return storeId;
    }

    public Location getAddress() {
        return address;
    }

    public List<Vehicle> getAvailableVehiclesAtStore(LocalDate startDate, LocalDate endDate) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : availableVehiclesAtStore.values()) {
            if (vehicle.getVehicleStatus() == VehicleStatus.AVAILABLE) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public Vehicle getVehicle(String registrationNumber) {
        return availableVehiclesAtStore.get(registrationNumber);
    }
}
