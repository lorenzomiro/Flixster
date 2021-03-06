package com.example.flixster.models;

//holds data for movie in the database

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String backdropPath;

    String posterPath;

    String title;

    String overview;

    int movieId;

    double rating;

    //add empty constructor so parcel library can be used

    public Movie() {

    }

    public Movie(JSONObject jsonObject) throws JSONException { //take in JSON objects + get important data

        backdropPath = jsonObject.getString("backdrop_path");

        posterPath = jsonObject.getString("poster_path");

        title = jsonObject.getString("title");

        overview = jsonObject.getString("overview");

        rating = jsonObject.getDouble("vote_average");

        movieId = jsonObject.getInt("id");

    }

    public static List<Movie> fromJSONArray(JSONArray movie_json_array) throws JSONException {

        List<Movie> movies = new ArrayList<>();

        for (int m = 0; m < movie_json_array.length(); m++) { //loop used to add movies

            //add movie into movies array

            movies.add(new Movie(movie_json_array.getJSONObject(m)));
            

        }

        return movies;

    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getPosterPath() {

        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);

    }

    public String getTitle() {

        return title;

    }

    public String getOverview() {

        return overview;

    }

    public double getRating() {

        return rating;

    }

    public int getMovieId() {

        return movieId;

    }

}

