package parkinglot_V1.parkingStrategy;

import parkinglot_V1.parkingSpotFactory.ParkingSpot;
import parkinglot_V1.parkingSpotFactory.ParkingSpotType;

public interface ParkingStrategy {
    ParkingSpot getParkingSpot(ParkingSpotType parkingSpotType);
}
