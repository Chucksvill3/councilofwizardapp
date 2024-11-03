package com.example.myapplication.model;

import java.util.ArrayList;

public class Airship {

    // Fields to store the name, registry, class of the airship, and a list of wizards assigned to it
    private String name;
    private String registry;
    private String airshipClass;
    private ArrayList<Wizard> wizards;

    // Constructor to initialize the airship with its name, registry, and class
    public Airship(String name, String registry, String airshipClass) {
        this.name = name;
        this.registry = registry;
        this.airshipClass = airshipClass;
        this.wizards = new ArrayList<>();
    }

    // Override the toString method to provide a string representation of the airship
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + ", " + airshipClass + ". Registry: " + registry + "\n");
        sb.append(getNumberOfWizards() + " wizards assigned.\n");
        for (Wizard wizard : wizards) {
            sb.append("- " + wizard.toString() + "\n");
        }
        return sb.toString();
    }

    // Method to add a wizard to the airship's list of wizards
    public void addWizard(Wizard wizard) {
        wizards.add(wizard);
    }

    // Method to get the number of wizards assigned to the airship
    public int getNumberOfWizards() {
        return wizards.size();
    }

    public String getName() {
        return name;
    }

    public String getRegistry() {
        return registry;
    }
}
