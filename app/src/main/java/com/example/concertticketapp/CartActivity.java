package com.example.concertticketapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Button buttonContinue = findViewById(R.id.buttonContinueShopping);
        buttonContinue.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
