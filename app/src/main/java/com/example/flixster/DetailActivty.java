package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivty extends YouTubeBaseActivity {

    //use API key to import YouTube data, movie trailers url

    private static final String YOUTUBE_API_KEY = "AIzaSyByv11zjXXyZM1iQ2j4TnMu6uzozaimlV0";

    public static final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    //implement textviews for movie overview + title, and rating bar

    TextView tvTitle;

    TextView tvOverview;

    RatingBar ratingBar;

    //implement YouTube player view

    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //create title/overview textviews, rating bar, youtube player view

        tvTitle = findViewById(R.id.tvTitle);

        tvOverview = findViewById(R.id.tvOverview);

        ratingBar = findViewById(R.id.ratingBar);

        youTubePlayerView = findViewById(R.id.player);

        //get title

        String title = getIntent().getStringExtra("title");

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        //push movie title + overview to text view

        tvTitle.setText(movie.getTitle());

        tvOverview.setText(movie.getOverview());

        //set movie rating + set to movie overview

        ratingBar.setRating((float) movie.getRating());

        //get movie trailer clients

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(String.format(VIDEOS_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {

                try {

                    JSONArray results = json.jsonObject.getJSONArray("results");

                    if (results.length() == 0) { //checks for empty results

                        return;

                    }

                    //get results from "key" data value

                    String youtube_key = results.getJSONObject(0).getString("key");

                    Log.d("DetailActivity", youtube_key);

                    initialize_youtube(youtube_key);

                } catch (JSONException e) {

                    Log.e("DetailActivity", "Failed to parse JSON ", e);

                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });

    }

        private void initialize_youtube(final String youtube_key){
            youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    Log.d("DetailActivity", "onInitializationSuccess");
                    youTubePlayer.loadVideo(youtube_key);
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                    Log.d("DetailActivity", "onInitializationFailure");
                }
            });

        }

}