package me.thanongsine.sampletheater.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.thanongsine.sampletheater.Movies;
import me.thanongsine.sampletheater.MoviesListAdapter;
import me.thanongsine.sampletheater.R;

public class MoviesListFragment extends Fragment {
    private DatabaseReference mDatabase;

    public static Fragment newInstance() {

        return new MoviesListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        mDatabase.child("movieslist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Movies> moviesList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Movies movies = snapshot.getValue(Movies.class);
                    moviesList.add(movies);
                    RecyclerView moviesListRecyclerView = view.findViewById(R.id.movies_list_recyclerView);
                    MoviesListAdapter adapter = new MoviesListAdapter(moviesList);
                    moviesListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    moviesListRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
