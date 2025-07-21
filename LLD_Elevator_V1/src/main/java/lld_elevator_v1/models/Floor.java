package lld_elevator_v1.models;

import lld_elevator_v1.enums.Direction;
import lld_elevator_v1.controller.ElevatorController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a floor in the building
 * Contains up/down buttons and multiple displays (one for each elevator)
 */
public class Floor {
    private final int floorNumber;
    private final boolean hasUpButton;
    private final boolean hasDownButton;
    private final List<Display> elevatorDisplays;
    private final Map<String, Display> displayByElevatorId;
    private ElevatorController controller;
    
    public Floor(int floorNumber, int totalFloors) {
        this.floorNumber = floorNumber;
        this.hasUpButton = floorNumber < totalFloors - 1; // No up button on top floor
        this.hasDownButton = floorNumber > 0; // No down button on ground floor
        this.elevatorDisplays = new ArrayList<>();
        this.displayByElevatorId = new HashMap<>();
    }
    
    /**
     * Add a display for a specific elevator
     */
    public void addElevatorDisplay(String elevatorId) {
        String displayId = String.format("Floor-%d-Elevator-%s", floorNumber, elevatorId);
        Display display = new Display(displayId, floorNumber, false, elevatorId);
        elevatorDisplays.add(display);
        displayByElevatorId.put(elevatorId, display);
    }
    
    /**
     * Get display for a specific elevator
     */
    public Display getDisplayForElevator(String elevatorId) {
        return displayByElevatorId.get(elevatorId);
    }
    
    /**
     * Get all elevator displays on this floor
     */
    public List<Display> getAllDisplays() {
        return new ArrayList<>(elevatorDisplays);
    }
    
    /**
     * Set the elevator controller for this floor
     */
    public void setController(ElevatorController controller) {
        this.controller = controller;
    }
    
    /**
     * Press up button on this floor
     */
    public void pressUpButton() {
        if (hasUpButton && controller != null) {
            System.out.printf("UP button pressed on floor %d%n", floorNumber);
            controller.requestElevator(floorNumber, Direction.UP);
        }
    }
    
    /**
     * Press down button on this floor
     */
    public void pressDownButton() {
        if (hasDownButton && controller != null) {
            System.out.printf("DOWN button pressed on floor %d%n", floorNumber);
            controller.requestElevator(floorNumber, Direction.DOWN);
        }
    }
    
    // Getters
    public int getFloorNumber() { return floorNumber; }
    public boolean hasUpButton() { return hasUpButton; }
    public boolean hasDownButton() { return hasDownButton; }

    
    @Override
    public String toString() {
        return String.format("Floor{number=%d, hasUp=%b, hasDown=%b, displays=%d}", 
                floorNumber, hasUpButton, hasDownButton, elevatorDisplays.size());
    }
} 