package com.codepath.kbanda.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.kbanda.flixter.R;
import com.codepath.kbanda.flixter.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the movie at the passed in position
        Movie movie = movies.get(position);
        //Bind the movie to the ViewHolder
        holder.bind(movie);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ImageView ivBackdrop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            ivBackdrop = itemView.findViewById(R.id.ivBackdrop);
        }
        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //if phone in landscape then backdrop path
                imageUrl = movie.getBackdropPath();
                Glide.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivBackdrop);
            } else {
                //if phone in portrait then poster path
                imageUrl = movie.getPosterPath();
                Glide.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivPoster);
            }
        }
    }

}
