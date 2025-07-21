package lld_elevator_v1.controller;

import lld_elevator_v1.models.Building;
import lld_elevator_v1.models.Elevator;
import lld_elevator_v1.models.Request;
import lld_elevator_v1.enums.Direction;
import lld_elevator_v1.strategy.ElevatorSchedulingStrategy;
import lld_elevator_v1.strategy.ScanElevatorStrategy;

import java.util.List;

// Singleton controller for managing all elevators in the building
public class ElevatorController {
    private static volatile ElevatorController instance;

    private Building building;
    private ElevatorSchedulingStrategy schedulingStrategy;
    

    private ElevatorController() {
        this.schedulingStrategy = new ScanElevatorStrategy(); // Default strategy
    }

    public synchronized static ElevatorController getInstance() {
        if (instance == null) {
            instance = new ElevatorController();
        }
        return instance;
    }
    
    // Initialize controller with a building
    public void initializeBuilding(Building building) {
        this.building = building;
        building.setControllerForFloors(this); // Set controller reference for floors
    }

    public void setSchedulingStrategy(ElevatorSchedulingStrategy strategy) {
        this.schedulingStrategy = strategy;
        System.out.printf("Scheduling strategy changed to: %s%n", strategy.getClass().getSimpleName());
    }

    // Request an elevator from a specific floor
    public void requestElevator(int sourceFloor, Direction direction) {
        Request request = new Request(sourceFloor, direction);
        // Process request synchronously
        processRequest(request);
    }

    //  Request elevator with destination (when user is inside elevator)
    public void requestFloor(String elevatorId, int destinationFloor) {
        Elevator elevator = findElevatorById(elevatorId);
        elevator.addRequest(destinationFloor);
    }
    
    // Process elevator request - from a floor
    private void processRequest(Request request) {
        List<Elevator> availableElevators = building.getAvailableElevators();
        
        if (availableElevators.isEmpty()) {
            System.err.println("No available elevators!");
            return;
        }
        
        Elevator selectedElevator = schedulingStrategy.selectElevator(availableElevators, request);
        
        if (selectedElevator != null) {
            selectedElevator.addRequest(request.getSourceFloor());
        } else {
            System.err.println("No elevator could be assigned to the request!");
        }
    }
    
   // Find elevator by ID
    private Elevator findElevatorById(String elevatorId) {
        return building.getElevators().stream()
                .filter(elevator -> elevator.getElevatorId().equals(elevatorId))
                .findFirst()
                .orElse(null);
    }
    
   // Get current status of all elevators
    public void displaySystemStatus() {
        if (building == null) {
            System.err.println("Building not initialized!");
            return;
        }
        
        System.out.println("\n=== ELEVATOR SYSTEM STATUS ===");
        System.out.printf("Building: %s%n", building.getBuildingId());
        System.out.printf("Strategy: %s%n", schedulingStrategy.getClass().getSimpleName());
        System.out.println("Elevators:");
        
        for (Elevator elevator : building.getElevators()) {
            System.out.printf("  %s - Floor: %d, Direction: %s, State: %s%n",
                    elevator.getElevatorId(),
                    elevator.getCurrentFloor(),
                    elevator.getCurrentDirection(),
                    elevator.getCurrentState());
        }
        System.out.println("==============================\n");
    }
} 