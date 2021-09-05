package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivty extends AppCompatActivity {

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

        //push title to text view

        tvTitle.setText(title);

    }
}