package com.example.concertticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConcertAdapter adapter;
    private List<Concert> concertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        concertList = new ArrayList<>();
        concertList.add(new Concert("Imagine Dragons", "Papp László Sportaréna", "2025-05-10", R.drawable.imagine_dragons));
        concertList.add(new Concert("Ed Sheeran", "Puskás Aréna", "2025-06-05", R.drawable.ed_sheeran));
        concertList.add(new Concert("Billie Eilish", "Budapest Park", "2025-07-20", R.drawable.billie_eilish));
        concertList.add(new Concert("Arctic Monkeys", "VOLT Fesztivál", "2025-08-15", R.drawable.arctic_monkeys));

        adapter = new ConcertAdapter(this, concertList);
        recyclerView.setAdapter(adapter);
    }
}
