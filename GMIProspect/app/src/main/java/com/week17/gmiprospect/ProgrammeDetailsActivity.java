package com.week17.gmiprospect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProgrammeDetailsActivity extends AppCompatActivity {

    private static final String APPLICATION_URL =
            "https://gmi.vialing.com/oa/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_programme_details);

        // SYSTEM NAVIGATION BAR SPACING
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.programmeDetailsRoot),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        // CONNECT COMPONENTS
        TextView category =
                findViewById(R.id.tvDetailsCategory);

        TextView title =
                findViewById(R.id.tvDetailsTitle);

        TextView department =
                findViewById(R.id.tvDetailsDepartment);

        TextView description =
                findViewById(R.id.tvDetailsDescription);

        TextView duration =
                findViewById(R.id.tvDetailsDuration);

        TextView fee =
                findViewById(R.id.tvDetailsFee);

        TextView requirements =
                findViewById(R.id.tvDetailsRequirements);

        TextView careers =
                findViewById(R.id.tvDetailsCareers);

        // RECEIVE PROGRAMME INFORMATION
        Intent intent = getIntent();

        String programmeTitle =
                intent.getStringExtra("title");

        String programmeCategory =
                intent.getStringExtra("category");

        String programmeDepartment =
                intent.getStringExtra("department");

        String programmeDescription =
                intent.getStringExtra("description");

        String programmeDuration =
                intent.getStringExtra("duration");

        String programmeFee =
                intent.getStringExtra("fee");

        String programmeRequirements =
                intent.getStringExtra("requirements");

        String programmeCareers =
                intent.getStringExtra("careers");

        // DISPLAY INFORMATION
        category.setText(
                programmeCategory == null
                        ? "GMI PROGRAMME"
                        : programmeCategory.toUpperCase()
        );

        title.setText(
                programmeTitle == null
                        ? "Programme Details"
                        : programmeTitle
        );

        department.setText(
                programmeDepartment == null
                        ? "German-Malaysian Institute"
                        : programmeDepartment
        );

        description.setText(
                programmeDescription == null
                        ? "Programme information is currently unavailable."
                        : programmeDescription
        );

        duration.setText(
                programmeDuration == null
                        ? "Contact GMI"
                        : programmeDuration
        );

        fee.setText(
                programmeFee == null
                        ? "Contact GMI"
                        : programmeFee
        );

        requirements.setText(
                programmeRequirements == null
                        ? "Please refer to GMI's official admission requirements."
                        : programmeRequirements
        );

        careers.setText(
                programmeCareers == null
                        ? "Please refer to GMI's official programme information for career pathways."
                        : programmeCareers
        );

        // BACK BUTTON
        findViewById(R.id.btnDetailsBack)
                .setOnClickListener(v -> finish());

        // APPLY NOW BUTTON
        findViewById(R.id.btnApplyNow)
                .setOnClickListener(v -> {

                    try {

                        Intent browser = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(APPLICATION_URL)
                        );

                        startActivity(browser);

                    } catch (Exception e) {

                        Toast.makeText(
                                this,
                                "Unable to open application website.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}
