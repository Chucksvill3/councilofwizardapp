package com.example.myapplication.model;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Fleet {
    private String name; // Name of the fleet
    private ArrayList<Airship> airships; // List to store airships in the fleet

    // Constructor to initialize the fleet with a name
    public Fleet(String name) {
        this.name = name;
        this.airships = new ArrayList<>();
    }

    // Method to get the size of the fleet
    public int getSizeOfFleet() {
        return airships.size();
    }

    // Method to add an airship to the fleet
    public void addAirship(Airship airship) {
        airships.add(airship);
    }

    // Overriding toString method to provide a string representation of the fleet
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Fleet: " + name + "\n");
        for (Airship airship : airships) {
            sb.append(airship.toString()).append("\n");
        }
        return sb.toString();
    }

    // Method to load airships from assets
    public void loadAirships(AssetManager assetManager) throws IOException {
        InputStream inputStream = assetManager.open("fleet.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        // Skip the first line
        reader.readLine();

        // Read the rest of the file
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                String name = parts[0];
                String registry = parts[1];
                String airshipClass = parts[2];
                Airship airship = new Airship(name, registry, airshipClass);
                addAirship(airship);
            }
        }
        reader.close();
    }



    public ArrayList<Airship> getAirships() {
        return airships;
    }

    public Airship getAirshipByRegistry(String registry) {
        for (Airship airship : airships) {
            if (airship.getRegistry().equals(registry)) {
                return airship;
            }
        }
        return null;
    }
}
