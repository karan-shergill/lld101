package lld_elevator_v1.models;

import lld_elevator_v1.enums.Direction;
import lld_elevator_v1.enums.ElevatorState;
import lld_elevator_v1.enums.ElevatorType;
import lld_elevator_v1.observer.Observer;
import lld_elevator_v1.observer.Subject;
import lld_elevator_v1.constants.Constants;

import java.util.*;

public abstract class Elevator implements Subject {
    protected final String elevatorId;
    protected final ElevatorType type;
    protected final int capacity;
    protected int currentFloor;
    protected Direction currentDirection;
    protected ElevatorState currentState;
    protected final Queue<Integer> upRequests;
    protected final Queue<Integer> downRequests;
    protected final List<Observer> observers;
    protected final Display elevatorDisplay;
    
    public Elevator(String elevatorId, ElevatorType type, int capacity) {
        this.elevatorId = elevatorId;
        this.type = type;
        this.capacity = capacity;
        this.currentFloor = Constants.GROUND_FLOOR;
        this.currentDirection = Direction.NONE;
        this.currentState = ElevatorState.IDLE;
        this.upRequests = new PriorityQueue<>();
        this.downRequests = new PriorityQueue<>(Collections.reverseOrder());
        this.observers = new ArrayList<>();
        this.elevatorDisplay = new Display("Elevator-" + elevatorId, currentFloor, true, elevatorId);
        addObserver(elevatorDisplay);
    }
    
    // Add a floor request to the elevator - requested from inside the elevator
    public void addRequest(int targetFloor) {
        if (targetFloor == currentFloor) {
            // Already at the requested floor
            return;
        }

        if (targetFloor > currentFloor) {
            upRequests.add(targetFloor);
        } else {
            downRequests.add(targetFloor);
        }

        if (currentState == ElevatorState.IDLE) {
            processNextRequest();
        }
    }

    // Process the next request in queue
    public void processNextRequest() {
        int nextFloor = getNextTargetFloor();
        if (nextFloor != -1) {
            moveToFloor(nextFloor);
        } else {
            currentState = ElevatorState.IDLE;
            currentDirection = Direction.NONE;
            notifyObservers();
        }
    }
    
    // Get the next target floor based on current direction
    private int getNextTargetFloor() {
        if (currentDirection == Direction.UP || currentDirection == Direction.NONE) {
            if (!upRequests.isEmpty()) {
                currentDirection = Direction.UP;
                return upRequests.poll();
            } else if (!downRequests.isEmpty()) {
                currentDirection = Direction.DOWN;
                return downRequests.poll();
            }
        } else if (currentDirection == Direction.DOWN) {
            if (!downRequests.isEmpty()) {
                return downRequests.poll();
            } else if (!upRequests.isEmpty()) {
                currentDirection = Direction.UP;
                return upRequests.poll();
            }
        }
        return -1;
    }

    // Move elevator to target floor
    private void moveToFloor(int targetFloor) {
        if (targetFloor == currentFloor) {
            return;
        }

        System.out.println("Elevator " + elevatorId + " moving from floor " + currentFloor + " to floor " + targetFloor);
        
        currentState = targetFloor > currentFloor ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN;
        currentDirection = targetFloor > currentFloor ? Direction.UP : Direction.DOWN;
        
        // Simulate movement (simplified - direct floor change)
        currentFloor = targetFloor;
        
        // Arrived at target floor
        currentState = ElevatorState.STOPPED;
        notifyObservers();
        
        System.out.println("Elevator " + elevatorId + " arrived  at floor " + currentFloor);
        
        processNextRequest();
    }

    // Check if elevator is available to take new requests
    public boolean isAvailable() {
        return currentState != ElevatorState.OUT_OF_SERVICE;
    }

    // Calculate distance to a floor (used for scheduling)
    public int getDistanceToFloor(int floor) {
        return Math.abs(currentFloor - floor);
    }
    
    // Observer pattern implementation
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        int nextStop = getNextStop();
        for (Observer observer : observers) {
            observer.update(currentFloor, currentDirection, nextStop);
        }
    }
    
    private int getNextStop() {
        if (currentDirection == Direction.UP && !upRequests.isEmpty()) {
            return upRequests.peek();
        } else if (currentDirection == Direction.DOWN && !downRequests.isEmpty()) {
            return downRequests.peek();
        } else if (!upRequests.isEmpty()) {
            return upRequests.peek();
        } else if (!downRequests.isEmpty()) {
            return downRequests.peek();
        }
        return currentFloor;
    }
    
    // Getters
    public String getElevatorId() { return elevatorId; }
    public ElevatorType getType() { return type; }
    public int getCapacity() { return capacity; }
    public int getCurrentFloor() { return currentFloor; }
    public Direction getCurrentDirection() { return currentDirection; }
    public ElevatorState getCurrentState() { return currentState; }
    public boolean hasRequests() { return !upRequests.isEmpty() || !downRequests.isEmpty(); }
    
    @Override
    public String toString() {
        return String.format("Elevator{id=%s, type=%s, floor=%d, direction=%s, state=%s}", 
                elevatorId, type, currentFloor, currentDirection, currentState);
    }
} 