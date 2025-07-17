package parkinglot.parkingStrategy;

import parkinglot.parkingSpotFactory.ParkingSpot;
import parkinglot.parkingSpotFactory.ParkingSpotType;

public interface ParkingStrategy {
    ParkingSpot getParkingSpot(ParkingSpotType parkingSpotType);
}
