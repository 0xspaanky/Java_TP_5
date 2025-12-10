package com.example.tp;

public class Mammifere extends Animal {
    private String couleurFourrure;

    public Mammifere(String nom, int age, String couleurFourrure) {
        super(nom, age);
        this.couleurFourrure = couleurFourrure;
    }

    public void allaiter() {
        System.out.println(nom + " allaite ses petits.");
    }

    @Override
    public String toString() {
        return super.toString() +
               " {Mammifere, fourrure=" + couleurFourrure + "}";
    }
}
