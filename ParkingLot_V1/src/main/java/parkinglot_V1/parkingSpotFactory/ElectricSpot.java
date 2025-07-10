package parkinglot_V1.parkingSpotFactory;

public class ElectricSpot extends ParkingSpot{
    public ElectricSpot(String number, ParkingSpotType parkingSpotType) {
        super(number, parkingSpotType);
    }

    public ElectricSpot(ParkingSpotType parkingSpotType) {
        super(parkingSpotType);
    }
}
