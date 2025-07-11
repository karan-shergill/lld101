package parkinglot.parkingRateStrategy;

import parkinglot.enums.DurationType;
import parkinglot.vehicleFactory.VehicleType;

public interface ParkingRateStrategy {
    double calculateParkingFee(VehicleType vehicleType, int duration, DurationType durationType);
}
