package com.example.concertticketapp;

import android.content.Context;
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
        holder.name.setText(concert.getName());
        holder.location.setText(concert.getLocation());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(concert.getDate().toDate());
        holder.date.setText(formattedDate);

        int imageResId = context.getResources().getIdentifier(
                concert.getImage(), "drawable", context.getPackageName()
        );
        holder.image.setImageResource(imageResId);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return concerts.size();
    }

    public static class ConcertViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, date;
        ImageView image;

        public ConcertViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewTitle);     // maradhat így, ha az ID még "textViewTitle"
            location = itemView.findViewById(R.id.textViewLocation);
            date = itemView.findViewById(R.id.textViewDate);
            image = itemView.findViewById(R.id.imageViewConcert);
        }
    }
}
