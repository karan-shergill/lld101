package parkinglot.parkingRateStrategy;

import parkinglot.enums.DurationType;
import parkinglot.vehicleFactory.VehicleType;

public class PremiumParkingRate implements ParkingRateStrategy{
    @Override
    public double calculateParkingFee(VehicleType vehicleType, int duration, DurationType durationType) {
        switch (vehicleType) {
            case TWO_WHEELER -> {
                return durationType == DurationType.HOUR
                        ? duration * 500
                        : duration * 5000;
            }

            case CAR -> {
                return durationType == DurationType.HOUR
                        ? duration * 1000
                        : duration * 10000;
            }

            case AUTO -> {
                return durationType == DurationType.HOUR
                        ? duration * 700
                        : duration * 7000;
            }

            case VAN -> {
                return durationType == DurationType.HOUR
                        ? duration * 1200
                        : duration * 12000;
            }

            case TRUCK -> {
                return durationType == DurationType.HOUR
                        ? duration * 2500
                        : duration * 25000;
            }

            default -> {
                return durationType == DurationType.HOUR
                        ? duration * 1500
                        : duration * 15000;
            }
        }
    }
}
