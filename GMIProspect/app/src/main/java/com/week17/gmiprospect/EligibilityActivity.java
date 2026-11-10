package com.week17.gmiprospect;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EligibilityActivity extends AppCompatActivity {

    private static final String APPLICATION_URL =
            "https://gmi.vialing.com/oa/login";

    private static final int RED = Color.rgb(200, 16, 46);
    private static final int NAVY = Color.rgb(20, 43, 69);
    private static final int GREY = Color.rgb(100, 100, 100);
    private static final int LIGHT_BORDER = Color.rgb(222, 228, 236);

    private Spinner spQualification;
    private Spinner spGappCandidate;

    private Spinner spBM;
    private Spinner spEnglish;
    private Spinner spMath;
    private Spinner spAddMath;
    private Spinner spPhysics;
    private Spinner spChemistry;
    private Spinner spScienceTech;
    private Spinner spOther1;
    private Spinner spOther2;
    private Spinner spOther3;
    private Spinner spOther4;

    private TextView tvLabelBM;
    private TextView tvLabelGapp;
    private TextView tvGradeHint;

    private TextView tvEligibilitySummary;
    private LinearLayout resultContainer;

    private Button btnCheckEligibility;
    private Button btnResetEligibility;
    private Button btnApplyOnline;

    private final List<Spinner> gradeSpinners = new ArrayList<>();

    private String currentQualification = "SPM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eligibility);

        View root = findViewById(R.id.eligibilityRoot);

        if (root != null) {
            ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
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
        }

        bindViews();
        setupGradeSpinnerList();

        setSpinnerItems(
                spQualification,
                new String[]{"SPM", "O-Level", "IGCSE"}
        );

        updateGradeOptions("SPM");

        spQualification.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            View view,
                            int position,
                            long id
                    ) {
                        String selected = parent.getItemAtPosition(position)
                                .toString();

                        if (!selected.equals(currentQualification)) {
                            currentQualification = selected;
                            updateGradeOptions(selected);
                            clearResults();
                        }
                    }

                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent
                    ) {
                    }
                }
        );

        btnCheckEligibility.setOnClickListener(v -> checkEligibility());
        btnResetEligibility.setOnClickListener(v -> resetForm());

        btnApplyOnline.setOnClickListener(v -> openApplicationPortal());

        findViewById(R.id.btnEligibilityBack)
                .setOnClickListener(v -> finish());
    }

    private void bindViews() {
        spQualification = findViewById(R.id.spQualification);
        spGappCandidate = findViewById(R.id.spGappCandidate);

        spBM = findViewById(R.id.spBM);
        spEnglish = findViewById(R.id.spEnglish);
        spMath = findViewById(R.id.spMath);
        spAddMath = findViewById(R.id.spAddMath);
        spPhysics = findViewById(R.id.spPhysics);
        spChemistry = findViewById(R.id.spChemistry);
        spScienceTech = findViewById(R.id.spScienceTech);
        spOther1 = findViewById(R.id.spOther1);
        spOther2 = findViewById(R.id.spOther2);
        spOther3 = findViewById(R.id.spOther3);
        spOther4 = findViewById(R.id.spOther4);

        tvLabelBM = findViewById(R.id.tvLabelBM);
        tvLabelGapp = findViewById(R.id.tvLabelGapp);
        tvGradeHint = findViewById(R.id.tvGradeHint);

        tvEligibilitySummary = findViewById(R.id.tvEligibilitySummary);
        resultContainer = findViewById(R.id.resultContainer);

        btnCheckEligibility = findViewById(R.id.btnCheckEligibility);
        btnResetEligibility = findViewById(R.id.btnResetEligibility);
        btnApplyOnline = findViewById(R.id.btnApplyOnline);
    }

    private void setupGradeSpinnerList() {
        gradeSpinners.clear();

        gradeSpinners.add(spBM);
        gradeSpinners.add(spEnglish);
        gradeSpinners.add(spMath);
        gradeSpinners.add(spAddMath);
        gradeSpinners.add(spPhysics);
        gradeSpinners.add(spChemistry);
        gradeSpinners.add(spScienceTech);
        gradeSpinners.add(spOther1);
        gradeSpinners.add(spOther2);
        gradeSpinners.add(spOther3);
        gradeSpinners.add(spOther4);
    }

    private void updateGradeOptions(String qualification) {
        String[] grades;

        if ("SPM".equals(qualification)) {
            grades = new String[]{
                    "Not taken", "A+", "A", "A-", "B+", "B",
                    "C+", "C", "D", "E", "G"
            };

            setSpinnerItems(
                    spGappCandidate,
                    new String[]{
                            "Private Candidate",
                            "Sponsored Candidate (YTP MARA)"
                    }
            );

        } else if ("O-Level".equals(qualification)) {
            grades = new String[]{
                    "Not taken", "A*", "A", "B", "C",
                    "D", "E", "F", "U"
            };

            setSpinnerItems(
                    spGappCandidate,
                    new String[]{"Private Candidate"}
            );

        } else {
            grades = new String[]{
                    "Not taken", "A*", "A", "B", "C",
                    "D", "E", "F", "G", "U"
            };

            setSpinnerItems(
                    spGappCandidate,
                    new String[]{"Private Candidate"}
            );
        }

        for (Spinner spinner : gradeSpinners) {
            setSpinnerItems(spinner, grades);
        }

        applyQualificationUi(qualification);
    }

    // Shows only what is relevant to the selected qualification.
    private void applyQualificationUi(String qualification) {
        boolean isSpm = "SPM".equals(qualification);

        // Sponsored (YTP MARA) route is SPM-only, so O-Level and IGCSE
        // applicants are always treated as Private Candidates.
        int visibility = isSpm ? View.VISIBLE : View.GONE;
        tvLabelGapp.setVisibility(visibility);
        spGappCandidate.setVisibility(visibility);

        tvLabelBM.setText(
                isSpm ? "Bahasa Melayu" : "Bahasa Melayu (if taken)"
        );

        if ("O-Level".equals(qualification)) {
            tvGradeHint.setText(
                    "O-Level: a credit is Grade C or above. English needs at least a pass (Grade E)."
            );
        } else if ("IGCSE".equals(qualification)) {
            tvGradeHint.setText(
                    "IGCSE: a credit is Grade C or above. English needs at least a pass (Grade G)."
            );
        } else {
            tvGradeHint.setText(
                    "SPM: a credit is Grade C or above. English needs at least a pass (Grade E)."
            );
        }
    }

    private void setSpinnerItems(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                items
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(adapter);
    }

    private String grade(Spinner spinner) {
        Object selected = spinner.getSelectedItem();

        if (selected == null) {
            return "Not taken";
        }

        return selected.toString();
    }

    private boolean isCredit(Spinner spinner) {
        String value = grade(spinner);

        if ("SPM".equals(currentQualification)) {
            return Arrays.asList(
                    "A+", "A", "A-", "B+", "B", "C+", "C"
            ).contains(value);
        }

        return Arrays.asList("A*", "A", "B", "C").contains(value);
    }

    private boolean isAorBetter(Spinner spinner) {
        if (!"SPM".equals(currentQualification)) {
            return false;
        }

        return Arrays.asList("A+", "A").contains(grade(spinner));
    }

    private boolean isEnglishPass() {
        String value = grade(spEnglish);

        if ("SPM".equals(currentQualification)) {
            return Arrays.asList(
                    "A+", "A", "A-", "B+", "B",
                    "C+", "C", "D", "E"
            ).contains(value);
        }

        if ("O-Level".equals(currentQualification)) {
            return Arrays.asList(
                    "A*", "A", "B", "C", "D", "E"
            ).contains(value);
        }

        return Arrays.asList(
                "A*", "A", "B", "C", "D", "E", "F", "G"
        ).contains(value);
    }

    private int countCredits() {
        int total = 0;

        for (Spinner spinner : gradeSpinners) {
            if (isCredit(spinner)) {
                total++;
            }
        }

        return total;
    }

    private boolean hasAnyGrade() {
        for (Spinner spinner : gradeSpinners) {
            if (!"Not taken".equals(grade(spinner))) {
                return true;
            }
        }

        return false;
    }

    private boolean hasScienceTechnicalCredit() {
        return isCredit(spPhysics)
                || isCredit(spChemistry)
                || isCredit(spScienceTech);
    }

    // GMI engineering diploma rule (SPM, O-Level and IGCSE):
    // at least 3 credits (Grade C or above), including Mathematics and
    // 1 Science/Technical/Vocational subject, plus a pass in English.
    private boolean meetsEngineeringDiplomaRequirement() {
        return countCredits() >= 3
                && isCredit(spMath)
                && hasScienceTechnicalCredit()
                && isEnglishPass();
    }

    private boolean meetsSoftwareEngineeringRequirement() {
        return countCredits() >= 3 && isCredit(spMath);
    }

    private boolean meetsCreativeMultimediaRequirement() {
        if ("SPM".equals(currentQualification)) {
            return countCredits() >= 3;
        }

        // O-Level / IGCSE: 3 credits incl. Mathematics + 1 Science/Technical
        // subject, and a pass in English.
        return meetsEngineeringDiplomaRequirement();
    }

    private boolean meetsCyberSecurityRequirement() {
        return countCredits() >= 3;
    }

    private boolean meetsGappPrivateRequirement() {
        return isCredit(spEnglish)
                && isCredit(spMath)
                && isCredit(spAddMath)
                && isCredit(spPhysics)
                && isCredit(spChemistry);
    }

    private boolean meetsGappSponsoredRequirement() {
        return "SPM".equals(currentQualification)
                && isAorBetter(spEnglish)
                && isAorBetter(spAddMath)
                && isAorBetter(spPhysics)
                && isAorBetter(spChemistry)
                // The four additional A/A+ subjects include Bahasa Melayu
                // and three other subjects.
                && isAorBetter(spBM)
                && isAorBetter(spOther1)
                && isAorBetter(spOther2)
                && isAorBetter(spOther3);
    }

    private boolean meetsGufpSpmRequirement() {
        return isCredit(spBM)
                && isCredit(spMath)
                && isCredit(spEnglish)
                && isCredit(spAddMath)
                && isCredit(spPhysics)
                && isCredit(spChemistry);
    }

    private boolean meetsGufpOLevelOrIgcseRequirement() {
        boolean fourRequiredCredits =
                isCredit(spMath)
                        && isCredit(spPhysics)
                        && isCredit(spChemistry)
                        && isCredit(spAddMath);

        boolean oneAdditionalCredit =
                isCredit(spBM)
                        || isCredit(spEnglish)
                        || isCredit(spScienceTech)
                        || isCredit(spOther1)
                        || isCredit(spOther2)
                        || isCredit(spOther3)
                        || isCredit(spOther4);

        return fourRequiredCredits && oneAdditionalCredit;
    }

    private void checkEligibility() {
        clearResults();

        if (!hasAnyGrade()) {
            tvEligibilitySummary.setText(
                    "Please enter your subject grades before checking eligibility."
            );
            setSummaryStyle(false);

            Toast.makeText(
                    this,
                    "Please enter at least one subject grade.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        // Calculate each programme group's result once.
        boolean engineeringMatch = meetsEngineeringDiplomaRequirement();
        boolean creativeMatch = meetsCreativeMultimediaRequirement();
        boolean softwareMatch = meetsSoftwareEngineeringRequirement();
        boolean cyberMatch = meetsCyberSecurityRequirement();

        String candidateType = grade(spGappCandidate);
        boolean sponsoredGapp =
                "Sponsored Candidate (YTP MARA)".equals(candidateType)
                        && "SPM".equals(currentQualification);

        boolean gappMatch = sponsoredGapp
                ? meetsGappSponsoredRequirement()
                : meetsGappPrivateRequirement();

        boolean gufpMatch = "SPM".equals(currentQualification)
                ? meetsGufpSpmRequirement()
                : meetsGufpOLevelOrIgcseRequirement();

        int matchedCount = 0;
        if (engineeringMatch) matchedCount++;
        if (creativeMatch) matchedCount++;
        if (softwareMatch) matchedCount++;
        if (cyberMatch) matchedCount++;
        if (gappMatch) matchedCount++;
        if (gufpMatch) matchedCount++;

        // ELECTRICAL ENGINEERING
        addResultCard(
                "Electrical Engineering Diploma Programmes",
                engineeringMatch
                        ? "Your entered grades may meet the general academic criteria. Relevant programmes include Mechatronics, Electronics, Instrumentation and Control, Autotronics, and Sustainable Energy and Power Distribution."
                        : "Not matched yet. You need at least 3 credits (Grade C or above) including Mathematics and one Science/Technical/Vocational subject, plus a pass in English."
                ,
                engineeringMatch
        );

        // MECHANICAL ENGINEERING
        addResultCard(
                "Mechanical Engineering Diploma Programmes",
                engineeringMatch
                        ? "Your entered grades may meet the general academic criteria. Relevant programmes include Precision Tooling, Industrial Design, Industrial Quality, Product Design, CNC Precision, Machine Tools Maintenance, and Manufacturing."
                        : "Not matched yet. You need at least 3 credits (Grade C or above) including Mathematics and one Science/Technical/Vocational subject, plus a pass in English."
                ,
                engineeringMatch
        );

        // CREATIVE MULTIMEDIA
        addResultCard(
                "Diploma in Creative Multimedia",
                creativeMatch
                        ? "Potential match based on the minimum credit rule currently configured in this checker. Confirm any additional course-specific conditions with GMI."
                        : "Not matched yet. The grades entered do not currently show the minimum credit requirement configured for this qualification."
                ,
                creativeMatch
        );

        // SOFTWARE ENGINEERING
        addResultCard(
                "Diploma in Software Engineering",
                softwareMatch
                        ? "Potential match: your entries show at least three credits, including Mathematics."
                        : "Not matched yet. The current checker requires at least three credits, including Mathematics."
                ,
                softwareMatch
        );

        // CYBER SECURITY
        addResultCard(
                "Diploma in Cyber Security Technology",
                cyberMatch
                        ? "Potential match based on at least three credits entered in this checker. Confirm any additional course-specific conditions with GMI."
                        : "Not matched yet. The current checker requires at least three credits."
                ,
                cyberMatch
        );

        // GAPP
        String gappDescription;

        if (sponsoredGapp) {
            gappDescription = gappMatch
                    ? "Potential match for the sponsored route: the entered SPM grades meet the A/A+ requirement for English, Additional Mathematics, Physics, Chemistry, Bahasa Melayu and three other subjects."
                    : "Not matched yet for the sponsored route. All eight required subjects must have grade A or A+: English, Additional Mathematics, Physics, Chemistry, Bahasa Melayu and three other subjects.";
        } else {
            gappDescription = gappMatch
                    ? "Potential match: the entered grades show C or above in English, Mathematics, Additional Mathematics, Physics and Chemistry."
                    : "Not matched yet. Check that English, Mathematics, Additional Mathematics, Physics and Chemistry each have a credit.";
        }

        addResultCard(
                sponsoredGapp
                        ? "GAPP – Sponsored Candidate (YTP MARA)"
                        : "GAPP – Private Candidate",
                gappDescription,
                gappMatch
        );

        // GUFP
        String gufpDescription;

        if ("SPM".equals(currentQualification)) {
            gufpDescription = gufpMatch
                    ? "Potential match: the entered SPM grades show C or above in Bahasa Melayu, Mathematics, English, Additional Mathematics, Physics and Chemistry."
                    : "Not matched yet. Check the six required SPM subjects: Bahasa Melayu, Mathematics, English, Additional Mathematics, Physics and Chemistry.";
        } else {
            gufpDescription = gufpMatch
                    ? "Potential match based on the O-Level/IGCSE subject-credit rule configured in this checker. Confirm equivalency with GMI."
                    : "Not matched yet. Check the required Mathematics, Physics, Chemistry, Additional Mathematics and one additional subject credits.";
        }

        addResultCard(
                "GUFP – GMI-UTP Foundation Programme",
                gufpDescription,
                gufpMatch
        );

        if (matchedCount > 0) {
            tvEligibilitySummary.setText(
                    "POTENTIAL MATCH: "
                            + matchedCount
                            + " of 6 programme categories. Green cards show possible matches; red cards show requirements not yet met based on the grades entered. This is a preliminary guide, not an admission decision."
            );

            btnApplyOnline.setVisibility(View.VISIBLE);
            btnApplyOnline.setText("VISIT GMI APPLICATION PORTAL");

            setSummaryStyle(true);

        } else {
            tvEligibilitySummary.setText(
                    "NO POTENTIAL MATCH IDENTIFIED YET. The grades entered do not currently meet the rules configured in this checker. Review the red cards or contact GMI to discuss your qualifications. This is not an official admission decision."
            );

            btnApplyOnline.setVisibility(View.GONE);
            setSummaryStyle(false);
        }
    }

    private void addResultCard(
            String title,
            String description,
            boolean matched
    ) {
        int accent = matched
                ? Color.rgb(35, 135, 75)
                : Color.rgb(190, 55, 65);

        int cardColor = matched
                ? Color.rgb(239, 249, 242)
                : Color.rgb(255, 244, 244);

        int borderColor = matched
                ? Color.rgb(190, 225, 200)
                : Color.rgb(240, 200, 205);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(14), dp(13), dp(14), dp(13));

        GradientDrawable background = new GradientDrawable();
        background.setColor(cardColor);
        background.setCornerRadius(dp(14));
        background.setStroke(dp(1), borderColor);
        card.setBackground(background);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        cardParams.topMargin = dp(10);
        card.setLayoutParams(cardParams);

        TextView statusView = new TextView(this);
        statusView.setText(
                matched ? "✓  POTENTIAL MATCH" : "!  NOT MATCHED YET"
        );
        statusView.setTextColor(accent);
        statusView.setTextSize(11);
        statusView.setTypeface(null, android.graphics.Typeface.BOLD);
        statusView.setPadding(0, 0, 0, dp(7));

        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTextColor(NAVY);
        titleView.setTextSize(15);
        titleView.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView descriptionView = new TextView(this);
        descriptionView.setText(description);
        descriptionView.setTextColor(GREY);
        descriptionView.setTextSize(13);
        descriptionView.setLineSpacing(dp(3), 1f);
        descriptionView.setPadding(0, dp(8), 0, 0);

        card.addView(statusView);
        card.addView(titleView);
        card.addView(descriptionView);

        resultContainer.addView(card);
    }

    private void setSummaryStyle(boolean hasMatch) {
        int backgroundColor = hasMatch
                ? Color.rgb(232, 246, 236)
                : Color.rgb(255, 239, 240);

        int borderColor = hasMatch
                ? Color.rgb(180, 220, 190)
                : Color.rgb(235, 190, 195);

        GradientDrawable summaryBackground = new GradientDrawable();
        summaryBackground.setColor(backgroundColor);
        summaryBackground.setCornerRadius(dp(12));
        summaryBackground.setStroke(dp(1), borderColor);

        tvEligibilitySummary.setBackground(summaryBackground);
        tvEligibilitySummary.setTextColor(NAVY);
        tvEligibilitySummary.setPadding(
                dp(14), dp(14), dp(14), dp(14)
        );
    }

    private void clearResults() {
        resultContainer.removeAllViews();

        tvEligibilitySummary.setText(
                "Your matching programmes will appear here after checking."
        );

        setSummaryStyle(false);
        btnApplyOnline.setVisibility(View.GONE);
    }

    private void resetForm() {
        spQualification.setSelection(0);
        currentQualification = "SPM";
        updateGradeOptions("SPM");

        for (Spinner spinner : gradeSpinners) {
            spinner.setSelection(0);
        }

        spGappCandidate.setSelection(0);
        clearResults();

        Toast.makeText(
                this,
                "Form has been reset.",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void openApplicationPortal() {
        try {
            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(APPLICATION_URL)
            );

            startActivity(browserIntent);

        } catch (Exception e) {
            Toast.makeText(
                    this,
                    "Unable to open GMI application portal.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private int dp(int value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }
}