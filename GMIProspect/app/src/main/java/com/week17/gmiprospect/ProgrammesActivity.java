package com.week17.gmiprospect;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;

public class ProgrammesActivity extends AppCompatActivity {

    private LinearLayout programmeContainer;
    private LinearLayout filterContainer;
    private EditText searchInput;
    private TextView resultCount;

    private String selectedCategory = "All";

    private final ArrayList<Programme> programmes =
            new ArrayList<>();

    private final int RED = Color.rgb(200, 16, 46);
    private final int NAVY = Color.rgb(20, 43, 69);
    private final int GREY = Color.rgb(100, 100, 100);

    private final String GAPP_URL =
            "https://www.gmi.edu.my/pre-university-programme/german-a-level-preparatory-programme-gapp/";

    private final String GUFP_URL =
            "https://www.gmi.edu.my/gmi-utp-foundation-studies-programme/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_programmes);

        // SYSTEM NAVIGATION BAR SPACING
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.programmesRoot),
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
                }
        );

        // CONNECT XML COMPONENTS
        programmeContainer =
                findViewById(R.id.programmeContainer);

        filterContainer =
                findViewById(R.id.filterContainer);

        searchInput =
                findViewById(R.id.etProgrammeSearch);

        resultCount =
                findViewById(R.id.tvProgrammeCount);

        TextView backButton = findViewById(R.id.btnBack);

        // MAKE SURE THE DYNAMIC SECTIONS ARE VISIBLE
        filterContainer.setVisibility(View.VISIBLE);
        resultCount.setVisibility(View.VISIBLE);
        programmeContainer.setVisibility(View.VISIBLE);

        // BACK BUTTON: RETURN TO THE HOMEPAGE
        backButton.setClickable(true);
        backButton.setFocusable(true);
        backButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(
                    ProgrammesActivity.this,
                    MainActivity.class
            );
            homeIntent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );
            startActivity(homeIntent);
            finish();
        });

        // GET SELECTED CATEGORY
        selectedCategory =
                getIntent().getStringExtra("category");

        if (selectedCategory == null ||
                selectedCategory.trim().isEmpty()) {

            selectedCategory = "All";
        }

        // LOAD PROGRAMMES
        loadProgrammes();

        // SEARCH QUERY
        String initialQuery =
                getIntent().getStringExtra("query");

        if (initialQuery != null) {
            searchInput.setText(initialQuery);
        }

        // BUILD CATEGORY FILTERS
        buildFilters();

        // SEARCH FUNCTION
        searchInput.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after) {
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count) {

                        displayProgrammes();
                    }

                    @Override
                    public void afterTextChanged(
                            Editable s) {
                    }
                }
        );

        displayProgrammes();
    }

    // =====================================
    // LOAD PROGRAMMES
    // =====================================

    private void loadProgrammes() {

        programmes.clear();

        // ELECTRICAL ENGINEERING

        add(
                "Diploma of Mechatronics Engineering Technology",
                "Diploma",
                "Electrical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Develop engineering and practical skills in mechatronics.",
                "https://www.gmi.edu.my/electrical-engineering/"
        );

        add(
                "Diploma in Engineering Technology (Sustainable Energy and Power Distribution)",
                "Diploma",
                "Electrical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Explore sustainable energy and power distribution technology.",
                "https://www.gmi.edu.my/electrical-engineering/"
        );

        add(
                "Diploma of Electronics Engineering Technology (Computer)",
                "Diploma",
                "Electrical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Explore electronics engineering and computer technology.",
                "https://www.gmi.edu.my/electrical-engineering/"
        );

        add(
                "Diploma of Engineering Technology (Instrumentation and Control)",
                "Diploma",
                "Electrical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Learn about instrumentation and industrial control systems.",
                "https://www.gmi.edu.my/electrical-engineering/"
        );

        add(
                "Diploma in Autotronics Engineering Technology",
                "Diploma",
                "Electrical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Explore automotive technology and electronic systems.",
                "https://www.gmi.edu.my/electrical-engineering/"
        );

        // MECHANICAL ENGINEERING

        add(
                "Diploma in Precision Tooling Engineering Technology",
                "Diploma",
                "Mechanical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Develop skills in precision tooling technology.",
                "https://www.gmi.edu.my/mechanical-engineering/"
        );

        add(
                "Diploma in Engineering Technology (Industrial Design)",
                "Diploma",
                "Mechanical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Explore industrial design and product development.",
                "https://www.gmi.edu.my/mechanical-engineering/"
        );

        add(
                "Diploma in Industrial Quality Engineering Technology",
                "Diploma",
                "Mechanical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Learn industrial quality management and inspection.",
                "https://www.gmi.edu.my/mechanical-engineering/"
        );

        add(
                "Diploma in Innovative Product Design Engineering Technology",
                "Diploma",
                "Mechanical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Explore innovative product design and engineering.",
                "https://www.gmi.edu.my/mechanical-engineering/"
        );

        add(
                "Diploma of Mechanical Engineering Technology (CNC Precision)",
                "Diploma",
                "Mechanical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Develop knowledge of CNC precision manufacturing.",
                "https://www.gmi.edu.my/mechanical-engineering/"
        );

        add(
                "Diploma in Engineering Technology (Machine Tools Maintenance)",
                "Diploma",
                "Mechanical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Learn about machine tools and maintenance technology.",
                "https://www.gmi.edu.my/mechanical-engineering/"
        );

        add(
                "Diploma of Mechanical Engineering Technology (Manufacturing)",
                "Diploma",
                "Mechanical Engineering",
                "6 semesters / 3 years",
                "RM33,324",
                "Explore manufacturing processes and technology.",
                "https://www.gmi.edu.my/mechanical-engineering/"
        );

        // COMPUTER AND INFORMATION

        add(
                "Diploma in Creative Multimedia",
                "Diploma",
                "Computer and Information",
                "6 semesters / 3 years",
                "RM33,324",
                "Explore creative multimedia and digital content production.",
                "https://www.gmi.edu.my/computer-and-information/"
        );

        add(
                "Diploma in Software Engineering",
                "Diploma",
                "Computer and Information",
                "6 semesters / 3 years",
                "RM33,324",
                "Develop software engineering and programming skills.",
                "https://www.gmi.edu.my/computer-and-information/"
        );

        add(
                "Diploma in Cyber Security Technology",
                "Diploma",
                "Computer and Information",
                "6 semesters / 3 years",
                "RM33,324",
                "Explore cybersecurity and digital security technology.",
                "https://www.gmi.edu.my/computer-and-information/"
        );

        // GAPP

        add(
                "German University Preparatory Programme (GAPP)",
                "GAPP",
                "Pre-University",
                "20 months at GMI + 6 months German language training",
                "RM42,400 for 4 semesters (subject to change)",
                "Prepare for further studies at a University of Applied Sciences in Germany.",
                GAPP_URL
        );

        // GUFP

        add(
                "GMI-UTP Foundation Programme (GUFP)",
                "GUFP",
                "Pre-University",
                "1 year",
                "From RM18,100 to RM20,500",
                "Foundation in Engineering and Science based on UTP's syllabus.",
                GUFP_URL
        );
    }

    private void add(
            String title,
            String category,
            String department,
            String duration,
            String fee,
            String description,
            String url) {

        programmes.add(
                new Programme(
                        title,
                        category,
                        department,
                        duration,
                        fee,
                        description,
                        url
                )
        );
    }

    // =====================================
    // CATEGORY FILTERS
    // =====================================

    private void buildFilters() {

        filterContainer.removeAllViews();

        String[] categories = {
                "All",
                "Diploma",
                "GAPP",
                "GUFP"
        };

        for (String category : categories) {

            TextView button = new TextView(this);

            button.setText(category);
            button.setTextSize(13);
            button.setTypeface(null, Typeface.BOLD);

            button.setGravity(Gravity.CENTER);

            button.setPadding(
                    dp(17),
                    dp(10),
                    dp(17),
                    dp(10)
            );

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            -2,
                            -2
                    );

            params.setMargins(0, 0, dp(9), 0);

            button.setLayoutParams(params);

            updateFilterStyle(button, category);

            button.setOnClickListener(v -> {

                selectedCategory = category;

                buildFilters();

                displayProgrammes();
            });

            filterContainer.addView(button);
        }
    }

    private void updateFilterStyle(
            TextView button,
            String category) {

        boolean selected =
                selectedCategory.equals(category);

        GradientDrawable background =
                new GradientDrawable();

        background.setCornerRadius(dp(22));

        background.setColor(
                selected ? RED : Color.WHITE
        );

        background.setStroke(dp(1), RED);

        button.setBackground(background);

        button.setTextColor(
                selected ? Color.WHITE : NAVY
        );
    }

    // =====================================
    // SEARCH AND DISPLAY
    // =====================================

    private void displayProgrammes() {

        programmeContainer.removeAllViews();

        String query = searchInput.getText()
                .toString()
                .toLowerCase(Locale.ROOT)
                .trim();

        int count = 0;

        for (Programme p : programmes) {

            boolean categoryMatch =
                    selectedCategory.equals("All")
                            || p.category.equals(selectedCategory);

            String searchable =
                    (p.title + " "
                            + p.department + " "
                            + p.description)
                            .toLowerCase(Locale.ROOT);

            boolean searchMatch =
                    searchable.contains(query);

            if (categoryMatch && searchMatch) {

                programmeContainer.addView(
                        createCard(p)
                );

                count++;
            }
        }

        resultCount.setText(
                count + " programme(s) available"
        );

        if (count == 0) {

            TextView empty = createText(
                    "No programmes found. Try another search.",
                    14,
                    GREY,
                    false
            );

            empty.setPadding(
                    dp(10),
                    dp(25),
                    dp(10),
                    dp(25)
            );

            programmeContainer.addView(empty);
        }
    }

    // =====================================
    // PROGRAMME CARDS
    // =====================================

    private View createCard(Programme p) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);

        card.setPadding(
                dp(17),
                dp(16),
                dp(17),
                dp(16)
        );

        card.setBackgroundResource(R.drawable.bg_card);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        cardParams.bottomMargin = dp(13);
        card.setLayoutParams(cardParams);

        // DEPARTMENT
        TextView department = createText(
                p.department.toUpperCase(Locale.ROOT),
                10,
                RED,
                true
        );

        // PROGRAMME TITLE
        TextView title = createText(
                p.title,
                16,
                NAVY,
                true
        );
        title.setPadding(0, dp(7), 0, dp(7));

        // DESCRIPTION
        TextView description = createText(
                p.description,
                12,
                Color.DKGRAY,
                false
        );

        // DURATION
        TextView duration = createText(
                "Duration: " + p.duration,
                11,
                GREY,
                false
        );
        duration.setPadding(0, dp(9), 0, 0);

        // VIEW DETAILS
        TextView details = createText(
                "VIEW DETAILS  →",
                12,
                RED,
                true
        );
        details.setPadding(0, dp(12), 0, 0);

        card.addView(department);
        card.addView(title);
        card.addView(description);
        card.addView(duration);
        card.addView(details);

        card.setClickable(true);
        card.setFocusable(true);
        card.setOnClickListener(v -> openProgrammeDetails(p));

        return card;
    }

    // OPEN INDIVIDUAL PROGRAMME DETAILS
    private void openProgrammeDetails(Programme p) {

        Intent intent = new Intent(
                ProgrammesActivity.this,
                ProgrammeDetailsActivity.class
        );

        intent.putExtra("title", p.title);
        intent.putExtra("category", p.category);
        intent.putExtra("department", p.department);
        intent.putExtra("description", p.description);
        intent.putExtra("duration", p.duration);
        intent.putExtra("fee", p.fee);
        intent.putExtra("url", p.url);

        intent.putExtra(
                "requirements",
                "Please refer to GMI's official admission requirements for the latest entry criteria."
        );

        intent.putExtra(
                "careers",
                "Career opportunities depend on the programme specialisation. Refer to GMI's official programme information."
        );

        startActivity(intent);
    }

    private TextView createText(
            String text,
            int size,
            int color,
            boolean bold) {

        TextView view = new TextView(this);

        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(color);

        if (bold) {
            view.setTypeface(
                    null,
                    Typeface.BOLD
            );
        }

        return view;
    }

    // =====================================
    // COLOURFUL PROGRAMME DETAILS POPUP
    // =====================================

    private void showDetails(Programme p) {

        Dialog dialog = new Dialog(this);

        dialog.setContentView(
                R.layout.dialog_programme_details
        );

        dialog.setCancelable(true);

        // CONNECT XML COMPONENTS

        TextView category =
                dialog.findViewById(
                        R.id.tvDialogCategory
                );

        TextView title =
                dialog.findViewById(
                        R.id.tvDialogTitle
                );

        TextView department =
                dialog.findViewById(
                        R.id.tvDialogDepartment
                );

        TextView description =
                dialog.findViewById(
                        R.id.tvDialogDescription
                );

        TextView duration =
                dialog.findViewById(
                        R.id.tvDialogDuration
                );

        TextView fee =
                dialog.findViewById(
                        R.id.tvDialogFee
                );

        // DISPLAY PROGRAMME INFORMATION

        category.setText(
                p.category.toUpperCase(Locale.ROOT)
                        + " PROGRAMME"
        );

        title.setText(p.title);

        department.setText(p.department);

        description.setText(p.description);

        duration.setText(p.duration);

        fee.setText(p.fee);

        // CLOSE BUTTON

        dialog.findViewById(
                R.id.btnDialogClose
        ).setOnClickListener(v -> dialog.dismiss());

        // OFFICIAL WEBSITE BUTTON

        dialog.findViewById(
                R.id.btnDialogWebsite
        ).setOnClickListener(v -> {

            try {

                Intent browser = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(p.url)
                );

                startActivity(browser);

                dialog.dismiss();

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "Unable to open website.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // DISPLAY CUSTOM DIALOG

        dialog.show();

        Window window = dialog.getWindow();

        if (window != null) {

            window.setBackgroundDrawableResource(
                    android.R.color.transparent
            );

            int width = (int) (
                    getResources()
                            .getDisplayMetrics()
                            .widthPixels * 0.90
            );

            window.setLayout(
                    width,
                    WindowManager.LayoutParams.WRAP_CONTENT
            );

            window.setGravity(Gravity.CENTER);
        }
    }

    // =====================================
    // DP CONVERSION
    // =====================================

    private int dp(int value) {

        return (int) (
                value * getResources()
                        .getDisplayMetrics()
                        .density
        );
    }

}

