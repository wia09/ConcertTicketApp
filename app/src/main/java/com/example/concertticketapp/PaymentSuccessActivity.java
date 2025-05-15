package com.example.concertticketapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PaymentSuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        Toolbar toolbar = findViewById(R.id.successToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Sikeres fizetés");
        }

        TextView text = findViewById(R.id.textSuccessMessage);
        text.setText("Sikeres fizetés! Jó szórakozást! :)");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
