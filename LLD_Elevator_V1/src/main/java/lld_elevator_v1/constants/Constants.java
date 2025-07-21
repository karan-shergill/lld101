package lld_elevator_v1.constants;

/**
 * Constants used throughout the elevator system
 */
public class Constants {
    // Elevator specifications
    public static final int DEFAULT_CAPACITY = 8;  // Default passenger capacity
    public static final int CARGO_CAPACITY = 20;   // Cargo elevator capacity
    public static final int SERVICE_CAPACITY = 4;  // Service elevator capacity
    
    // Building specifications
    public static final int GROUND_FLOOR = 0;      // Ground floor number
    
    // Display messages
    public static final String GOING_UP = "↑";
    public static final String GOING_DOWN = "↓";
    public static final String IDLE = "-";
    
    private Constants() {
        // Private constructor to prevent instantiation
    }
} 