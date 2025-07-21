package lld_elevator_v1.enums;

/**
 * Enum representing different states of an elevator
 * Used in State Design Pattern
 */
public enum ElevatorState {
    IDLE,        // Elevator is not moving and has no pending requests
    MOVING_UP,   // Elevator is moving upward
    MOVING_DOWN, // Elevator is moving downward
    STOPPED,     // Elevator has stopped at a floor
    OUT_OF_SERVICE // Elevator is out of service
} 