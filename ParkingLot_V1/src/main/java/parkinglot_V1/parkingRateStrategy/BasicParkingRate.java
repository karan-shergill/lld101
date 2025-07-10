package parkinglot_V1.parkingRateStrategy;

import parkinglot_V1.enums.DurationType;
import parkinglot_V1.vehicleFactory.VehicleType;

public class BasicParkingRate implements ParkingRateStrategy{
    @Override
    public double calculateParkingFee(VehicleType vehicleType, int duration, DurationType durationType) {
        switch (vehicleType) {
            case TWO_WHEELER -> {
                return durationType == DurationType.HOUR
                        ? duration * 50
                        : duration * 500;
            }

            case CAR -> {
                return durationType == DurationType.HOUR
                        ? duration * 100
                        : duration * 1000;
            }

            case AUTO -> {
                return durationType == DurationType.HOUR
                        ? duration * 70
                        : duration * 700;
            }

            case VAN -> {
                return durationType == DurationType.HOUR
                        ? duration * 120
                        : duration * 1200;
            }

            case TRUCK -> {
                return durationType == DurationType.HOUR
                        ? duration * 250
                        : duration * 2500;
            }

            default -> {
                return durationType == DurationType.HOUR
                        ? duration * 150
                        : duration * 1500;
            }
        }
    }
}
