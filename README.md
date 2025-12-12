# TP_5 - Inheritance and Polymorphism in Java

This repository contains 4 Java exercises focusing on inheritance, polymorphism, composition, and object-oriented design patterns.

## Exercises Overview

### Exercise 1: Bank Account Hierarchy
Implements specialized account types with inheritance.

**Key Concepts:** Inheritance, method overriding, protected access, static counter

**Hierarchy:**
```
Compte (Base)
  ├─ CompteEpargne (Savings with interest)
  └─ CompteCourant (Checking with overdraft)
```

**Features:**
- Auto-incremented account numbers
- Interest calculation (+3% for savings)
- Overdraft authorization for checking
- Deposit/withdraw operations

---

### Exercise 2: Zoo Management System
Models a zoo with multiple animal types and dynamic array expansion.

**Key Concepts:** Multi-type inheritance, dynamic arrays, polymorphic collections, type casting

**Hierarchy:**
```
Animal (Base)
  ├─ Mammifere (Mammals with fur)
  ├─ Oiseau (Birds with wingspan)
  └─ Reptile (Reptiles with scales)
```

**Features:**
- Dynamic array doubles when full
- Specific behaviors (nursing, flying, basking)
- Polymorphic toString() calls
- 6+ animals testing array growth

---

### Exercise 3: Vehicle Fleet Management
Advanced multi-level hierarchy with composition and fleet statistics.

**Key Concepts:** Multi-level inheritance (3 levels), composition (Moteur), fuel management, fleet tracking

**Hierarchy:**
```
Vehicule (Base)
  └─ Motorise (with Moteur engine)
      ├─ Voiture (Car with doors)
      ├─ Camion (Truck with cargo)
      ├─ Moto (Motorcycle)
      └─ VoitureElectrique (Electric car)
```

**Features:**
- Fuel consumption (0.1 units/km)
- Distance tracking per vehicle
- Total fleet statistics
- Electric vs. fuel vehicles
- Dynamic fleet expansion

---

### Exercise 4: Library Management System
Comprehensive document hierarchy with interactive menu system.

**Key Concepts:** Multi-level inheritance (3 levels), CRUD operations, instanceof checking, interactive console

**Hierarchy:**
```
Document (Base)
  ├─ Livre (Book)
  │   ├─ Dictionnaire (Dictionary)
  │   ├─ Roman (Novel)
  │   └─ Manuel (Manual)
  └─ Revue (Journal)
```

**Features:**
- Menu-driven interface
- Add/remove/search documents
- Display authors from books only
- Fixed capacity management
- Registration number search

---

## Comparison

| Feature | Exerc_1 | Exerc_2 | Exerc_3 | Exerc_4 |
|---------|---------|---------|---------|---------|
| Inheritance Levels | 2 | 2 | 3 | 3 |
| Composition | No | No | Yes (Moteur) | No |
| Array Type | None | Dynamic | Dynamic | Fixed |
| Special Features | Interest/overdraft | 3 subclasses | Multi-level + composition | Menu + instanceof |

## Common Patterns

### Auto-Incremented IDs
```java
private static int compteur = 0;
private final int id;

public Constructor(...) {
    this.id = ++compteur;
}
```

### Method Overriding
```java
@Override
public String toString() {
    return super.toString() + " {additional info}";
}
```

### Dynamic Arrays (Exerc_2, 3)
```java
if (count == array.length) {
    Type[] tmp = new Type[array.length * 2];
    System.arraycopy(array, 0, tmp, 0, array.length);
    array = tmp;
}
```

## Technologies
- Java 8+
- Inheritance, Polymorphism, Encapsulation, Composition
- Auto-increment IDs, Dynamic/Fixed arrays, Type checking

## Compilation

### Standard (Exerc_1, 2, 3)
```bash
cd Exerc_X/src
javac com/example/tp/*.java
java com.example.tp.Main
```

### Package Structure (Exerc_4)
```bash
cd Exerc_4
javac entities/*.java test/*.java
java test.Main
```

## Learning Objectives
- Understanding inheritance hierarchies
- Implementing method overriding
- Using super() for parent initialization
- Polymorphic collections and references
- Dynamic vs. fixed array management
- Type checking with instanceof
- Composition vs. inheritance decisions

## Key Differences: Inheritance vs. Composition

**Inheritance (IS-A):**
```java
class Voiture extends Motorise  // Car IS-A Motorized vehicle
```

**Composition (HAS-A):**
```java
class Motorise {
    private Moteur moteur;  // Motorized HAS-A Engine
}
```

## Extensions
- Add persistence (save/load from files)
- Implement search and filter operations
- Create GUI interfaces (Swing/JavaFX)
- Add data validation and error handling
- Implement comparison and sorting

---

**Course:** Object-Oriented Programming with Java
**Institution:** FST
**Focus:** Inheritance, Polymorphism, and OOP Design
