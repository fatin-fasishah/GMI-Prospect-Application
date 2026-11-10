package com.week17.gmiprospect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnquiryActivity extends AppCompatActivity {

    private static final String GMI_EMAIL = "marketing@gmi.edu.my";
    private static final String GMI_PHONE = "+60389219000";
    private static final String GMI_ADDRESS =
            "German-Malaysian Institute, Jalan Ilmiah, Taman Universiti, 43000 Kajang, Selangor, Malaysia";

    private static final String CONTACT_URL =
            "https://www.gmi.edu.my/contact-us/";

    private EditText etName;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etSubject;
    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enquiry);

        View root = findViewById(R.id.enquiryRoot);

        ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            Insets systemBars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
            );

            view.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });



        TextView backButton = findViewById(R.id.btnEnquiryBack);
        Button callButton = findViewById(R.id.btnCallGmi);
        Button emailButton = findViewById(R.id.btnEmailGmi);
        Button mapButton = findViewById(R.id.btnOpenGmiMap);

        backButton.setOnClickListener(v -> finish());
        callButton.setOnClickListener(v -> callGmi());
        emailButton.setOnClickListener(v -> emailGmi());
        mapButton.setOnClickListener(v -> openGmiMap());
    }

    private void callGmi() {
        Intent intent = new Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:" + GMI_PHONE)
        );
        startActivity(intent);
    }

    private void emailGmi() {
        Intent intent = new Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:" + GMI_EMAIL)
        );
        intent.putExtra(
                Intent.EXTRA_SUBJECT,
                getString(R.string.enquiry_email_subject)
        );

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(
                    this,
                    R.string.enquiry_no_email_app,
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void openGmiMap() {
        Uri mapUri = Uri.parse(
                "geo:0,0?q=" + Uri.encode(GMI_ADDRESS)
        );

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);

        try {
            startActivity(mapIntent);
        } catch (Exception e) {
            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://www.google.com/maps/search/?api=1&query="
                                    + Uri.encode(GMI_ADDRESS)
                    )
            );
            startActivity(browserIntent);
        }
    }

    private void openOfficialEnquiryForm() {
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(CONTACT_URL)
        );
        startActivity(intent);
    }

    private void sendEnquiryEmail() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String subject = etSubject.getText().toString().trim();
        String message = etMessage.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            etName.setError(getString(R.string.enquiry_required));
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)
                || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.enquiry_valid_email));
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(subject)) {
            etSubject.setError(getString(R.string.enquiry_required));
            etSubject.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(message)) {
            etMessage.setError(getString(R.string.enquiry_required));
            etMessage.requestFocus();
            return;
        }

        String emailBody =
                "Name: " + name + "\n"
                        + "Email: " + email + "\n"
                        + "Phone: " + phone + "\n\n"
                        + "Enquiry:\n" + message;

        Intent intent = new Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:" + GMI_EMAIL)
        );

        intent.putExtra(
                Intent.EXTRA_SUBJECT,
                "GMI Prospect Enquiry: " + subject
        );
        intent.putExtra(Intent.EXTRA_TEXT, emailBody);

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(
                    this,
                    R.string.enquiry_no_email_app,
                    Toast.LENGTH_LONG
            ).show();

            openOfficialEnquiryForm();
        }
    }
}
