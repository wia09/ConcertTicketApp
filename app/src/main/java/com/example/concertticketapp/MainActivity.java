package com.example.concertticketapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConcertAdapter adapter;
    private List<Concert> concertList;
    private FirebaseFirestore db;
    private Button buttonUpcoming, buttonBudapest, buttonSortDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Koncertjegy App");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        concertList = new ArrayList<>();
        adapter = new ConcertAdapter(this, concertList, concert -> {
            Intent intent = new Intent(MainActivity.this, ConcertDetailActivity.class);
            intent.putExtra("concertId", concert.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadConcertsFromFirestore();

        ImageButton buttonCart = findViewById(R.id.buttonCart);
        buttonCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

        buttonUpcoming = findViewById(R.id.buttonUpcoming);
        buttonBudapest = findViewById(R.id.buttonBudapest);
        buttonSortDate = findViewById(R.id.buttonSortDate);

        buttonUpcoming.setOnClickListener(v -> {
            db.collection("concerts")
                    .orderBy("date")
                    .limit(3)
                    .get()
                    .addOnSuccessListener(this::updateConcertList)
                    .addOnFailureListener(e -> showError());
        });

        buttonBudapest.setOnClickListener(v -> {
            db.collection("concerts")
                    .whereEqualTo("location", "Budapest Park")
                    .get()
                    .addOnSuccessListener(this::updateConcertList)
                    .addOnFailureListener(e -> showError());
        });

        buttonSortDate.setOnClickListener(v -> {
            db.collection("concerts")
                    .orderBy("date")
                    .get()
                    .addOnSuccessListener(this::updateConcertList)
                    .addOnFailureListener(e -> showError());
        });
    }

    private void loadConcertsFromFirestore() {
        db.collection("concerts")
                .get()
                .addOnSuccessListener(this::updateConcertList)
                .addOnFailureListener(e -> showError());
    }

    private void updateConcertList(QuerySnapshot queryDocumentSnapshots) {
        concertList.clear();
        for (DocumentSnapshot doc : queryDocumentSnapshots) {
            Concert concert = doc.toObject(Concert.class);
            concert.setId(doc.getId());
            concertList.add(concert);
        }
        adapter.notifyDataSetChanged();
    }

    private void showError() {
        Toast.makeText(this, "Hiba történt a lekérdezés során.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}