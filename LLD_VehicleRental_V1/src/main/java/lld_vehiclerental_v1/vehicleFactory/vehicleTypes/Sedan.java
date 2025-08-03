package lld_vehiclerental_v1.vehicleFactory.vehicleTypes;

import lld_vehiclerental_v1.constants.VehicleType;
import lld_vehiclerental_v1.vehicleFactory.Vehicle;

public class Sedan extends Vehicle {
    public Sedan(String name, String vehicleNo) {
        super(name, vehicleNo);
        super.setVehicleType(VehicleType.SEDAN);
        super.setCapacity(5);
    }
}
