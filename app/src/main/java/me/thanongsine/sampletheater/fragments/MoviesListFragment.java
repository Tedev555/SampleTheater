package me.thanongsine.sampletheater.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.thanongsine.sampletheater.Movies;
import me.thanongsine.sampletheater.MoviesListAdapter;
import me.thanongsine.sampletheater.R;

public class MoviesListFragment extends Fragment {
    public static Fragment newInstance() {

        return new MoviesListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        List<Movies> moviesList = createMoviesData();

        RecyclerView moviesListRecyclerView = view.findViewById(R.id.movies_list_recyclerView);
        MoviesListAdapter adapter = new MoviesListAdapter(moviesList);
        moviesListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        moviesListRecyclerView.setAdapter(adapter);

        return view;
    }

    private List<Movies> createMoviesData() {
        List<Movies> moviesList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Movies movies = new Movies("https://i2.wp.com/speculativechic.com/wp-content/uploads/2017/05/kubo-main_0.jpeg?resize=600%2C379", "Kubo Two Strings");
            moviesList.add(movies);
        }

        return moviesList;
    }
}
