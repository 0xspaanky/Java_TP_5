# Exercise 3: Vehicle Fleet Management - Inheritance and Composition

![Exercise 3 Diagram](./image.png)

## Objective
Model a vehicle fleet in Java with:
- Multi-level class hierarchy (Vehicule → Motorise → Voiture, Camion, Moto, VoitureElectrique)
- Composition with Moteur (Engine) class
- Fuel/battery management without exceptions
- Flotte (Fleet) class with dynamic storage and statistics

## Description
This comprehensive exercise demonstrates advanced inheritance with multiple levels, composition patterns, and practical fleet management. The system tracks fuel consumption, distance traveled, and provides realistic vehicle operations.

## Class Hierarchy

```
   Vehicule
   ├─ id         : int (auto-incr)
   ├─ modele     : String
   + toString()

       ↓

   Motorise
   ├─ moteur         : Moteur (composition)
   ├─ niveauCarburant: double
   ├─ kmTotal        : double
   + demarrer()
   + rouler(km)      : void
   + refuel(litres)  : void

       ↓          ↓             ↓             ↓
    Voiture     Camion        Moto     VoitureElectrique
    {nbPortes}  {charge}   {wheelie()}  {recharger(kWh)}
```

## Class Structure

### Moteur (Engine - Composition)
Located in `com.example.tp` package

**Attributes:**
- `type` (String): Engine type (e.g., "Essence", "Diesel", "Élec")
- `puissance` (int): Power in horsepower

**Constructor:**
```java
public Moteur(String type, int puissance)
```

**Example:** `new Moteur("Essence", 75)`

### Vehicule (Base Class)

**Attributes:**
- `compteur` (static int): Global counter for ID generation
- `id` (final int): Unique vehicle ID
- `modele` (protected String): Vehicle model name

**Constructor:**
```java
public Vehicule(String modele)
```

### Motorise (Motorized Vehicle)

**Attributes:**
- `moteur` (Moteur): Engine instance (composition)
- `niveauCarburant` (double): Fuel/battery level in units
- `kmTotal` (double): Total kilometers driven

**Constructor:**
```java
public Motorise(String modele, Moteur moteur, double carburantInitial)
```

**Methods:**
- `demarrer()`: Starts vehicle, displays fuel level and engine info
- `rouler(double km)`: Drive specified distance
  - Consumption: 0.1 units per km
  - Checks if sufficient fuel available
  - Updates fuel level and total km
- `refuel(double quantite)`: Refills fuel/battery

**Fuel Logic:**
```
consumption = km × 0.1
if (fuel >= consumption):
    fuel -= consumption
    total_km += km
else:
    refuse and display warning
```

### Voiture (Car)

**Attributes:**
- `nbPortes` (int): Number of doors

**Constructor:**
```java
public Voiture(String modele, Moteur moteur, double carburant, int nbPortes)
```

**Methods:**
- `klaxonner()`: Honk the horn - "Pouet!"

### Camion (Truck)

**Attributes:**
- `capaciteCharge` (double): Load capacity in tons

**Constructor:**
```java
public Camion(String modele, Moteur moteur, double carburant, double capaciteCharge)
```

**Methods:**
- `charger(double poids)`: Load cargo

### Moto (Motorcycle)

**Attributes:**
- `cylindree` (double): Engine displacement in cm³

**Constructor:**
```java
public Moto(String modele, Moteur moteur, double carburant, double cylindree)
```

**Methods:**
- `faireWheelie()`: Perform a wheelie stunt

### VoitureElectrique (Electric Car)

Extends Motorise with electric-specific behavior.

**Constructor:**
```java
public VoitureElectrique(String modele, Moteur moteur, double batterieInitial)
```

**Overridden Methods:**
- `refuel(double kWh)`: Renamed to show "recharge" instead of refuel
- `toString()`: Displays "batterie" instead of "carburant"

### Flotte (Fleet Management)

**Attributes:**
- `parc` (Vehicule[]): Dynamic array of vehicles
- `nb` (int): Current number of vehicles

**Constructor:**
```java
public Flotte()
```
- Initializes array with capacity of 5

**Methods:**
- `ajouter(Vehicule v)`: Adds vehicle, doubles array if full
- `afficherTous()`: Displays all vehicles
- `distanceTotale()`: Calculates total kilometers driven by all vehicles

**Distance Calculation:**
```java
for each vehicle:
    if (vehicle instanceof Motorise):
        sum += vehicle.kmTotal
```

## Example Usage

```java
Flotte flotte = new Flotte();

Voiture v1 = new Voiture("Clio", new Moteur("Essence",75), 50, 5);
Camion c1 = new Camion("Volvo", new Moteur("Diesel",400), 200, 20);
Moto m1 = new Moto("Harley", new Moteur("Essence",90), 20, 1200);
VoitureElectrique e1 = new VoitureElectrique("Tesla", new Moteur("Élec",300), 85);

flotte.ajouter(v1);
flotte.ajouter(c1);
flotte.ajouter(m1);
flotte.ajouter(e1);

flotte.afficherTous();

// Driving scenario
v1.demarrer();
v1.rouler(100);      // Uses 10 units of fuel
v1.refuel(20);       // Adds 20 units
v1.rouler(300);      // Uses 30 units

e1.demarrer();
e1.rouler(200);      // Uses 20 kWh
e1.refuel(50);       // Adds 50 kWh
e1.rouler(300);      // Uses 30 kWh

System.out.println("Distance totale : " + flotte.distanceTotale() + " km");
```

## Expected Output Pattern

```
Flotte (4 véhicules) :
  • Vehicule#1 [Clio] {Essence 75ch, carburant=50.0} {Voiture, portes=5}
  • Vehicule#2 [Volvo] {Diesel 400ch, carburant=200.0} {Camion, cap=20.0 t}
  • Vehicule#3 [Harley] {Essence 90ch, carburant=20.0} {Moto, cylindrée=1200.0 cm³}
  • Vehicule#4 [Tesla] {Élec 300ch, batterie=85.0} {Electrique}

Vehicule#1 [Clio] démarre avec 50.0 unités et moteur Essence 75ch
Clio a roulé 100.0 km, reste 40.0 unités
Clio ravitaillé de 20.0 unités (nouveau niveau : 60.0)
Clio a roulé 300.0 km, reste 30.0 unités

Vehicule#4 [Tesla] démarre avec 85.0 unités et moteur Élec 300ch
Tesla a roulé 200.0 km, reste 65.0 unités
Tesla recharge 50.0 kWh (niveau=115.0)
Tesla a roulé 300.0 km, reste 85.0 unités

Distance totale parcourue : 600.0 km
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
- Vehicule → Motorise → specific types
- Three levels of hierarchy
- Each level adds functionality

### Composition over Inheritance
- `Moteur` is composed into `Motorise`
- "Has-a" relationship (not "is-a")
- Engine can be reused across vehicle types

### Polymorphism
- Fleet stores vehicles as `Vehicule[]`
- `instanceof` check for type-specific operations
- Different vehicle types treated uniformly

### Method Overriding
- `VoitureElectrique` overrides `refuel()` and `toString()`
- Customizes behavior for electric vehicles

### Dynamic Array Management
- Fleet expands automatically when full
- Uses `System.arraycopy()` for efficiency

### Fuel Management Logic
- Simple consumption model (0.1 units/km)
- Validation before operations
- User-friendly error messages

## Verification Checklist
- [ ] Auto-incremented vehicle IDs work correctly
- [ ] Fuel consumption calculated properly (0.1 per km)
- [ ] Vehicles refuse operations when fuel insufficient
- [ ] Electric car displays "batterie" instead of "carburant"
- [ ] Fleet calculates total distance correctly
- [ ] Dynamic array expands when needed
- [ ] Composition (Moteur) works as expected

## Possible Extensions

### Enhanced Fuel Management
- Different consumption rates per vehicle type
- Fuel efficiency based on speed/load
- Fuel tank capacity limits

### Maintenance System
- Track maintenance schedules
- Oil changes, tire rotations
- Service history logging

### Advanced Fleet Features
- GPS tracking system
- Route optimization
- Driver assignment
- Cost calculations (fuel, maintenance)

### Additional Vehicle Types
- Bus (passenger capacity)
- Bicycle (non-motorized)
- Hybrid (dual fuel system)

### Statistics & Reporting
- Fuel efficiency rankings
- Most/least driven vehicles
- Monthly expense reports
- Environmental impact calculations

## Files
- `Moteur.java`: Engine class (composition)
- `Vehicule.java`: Base vehicle class
- `Motorise.java`: Motorized vehicle with fuel management
- `Voiture.java`: Car with doors and horn
- `Camion.java`: Truck with cargo capacity
- `Moto.java`: Motorcycle with wheelie
- `VoitureElectrique.java`: Electric car with battery
- `Flotte.java`: Fleet management with statistics
- `Main.java`: Test program with driving scenarios
- `subejct.txt`: Complete exercise specifications
