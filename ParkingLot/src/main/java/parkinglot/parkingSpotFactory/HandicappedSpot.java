package parkinglot.parkingSpotFactory;

public class HandicappedSpot extends ParkingSpot {
    public HandicappedSpot(String number, ParkingSpotType parkingSpotType) {
        super(number, parkingSpotType);
    }

    public HandicappedSpot(ParkingSpotType parkingSpotType) {
        super(parkingSpotType);
    }
}
