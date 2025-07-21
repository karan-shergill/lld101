package lld_elevator_v1.strategy;

import lld_elevator_v1.models.Elevator;
import lld_elevator_v1.models.Request;
import lld_elevator_v1.enums.Direction;
import lld_elevator_v1.enums.ElevatorState;
import java.util.List;

/**
 * Strategy that implements SCAN algorithm (elevator continues in same direction)
 * Selects elevator that is moving in the same direction as the request
 * or the nearest idle elevator
 */
public class ScanElevatorStrategy implements ElevatorSchedulingStrategy {
    
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        if (elevators.isEmpty()) {
            return null;
        }
        
        Elevator bestElevator = null;
        int minCost = Integer.MAX_VALUE;
        
        for (Elevator elevator : elevators) {
            if (!elevator.isAvailable()) {
                continue;
            }
            
            int cost = calculateCost(elevator, request);
            if (cost < minCost) {
                minCost = cost;
                bestElevator = elevator;
            }
        }
        
        return bestElevator;
    }
    
    /**
     * Calculate cost for assigning request to an elevator
     * Lower cost means better choice
     */
    private int calculateCost(Elevator elevator, Request request) {
        int sourceFloor = request.getSourceFloor();
        int elevatorFloor = elevator.getCurrentFloor();
        Direction elevatorDirection = elevator.getCurrentDirection();
        Direction requestDirection = request.getDirection();
        ElevatorState elevatorState = elevator.getCurrentState();
        
        // If elevator is idle, cost is just the distance
        if (elevatorState == ElevatorState.IDLE) {
            return Math.abs(elevatorFloor - sourceFloor);
        }
        
        // If elevator is moving in same direction and will pass the request floor
        if (elevatorDirection == requestDirection) {
            if (requestDirection == Direction.UP && elevatorFloor <= sourceFloor) {
                return sourceFloor - elevatorFloor; // Direct pickup
            } else if (requestDirection == Direction.DOWN && elevatorFloor >= sourceFloor) {
                return elevatorFloor - sourceFloor; // Direct pickup
            }
        }
        
        // If elevator needs to change direction or go out of way
        // Higher cost but still possible
        int distance = Math.abs(elevatorFloor - sourceFloor);
        
        // Add penalty for direction change
        if (elevatorDirection != requestDirection && elevatorDirection != Direction.NONE) {
            distance += 50; // Penalty for changing direction
        }
        
        // Add penalty if elevator has many pending requests
        if (elevator.hasRequests()) {
            distance += 20; // Penalty for busy elevator
        }
        
        return distance;
    }
} 