package com.example.concertticketapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ConcertDetailActivity extends AppCompatActivity {
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
                            Concert concert = document.toObject(Concert.class);

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
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}