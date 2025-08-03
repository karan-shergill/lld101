package lld_vehiclerental_v1.vehicleFactory.vehicleTypes;

import lld_vehiclerental_v1.constants.VehicleType;
import lld_vehiclerental_v1.vehicleFactory.Vehicle;

public class Traveler extends Vehicle {
    public Traveler(String name, String vehicleNo) {
        super(name, vehicleNo);
        super.setVehicleType(VehicleType.TRAVELER);
        super.setCapacity(13);
    }
}
