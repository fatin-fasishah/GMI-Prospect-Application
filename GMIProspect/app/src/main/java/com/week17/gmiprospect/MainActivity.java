package com.week17.gmiprospect;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // SYSTEM WINDOW INSETS
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

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
                }
        );

        // MENU BUTTON
        TextView menuButton = findViewById(R.id.btnMenu);
        menuButton.setOnClickListener(v -> showMenu(menuButton));

        // VIEW ALL PROGRAMMES
        findViewById(R.id.btnExploreCourses)
                .setOnClickListener(v -> {

                    EditText search = findViewById(R.id.etSearchProgram);

                    Intent intent = new Intent(
                            MainActivity.this,
                            ProgrammesActivity.class
                    );

                    intent.putExtra("category", "All");
                    intent.putExtra(
                            "query",
                            search.getText().toString()
                    );

                    startActivity(intent);
                });

        // DIPLOMA CARD
        findViewById(R.id.cardDiploma)
                .setOnClickListener(v -> openProgrammes("Diploma"));

        // GAPP CARD
        findViewById(R.id.cardGapp)
                .setOnClickListener(v -> openProgrammes("GAPP"));

        // GUFP CARD
        findViewById(R.id.cardGufp)
                .setOnClickListener(v -> openProgrammes("GUFP"));

        // CHECK ELIGIBILITY BUTTON
        findViewById(R.id.btnCheckEligibility)
                .setOnClickListener(v -> {
                    Intent intent = new Intent(
                            MainActivity.this,
                            EligibilityActivity.class
                    );

                    startActivity(intent);
                });

        // ABOUT GMI BUTTON
        findViewById(R.id.btnAboutGmi)
                .setOnClickListener(v -> openAbout());

        // CONTACT US BUTTON
        findViewById(R.id.btnContactUs)
                .setOnClickListener(v -> openContact());
    }

    // OPEN SEPARATE ABOUT GMI PAGE
    private void openAbout() {
        startActivity(new Intent(MainActivity.this, AboutActivity.class));
    }

    // OPEN SEPARATE CONTACT US PAGE
    private void openContact() {
        startActivity(new Intent(MainActivity.this, ContactActivity.class));
    }

    // OPEN PROGRAMME CATALOGUE
    private void openProgrammes(String category) {

        Intent intent = new Intent(
                MainActivity.this,
                ProgrammesActivity.class
        );

        intent.putExtra("category", category);
        startActivity(intent);
    }

    // DISPLAY NAVIGATION MENU
    private void showMenu(View anchor) {

        PopupMenu popup = new PopupMenu(
                new ContextThemeWrapper(
                        this,
                        R.style.GmiPopupTheme
                ),
                anchor
        );

        popup.getMenu().add(0, 1, 0, "Home");
        popup.getMenu().add(0, 2, 1, "Programmes");
        popup.getMenu().add(0, 3, 2, "Check Eligibility");
        popup.getMenu().add(0, 4, 3, "About GMI");
        popup.getMenu().add(0, 5, 4, "Contact Us");

        popup.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case 1:
                    ScrollView scrollView = findViewById(R.id.main);
                    scrollView.smoothScrollTo(0, 0);
                    return true;

                case 2:
                    openProgrammes("All");
                    return true;

                case 3:
                    Intent eligibilityIntent = new Intent(
                            MainActivity.this,
                            EligibilityActivity.class
                    );
                    startActivity(eligibilityIntent);
                    return true;

                case 4:
                    openAbout();
                    return true;

                case 5:
                    openContact();
                    return true;

                default:
                    return false;
            }
        });

        popup.show();
    }

    // SCROLL TO SELECTED SECTION
    private void scrollToView(int viewId) {

        View target = findViewById(viewId);

        if (target != null) {
            target.requestRectangleOnScreen(
                    new Rect(
                            0,
                            0,
                            target.getWidth(),
                            target.getHeight()
                    ),
                    true
            );
        }
    }
}
