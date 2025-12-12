# Exercise 4: Library Management System

![Exercise 4 Diagram](./entities/image.png)

## Objective
Develop a library management system with document hierarchy (Document → Livre/Revue → specialized books), CRUD operations, and interactive menu interface.

## Key Concepts
- Multi-level inheritance (3 levels)
- Fixed-capacity array management
- CRUD operations (Create, Read, Update, Delete)
- Type checking with `instanceof`
- Interactive console menu

## Class Hierarchy
```
Document (base)
├─ Livre (Book)
│   ├─ Dictionnaire (Dictionary)
│   ├─ Roman (Novel)
│   └─ Manuel (Manual)
└─ Revue (Journal)
```

## Implementation

### Document (Base Class)
```java
package entities;

public class Document {
    private static int compteur = 0;
    private final int numEnreg;
    protected String titre;

    public Document(String titre) {
        this.numEnreg = ++compteur;
        this.titre = titre;
    }

    public int getNumEnreg() { return numEnreg; }
    public String getTitre() { return titre; }

    @Override
    public String toString() {
        return "Document[numEnreg=" + numEnreg + ", titre='" + titre + "']";
    }
}
```

### Livre (Book)
```java
public class Livre extends Document {
    private String auteur;
    private int nbPages;

    public Livre(String titre, String auteur, int nbPages) {
        super(titre);
        this.auteur = auteur;
        this.nbPages = nbPages;
    }

    public String getAuteur() { return auteur; }

    @Override
    public String toString() {
        return "Livre[numEnreg=" + getNumEnreg() + ", titre='" + getTitre() +
               "', auteur='" + auteur + "', pages=" + nbPages + "]";
    }
}
```

### Dictionnaire (Dictionary)
```java
public class Dictionnaire extends Livre {
    private String langue;

    public Dictionnaire(String titre, String auteur, int nbPages, String langue) {
        super(titre, auteur, nbPages);
        this.langue = langue;
    }

    @Override
    public String toString() {
        return "Dictionnaire" + super.toString() + " {langue='" + langue + "'}";
    }
}
```

### Roman (Novel)
```java
public class Roman extends Livre {
    private String genre;

    public Roman(String titre, String auteur, int nbPages, String genre) {
        super(titre, auteur, nbPages);
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Roman" + super.toString() + " {genre='" + genre + "'}";
    }
}
```

### Revue (Journal)
```java
public class Revue extends Document {
    private int numero;
    private int mois;
    private int annee;

    public Revue(String titre, int numero, int mois, int annee) {
        super(titre);
        this.numero = numero;
        this.mois = mois;
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Revue[numEnreg=" + getNumEnreg() + ", titre='" + getTitre() +
               "', numero=" + numero + ", mois=" + mois + ", annee=" + annee + "]";
    }
}
```

### Bibliotheque (Library)
```java
public class Bibliotheque {
    private Document[] documents;
    private int capacite;
    private int nbDocuments = 0;

    public Bibliotheque(int capacite) {
        this.capacite = capacite;
        this.documents = new Document[capacite];
    }

    public boolean ajouter(Document doc) {
        if (nbDocuments >= capacite) return false;
        documents[nbDocuments++] = doc;
        return true;
    }

    public boolean supprimer(Document doc) {
        for (int i = 0; i < nbDocuments; i++) {
            if (documents[i] == doc) {
                // Shift remaining elements
                for (int j = i; j < nbDocuments - 1; j++) {
                    documents[j] = documents[j + 1];
                }
                documents[--nbDocuments] = null;
                return true;
            }
        }
        return false;
    }

    public Document document(int numEnreg) {
        for (int i = 0; i < nbDocuments; i++) {
            if (documents[i].getNumEnreg() == numEnreg) {
                return documents[i];
            }
        }
        return null;
    }

    public void afficherDocuments() {
        System.out.println("===== BIBLIOTHEQUE =====");
        for (int i = 0; i < nbDocuments; i++) {
            System.out.println("[" + (i+1) + "] " + documents[i]);
        }
    }

    public void afficherAuteurs() {
        System.out.println("===== AUTEURS =====");
        for (int i = 0; i < nbDocuments; i++) {
            if (documents[i] instanceof Livre) {
                Livre livre = (Livre) documents[i];
                System.out.println("- " + livre.getAuteur());
            }
        }
    }
}
```

## Usage Example
```java
package test;
import entities.*;

public class Main {
    public static void main(String[] args) {
        Bibliotheque biblio = new Bibliotheque(10);

        Document d1 = new Roman("Les Misérables", "Victor Hugo", 1500, "Classique");
        Document d2 = new Dictionnaire("Larousse", "Collectif", 2000, "FR-EN");
        Document d3 = new Revue("Science et Vie", 423, 6, 2024);

        biblio.ajouter(d1);
        biblio.ajouter(d2);
        biblio.ajouter(d3);

        biblio.afficherDocuments();
        biblio.afficherAuteurs();

        Document found = biblio.document(2);
        if (found != null) {
            System.out.println("Trouvé: " + found);
        }

        biblio.supprimer(d3);
    }
}
```

## Expected Output
```
===== BIBLIOTHEQUE =====
[1] Roman[numEnreg=1, titre='Les Misérables', auteur='Victor Hugo', pages=1500] {genre='Classique'}
[2] Dictionnaire[numEnreg=2, titre='Larousse', auteur='Collectif', pages=2000] {langue='FR-EN'}
[3] Revue[numEnreg=3, titre='Science et Vie', numero=423, mois=6, annee=2024]

===== AUTEURS =====
- Victor Hugo
- Collectif

Trouvé: Dictionnaire[numEnreg=2, titre='Larousse', auteur='Collectif', pages=2000] {langue='FR-EN'}
```

## Compilation & Execution
```bash
javac entities/*.java test/*.java
java test.Main
```

## Interactive Menu
```
===== MENU BIBLIOTHEQUE =====
1. Ajouter un document
2. Afficher tous les documents
3. Supprimer un document
4. Rechercher un document
5. Afficher tous les auteurs
6. Quitter
============================
```

## Key Features
- **Fixed capacity**: Library enforces maximum document limit
- **Array shifting**: Remove operation shifts elements left
- **Type checking**: `instanceof` to filter books for authors
- **Auto-increment IDs**: Unique registration numbers

## Extensions
- Add ISBN management for books
- Track borrowed documents and due dates
- Implement user/borrower system
- Search by title or author
- Save/load library data from files
