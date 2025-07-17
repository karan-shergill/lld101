# Parking Lot V1

## LLD Requirements Fulfilled

### 1. **Scalability & Extensibility**
- ✅ **Multiple Vehicle Types**: Support for Car, TwoWheeler, Van, Truck with extensible vehicle factory
- ✅ **Multiple Parking Spot Types**: Compact, Large, TwoWheeler, Electric, Handicapped spots
- ✅ **Configurable Floors**: Dynamic addition of parking floors with mixed spot types
- ✅ **Multiple Payment Methods**: Cash, Credit Card, UPI with extensible payment strategies
- ✅ **Flexible Rate Calculation**: Basic and Premium rate strategies with time-based billing
- ✅ **Multiple Entrances/Exits**: Support for multiple access points with individual tracking

### 2. **Core Parking Operations**
- ✅ **Vehicle Entry Management**: Automated ticket generation and spot assignment
- ✅ **Spot Assignment**: Intelligent spot allocation based on vehicle requirements
- ✅ **Real-time Availability**: Live tracking of available spots by type and floor
- ✅ **Duration Tracking**: Accurate entry/exit timestamp management
- ✅ **Fee Calculation**: Automated parking fee computation based on duration and vehicle type
- ✅ **Payment Processing**: Integrated payment handling with multiple payment options

### 3. **Business Logic & Rules**
- ✅ **Rate Strategies**: Hourly and daily rate calculations with vehicle-specific pricing
- ✅ **Spot Allocation**: Nearest available spot assignment strategy
- ✅ **Capacity Management**: Real-time tracking of occupied vs available spots
- ✅ **Administrative Functions**: Admin controls for adding floors, spots, and access points
- ✅ **Display Boards**: Floor-wise parking availability display systems
- ✅ **Ticket Lifecycle**: Complete ticket management from creation to completion

### 4. **System Reliability & Management**
- ✅ **Input Validation**: Comprehensive validation for vehicles, tickets, and payments
- ✅ **Error Handling**: Graceful handling of invalid tickets, unavailable spots, payment failures
- ✅ **Resource Management**: Proper cleanup of tickets and spot assignments
- ✅ **Account Management**: Admin and attendant role-based access control
- ✅ **State Consistency**: Synchronized spot availability across floors and display boards

## Design Patterns Used

### 1. **Factory Pattern**
- **Implementation**: `VehicleFactory`, `ParkingSpotFactory`, `AccountFactory`
- **Purpose**: Creates objects without exposing instantiation logic
- **Benefit**: Easy to add new vehicle types, spot types, and account types

```java
Vehicle car = VehicleFactory.getVehicleOfType("CAR-001", VehicleType.CAR);
ParkingSpot compactSpot = ParkingSpotFactory.getParkingSpotByType("C1", ParkingSpotType.COMPACT_SPOT);
Account admin = AccountFactory.getAccountOfType(AccountType.PARKING_ADMIN, person, username, password, status);
```

### 2. **Strategy Pattern**
- **Implementation**: `ParkingStrategy`, `ParkingRateStrategy`, `PaymentStrategy`
- **Purpose**: Interchangeable algorithms for spot allocation, pricing, and payments
- **Benefit**: Easy to implement different allocation algorithms and pricing models

```java
// Different parking strategies
ParkingStrategy nearestStrategy = new NearestParking(floors);

// Different rate strategies  
ParkingRateStrategy basicRates = new BasicParkingRate();
ParkingRateStrategy premiumRates = new PremiumParkingRate();

// Different payment methods
PaymentStrategy cashPayment = new CashPayment();
PaymentStrategy cardPayment = new CreditCardPayment(cardNumber, pin);
```

### 3. **Manager/Service Pattern**
- **Implementation**: `ParkingSpotManager`, `TicketManager`
- **Purpose**: Centralized management of related operations
- **Benefit**: Single responsibility and coordinated state management

### 4. **Interface Segregation Pattern**
- **Implementation**: `ParkingLotService`, `TicketService`
- **Purpose**: Clean contracts for different system operations
- **Benefit**: Flexible implementations and dependency injection

### 5. **Facade Pattern** (Implicit)
- **Implementation**: `ParkingLot` class orchestrating multiple managers
- **Purpose**: Simplified interface for complex parking operations
- **Benefit**: Hides complexity of coordinating multiple subsystems

## OOP Principles Followed

### 1. **Encapsulation**
- **Private Fields**: Ticket details, parking spot states, vehicle information
- **Public Methods**: Controlled access through well-defined interfaces
- **Data Hiding**: Internal fee calculations and spot assignment logic hidden

### 2. **Inheritance**
- **Base Classes**: Abstract `Vehicle`, `ParkingSpot`, `Account` classes
- **Derived Classes**: Specific implementations (Car, CompactSpot, ParkingAdmin)
- **Code Reuse**: Common attributes and methods in base classes

### 3. **Polymorphism**
- **Runtime Polymorphism**: Different behaviors for vehicle types, payment methods, rate strategies
- **Interface Polymorphism**: Uniform treatment through interfaces (`ParkingLotService`, `PaymentStrategy`)
- **Method Overriding**: Vehicle-specific spot requirements, payment-specific processing

### 4. **Abstraction**
- **Abstract Classes**: `Vehicle`, `ParkingSpot`, `Account` define contracts
- **Interfaces**: Clean separation between contracts and implementations
- **Enums**: Type-safe constants for vehicle types, spot types, account statuses

## Data Structures & Algorithms

### Data Structures Used

1. **HashMap** (`Map<String, ParkingTicket>`)
   - **Purpose**: Fast O(1) ticket lookup by ticket number
   - **Time Complexity**: O(1) for CRUD operations
   - **Why Used**: Instant ticket retrieval for exit processing

2. **HashMap** (`Map<ParkingSpotType, List<ParkingSpot>>`)
   - **Purpose**: Organize spots by type on each floor
   - **Time Complexity**: O(1) for type lookup, O(n) for availability search
   - **Why Used**: Efficient spot categorization and availability tracking

3. **List** (`List<ParkingFloor>`, `List<Entrance>`, `List<Exit>`)
   - **Purpose**: Manage collections of floors and access points
   - **Time Complexity**: O(n) for iteration, O(1) for indexed access
   - **Why Used**: Ordered collections for systematic processing

4. **Enums**
   - **VehicleType**: Type-safe vehicle classification
   - **ParkingSpotType**: Spot categorization
   - **AccountType**: Role-based access control

### Algorithms Used

1. **Spot Assignment Algorithm**
   - **Time Complexity**: O(f × s) where f = floors, s = spots per floor
   - **Strategy**: Nearest available spot selection
   ```java
   // Iterate through floors to find first available spot
   for (ParkingFloor floor : floors) {
       ParkingSpot spot = floor.getAvailableSpot(spotType);
       if (spot != null) return spot;
   }
   ```

2. **Fee Calculation Algorithm**
   - **Time Complexity**: O(1)
   - **Logic**: Duration-based fee calculation with vehicle-specific rates
   ```java
   // Calculate parking duration and apply rate strategy
   long durationHours = (exitTime - entryTime) / (1000 * 60 * 60);
   double fee = rateStrategy.calculateParkingFee(vehicleType, duration, DurationType.HOUR);
   ```

3. **Availability Tracking Algorithm**
   - **Time Complexity**: O(1) for updates, O(f × s) for complete count
   - **Logic**: Real-time availability updates with display board synchronization
   ```java
   // Immediate updates to availability displays
   displayBoard.removeFreeParkingSpot(spot); // O(1) HashMap removal
   spot.setFree(false); // O(1) state update
   ```

4. **Ticket Generation Algorithm**
   - **Time Complexity**: O(1)
   - **Logic**: Unique ticket number generation with timestamp
   ```java
   // UUID-based unique ticket generation
   String ticketNumber = "TKT-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8);
   ```

5. **Payment Processing Algorithm**
   - **Time Complexity**: O(1)
   - **Logic**: Strategy pattern for different payment methods
   ```java
   // Delegated payment processing
   Payment payment = new Payment(paymentStrategy);
   boolean success = payment.pay(amountInCents);
   ```

### Key Algorithms to Know for LLD Problems

1. **Resource Allocation**: Efficient assignment of limited resources (parking spots)
2. **State Management**: Coordinated updates across multiple system components
3. **Rate Calculation**: Time-based billing with configurable rate strategies
4. **Factory Pattern Implementation**: Type-based object creation systems
5. **Strategy Pattern Implementation**: Pluggable algorithm architectures
6. **Real-time Availability Tracking**: Live capacity management systems
7. **Multi-layered Management**: Hierarchical organization (floors → spots)

## Technical Specifications

### Vehicle-to-Spot Mapping
- **Car**: Requires CompactSpot or LargeSpot
- **TwoWheeler**: Requires TwoWheelerSpot
- **Van/Truck**: Requires LargeSpot
- **Electric Vehicles**: Can use ElectricSpot with charging facilities
- **Handicapped**: Priority access to HandicappedSpot

### Rate Structure
- **Basic Rates**: TWO_WHEELER: ₹50/hr, CAR: ₹100/hr, VAN: ₹120/hr, TRUCK: ₹250/hr
- **Premium Rates**: 10x basic rates for premium locations
- **Duration Types**: Hourly and daily rate calculations
- **Grace Period**: Configurable minimum billing period

### Payment Flow
1. **Fee Calculation**: Based on duration and vehicle type
2. **Payment Processing**: Strategy-based payment method selection
3. **Receipt Generation**: Ticket update with payment confirmation
4. **Spot Release**: Automated spot freeing after successful payment

### System Architecture
- **Entry Flow**: Vehicle → Entrance → Spot Assignment → Ticket Generation
- **Exit Flow**: Ticket Validation → Fee Calculation → Payment → Spot Release
- **Admin Operations**: Floor/Spot Management, Rate Configuration, System Monitoring

## Technical Learning Outcomes

- **Design Patterns**: Factory, Strategy, Manager/Service, Interface Segregation, Facade
- **OOP Concepts**: Inheritance, Polymorphism, Encapsulation, Abstraction
- **Data Structures**: HashMap, List, Enums, Custom Objects
- **Algorithms**: Resource allocation, state management, rate calculation, real-time tracking
- **System Design**: Multi-layered architecture, separation of concerns, dependency injection
- **Business Logic**: Rate strategies, capacity management, payment processing
- **Best Practices**: Input validation, error handling, state consistency, role-based access

## Areas for Enhancement

### 1. **Advanced Features**
- **Reservation System**: Pre-booking parking spots
- **Dynamic Pricing**: Surge pricing during peak hours
- **Mobile Integration**: QR code-based ticket generation
- **Loyalty Programs**: Frequent parker discounts

### 2. **Performance Optimizations**
- **Caching**: Redis-based spot availability caching
- **Database Integration**: Persistent storage for tickets and transactions
- **Load Balancing**: Multiple entrance/exit processing
- **Analytics**: Usage patterns and revenue reporting

### 3. **Advanced Algorithms**
- **Predictive Allocation**: ML-based spot availability prediction
- **Optimal Routing**: Guide vehicles to nearest available spots
- **Dynamic Rate Adjustment**: Real-time pricing based on demand
- **Fraud Detection**: Anomaly detection in parking patterns 