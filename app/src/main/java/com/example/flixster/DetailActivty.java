package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class DetailActivty extends AppCompatActivity {

    //use API key to import YouTube data

    private static final String YOUTUBE_API_KEY = "AIzaSyByv11zjXXyZM1iQ2j4TnMu6uzozaimlV0";

    //implement textviews for movie overview + title, and rating bar

    TextView tvTitle;

    TextView tvOverview;

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //create title/overview textviews, rating bar

        tvTitle = findViewById(R.id.tvTitle);

        tvOverview = findViewById(R.id.tvOverview);

        ratingBar = findViewById(R.id.ratingBar);

        //get title

        String title = getIntent().getStringExtra("title");

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        //push movie title + overview to text view

        tvTitle.setText(movie.getTitle());

        tvOverview.setText(movie.getOverview());

        //set movie rating + set to movie overview

        ratingBar.setRating((float) movie.getRating());

    }
}