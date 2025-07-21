package lld_elevator_v1.strategy;

import lld_elevator_v1.models.Elevator;
import lld_elevator_v1.models.Request;
import lld_elevator_v1.enums.ElevatorState;
import java.util.List;

/**
 * Strategy that balances load among elevators
 * Prefers elevators with fewer pending requests
 */
public class LoadBalancingStrategy implements ElevatorSchedulingStrategy {
    
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        if (elevators.isEmpty()) {
            return null;
        }
        
        Elevator bestElevator = null;
        double bestScore = Double.MIN_VALUE;
        
        for (Elevator elevator : elevators) {
            if (!elevator.isAvailable()) {
                continue;
            }
            
            double score = calculateScore(elevator, request);
            if (score > bestScore) {
                bestScore = score;
                bestElevator = elevator;
            }
        }
        
        return bestElevator;
    }
    
    /**
     * Calculate score for elevator selection
     * Higher score means better choice
     */
    private double calculateScore(Elevator elevator, Request request) {
        double score = 100.0; // Base score
        
        // Distance factor (closer is better)
        int distance = elevator.getDistanceToFloor(request.getSourceFloor());
        score -= distance * 2.0;
        
        // Load factor (less busy is better)
        if (elevator.hasRequests()) {
            score -= 30.0; // Penalty for having pending requests
        }
        
        // State factor (idle elevators are preferred)
        if (elevator.getCurrentState() == ElevatorState.IDLE) {
            score += 20.0; // Bonus for idle elevator
        }
        
        // Direction compatibility
        if (elevator.getCurrentDirection() == request.getDirection()) {
            score += 15.0; // Bonus for same direction
        }
        
        return score;
    }
} 