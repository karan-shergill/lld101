package lld_elevator_v1.models;

import lld_elevator_v1.enums.Direction;
import lld_elevator_v1.observer.Observer;

/**
 * Display class that implements Observer pattern
 * Shows elevator status on floors and inside elevators
 * Each display is associated with a specific elevator
 */
public class Display implements Observer {
    private final String displayId;
    private final int floorNumber;
    private final boolean isInsideElevator;
    private final String elevatorId; // ID of the elevator this display belongs to
    
    // Constructor for displays with elevator association
    public Display(String displayId, int floorNumber, boolean isInsideElevator, String elevatorId) {
        this.displayId = displayId;
        this.floorNumber = floorNumber;
        this.isInsideElevator = isInsideElevator;
        this.elevatorId = elevatorId;
    }
    
    @Override
    public void update(int currentFloor, Direction direction, int nextStop) {
        if (isInsideElevator) {
            showElevatorDisplay(currentFloor, direction, nextStop);
        } else {
            showFloorDisplay(currentFloor, direction);
        }
    }
    
    private void showElevatorDisplay(int currentFloor, Direction direction, int nextStop) {
        String directionSymbol = getDirectionSymbol(direction);
        System.out.printf("[Elevator %s Display] Floor: %d | Direction: %s | Next Stop: %d%n", 
                elevatorId, currentFloor, directionSymbol, nextStop);
    }
    
    private void showFloorDisplay(int currentFloor, Direction direction) {
        if (currentFloor == floorNumber) {
            String directionSymbol = getDirectionSymbol(direction);
            System.out.printf("[Floor %d - Elevator %s Display] Elevator arrived - Direction: %s%n", 
                    floorNumber, elevatorId, directionSymbol);
        }
    }
    
    private String getDirectionSymbol(Direction direction) {
        return switch (direction) {
            case UP -> "↑";
            case DOWN -> "↓";
            default -> "-";
        };
    }
    
    // Getters
    public String getDisplayId() { return displayId; }
    public int getFloorNumber() { return floorNumber; }
    public boolean isInsideElevator() { return isInsideElevator; }
    public String getElevatorId() { return elevatorId; }
    
    @Override
    public String toString() {
        return String.format("Display{id=%s, floor=%d, elevator=%s, inside=%b}", 
                displayId, floorNumber, elevatorId, isInsideElevator);
    }
} 