package com.example.concertticketapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static final String PREF_NAME = "cart_pref";
    private static final String KEY_CART = "cart_items";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public CartManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addToCart(Concert concert) {
        List<Concert> cartItems = getCartItems();
        cartItems.add(concert);
        saveCart(cartItems);
    }

    public List<Concert> getCartItems() {
        String json = sharedPreferences.getString(KEY_CART, "");
        Type type = new TypeToken<List<Concert>>() {}.getType();
        return json.isEmpty() ? new ArrayList<>() : gson.fromJson(json, type);
    }

    public void clearCart() {
        sharedPreferences.edit().remove(KEY_CART).apply();
    }

    private void saveCart(List<Concert> items) {
        String json = gson.toJson(items);
        sharedPreferences.edit().putString(KEY_CART, json).apply();
    }
}
