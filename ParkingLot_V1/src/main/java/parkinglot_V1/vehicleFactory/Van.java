package parkinglot_V1.vehicleFactory;

import parkinglot_V1.parkingSpotFactory.ParkingSpotType;

public class Van extends Vehicle{
    public Van(String licenseNumber, VehicleType vehicleType) {
        super(licenseNumber, vehicleType);
    }
    
    @Override
    public ParkingSpotType getRequiredSpotType() {
        return ParkingSpotType.LARGE_SPOT;
    }
}
