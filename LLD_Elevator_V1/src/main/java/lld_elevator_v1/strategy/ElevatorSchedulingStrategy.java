package lld_elevator_v1.strategy;

import lld_elevator_v1.models.Elevator;
import lld_elevator_v1.models.Request;
import java.util.List;

/**
 * Strategy interface for elevator scheduling algorithms
 * Implements Strategy Design Pattern
 */
public interface ElevatorSchedulingStrategy {
    /**
     * Select the best elevator for a given request
     * @param elevators List of available elevators
     * @param request The elevator request
     * @return Selected elevator, or null if none available
     */
    Elevator selectElevator(List<Elevator> elevators, Request request);
} 