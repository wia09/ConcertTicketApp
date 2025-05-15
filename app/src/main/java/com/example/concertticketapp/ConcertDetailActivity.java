package com.example.concertticketapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.widget.Button;
import android.widget.Toast;

public class ConcertDetailActivity extends AppCompatActivity {
    private Concert concert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_detail);

        Toolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        String concertId = getIntent().getStringExtra("concertId");
        if (concertId != null) {
            FirebaseFirestore.getInstance().collection("concerts").document(concertId)
                    .get()
                    .addOnSuccessListener(document -> {
                        if (document.exists()) {
                            concert = document.toObject(Concert.class);

                            TextView title = findViewById(R.id.detailTitle);
                            TextView location = findViewById(R.id.detailLocation);
                            TextView date = findViewById(R.id.detailDate);
                            TextView price = findViewById(R.id.detailPrice);
                            TextView capacity = findViewById(R.id.detailCapacity);
                            TextView description = findViewById(R.id.detailDescription);
                            ImageView image = findViewById(R.id.detailImage);

                            title.setText(concert.getName());
                            location.setText(concert.getLocation());
                            date.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(concert.getDate().toDate()));
                            price.setText(concert.getPrice() + " Ft");
                            capacity.setText("Férőhely: " + concert.getCapacity());
                            description.setText(concert.getDescription());

                            int imageResId = getResources().getIdentifier(concert.getImage(), "drawable", getPackageName());
                            image.setImageResource(imageResId);
                        }
                    });
        }

        Button buttonAddToCart = findViewById(R.id.buttonAddToCart);

        buttonAddToCart.setOnClickListener(v -> {
            if (concert != null) {
                CartManager cartManager = new CartManager(ConcertDetailActivity.this);
                cartManager.addToCart(concert);
                Toast.makeText(this, "Hozzáadva a kosárhoz", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Hiba: koncert adat nem elérhető", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}