package com.example.concertticketapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.ConcertViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Concert concert);
    }

    private List<Concert> concerts;
    private Context context;
    private OnItemClickListener listener;

    public ConcertAdapter(Context context, List<Concert> concerts, OnItemClickListener listener) {
        this.context = context;
        this.concerts = concerts;
        this.listener = listener;
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
        holder.title.setText(concert.getName());
        holder.location.setText(concert.getLocation());
        holder.date.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(concert.getDate().toDate()));
        int imageResId = context.getResources().getIdentifier(
                concert.getImage(), "drawable", context.getPackageName()
        );
        holder.image.setImageResource(imageResId);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(concert);
            } else {
                Intent intent = new Intent(context, ConcertDetailActivity.class);
                intent.putExtra("concertId", concert.getId());
                context.startActivity(intent);
            }
        });
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