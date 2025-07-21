package lld_elevator_v1.observer;

import lld_elevator_v1.enums.Direction;

/**
 * Observer interface for the Observer Design Pattern
 * Used to notify displays and other components about elevator status changes
 */
public interface Observer {
    /**
     * Called when elevator status changes
     * @param currentFloor Current floor of the elevator
     * @param direction Current direction of movement
     * @param nextStop Next scheduled stop
     */
    void update(int currentFloor, Direction direction, int nextStop);
} 