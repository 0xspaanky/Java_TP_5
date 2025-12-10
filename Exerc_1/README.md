# Exercise 1: Bank Account Hierarchy

![Exercise 1 Diagram](./image.png)

## Objective
Understand and implement inheritance in Java through a concrete hierarchy of classes: a base bank account (Compte), with two specialized types (CompteEpargne and CompteCourant), each adding its own behavior.

## Description
This exercise creates a banking system with three types of accounts demonstrating inheritance concepts without using abstract classes or interfaces. Each account type has unique features while sharing common base functionality.

## UML Class Diagram

```
           +-------------------------------+
           |            Compte             |
           +-------------------------------+
           | - compteur : int (static)     |
           | - numero   : int              |
           | - solde    : double           |
           +-------------------------------+
           | + Compte(soldeInitial)        |
           | + deposer(montant)            |
           | + retirer(montant)            |
           | + afficherDetails()           |
           +-------------------------------+
                /|\             /|\
                 |               |
     +-----------+---------------+-----------+
     |                                       |
+-----------------------+             +-----------------------------+
|   CompteEpargne       |             |    CompteCourant            |
+-----------------------+             +-----------------------------+
| - tauxInteret : double|             | - decouvertAutorise: double |
+-----------------------+             +-----------------------------+
| + CompteEpargne(...)  |             | + CompteCourant(...)        |
| + appliquerInterets() |             | + retirer(montant)          |  (override)
| + afficherDetails()   |             | + afficherDetails()         |  (override)
+-----------------------+             +-----------------------------+
```

## Class Structure

### Compte (Base Class)
Located in `com.example.tp` package

**Attributes:**
- `compteur` (static int): Global counter for account number generation
- `numero` (final int): Unique account number
- `solde` (protected double): Account balance

**Constructor:**
```java
public Compte(double soldeInitial)
```

**Methods:**
- `deposer(double montant)`: Deposits money into account
- `retirer(double montant)`: Withdraws money if sufficient balance
- `afficherDetails()`: Displays account information
- Getters: `getNumero()`, `getSolde()`

### CompteEpargne (Savings Account)

**Attributes:**
- `tauxInteret` (double): Interest rate in percentage (e.g., 2.5 for 2.5%)

**Constructor:**
```java
public CompteEpargne(double soldeInitial, double tauxInteret)
```

**Additional Methods:**
- `appliquerInterets()`: Applies interest to current balance
- `afficherDetails()`: Overrides to show interest rate

**Interest Calculation:**
```
interest = balance × (rate / 100)
new_balance = balance + interest
```

### CompteCourant (Checking Account)

**Attributes:**
- `decouvertAutorise` (double): Authorized overdraft limit

**Constructor:**
```java
public CompteCourant(double soldeInitial, double decouvertAutorise)
```

**Overridden Methods:**
- `retirer(double montant)`: Allows overdraft up to authorized limit
- `afficherDetails()`: Shows overdraft limit

**Overdraft Logic:**
```
Can withdraw if: balance + overdraft_limit >= amount
```

## Key Concepts Demonstrated

### Inheritance
- `CompteEpargne` and `CompteCourant` extend `Compte`
- Inherit common functionality (deposit, getters)
- Add specialized behavior

### Method Overriding
- `retirer()` in `CompteCourant` allows overdraft
- `afficherDetails()` customized in each subclass
- Uses `@Override` annotation

### Polymorphism
```java
Compte poly = new CompteEpargne(100, 5);
poly.afficherDetails();  // Calls CompteEpargne version
```

### Constructor Chaining
- Subclasses call `super(soldeInitial)` to initialize parent

## Example Usage

```java
// Create accounts
Compte compte1 = new Compte(1000.0);
CompteEpargne ce = new CompteEpargne(500.0, 3.0);
CompteCourant cc = new CompteCourant(200.0, 300.0);

// Operations on base account
compte1.deposer(200);
compte1.retirer(1500);  // Refused

// Savings account operations
ce.appliquerInterets();  // Adds 3% interest
ce.retirer(100);

// Checking account operations
cc.retirer(400);  // Allowed with overdraft
cc.retirer(200);  // Refused, exceeds limit
```

## Expected Output

```
Compte #1 — solde = 1000.0
Compte #1 : dépôt de 200.0
Compte #1 : retrait impossible (solde insuffisant)
Compte #1 — solde = 1200.0

CompteEpargne #2 — solde = 500.0, taux = 3.0%
CompteEpargne #2 : intérêts de 15.0
CompteEpargne #2 — solde = 515.0, taux = 3.0%
CompteEpargne #2 : retrait de 100.0
CompteEpargne #2 — solde = 415.0, taux = 3.0%

CompteCourant #3 — solde = 200.0, découvert autorisé = 300.0
CompteCourant #3 : retrait de 400.0
CompteCourant #3 — solde = -200.0, découvert autorisé = 300.0
CompteCourant #3 : retrait impossible (découvert max dépassé)
CompteCourant #3 — solde = -200.0, découvert autorisé = 300.0

CompteEpargne #4 — solde = 100.0, taux = 5.0%
```

## Compilation & Execution

```bash
# From src/ directory
javac com/example/tp/*.java
java com.example.tp.Main
```

## Key Learning Points

### Concrete Inheritance
- No abstract classes or interfaces needed
- Direct inheritance with concrete implementations
- Simple hierarchy structure

### Protected Access
- `solde` is protected for subclass access
- Demonstrates access modifier usage

### Constructor Pattern
- `super()` must be first in subclass constructor
- Parent initialization before child

### Polymorphic Behavior
- Parent reference can hold child objects
- Correct method version called at runtime

## Verification Checklist
- [ ] Account numbers auto-increment correctly
- [ ] Base account refuses overdraft
- [ ] Savings account calculates interest properly
- [ ] Checking account allows authorized overdraft
- [ ] Polymorphism works (parent reference calls overridden methods)
- [ ] All output matches expected format

## Possible Extensions
- Add transaction history
- Implement account transfer between accounts
- Add fees for certain operations
- Create account statement generation
- Add date/time stamps for transactions
- Implement minimum balance requirements

## Files
- `Compte.java`: Base account class
- `CompteEpargne.java`: Savings account with interest
- `CompteCourant.java`: Checking account with overdraft
- `Main.java`: Test program demonstrating all features
- `subejct.txt`: Complete exercise specifications
