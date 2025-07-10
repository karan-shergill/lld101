package parkinglot_V1.parkingSpotFactory;

public class TwoWheelerSpot extends ParkingSpot {
    public TwoWheelerSpot(String number, ParkingSpotType parkingSpotType) {
        super(number, parkingSpotType);
    }

    public TwoWheelerSpot(ParkingSpotType parkingSpotType) {
        super(parkingSpotType);
    }
}
