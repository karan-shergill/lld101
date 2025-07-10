package parkinglot_V1.parkingStrategy;

import parkinglot_V1.parkingSpotFactory.ParkingSpot;
import parkinglot_V1.parkingSpotFactory.ParkingSpotType;
import parkinglot_V1.parkingLot.ParkingFloor;

import java.util.List;

public class NearestParking implements ParkingStrategy {
    private List<ParkingFloor> floors;
    
    public NearestParking(List<ParkingFloor> floors) {
        this.floors = floors;
    }
    
    @Override
    public ParkingSpot getParkingSpot(ParkingSpotType parkingSpotType) {
        // Find the nearest available spot by checking floors in order
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.getAvailableSpot(parkingSpotType);
            if (spot != null) {
                return spot;
            }
        }
        return null; // No available spot found
    }
}
