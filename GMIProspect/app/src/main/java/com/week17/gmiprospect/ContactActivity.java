package com.week17.gmiprospect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactActivity extends AppCompatActivity {
    private static final String GMI_EMAIL = "marketing@gmi.edu.my";
    private static final String GMI_PHONE = "+60389219000";
    private static final String GMI_ADDRESS =
            "German-Malaysian Institute, Jalan Ilmiah, Taman Universiti, 43000 Kajang, Selangor, Malaysia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contactRoot), (view, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        TextView back = findViewById(R.id.btnContactBack);
        Button call = findViewById(R.id.btnCallGmi);
        Button email = findViewById(R.id.btnEmailGmi);
        Button map = findViewById(R.id.btnOpenGmiMap);

        back.setOnClickListener(v -> finish());
        call.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + GMI_PHONE));
            startActivity(intent);
        });
        email.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + GMI_EMAIL));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.enquiry_email_subject));
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, R.string.enquiry_no_email_app, Toast.LENGTH_LONG).show();
            }
        });
        map.setOnClickListener(v -> openMap());
    }

    private void openMap() {
        Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(GMI_ADDRESS));
        Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Intent browser = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(GMI_ADDRESS)));
            startActivity(browser);
        }
    }
}
