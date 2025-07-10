package parkinglot_V1.parkingSpotFactory;

public class CompactSpot extends ParkingSpot{
    public CompactSpot(String number, ParkingSpotType parkingSpotType) {
        super(number, parkingSpotType);
    }

    public CompactSpot(ParkingSpotType parkingSpotType) {
        super(parkingSpotType);
    }
}
