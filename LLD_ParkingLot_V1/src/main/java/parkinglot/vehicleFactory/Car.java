package parkinglot.vehicleFactory;

import parkinglot.parkingSpotFactory.ParkingSpotType;

public class Car extends Vehicle{
    public Car(String licenseNumber, VehicleType vehicleType) {
        super(licenseNumber, vehicleType);
    }
    
    @Override
    public ParkingSpotType getRequiredSpotType() {
        return ParkingSpotType.COMPACT_SPOT;
    }
}
