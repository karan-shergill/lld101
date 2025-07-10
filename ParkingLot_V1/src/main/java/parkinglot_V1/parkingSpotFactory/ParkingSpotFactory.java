package parkinglot_V1.parkingSpotFactory;

public class ParkingSpotFactory {
    public static ParkingSpot getParkingSpotByType(ParkingSpotType parkingSpotType) {
        return switch (parkingSpotType) {
            case COMPACT_SPOT -> new CompactSpot(parkingSpotType);
            case ELECTRIC_SPOT -> new ElectricSpot(parkingSpotType);
            case HANDICAPPED_SPOT -> new HandicappedSpot(parkingSpotType);
            case LARGE_SPOT -> new LargeSpot(parkingSpotType);
            case TWO_WHEELER_SPOT -> new TwoWheelerSpot(parkingSpotType);
        };
    }
    
    public static ParkingSpot getParkingSpotByType(String number, ParkingSpotType parkingSpotType) {
        return switch (parkingSpotType) {
            case COMPACT_SPOT -> new CompactSpot(number, parkingSpotType);
            case ELECTRIC_SPOT -> new ElectricSpot(number, parkingSpotType);
            case HANDICAPPED_SPOT -> new HandicappedSpot(number, parkingSpotType);
            case LARGE_SPOT -> new LargeSpot(number, parkingSpotType);
            case TWO_WHEELER_SPOT -> new TwoWheelerSpot(number, parkingSpotType);
        };
    }
}
