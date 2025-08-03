package lld_vehiclerental_v1.vehicleFactory;

import lld_vehiclerental_v1.constants.VehicleType;
import lld_vehiclerental_v1.vehicleFactory.vehicleTypes.Sedan;
import lld_vehiclerental_v1.vehicleFactory.vehicleTypes.Traveler;
import lld_vehiclerental_v1.vehicleFactory.vehicleTypes.TwoWheeler;

public class VehicleFactory {
    public static Vehicle createVehicle(String name, String vehicleNo, VehicleType vehicleType) {
        switch (vehicleType) {
            case SEDAN -> {
                return new Sedan(name, vehicleNo);
            }
            case TRAVELER -> {
                return new Traveler(name, vehicleNo);
            }
            case TWO_WHEELER -> {
                return new TwoWheeler(name, vehicleNo);
            }
        }
        return null;
    }
}
