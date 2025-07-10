package parkinglot_V1.vehicleFactory;

public class VehicleFactory {
    public static Vehicle getVehicleOfType(String licenceNo, VehicleType vehicleType) {
        if (licenceNo == null || licenceNo.trim().isEmpty()) {
            throw new IllegalArgumentException("License number cannot be null or empty");
        }
        
        return switch (vehicleType) {
            case CAR -> new Car(licenceNo, vehicleType);
            case VAN -> new Van(licenceNo, vehicleType);
            case TWO_WHEELER -> new TwoWheeler(licenceNo, vehicleType);
            case TRUCK -> new Truck(licenceNo, vehicleType);
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        };
    }
}
