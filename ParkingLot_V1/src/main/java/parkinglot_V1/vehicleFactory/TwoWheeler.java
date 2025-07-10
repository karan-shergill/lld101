package parkinglot_V1.vehicleFactory;

import parkinglot_V1.parkingSpotFactory.ParkingSpotType;

public class TwoWheeler extends Vehicle{
    public TwoWheeler(String licenseNumber, VehicleType vehicleType) {
        super(licenseNumber, vehicleType);
    }
    
    @Override
    public ParkingSpotType getRequiredSpotType() {
        return ParkingSpotType.TWO_WHEELER_SPOT;
    }
}
