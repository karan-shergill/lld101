package lld_elevator_v1.strategy;

import lld_elevator_v1.models.Elevator;
import lld_elevator_v1.models.Request;
import java.util.List;

/**
 * Strategy that selects the nearest available elevator
 * Simple distance-based selection
 */
public class NearestElevatorStrategy implements ElevatorSchedulingStrategy {
    
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        if (elevators.isEmpty()) {
            return null;
        }
        
        Elevator nearestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        
        for (Elevator elevator : elevators) {
            if (elevator.isAvailable()) {
                int distance = elevator.getDistanceToFloor(request.getSourceFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestElevator = elevator;
                }
            }
        }
        
        return nearestElevator;
    }
} 