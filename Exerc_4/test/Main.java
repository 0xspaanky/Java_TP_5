package test;

import entities.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez la capacité de la bibliothèque : ");
        int n = sc.nextInt();
        sc.nextLine();

        Bibliotheque bib = new Bibliotheque(n);

        // Deux documents initiaux
        bib.ajouter(new Roman("Harry Potter", "J.K. Rowling", 500, 29.99));
        bib.ajouter(new Revue("Science & Vie", "Mars", 2023));

        int choix;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Ajouter un document");
            System.out.println("2. Afficher tous les documents");
            System.out.println("3. Supprimer un document");
            System.out.println("4. Rechercher par numéro");
            System.out.println("5. Afficher les auteurs");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("Ajout de Revue test...");
                    bib.ajouter(new Revue("Le Monde", "Décembre", 2021));
                    break;
                case 2:
                    bib.afficherDocuments();
                    break;
                case 3:
                    System.out.print("Numéro d'enregistrement à supprimer : ");
                    int num = sc.nextInt();
                    Document d = bib.document(num);
                    if (d != null && bib.supprimer(d)) {
                        System.out.println("Supprimé.");
                    } else {
                        System.out.println("Échec.");
                    }
                    break;
                case 4:
                    System.out.print("Numéro d'enregistrement à rechercher : ");
                    int id = sc.nextInt();
                    Document doc = bib.document(id);
                    System.out.println(doc != null ? doc : "Non trouvé.");
                    break;
                case 5:
                    bib.afficherAuteurs();
                    break;
            }
        } while (choix != 0);
    }
}
