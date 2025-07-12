package parkinglot.vehicleFactory;

import parkinglot.parkingSpotFactory.ParkingSpotType;

public class TwoWheeler extends Vehicle{
    public TwoWheeler(String licenseNumber, VehicleType vehicleType) {
        super(licenseNumber, vehicleType);
    }
    
    @Override
    public ParkingSpotType getRequiredSpotType() {
        return ParkingSpotType.TWO_WHEELER_SPOT;
    }
}
