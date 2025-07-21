package lld_elevator_v1.models.elevators;

import lld_elevator_v1.enums.ElevatorType;
import lld_elevator_v1.constants.Constants;
import lld_elevator_v1.models.Elevator;

/**
 * Cargo Elevator - Heavy-duty elevator for freight transport
 * Features: High capacity, slower speed, longer stop time for loading
 */
public class CargoElevator extends Elevator {

    public CargoElevator(String elevatorId) {
        super(elevatorId, ElevatorType.CARGO, Constants.CARGO_CAPACITY);
    }

} 