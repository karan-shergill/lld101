# LLD Interview template

- [Clarify the Problem Statement](#clarify-the-problem-statement)
- [List Out the Key Functional Requirements](#list-out-the-key-functional-requirements)
- [Identify and Call Out Non-Functional Requirements (NFRs)](#identify-and-call-out-non-functional-requirements-nfrs)
- [State Your High-Level Approach (Top-Down)](#state-your-high-level-approach-top-down)
- [Identify Key Entities / Nouns](#identify-key-entities--nouns)
- [Spot Design Patterns (if applicable)](#spot-design-patterns-if-applicable)
- [Move Into Class Diagram](#move-into-class-diagram)
- [Explain Core Flows (Use Case Walkthrough)](#explain-core-flows-use-case-walkthrough)
- [Talk About Testability & Extensibility](#talk-about-testability--extensibility)
- [Mention Trade-offs / Assumptions](#mention-trade-offs--assumptions)
- [Code the LLD Design](#code-the-lld-design)


### Clarify the Problem Statement

> “Let me quickly repeat the problem to make sure I understand it correctly…”
>

✅ **What to say**:

- “As I understand, we need to design a system that can _____. The core goal is to allow the user to ______, and it should support features like ______.”
- “Are there any constraints or non-functional requirements I should be aware of?”

✅ **Ask**:

- What are the core use cases you’d like me to focus on?
- Is concurrency, scaling, or persistence expected in the scope?
- Do we need an e2e working system

### List Out the Key Functional Requirements

> “Based on what we discussed, the main functional requirements I see are…”
>

✅ Example:

- User can register and login
- User can search for items
- System should allow booking
- Admin can manage inventory

✅ *Ask the interviewer if you're missing anything.*

### Identify and Call Out Non-Functional Requirements (NFRs)

> “Do we need to account for any specific non-functional aspects like…”
>

✅ Options:

- Concurrency/thread safety
- Performance/latency
- Fault tolerance
- Testability
- Security/permissions
- Extensibility

### State Your High-Level Approach (Top-Down)

> “Here’s how I’m thinking of approaching the design…”
>

✅ Mention:

- “First, I’ll identify the key components and their responsibilities.”
- “Then I’ll define the main classes/interfaces and their relationships.”
- “I’ll focus on a clean and extensible OOP structure, and where helpful, I’ll use appropriate design patterns.”
- “Once the structure is in place, I’ll walk you through the main flows like using a sequence diagram or example.”

### Identify Key Entities / Nouns

> “Let me start by identifying the key entities or classes involved in the system…”
>

✅ Use bullets like:

- User
- Booking
- InventoryManager
- NotificationService

🧠 *Think: what are the main ‘nouns’ in the system?*

### Spot Design Patterns (if applicable)

> “This seems like a good place to apply the Strategy/Factory/Observer pattern…”
>

✅ Mention *why* and what it helps solve:

- Strategy: for pluggable algorithms
- Factory: for object creation, decoupling
- Singleton: for shared config
- Observer: for event propagation

### Move Into Class Diagram

> “Now I’ll sketch a class diagram showing the core classes and their relationships…”
>

✅ Define:

- Class names
- Key attributes
- Public methods (signatures)
- Relationships (inheritance, composition, etc.)

### Explain Core Flows (Use Case Walkthrough)

> “Let me walk you through how a typical flow would work…”
>

✅ Example: “User signs up → request goes to AuthService → user object created → saved to DB”

Consider using a:

- Sequence diagram (if asked or needed)
- Simple verbal explanation of interaction

### Talk About Testability & Extensibility

> “This design is testable because dependencies are abstracted, and components are decoupled.”
>

✅ Mention:

- “Easy to mock services during unit tests.”
- “If a new payment gateway is added, we can just plug in a new implementation without changing core logic.”

### Mention Trade-offs / Assumptions

> “Just to be clear, I’m assuming this doesn’t need persistence for now, but if needed we can introduce a DAO or repository layer later.”
>

✅ Show that you are thinking long-term:

- Performance vs simplicity
- Memory vs disk
- Composition vs inheritance

### Code the LLD Design

Read lld_coding_tips.md for coding tips