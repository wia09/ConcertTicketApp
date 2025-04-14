package com.example.concertticketapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        Button addConcertButton = findViewById(R.id.button_add_concert);
        addConcertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Concert concert = new Concert("Imagine Dragons", "Budapest Aréna", "2025-06-15");

                db.collection("concerts")
                        .add(concert)
                        .addOnSuccessListener(documentReference -> {
                            System.out.println("Sikeres mentés, ID: " + documentReference.getId());
                        })
                        .addOnFailureListener(e -> {
                            System.err.println("Hiba történt: " + e.getMessage());
                        });
            }
        });
    }
}