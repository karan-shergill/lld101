# Structuring an LLD project

- [High-Level Package / Module Breakdown](#high-level-package--module-breakdown)
- [Files Breakdown by Responsibility](#files-breakdown-by-responsibility)
- [Project Folder Template (in Java)](#project-folder-template-in-java)
- [Best Practices](#best-practices)

### High-Level Package / Module Breakdown

A typical LLD project (especially Java-style) can follow this breakdown:

```
com.myproject
├── model             # Core entities / POJOs / data models
├── service           # Interfaces declaring business logic
├── impl              # Implementations of services
├── controller        # Entry points (optional, for full app flow)
├── util              # Reusable helpers, converters, or validators
├── config            # Constants, enums, application settings
└── exception         # Custom exceptions, error handling

```

### Files Breakdown by Responsibility

| Layer | Folder | File Type | Purpose |
| --- | --- | --- | --- |
| **Model Layer** | `model` | `User.java`, `Booking.java`, etc. | Represents the core domain entities. Contains fields, constructors, getters/setters. |
| **Service Layer** | `service` | `BookingService.java` (Interface) | Defines the behavior expected (without implementation). |
| **Impl Layer** | `impl` | `BookingServiceImpl.java` | Implements the interface and contains actual business logic. |
| **Controller Layer (optional)** | `controller` | `BookingController.java` | Exposes APIs (for full-stack or simulated flow), or drives sample input. |
| **Utility Layer** | `util` | `DateUtil.java`, `Validator.java` | Shared helper functions, reusable logic. |
| **Config Layer** | `config` | `Constants.java`, `Enums.java` | Constants, enums, app-level configurations. |
| **Exception Layer** | `exception` | `InvalidBookingException.java` | Custom exceptions for domain-specific errors. |

### Project Folder Template (in Java)

```
src/
└── com/myproject
    ├── model/
    │   ├── User.java
    │   └── Booking.java
    ├── service/
    │   └── BookingService.java
    ├── impl/
    │   └── BookingServiceImpl.java
    ├── controller/
    │   └── AppRunner.java     # (for console or demo flow)
    ├── util/
    │   └── DateUtil.java
    ├── config/
    │   ├── Constants.java
    │   └── BookingStatus.java
    └── exception/
        └── BookingLimitExceededException.java

```

### Best Practices

- Keep interfaces in `service`, implementations in `impl`.
- Use proper package names (`booking.model`, `booking.service`) for big systems.
- Separate **business logic** from **data** and **utilities**.
- Unit-testable: use interfaces and Dependency Injection to mock dependencies.
- Avoid circular dependencies between packages.