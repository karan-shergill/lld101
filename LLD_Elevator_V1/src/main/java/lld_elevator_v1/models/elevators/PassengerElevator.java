package lld_elevator_v1.models.elevators;

import lld_elevator_v1.enums.ElevatorType;
import lld_elevator_v1.constants.Constants;
import lld_elevator_v1.models.Elevator;

/**
 * Passenger Elevator - Standard elevator for people transport
 * Features: Standard capacity, normal speed, serves all floors
 */
public class PassengerElevator extends Elevator {

    public PassengerElevator(String elevatorId) {
        super(elevatorId, ElevatorType.PASSENGER, Constants.DEFAULT_CAPACITY);
    }

} 