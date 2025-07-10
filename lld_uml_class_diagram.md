# LLD UML Class Diagram

- [Quick Cheatsheet](#quick-cheatsheet)
- [Reference](#reference)
- [What Is a Class Diagram?](#what-is-a-class-diagram)
- [When to Use It?](#when-to-use-it)
- [Steps to Start a Class Diagram for LLD](#steps-to-start-a-class-diagram-for-lld)
    - [Step 1: Identify Key Entities (Nouns)](#step-1-identify-key-entities-nouns)
    - [Step 2: Define Class Responsibilities](#step-2-define-class-responsibilities)
    - [Step 3: Identify Relationships Between Classes](#step-3-identify-relationships-between-classes)
    - [Step 4: Draw the Class Diagram](#step-4-draw-the-class-diagram)
    - [Step 5: Add Multiplicity (optional but good)](#step-5-add-multiplicity-optional-but-good)
- [Tips for Interview](#tips-for-interview)
- [Association](#association)

## Quick Cheatsheet

1. Understand use-cases → extract nouns → identify entities
2. Define class responsibilities: attributes + methods
3. Model relationships:
    - IS-A → Inheritance
    - HAS-A → Composition/Aggregation
    - USES → Association
4. Represent multiplicity where needed (1..*, 0..1, etc.)
5. Draw clean, simple class boxes
6. Walk through the flows using this diagram

## Reference

1. [UML class diagrams](https://youtu.be/6XrL5jXmTwM)
2. [UML Class Diagram Explained with Examples](https://blog.algomaster.io/p/uml-class-diagram-explained-with-examples) **⭐️ [A MUST READ]**
3. [UML Class Diagrams Examples](https://www.uml-diagrams.org/class-diagrams-examples.html)

## What Is a Class Diagram?

A **Class Diagram** is a type of UML diagram that shows:

- Classes (your building blocks)
- Attributes (fields/data)
- Methods (behavior)
- Relationships between classes (association, inheritance, composition, etc.)

It visually represents the **structure** of your object-oriented design.

## When to Use It?

- At the **start of LLD design**
- To discuss responsibilities and relationships
- To communicate modular structure
- Before writing code

## Steps to Start a Class Diagram for LLD

### Step 1: Identify Key Entities (Nouns)

> Look at the problem and extract key nouns from the use cases.
>
>
> These are your potential classes.
>

**Example:** “Design a Parking Lot system”

Key nouns:

- Vehicle
- ParkingSlot
- ParkingLot
- Ticket
- Gate
- ParkingFloor

### Step 2: Define Class Responsibilities

> What does each class do? What data does it need to hold?
>

**Example:**

```
Class: Vehicle
- Fields: licensePlate, vehicleType
- Methods: getLicensePlate(), getVehicleType()

Class: ParkingSlot
- Fields: slotId, slotType, isOccupied
- Methods: assignVehicle(), removeVehicle(), isFree()
```

✅ Use SRP (Single Responsibility Principle) to avoid bloated classes.

### Step 3: Identify Relationships Between Classes

There are 4 primary relationships:

| Relationship | UML Symbol | Meaning |
| --- | --- | --- |
| **Association** | → | “Uses” or “knows about” |
| **Aggregation** | ◇→ | “Has a” (weak ownership) |
| **Composition** | ◆→ | “Owns” (strong ownership, lifetime) |
| **Inheritance** | ▲ | “Is a” (extends/implements) |

**Example:**

- `ParkingLot` → contains many → `ParkingFloor` (**Aggregation**)
- `Car` → is a → `Vehicle` (**Inheritance**)
- `Ticket` → is generated for → `Vehicle` (**Association**)

### Step 4: Draw the Class Diagram

Each class block typically looks like:

```
+------------------+
|  ClassName       |
+------------------+
| - field1: Type   |
| - field2: Type   |
+------------------+
| + method1(): Ret |
| + method2(): Ret |
+------------------+
```

**Symbols:**

- `+` = public
- = private
- `#` = protected

### Step 5: Add Multiplicity (optional but good)

| Notation | Meaning |
| --- | --- |
| `1` | Exactly one |
| `0..1` | Zero or one |
| `*` | Zero or many |
| `1..*` | One or more |

**Example:**

`ParkingLot` 1 → * `ParkingFloor`

## Tips for Interview

| Do | Avoid |
| --- | --- |
| Start with just 3–5 key classes | Adding too many classes too soon |
| Keep methods short and purposeful | Dumping implementation logic into diagram |
| Use interfaces for behavior extension | Making everything concrete at once |
| Use inheritance only if **IS-A** is truly valid | Deep inheritance trees |

## Association

**Association** provides a mechanism to communicate one object with another object, or one object provides services to another object. Association represents the relationship between classes.

The association can be divided into two categories:

1. Class association (Inheritance)
2. Object association
    1. Simple association
    2. Composition: An object may be composed of smaller objects, and the relationship between the “part” objects and “whole” objects is known as **composition**. Composition is denoted by a line with a filled diamond head at the composer class pointing to the component class.
        1. The composed object becomes a part of the composer.
        2. Composed objects can not exist independently.
    3. Aggregation: **Aggregation** describes the relationship between the container and the object it contains. An object may contain an aggregate of another object. Aggregation is denoted by a line with an unfilled diamond head towards the container.
        1. Aggregate objects are not a part of the container.
        2. Aggregate objects can exist independently.