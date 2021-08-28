package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;

    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {

        this.context = context;

        this.movies = movies;

    }


    //inflate XML layout and return holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("MovieAdapter ","onCreateViewHolder");

        View movie_view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(movie_view);
    }

    //populate data through viewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("MovieAdapter","onBindViewHolder " + position);

        //get movie at position passed

        Movie movie = movies.get(position);

        //bind movie data -> viewholder

        holder.bind(movie);

    }

    //return total item count
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        TextView tvOverview;

        ImageView ivPoster;

        //create a constructor to represent the ViewHolder

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            //define location of textViews + imageViews

            tvTitle = itemView.findViewById(R.id.tvTitle);

            tvOverview = itemView.findViewById(R.id.tvOverview);

            ivPoster = itemView.findViewById(R.id.ivPoster);

        }

        public void bind(Movie movie) {

            tvTitle.setText(movie.getTitle());

            tvOverview.setText(movie.getOverview());

            String image_url;

            //if phone in landscape: image url = backdrop image

            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                image_url = movie.getBackdropPath();

                } else {

                    //else image url = poster image

                    image_url = movie.getPosterPath();

            }

            Glide.with(context).load(image_url).into(ivPoster);

        }
    }

}
