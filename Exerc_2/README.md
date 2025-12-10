# Exercise 2: Zoo Management with Dynamic Animal Array

![Exercise 2 Diagram](./image.png)

## Objective
Design a Java class hierarchy to model a zoo, applying inheritance (without abstract classes or interfaces) and managing a dynamic Animal array that automatically expands.

## Description
This exercise creates a comprehensive zoo management system with multiple animal types, demonstrating inheritance, polymorphism, and dynamic array management. The system can store various animals and manage their specific behaviors.

## UML Class Diagram

```
               +---------------------+
               |       Animal        |
               +---------------------+
               | - compteur: int     |
               | - id : int          |
               | - nom : String      |
               | - age : int         |
               +---------------------+
               | + seDeplacer()      |
               | + toString()        |
               +---------------------+
                       /|\
                        |
    +-------------------+-------------------+
    |                   |                   |
+-----------+      +------------+      +----------------+
| Mammifere |      |  Oiseau    |      | Reptile        |
+-----------+      +------------+      +----------------+
| - couleur |      | - envergure|      | - typeEcailles |
+-----------+      +------------+      +----------------+
| +allaiter()|     | + voler()  |      | +seChauffer()  |
+-----------+      +------------+      +----------------+

                   +--------------------+
                   |   Zoo              |
                   +--------------------+
                   | - animaux[]        |
                   | - nbAnimaux        |
                   +--------------------+
                   | + ajouterAnimal()  |
                   | + afficherTous()   |
                   +--------------------+
                         |
                         * contains
                         |
                      Animal
```

## Class Structure

### Animal (Base Class)
Located in `com.example.tp` package

**Attributes:**
- `compteur` (static int): Global counter for ID generation
- `id` (final int): Unique animal identifier
- `nom` (protected String): Animal's name
- `age` (protected int): Animal's age

**Constructor:**
```java
public Animal(String nom, int age)
```

**Methods:**
- `seDeplacer()`: Generic movement method
- `toString()`: Returns formatted animal information
- Getters: `getId()`, `getNom()`, `getAge()`

### Mammifere (Mammal)

**Attributes:**
- `couleurFourrure` (String): Fur color

**Constructor:**
```java
public Mammifere(String nom, int age, String couleurFourrure)
```

**Methods:**
- `allaiter()`: Specific mammal behavior - nursing young
- `toString()`: Overridden to include fur color

### Oiseau (Bird)

**Attributes:**
- `envergure` (double): Wingspan in meters

**Constructor:**
```java
public Oiseau(String nom, int age, double envergure)
```

**Methods:**
- `voler()`: Specific bird behavior - flying
- `toString()`: Overridden to include wingspan

### Reptile

**Attributes:**
- `typeEcailles` (String): Scale type (e.g., "Épaisse", "Lisse")

**Constructor:**
```java
public Reptile(String nom, int age, String typeEcailles)
```

**Methods:**
- `seChauffer()`: Specific reptile behavior - basking in sun
- `toString()`: Overridden to include scale type

### Zoo (Container Class)

**Attributes:**
- `animaux` (Animal[]): Dynamic array of animals
- `nbAnimaux` (int): Current number of animals

**Constructor:**
```java
public Zoo()
```
- Initializes array with capacity of 5

**Methods:**
- `ajouterAnimal(Animal a)`: Adds animal, doubles array if full
- `afficherTous()`: Displays all animals in zoo

## Dynamic Array Management

When the array is full (`nbAnimaux == animaux.length`):
1. Creates new array with double the capacity
2. Copies all existing animal references using `System.arraycopy()`
3. Replaces old array with expanded one
4. Adds the new animal

## Example Usage

```java
Zoo monZoo = new Zoo();

// Add various animals
monZoo.ajouterAnimal(new Mammifere("Lion", 5, "Dorée"));
monZoo.ajouterAnimal(new Oiseau("Aigle", 3, 2.3));
monZoo.ajouterAnimal(new Reptile("Crocodile", 12, "Épaisse"));
monZoo.ajouterAnimal(new Mammifere("Girafe", 7, "Tachetée"));
monZoo.ajouterAnimal(new Oiseau("Perroquet", 2, 0.4));
monZoo.ajouterAnimal(new Reptile("Serpent", 4, "Lisse"));

// Display all animals
monZoo.afficherTous();

// Specific behaviors with casting
Animal a1 = new Mammifere("Éléphant", 10, "Grise");
monZoo.ajouterAnimal(a1);
((Mammifere)a1).allaiter();

Animal a2 = new Oiseau("Autruche", 6, 1.8);
monZoo.ajouterAnimal(a2);
((Oiseau)a2).voler();
```

## Expected Output

```
Le zoo contient 6 animaux :
  - Animal[id=1, nom=Lion, age=5] {Mammifere, fourrure=Dorée}
  - Animal[id=2, nom=Aigle, age=3] {Oiseau, envergure=2.3}
  - Animal[id=3, nom=Crocodile, age=12] {Reptile, écailles=Épaisse}
  - Animal[id=4, nom=Girafe, age=7] {Mammifere, fourrure=Tachetée}
  - Animal[id=5, nom=Perroquet, age=2] {Oiseau, envergure=0.4}
  - Animal[id=6, nom=Serpent, age=4] {Reptile, écailles=Lisse}

Éléphant allaite ses petits.
Autruche s'envole avec une envergure de 1.8 m.
```

## Compilation & Execution

```bash
# From src/ directory
cd src
javac com/example/tp/*.java
java com.example.tp.Main
```

## Key Concepts Demonstrated

### Multi-Level Inheritance
- Three classes inherit from Animal
- Each adds specific attributes and behaviors
- Common functionality shared through inheritance

### Dynamic Array Expansion
- Starts with capacity of 5
- Automatically doubles when full
- No size limit for zoo

### Polymorphism
- Zoo stores all animals as `Animal[]`
- Can hold any subtype (Mammifere, Oiseau, Reptile)
- Correct `toString()` called based on actual type

### Type Casting
```java
Animal a = new Mammifere("Lion", 5, "Dorée");
((Mammifere)a).allaiter();  // Downcast to access specific method
```

### Protected Members
- Subclasses can access `nom` and `age` directly
- Demonstrates access modifier usage

## Verification Checklist
- [ ] Auto-incremented IDs are unique and sequential
- [ ] Dynamic array expands correctly (test with >5 animals)
- [ ] Each animal type has correct specific behavior
- [ ] Polymorphism works (Animal reference calls correct toString())
- [ ] Casting to specific types works for specialized methods
- [ ] All output matches expected format

## Possible Extensions

### Additional Animal Types
- Add `Poisson` (Fish) class with `nager()` method
- Add `Amphibien` (Amphibian) class
- Create more specialized mammals (Felin, Canide)

### Zoo Management Features
- `supprimerAnimal(int id)`: Remove animal by ID
- `rechercherParNom(String nom)`: Find animals by name
- `trierParAge()`: Sort animals by age
- `statistiques()`: Count animals by type
- `alimenter()`: Feed animals with schedules

### Persistence
- Save zoo data to file
- Load zoo from file
- Export animal list to CSV

### Advanced Features
- Add enclosures with capacity limits
- Track animal health status
- Implement breeding system
- Add veterinary records

## Files
- `Animal.java`: Base animal class
- `Mammifere.java`: Mammal subclass
- `Oiseau.java`: Bird subclass
- `Reptile.java`: Reptile subclass
- `Zoo.java`: Container class with dynamic array
- `Main.java`: Test program with 6+ animals
- `subejct.txt`: Complete exercise specifications
