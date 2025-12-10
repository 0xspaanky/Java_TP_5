# TP_4 - Inheritance and Polymorphism in Java

This repository contains 4 Java exercises focusing on inheritance, polymorphism, composition, and object-oriented design patterns.

## Repository Structure

```
TP_4/
├── Exerc_1/    # Bank Account Hierarchy
├── Exerc_2/    # Zoo Management System
├── Exerc_3/    # Vehicle Fleet Management
└── Exerc_4/    # Library Management System
```

## Exercises Overview

### Exercise 1: Bank Account Hierarchy
Implements a banking system with inheritance demonstrating specialized account types.

**Key Concepts:**
- Concrete inheritance (no abstract classes)
- Method overriding (withdraw, display)
- Polymorphism with parent references
- Protected access modifiers

**Classes:**
- `Compte` (Base account)
- `CompteEpargne` (Savings with interest)
- `CompteCourant` (Checking with overdraft)

**Features:**
- Auto-incremented account numbers
- Interest calculation for savings
- Overdraft authorization for checking
- Deposit/withdraw operations

---

### Exercise 2: Zoo Management with Dynamic Arrays
Models a zoo with multiple animal types and dynamic array expansion.

**Key Concepts:**
- Multi-type inheritance (3 animal types)
- Dynamic array management
- Polymorphic collections
- Type casting for specific behaviors

**Classes:**
- `Animal` (Base class)
- `Mammifere` (Mammals with fur)
- `Oiseau` (Birds with wingspan)
- `Reptile` (Reptiles with scales)
- `Zoo` (Container with auto-expansion)

**Features:**
- 6+ animals testing array growth
- Specific behaviors (nursing, flying, basking)
- Dynamic array doubles when full
- Polymorphic toString() calls

---

### Exercise 3: Vehicle Fleet Management
Advanced multi-level hierarchy with composition and fleet statistics.

**Key Concepts:**
- Multi-level inheritance (3 levels)
- Composition (Moteur class)
- Fuel/battery management
- Fleet statistics and tracking

**Classes:**
- `Vehicule` (Base)
- `Motorise` (Middle layer with engine)
- `Voiture`, `Camion`, `Moto` (Specific types)
- `VoitureElectrique` (Electric variant)
- `Moteur` (Composition)
- `Flotte` (Fleet management)

**Features:**
- Realistic fuel consumption (0.1 units/km)
- Distance tracking per vehicle
- Total fleet statistics
- Electric vs. fuel vehicles
- Dynamic fleet expansion

---

### Exercise 4: Library Management System
Comprehensive document hierarchy with interactive menu system.

**Key Concepts:**
- Multi-level inheritance (3 levels)
- Fixed-capacity arrays
- CRUD operations
- Type checking with instanceof
- Interactive console interface

**Classes:**
- `Document` (Base)
- `Livre` (Book with author)
- `Revue` (Journal)
- `Dictionnaire`, `Roman`, `Manuel` (Book types)
- `Bibliotheque` (Library container)

**Features:**
- Menu-driven interface
- Add/remove/search documents
- Display authors from books only
- Capacity management
- Registration number search

---

## Common Themes Across All Exercises

### Inheritance Patterns

#### Single-Level Inheritance
```
Parent → Child
```
Used in: Exercise 1, 2

#### Multi-Level Inheritance
```
Grandparent → Parent → Child
```
Used in: Exercise 3, 4

### Auto-Incremented IDs
All exercises use the same pattern:
```java
private static int compteur = 0;
private final int id;

public Constructor(...) {
    this.id = ++compteur;
    // ...
}
```

### Method Overriding
Every exercise demonstrates `@Override`:
- `toString()` - customized display
- Specific methods (withdraw, refuel, etc.)

### Polymorphism
All exercises use polymorphic collections:
```java
Parent[] array = new Parent[capacity];
array[0] = new Child1(...);
array[1] = new Child2(...);
```

### Dynamic vs. Fixed Arrays
- **Dynamic** (Exerc_1, 2, 3): Auto-expand when full
- **Fixed** (Exerc_4): Capacity limit enforced

## Progressive Complexity

| Exercise | Inheritance Levels | Composition | Arrays | Special Features |
|----------|-------------------|-------------|---------|------------------|
| 1 | 2 levels | No | None | Method override |
| 2 | 2 levels | No | Dynamic | 3 subclasses |
| 3 | 3 levels | Yes | Dynamic | Multi-level + composition |
| 4 | 3 levels | No | Fixed | Menu system + instanceof |

## Technologies
- **Language:** Java 8+
- **Concepts:** Inheritance, Polymorphism, Encapsulation, Composition
- **Patterns:** Auto-increment IDs, Dynamic arrays, Type checking

## Compilation & Execution

### Standard Structure (Exercises 1, 2, 3)
```bash
# Navigate to exercise directory
cd Exerc_X

# Compile from src/
cd src
javac com/example/tp/*.java

# Run
java com.example.tp.Main
```

### Package Structure (Exercise 4)
```bash
# Navigate to Exerc_4
cd Exerc_4

# Compile both packages
javac entities/*.java test/*.java

# Run
java test.Main
```

## Learning Objectives

### Fundamental Concepts
- Understanding inheritance hierarchies
- Implementing method overriding
- Using super() for parent initialization
- Access modifiers (protected, private, public)

### Intermediate Concepts
- Polymorphic collections and references
- Dynamic array management
- Type checking with instanceof
- Downcasting to access specific methods

### Advanced Concepts
- Multi-level inheritance design
- Composition vs. inheritance decisions
- Interactive menu systems
- CRUD operations on collections

### Design Patterns
- Auto-increment ID generation
- Dynamic capacity management
- Template method pattern (override)
- Composition for "has-a" relationships

## Key Differences: Inheritance vs. Composition

### Inheritance (IS-A)
```java
class Voiture extends Motorise  // Car IS-A Motorized vehicle
```
Used in all exercises for type hierarchies.

### Composition (HAS-A)
```java
class Motorise {
    private Moteur moteur;  // Motorized HAS-A Engine
}
```
Used in Exercise 3 for the engine component.

**When to use each:**
- **Inheritance**: Specialization (Dog is an Animal)
- **Composition**: Functionality (Car has an Engine)

## Method Override Best Practices

### Always Use @Override
```java
@Override
public String toString() {
    // Clear indication of override
}
```

### Call super When Extending
```java
@Override
public String toString() {
    return super.toString() + " {additional info}";
}
```

### Constructor Chaining
```java
public Child(String name, int value) {
    super(name);  // Must be first
    this.value = value;
}
```

## Common Pitfalls and Solutions

### 1. Forgetting super()
```java
// Wrong
public CompteEpargne(double solde, double taux) {
    this.taux = taux;  // solde not initialized!
}

// Correct
public CompteEpargne(double solde, double taux) {
    super(solde);  // Initialize parent first
    this.taux = taux;
}
```

### 2. Incorrect Downcasting
```java
// Dangerous
Mammifere m = (Mammifere) animal;  // May fail at runtime

// Safe
if (animal instanceof Mammifere) {
    Mammifere m = (Mammifere) animal;
    m.allaiter();
}
```

### 3. Dynamic Array Not Expanding
```java
// Wrong
if (nb == array.length) {
    return;  // Silently fails
}

// Correct
if (nb == array.length) {
    expand();  // Double capacity
}
```

## Verification Tips

For all exercises:
- [ ] Auto-increment IDs are unique and sequential
- [ ] Method overriding works correctly
- [ ] Polymorphism: parent references call child methods
- [ ] toString() provides useful information
- [ ] No NullPointerException errors
- [ ] Dynamic arrays expand when needed (Exerc 1-3)
- [ ] Capacity limits enforced (Exerc 4)

## Possible Extensions

### For All Exercises
- Add persistence (save/load from files)
- Implement search and filter operations
- Create GUI interfaces (Swing/JavaFX)
- Add data validation and error handling
- Implement comparison and sorting

### Exercise-Specific
- **Exerc_1**: Transaction history, account transfers
- **Exerc_2**: Enclosures, feeding schedules, breeding
- **Exerc_3**: GPS tracking, route optimization, maintenance logs
- **Exerc_4**: Borrowing system, due dates, fines, reservations

## Documentation
Each exercise directory contains:
- `README.md`: Detailed specifications with diagrams
- `subejct.txt`: Original French requirements
- Java source files: Complete implementations
- `image.png`: UML or output diagrams

## Advanced Topics Introduced

### Exercise 1
- Basic inheritance
- Method overriding
- Polymorphic references

### Exercise 2
- Multiple subclasses from one parent
- Dynamic array management
- Type-specific behaviors

### Exercise 3
- Multi-level inheritance (3 levels)
- Composition pattern
- Complex fleet statistics

### Exercise 4
- Fixed-capacity management
- Interactive console menus
- Type checking and casting
- CRUD operations

---

**Course:** Object-Oriented Programming with Java
**Institution:** FST
**Focus:** Inheritance, Polymorphism, and OOP Design
**Difficulty:** Intermediate to Advanced
