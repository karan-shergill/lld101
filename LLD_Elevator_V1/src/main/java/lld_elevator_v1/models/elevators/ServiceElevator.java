package lld_elevator_v1.models.elevators;

import lld_elevator_v1.enums.ElevatorType;
import lld_elevator_v1.constants.Constants;
import lld_elevator_v1.models.Elevator;

/**
 * Service Elevator - Maintenance and utility elevator
 * Features: Limited capacity, restricted floor access, faster speed
 */
public class ServiceElevator extends Elevator {

    public ServiceElevator(String elevatorId) {
        super(elevatorId, ElevatorType.SERVICE, Constants.SERVICE_CAPACITY);
    }

} 