package lld_elevator_v1.models;

import lld_elevator_v1.enums.Direction;

/**
 * Represents a request for elevator service
 * Enhanced with timestamp for FCFS scheduling
 */
public class Request {
    private final int sourceFloor;
    private final Direction direction;
    private final long timestamp;
    
    public Request(int floor, Direction direction) {
        this.sourceFloor = floor;
        this.direction = direction;
        this.timestamp = System.currentTimeMillis();
    }
    
    // Constructor with custom timestamp (for testing)
    public Request(int floor, Direction direction, long timestamp) {
        this.sourceFloor = floor;
        this.direction = direction;
        this.timestamp = timestamp;
    }
    
    // Getters
    public int getSourceFloor() { return sourceFloor; }
    public Direction getDirection() { return direction; }
    public long getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return String.format("Request{floor=%d, direction=%s, timestamp=%d}", 
                sourceFloor, direction, timestamp);
    }
} 