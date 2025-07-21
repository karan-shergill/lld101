package lld_elevator_v1;

import lld_elevator_v1.controller.ElevatorController;
import lld_elevator_v1.factory.ElevatorFactory;
import lld_elevator_v1.models.Building;
import lld_elevator_v1.models.Floor;
import lld_elevator_v1.models.elevators.PassengerElevator;
import lld_elevator_v1.models.elevators.CargoElevator;
import lld_elevator_v1.models.elevators.ServiceElevator;
import lld_elevator_v1.enums.Direction;
import lld_elevator_v1.enums.ElevatorType;
import lld_elevator_v1.strategy.NearestElevatorStrategy;
import lld_elevator_v1.strategy.TrueFCFSStrategy;
import lld_elevator_v1.strategy.LoadBalancingStrategy;

/**
 * Main class demonstrating the Elevator System
 * Shows 4 essential design patterns in action:
 * - Singleton (ElevatorController)
 * - Factory (ElevatorFactory) - Creates concrete elevator subclasses
 * - Observer (Display notifications)
 * - Strategy (Different scheduling algorithms)
 * 
 * Also demonstrates proper OOP design with abstract Elevator class and concrete subclasses
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== ELEVATOR SYSTEM DEMONSTRATION ===\n");
        
        // 1. SINGLETON PATTERN - Get controller instance
        ElevatorController controller = ElevatorController.getInstance();
        System.out.println("✓ Singleton: ElevatorController instance created\n");
        
        // 2. Create building
        Building building = new Building("TechTower", 12);
        System.out.println("✓ Building created: " + building + "\n");
        
        // 3. FACTORY PATTERN - Create different concrete elevator types
        System.out.println("=== FACTORY PATTERN: Creating Concrete Elevator Types ===");
        PassengerElevator passengerElevator1 = (PassengerElevator) ElevatorFactory.createElevator(ElevatorType.PASSENGER);
        PassengerElevator passengerElevator2 = (PassengerElevator) ElevatorFactory.createElevator(ElevatorType.PASSENGER);
        CargoElevator cargoElevator = (CargoElevator) ElevatorFactory.createElevator(ElevatorType.CARGO);
        ServiceElevator serviceElevator = (ServiceElevator) ElevatorFactory.createElevator(ElevatorType.SERVICE);
        
        System.out.printf("✓ Created: %s (Capacity: %d)%n", passengerElevator1, passengerElevator1.getCapacity());
        System.out.printf("✓ Created: %s (Capacity: %d)%n", passengerElevator2, passengerElevator2.getCapacity());
        System.out.printf("✓ Created: %s (Capacity: %d)%n", cargoElevator, cargoElevator.getCapacity());
        System.out.printf("✓ Created: %s (Capacity: %d)%n", serviceElevator, serviceElevator.getCapacity());
        System.out.println();
        
        // Add elevators to building
        building.addElevator(passengerElevator1);
        building.addElevator(passengerElevator2);
        building.addElevator(cargoElevator);
        building.addElevator(serviceElevator);
        
        // Initialize controller with building
        controller.initializeBuilding(building);
        System.out.println();
        
        // 4. OBSERVER PATTERN - Display updates are automatic
        System.out.println("=== OBSERVER PATTERN: Automatic Display Updates ===");
        System.out.println("✓ Floor and elevator displays automatically update via Observer pattern");
        System.out.println("✓ Watch for display notifications in the elevator movement logs below\n");
        
        // 5. STRATEGY PATTERN - Demonstrate different scheduling strategies
        System.out.println("=== STRATEGY PATTERN: Testing Different Algorithms ===");
        
        // Test True FCFS Strategy
        controller.setSchedulingStrategy(new TrueFCFSStrategy());
        demonstrateElevatorRequests(controller, "True FCFS Strategy (First Available)");
        
        // Test Nearest Elevator Strategy
        controller.setSchedulingStrategy(new NearestElevatorStrategy());
        demonstrateElevatorRequests(controller, "Nearest Elevator Strategy");
        
        // Test Load Balancing Strategy
        controller.setSchedulingStrategy(new LoadBalancingStrategy());
        demonstrateElevatorRequests(controller, "Load Balancing Strategy");
        
        // 6. CONCRETE ELEVATOR BEHAVIOR DEMONSTRATION
        System.out.println("\n=== CONCRETE ELEVATOR TYPES: Different Behaviors ===");
        demonstrateElevatorTypes(controller, passengerElevator1, cargoElevator, serviceElevator);
        
        // 7. Test floor button functionality
        System.out.println("\n=== FLOOR BUTTON FUNCTIONALITY ===");
        demonstrateFloorButtons(building);
        
        // 8. MULTIPLE DISPLAYS DEMONSTRATION
        System.out.println("\n=== MULTIPLE DISPLAYS PER FLOOR ===");
        demonstrateMultipleDisplays(building);
        
        // 9. Final system status
        controller.displaySystemStatus();
        building.displayBuildingStatus();
        
        // Cleanup - no shutdown needed in simplified version
        System.out.println("=== DEMONSTRATION COMPLETE ===");
        System.out.println("\n✅ Successfully demonstrated 4 essential design patterns:");
        System.out.println("   1. Singleton Pattern (ElevatorController)");
        System.out.println("   2. Factory Pattern (Creates concrete elevator subclasses)");
        System.out.println("   3. Observer Pattern (Display notifications)");
        System.out.println("   4. Strategy Pattern (True FCFS, Nearest, Load Balancing)");
        System.out.println("\n🏗️ OOP Design Benefits:");
        System.out.println("   • Abstract Elevator class with concrete subclasses");
        System.out.println("   • Type-specific behaviors for different elevator types");
        System.out.println("   • True polymorphism in action");
        System.out.println("   • Easy to extend with new elevator types");
        System.out.println("\n🖥️ Multiple Display Architecture:");
        System.out.println("   • Each floor has one display per elevator");
        System.out.println("   • Displays show information only for their associated elevator");
        System.out.println("   • No information conflicts between multiple elevators");
        System.out.println("   • Realistic building elevator display simulation");
    }
    
    /**
     * Demonstrate elevator requests with different strategies
     */
    private static void demonstrateElevatorRequests(ElevatorController controller, String strategyName) {
        System.out.printf("\n--- Testing %s ---\n", strategyName);
        
        // Simulate elevator requests from different floors
        controller.requestElevator(3, Direction.UP);
        controller.requestElevator(7, Direction.DOWN);
        controller.requestElevator(1, Direction.UP);
        
        controller.displaySystemStatus();
    }
    
    /**
     * Demonstrate different behaviors of concrete elevator types
     */
    private static void demonstrateElevatorTypes(ElevatorController controller, 
                                               PassengerElevator passenger, 
                                               CargoElevator cargo, 
                                               ServiceElevator service) {
        System.out.println("Testing type-specific behaviors:");
        
        // Test passenger elevator - normal behavior
        System.out.println("\n1. Passenger Elevator (Standard behavior):");
        controller.requestFloor(passenger.getElevatorId(), 5);
        
        // Test cargo elevator - slower, longer stops
        System.out.println("\n2. Cargo Elevator (Slower movement, longer stops):");
        controller.requestFloor(cargo.getElevatorId(), 4);
        
        // Test service elevator - restricted access, faster movement
        System.out.println("\n3. Service Elevator (Restricted access, faster movement):");
        controller.requestFloor(service.getElevatorId(), 0); // Should be denied
        controller.requestFloor(service.getElevatorId(), 6); // Should work
    }
    
    /**
     * Demonstrate floor button functionality (simplified - no command pattern)
     */
    private static void demonstrateFloorButtons(Building building) {
        System.out.println("Testing floor button presses:");
        
        // Simulate button presses on different floors
        Floor floor3 = building.getFloor(3);
        Floor floor7 = building.getFloor(7);
        Floor floor1 = building.getFloor(1);
        
        floor3.pressUpButton();
        floor7.pressDownButton();
        floor1.pressUpButton();
        
        System.out.println("✓ Floor buttons pressed - requests sent to controller");
    }
    
    /**
     * Demonstrate multiple displays per floor functionality
     */
    private static void demonstrateMultipleDisplays(Building building) {
        System.out.println("Demonstrating multiple displays per floor:");
        System.out.printf("Building has %d floors and %d elevators%n", 
                building.getTotalFloors(), building.getElevators().size());
        
        // Show displays on a specific floor
        Floor sampleFloor = building.getFloor(5);
        System.out.printf("Floor %d has %d displays:%n", 
                sampleFloor.getFloorNumber(), sampleFloor.getAllDisplays().size());
        
        for (var display : sampleFloor.getAllDisplays()) {
            System.out.printf("  - %s%n", display);
        }
        
        System.out.println("✓ Each elevator has its own dedicated display on every floor");
        System.out.println("✓ Displays update independently based on their elevator's status");
    }

}