package lld_elevator_v1.models;

import lld_elevator_v1.constants.Constants;
import lld_elevator_v1.controller.ElevatorController;
import java.util.ArrayList;
import java.util.List;

/**
 * Building class that contains floors and elevators
 * Represents the entire building structure
 */
public class Building {
    private final String buildingId;
    private final int totalFloors;
    private final List<Floor> floors;
    private final List<Elevator> elevators;
    
    public Building(String buildingId, int totalFloors) {
        this.buildingId = buildingId;
        this.totalFloors = totalFloors;
        this.floors = new ArrayList<>();
        this.elevators = new ArrayList<>();
        initializeFloors();
    }
    
    /**
     * Initialize all floors in the building
     */
    private void initializeFloors() {
        for (int i = 0; i < totalFloors; i++) {
            floors.add(new Floor(i, totalFloors));
        }
    }
    
    /**
     * Set controller reference for all floors
     */
    public void setControllerForFloors(ElevatorController controller) {
        for (Floor floor : floors) {
            floor.setController(controller);
        }
    }
    
    /**
     * Add an elevator to the building
     * Creates displays for this elevator on all floors
     */
    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
        
        // Create displays for this elevator on all floors
        for (Floor floor : floors) {
            floor.addElevatorDisplay(elevator.getElevatorId());
            
            // Add the specific display for this elevator as an observer
            Display elevatorDisplay = floor.getDisplayForElevator(elevator.getElevatorId());
            if (elevatorDisplay != null) {
                elevator.addObserver(elevatorDisplay);
            }
        }
        
        System.out.printf("Elevator %s added to building with displays on all %d floors%n", 
                elevator.getElevatorId(), totalFloors);
    }
    
    /**
     * Remove an elevator from the building
     * Removes all associated displays
     */
    public void removeElevator(String elevatorId) {
        Elevator elevatorToRemove = null;
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorId().equals(elevatorId)) {
                elevatorToRemove = elevator;
                break;
            }
        }
        
        if (elevatorToRemove != null) {
            elevators.remove(elevatorToRemove);
            
            // Remove displays for this elevator from all floors
            for (Floor floor : floors) {
                Display display = floor.getDisplayForElevator(elevatorId);
                if (display != null) {
                    elevatorToRemove.removeObserver(display);
                }
            }
            
            System.out.printf("Elevator %s removed from building%n", elevatorId);
        }
    }
    
    /**
     * Get floor by number
     */
    public Floor getFloor(int floorNumber) {
        if (floorNumber >= 0 && floorNumber < totalFloors) {
            return floors.get(floorNumber);
        }
        throw new IllegalArgumentException("Invalid floor number: " + floorNumber);
    }
    
    /**
     * Get all elevators in the building
     */
    public List<Elevator> getElevators() {
        return new ArrayList<>(elevators);
    }
    
    /**
     * Get available elevators (not out of service)
     */
    public List<Elevator> getAvailableElevators() {
        List<Elevator> available = new ArrayList<>();
        for (Elevator elevator : elevators) {
            if (elevator.isAvailable()) {
                available.add(elevator);
            }
        }
        return available;
    }
    
    /**
     * Display building status including all elevator displays
     */
    public void displayBuildingStatus() {
        System.out.println("\n=== BUILDING STATUS ===");
        System.out.printf("Building: %s%n", buildingId);
        System.out.printf("Total Floors: %d%n", totalFloors);
        System.out.printf("Total Elevators: %d%n", elevators.size());
        
        for (Floor floor : floors) {
            System.out.printf("Floor %d: %d displays%n", 
                    floor.getFloorNumber(), floor.getAllDisplays().size());
        }
        System.out.println("=======================\n");
    }
    
    // Getters
    public String getBuildingId() { return buildingId; }
    public int getTotalFloors() { return totalFloors; }
    public List<Floor> getFloors() { return new ArrayList<>(floors); }
    
    @Override
    public String toString() {
        return String.format("Building{id=%s, floors=%d, elevators=%d}", 
                buildingId, totalFloors, elevators.size());
    }
} 