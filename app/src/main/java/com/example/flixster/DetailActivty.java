package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get tile

        String title = getIntent().getStringExtra("title");

    }
}