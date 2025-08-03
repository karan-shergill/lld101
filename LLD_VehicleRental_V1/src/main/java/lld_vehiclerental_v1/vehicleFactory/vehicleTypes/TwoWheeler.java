package lld_vehiclerental_v1.vehicleFactory.vehicleTypes;

import lld_vehiclerental_v1.constants.VehicleType;
import lld_vehiclerental_v1.vehicleFactory.Vehicle;

public class TwoWheeler extends Vehicle {
    public TwoWheeler(String name, String vehicleNo) {
        super(name, vehicleNo);
        super.setVehicleType(VehicleType.TWO_WHEELER);
        super.setCapacity(2);
    }
}
