package parkinglot.vehicleFactory;

import parkinglot.parkingSpotFactory.ParkingSpotType;

public class Truck extends Vehicle{
    public Truck(String licenseNumber, VehicleType vehicleType) {
        super(licenseNumber, vehicleType);
    }
    
    @Override
    public ParkingSpotType getRequiredSpotType() {
        return ParkingSpotType.LARGE_SPOT;
    }
}
