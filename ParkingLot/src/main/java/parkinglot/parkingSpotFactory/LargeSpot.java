package parkinglot.parkingSpotFactory;

public class LargeSpot extends ParkingSpot {
    public LargeSpot(String number, ParkingSpotType parkingSpotType) {
        super(number, parkingSpotType);
    }

    public LargeSpot(ParkingSpotType parkingSpotType) {
        super(parkingSpotType);
    }
} 