package com.example.tp;

public class Main {
    public static void main(String[] args) {
        // 1. Compte de base
        Compte compte1 = new Compte(1000.0);
        compte1.afficherDetails();
        compte1.deposer(200);
        compte1.retirer(1500); // refusé
        compte1.afficherDetails();
        System.out.println();

        // 2. Compte Épargne
        CompteEpargne ce = new CompteEpargne(500.0, 3.0);
        ce.afficherDetails();
        ce.appliquerInterets();
        ce.afficherDetails();
        ce.retirer(100);
        ce.afficherDetails();
        System.out.println();

        // 3. Compte Courant
        CompteCourant cc = new CompteCourant(200.0, 300.0);
        cc.afficherDetails();
        cc.retirer(400);  // autorisé grâce au découvert
        cc.afficherDetails();
        cc.retirer(200);  // refusé, découvert dépassé
        cc.afficherDetails();
        System.out.println();

        // 4. Polymorphisme
        Compte poly = new CompteEpargne(100, 5);
        poly.afficherDetails(); // méthode de CompteEpargne
    }
}
