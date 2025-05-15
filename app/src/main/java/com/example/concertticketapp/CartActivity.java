package com.example.concertticketapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textEmptyCart;
    private Button buttonContinueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = findViewById(R.id.cartToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Kosár");
        }

        recyclerView = findViewById(R.id.recyclerViewCart);
        textEmptyCart = findViewById(R.id.textEmptyCart);
        buttonContinueShopping = findViewById(R.id.buttonContinueShopping);

        CartManager cartManager = new CartManager(this);
        List<Concert> cartItems = cartManager.getCartItems();

        if (cartItems.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            textEmptyCart.setVisibility(View.VISIBLE);
            buttonContinueShopping.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            textEmptyCart.setVisibility(View.GONE);
            buttonContinueShopping.setVisibility(View.GONE);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ConcertAdapter adapter = new ConcertAdapter(this, cartItems, new ConcertAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Concert concert) {

                }
            });

            recyclerView.setAdapter(adapter);
        }

        buttonContinueShopping.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}