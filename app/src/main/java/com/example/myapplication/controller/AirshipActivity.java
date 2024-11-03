package com.example.myapplication.controller;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.Airship;
import com.example.myapplication.model.Fleet;
import com.example.myapplication.model.Wizard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AirshipActivity extends AppCompatActivity {
    private Fleet fleet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airship);

        String registry = getIntent().getStringExtra("registry");
        AssetManager assetManager = getAssets();

        try {
            fleet = new Fleet("Council Fleet");
            fleet.loadAirships(assetManager);

            Airship airship = fleet.getAirshipByRegistry(registry);
            TextView airshipName = findViewById(R.id.airshipName);
            airshipName.setText(airship.getName() + " (" + airship.getRegistry() + ")");

            loadWizards(assetManager, registry);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWizards(AssetManager assetManager, String registry) throws IOException {
        InputStream inputStream = assetManager.open("wizards.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        LinearLayout wizardList = findViewById(R.id.wizardListLayout);

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[4].equals(registry)) {
                Wizard wizard = new Wizard(parts[0], parts[1], parts[2], parts[3], parts[4]);

                // Create a horizontal LinearLayout for each wizard
                LinearLayout wizardLayout = new LinearLayout(this);
                wizardLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(16, 16, 16, 16); // Set margins for the wizard layout
                wizardLayout.setLayoutParams(layoutParams);

                // Create and add the ImageView
                ImageView wizardImage = new ImageView(this);
                try {
                    // Assuming images are stored in the "images" folder within the assets directory
                    InputStream imageStream = assetManager.open("sample_images/" + parts[0].toLowerCase() + ".jpeg");
                    Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                    wizardImage.setImageBitmap(bitmap);
                    LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                            200, // Width
                            200  // Height
                    );
                    imageParams.setMargins(16, 16, 16, 16); // Set margins for the image
                    wizardImage.setLayoutParams(imageParams);
                    wizardLayout.addView(wizardImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Create a vertical LinearLayout for the text views
                LinearLayout textLayout = new LinearLayout(this);
                textLayout.setOrientation(LinearLayout.VERTICAL);

                // Create and add the TextView for the role
                TextView wizardRole = new TextView(this);
                wizardRole.setText(wizard.getRank());
                wizardRole.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                wizardRole.setTextSize(18);
                wizardRole.setTypeface(null, android.graphics.Typeface.BOLD);
                LinearLayout.LayoutParams roleParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                roleParams.setMargins(16, 16, 16, 0); // Set margins for the role text
                wizardRole.setLayoutParams(roleParams);
                textLayout.addView(wizardRole);

                // Create and add the TextView for the name
                TextView wizardName = new TextView(this);
                wizardName.setText( wizard.getName());
                wizardName.setTextSize(14);
                LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                nameParams.setMargins(16, 0, 16, 16); // Set margins for the name text
                wizardName.setLayoutParams(nameParams);
                textLayout.addView(wizardName);

                // Add the text layout to the wizard layout
                wizardLayout.addView(textLayout);

                // Add the wizard layout to the main wizard list
                wizardList.addView(wizardLayout);
            }
        }
        reader.close();
    }
}
