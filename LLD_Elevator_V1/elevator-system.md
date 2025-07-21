# Elevator System V1 - Design Documentation

This document outlines the low-level design (LLD) of the Elevator System, implementing multiple design patterns and demonstrating object-oriented principles.

## System Overview

The Elevator System manages multiple elevators in a building, handling requests from floors and inside elevators, using various scheduling strategies for optimal performance. The system demonstrates **Singleton**, **Factory**, **Strategy**, and **Observer** design patterns.

## Architecture Components

### Constants
- **`Direction`**: Direction of the elevator movement
  - Values: `UP`, `DOWN`, `NONE`
  - Used for elevator movement tracking and request direction
- **`ElevatorState`**: All possible states of an elevator
  - Values: `MOVING_UP`, `MOVING_DOWN`, `STOPPED`, `IDLE`, `OUT_OF_SERVICE`
  - Used for state management and display updates
- **`ElevatorType`**: Different types of elevators for object creation
  - Values: `PASSENGER`, `CARGO`, `SERVICE`
  - Used by the Factory pattern for creating specific elevator types
- **`Constants`**: System-wide configuration values
  - `DEFAULT_CAPACITY = 8`: Standard passenger elevator capacity
  - `CARGO_CAPACITY = 20`: Cargo elevator capacity  
  - `SERVICE_CAPACITY = 4`: Service elevator capacity
  - `GROUND_FLOOR = 0`: Base floor number

### Factory Pattern
- **`ElevatorFactory`**: Creates objects for different types of elevators
  - Called from Main class during system initialization
  - Generates unique elevator IDs with type-specific prefixes
  - Methods:
    - `createElevator(ElevatorType type)`: Factory method returning specific elevator instances
    - `generateElevatorId(ElevatorType type)`: Creates unique IDs (PASS-1, CARGO-1, SVC-1)

### Model Layer

#### Elevator Hierarchy (Inheritance & Polymorphism)
- **`Elevator`** (Abstract Base Class): Defines common elevator behavior
  - **Core Properties**:
    - `elevatorId`: Unique identifier for the elevator
    - `type`: Type of elevator (Passenger/Cargo/Service)
    - `capacity`: Maximum load capacity
    - `currentFloor`: Current position in the building
    - `currentDirection`: Current movement direction
    - `currentState`: Current operational state
    - `upRequests`: Priority queue for upward floor requests
    - `downRequests`: Priority queue for downward floor requests (reverse order)
    - `observers`: List of observers for notifications
    - `elevatorDisplay`: Internal display for the elevator
  - **Core Methods**:
    - `addRequest(int targetFloor)`: Add floor request from inside elevator
    - `processNextRequest()`: Process next request in queue based on direction
    - `isAvailable()`: Check if elevator can accept new requests
    - `getDistanceToFloor(int floor)`: Calculate distance for scheduling algorithms
    - Observer pattern methods: `addObserver()`, `removeObserver()`, `notifyObservers()`

- **Concrete Elevator Types**:
  - **`PassengerElevator`**: Standard passenger transport
    - Capacity: 8 people
    - Serves all floors
    - Normal speed operation
  - **`CargoElevator`**: Heavy-duty freight transport  
    - Capacity: 20 units
    - Slower speed for heavy loads
    - Longer stop times for loading/unloading
  - **`ServiceElevator`**: Maintenance and utility access
    - Capacity: 4 people  
    - Restricted floor access
    - Faster movement for service operations

#### Building Management
- **`Building`**: Container for floors and elevators
  - **Properties**:
    - `buildingId`: Unique building identifier
    - `totalFloors`: Number of floors in the building
    - `floors`: List of all floors in the building
    - `elevators`: List of all elevators in the building
  - **Methods**:
    - `initializeFloors()`: Creates floor objects for the building
    - `setControllerForFloors(controller)`: Sets controller reference for all floors
    - `addElevator(elevator)`: Adds elevator and creates displays on all floors
    - `removeElevator(elevatorId)`: Removes elevator and updates displays
    - `getAvailableElevators()`: Returns list of operational elevators
    - `getFloor(floorNumber)`: Retrieves specific floor object

#### Floor Management  
- **`Floor`**: Represents individual floors with request capabilities
  - **Properties**:
    - `floorNumber`: Floor position in building
    - `hasUpButton`: Whether floor has up request button (not on top floor)
    - `hasDownButton`: Whether floor has down request button (not on ground floor)
    - `elevatorDisplays`: List of displays for each elevator
    - `displayByElevatorId`: Map for quick display lookup by elevator ID
    - `controller`: Reference to the elevator controller
  - **Methods**:
    - `addElevatorDisplay(elevatorId)`: Creates display for new elevator
    - `getDisplayForElevator(elevatorId)`: Retrieves specific elevator display
    - `pressUpButton()`: Makes UP request via controller
    - `pressDownButton()`: Makes DOWN request via controller
    - `setController(controller)`: Sets controller reference for making requests

#### Request Management
- **`Request`**: Represents elevator service requests
  - **Properties**:
    - `sourceFloor`: Floor where request originated
    - `direction`: Requested direction (UP/DOWN)
    - `timestamp`: Request creation time for FCFS scheduling
  - **Usage**:
    - Created when floor buttons are pressed
    - Used by scheduling strategies for elevator assignment
    - Contains timing information for queue management

#### Display System (Observer Pattern)
- **`Display`**: Shows elevator status and implements Observer pattern
  - **Display Types**:
    - **Floor Displays**: Show elevator position and direction for each elevator on each floor
      - Display format: `[Floor X - Elevator Y Display] Elevator arrived - Direction: ↑`
      - Only activates when elevator reaches that floor
    - **Elevator Internal Displays**: Show current floor, direction, and next stop
      - Display format: `[Elevator X Display] Floor: 5 | Direction: ↑ | Next Stop: 8`
      - Updates continuously during elevator operation
  - **Display Creation**:
    - **Elevator Displays**: Created automatically when elevator is instantiated
    - **Floor Displays**: Created when elevator is added to building (one per floor per elevator)
  - **Observer Implementation**:
    - `update(currentFloor, direction, nextStop)`: Called when elevator status changes
    - Automatically updates when elevators move or change direction

### Controller Layer (Singleton Pattern)
- **`ElevatorController`**: Central control system for all elevators
  - **Singleton Implementation**:
    - Single instance per building using synchronized lazy initialization
    - `getInstance()`: Thread-safe singleton accessor method
    - Ensures consistent coordination across all system components
  - **Core Responsibilities**:
    - **Building Initialization**: `initializeBuilding(building)` sets up controller-building relationship
    - **Strategy Management**: `setSchedulingStrategy(strategy)` allows runtime strategy switching
    - **External Requests**: `requestElevator(sourceFloor, direction)` handles floor button presses
    - **Internal Requests**: `requestFloor(elevatorId, destinationFloor)` handles cabin button presses
    - **Request Processing**: `processRequest(request)` uses strategy to assign elevators
    - **System Monitoring**: `displaySystemStatus()` shows real-time system state
  - **Request Flow**:
    - From outside elevator (floor): Creates request → Strategy selects elevator → Request added to elevator queue
    - From inside elevator: Direct request addition to specific elevator's queue
  - **Strategy Integration**: Uses `ElevatorSchedulingStrategy` object for intelligent elevator assignment

### Strategy Pattern (Scheduling Algorithms)
- **`ElevatorSchedulingStrategy`** (Interface): Defines contract for scheduling algorithms
  - `selectElevator(elevators, request)`: Returns best elevator for given request

#### Implemented Strategies:

1. **`TrueFCFSStrategy`**: Simple First-Come-First-Served
   - **Algorithm**: Assigns requests to first available elevator in list
   - **Time Complexity**: O(n) where n = number of elevators
   - **Use Case**: Simple systems with minimal optimization requirements
   - **Logic**: No complex scoring - pure FCFS order

2. **`NearestElevatorStrategy`**: Distance-based selection
   - **Algorithm**: Selects elevator with minimum distance to request floor
   - **Time Complexity**: O(n) where n = number of elevators  
   - **Use Case**: Minimize wait time for passengers
   - **Logic**: `distance = Math.abs(elevatorFloor - requestFloor)`

3. **`ScanElevatorStrategy`**: Direction-aware SCAN algorithm
   - **Algorithm**: Prefers elevators moving in same direction; continues motion efficiently
   - **Features**:
     - Direct pickup: Elevator going UP picks up UP requests on the way
     - Direction change penalty: Higher cost for opposite direction
     - Idle elevator handling: Uses simple distance for idle elevators
   - **Cost Calculation**:
     - Base cost = distance to request floor
     - +50 penalty for direction change  
     - +20 penalty for busy elevators
   - **Use Case**: High-traffic buildings requiring directional efficiency

4. **`LoadBalancingStrategy`**: Multi-factor optimization
   - **Algorithm**: Uses weighted scoring system considering multiple factors
   - **Scoring Factors**:
     - Base score: 100 points
     - Distance penalty: -2 points per floor
     - Load penalty: -30 points for pending requests
     - Idle bonus: +20 points for idle elevators
     - Direction bonus: +15 points for matching direction
   - **Use Case**: Complex buildings requiring balanced load distribution

### Observer Pattern Implementation
- **`Subject`** (Interface): Defines observer management contract
  - `addObserver(observer)`: Register new observer
  - `removeObserver(observer)`: Unregister observer  
  - `notifyObservers()`: Notify all registered observers
- **`Observer`** (Interface): Defines notification contract
  - `update(currentFloor, direction, nextStop)`: Receive state updates
- **Implementation**: Elevator extends Subject, Display implements Observer
- **Notification Flow**: Elevator state change → `notifyObservers()` → All displays update automatically

### Main Class (System Demonstration)
- **`Main`**: Demonstrates all design patterns and system functionality
  - **Pattern Demonstrations**:
    - Singleton: ElevatorController instance creation
    - Factory: Creating different elevator types (Passenger, Cargo, Service)
    - Observer: Automatic display updates during elevator operations  
    - Strategy: Runtime switching between scheduling algorithms
  - **System Workflow**:
    1. Initialize singleton controller
    2. Create building with multiple floors
    3. Factory creates different elevator types
    4. Add elevators to building (creates displays automatically)
    5. Initialize controller with building
    6. Demonstrate various scheduling strategies
    7. Show external requests (floor buttons)
    8. Show internal requests (elevator buttons)
    9. Display real-time system status

## Design Patterns Summary

### 1. Singleton Pattern
- **Class**: `ElevatorController`
- **Purpose**: Single point of control for all elevators
- **Implementation**: Thread-safe lazy initialization
- **Benefits**: Centralized coordination, consistent state management

### 2. Factory Pattern  
- **Class**: `ElevatorFactory`
- **Purpose**: Create elevator objects without exposing instantiation logic
- **Implementation**: Switch-based creation with auto-generated IDs
- **Benefits**: Easy extension for new elevator types

### 3. Strategy Pattern
- **Interface**: `ElevatorSchedulingStrategy`  
- **Purpose**: Interchangeable scheduling algorithms
- **Implementation**: Runtime strategy switching via controller
- **Benefits**: Algorithm flexibility, easy testing, performance optimization

### 4. Observer Pattern
- **Classes**: `Subject` interface, `Observer` interface, `Display` implementation
- **Purpose**: Real-time status notifications
- **Implementation**: Automatic display updates on elevator state changes
- **Benefits**: Loose coupling, real-time updates, scalable notification system

## OOP Principles Demonstrated

### Encapsulation
- Private fields with controlled access through public methods
- Internal elevator logic hidden from external classes
- State management encapsulated within respective classes

### Inheritance  
- Abstract `Elevator` base class with concrete implementations
- Common functionality shared across elevator types
- Type-specific behavior in derived classes

### Polymorphism
- Runtime strategy selection through common interface
- Different elevator types through common base class
- Observer pattern with multiple display implementations

### Abstraction
- Interface-based design hiding implementation details
- Abstract elevator behavior with concrete implementations
- Strategy pattern abstracting scheduling algorithms

## System Features

### Core Capabilities
- Multiple elevator types with different capacities and characteristics
- Real-time status tracking and display updates
- Intelligent request routing and queue management
- Multiple scheduling algorithms for different optimization goals
- Extensible architecture for adding new elevator types and strategies

### Advanced Features  
- Direction-aware scheduling for optimal throughput
- Load balancing across multiple elevators
- Observer-based real-time notifications
- Thread-safe singleton controller
- Priority queue-based request management
- Runtime algorithm switching without system restart

### Scalability
- Easy addition of new elevator types via factory pattern
- Pluggable scheduling strategies via strategy pattern
- Dynamic building configuration (floors and elevators)
- Observer pattern supports unlimited displays
- Modular architecture supports system expansion