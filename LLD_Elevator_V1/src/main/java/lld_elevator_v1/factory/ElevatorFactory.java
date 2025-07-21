package lld_elevator_v1.factory;

import lld_elevator_v1.enums.ElevatorType;
import lld_elevator_v1.models.Elevator;
import lld_elevator_v1.models.elevators.PassengerElevator;
import lld_elevator_v1.models.elevators.CargoElevator;
import lld_elevator_v1.models.elevators.ServiceElevator;

// Factory class for creating different types of elevators
public class ElevatorFactory {
    private static int elevatorCounter = 1;
    
    // Create an elevator of specified type
    public static Elevator createElevator(ElevatorType type) {
        String elevatorId = generateElevatorId(type);

        return switch (type) {
            case PASSENGER -> new PassengerElevator(elevatorId);
            case CARGO -> new CargoElevator(elevatorId);
            case SERVICE -> new ServiceElevator(elevatorId);
        };
    }
    
    // Generate unique elevator ID based on type
    private static String generateElevatorId(ElevatorType type) {
        String prefix = switch (type) {
            case PASSENGER -> "PASS";
            case CARGO -> "CARGO";
            case SERVICE -> "SVC";
        };
        return prefix + "-" + String.valueOf(elevatorCounter++);
    }
} 