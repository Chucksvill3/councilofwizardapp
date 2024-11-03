package com.example.myapplication.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.Airship;
import com.example.myapplication.model.Fleet;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Fleet fleet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fleet = new Fleet("Council Fleet");
        LinearLayout layout = findViewById(R.id.airshipButtonsLayout);

        try {
            // Load airships directly using the Fleet's method
            fleet.loadAirships(getAssets());

            // Dynamically add buttons for each airship
            for (Airship airship : fleet.getAirships()) {
                Button button = new Button(this);
                button.setText(airship.getName() + " " + airship.getRegistry());
                button.setBackgroundResource(R.drawable.button_background);
                button.setTextColor(Color.WHITE);// Set custom background

                // Set button layout parameters with margins
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, // Width
                        LinearLayout.LayoutParams.WRAP_CONTENT  // Height
                );
                params.setMargins(16, 16, 16, 16); // Add margins (left, top, right, bottom)
                button.setLayoutParams(params);

                layout.addView(button);

                // Set click listener for each button
                button.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, AirshipActivity.class);
                    intent.putExtra("registry", airship.getRegistry());
                    startActivity(intent);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            TextView errorText = new TextView(this);
            errorText.setText("Error loading airships.");
            layout.addView(errorText);
        }
    }
}
