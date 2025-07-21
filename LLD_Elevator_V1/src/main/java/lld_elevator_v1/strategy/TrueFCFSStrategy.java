package lld_elevator_v1.strategy;

import lld_elevator_v1.models.Elevator;
import lld_elevator_v1.models.Request;
import java.util.List;

/**
 * True First Come First Serve (FCFS) Scheduling Strategy
 * Simply assigns each request to the first available elevator in the list
 * No complex scoring - just pure FCFS order
 */
public class TrueFCFSStrategy implements ElevatorSchedulingStrategy {
    
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        if (elevators.isEmpty()) {
            return null;
        }
        
        // True FCFS: Simply return the first available elevator
        for (int i = 0; i < elevators.size(); i++) {
            Elevator elevator = elevators.get(i);
            
            if (elevator.isAvailable()) {
                return elevator;
            }
        }
        
        System.out.println("True FCFS: No available elevators found");
        return null;
    }
    
    @Override
    public String toString() {
        return "TrueFCFSStrategy{simple first-available selection}";
    }
} 