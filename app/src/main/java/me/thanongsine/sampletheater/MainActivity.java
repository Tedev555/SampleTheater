package me.thanongsine.sampletheater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Movies> moviesList = createMoviesData();

        RecyclerView moviesListRecyclerView = findViewById(R.id.movies_list_recyclerView);
        MoviesListAdapter adapter = new MoviesListAdapter(moviesList);
        moviesListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        moviesListRecyclerView.setAdapter(adapter);
    }

    private List<Movies> createMoviesData() {
        List<Movies> moviesList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Movies movies = new Movies(R.drawable.kubo_movies, "Kubo Two Strings");
            moviesList.add(movies);
        }

        return moviesList;
    }
}
