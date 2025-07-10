package parkinglot_V1.parkingRateStrategy;

import parkinglot_V1.enums.DurationType;
import parkinglot_V1.vehicleFactory.VehicleType;

public interface ParkingRateStrategy {
    double calculateParkingFee(VehicleType vehicleType, int duration, DurationType durationType);
}
