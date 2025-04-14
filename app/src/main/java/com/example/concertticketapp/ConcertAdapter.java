package com.example.concertticketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.ConcertViewHolder> {
    private List<Concert> concerts;
    private Context context;

    public ConcertAdapter(Context context, List<Concert> concerts) {
        this.context = context;
        this.concerts = concerts;
    }

    @NonNull
    @Override
    public ConcertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.concert_item, parent, false);
        return new ConcertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertViewHolder holder, int position) {
        Concert concert = concerts.get(position);
        holder.title.setText(concert.getTitle());
        holder.location.setText(concert.getLocation());
        holder.date.setText(concert.getDate());
        holder.image.setImageResource(concert.getImageResId());
    }

    @Override
    public int getItemCount() {
        return concerts.size();
    }

    public static class ConcertViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, date;
        ImageView image;

        public ConcertViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            location = itemView.findViewById(R.id.textViewLocation);
            date = itemView.findViewById(R.id.textViewDate);
            image = itemView.findViewById(R.id.imageViewConcert);
        }
    }
}