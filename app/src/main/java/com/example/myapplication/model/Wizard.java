package com.example.myapplication.model;

// Class representing a Wizard with various attributes
public class Wizard {

    // Private fields for the Wizard's attributes
    private String name;
    private String role;
    private String rank;
    private String species;
    private String assignment;

    // Constructor to initialize all attributes of the Wizard
    public Wizard(String name, String role, String rank, String species, String assignment) {
        this.name = name;
        this.role = role;
        this.rank = rank;
        this.species = species;
        this.assignment = assignment;
    }

    // Overloaded constructor to initialize a Wizard without an assignment
    public Wizard(String name, String role, String rank, String species) {
        this(name, role, rank, species, null);
    }
    public Wizard getWizards(){
        return this;
    }
    public String getName(){
        return name;
    }

    public String getRank() {
        return rank;
    }

    // Override the toString method to provide a string representation of the Wizard
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + " (" + rank + ") - " + role + " [" + species + "]");
        if (assignment != null) {
            sb.append(" - Assignment: " + assignment);
        }
        return sb.toString();
    }

    public String toString2() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + " (" + rank + ") - " + role + " [" + species + "]");
        if (assignment != null) {
            sb.append(" - Assignment: " + assignment);
        }
        return sb.toString();
    }


}
