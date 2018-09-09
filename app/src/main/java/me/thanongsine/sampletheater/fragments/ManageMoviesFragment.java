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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import me.thanongsine.sampletheater.Movies;
import me.thanongsine.sampletheater.MoviesListAdapter;
import me.thanongsine.sampletheater.R;

public class ManageMoviesFragment extends Fragment {
    private DatabaseReference mDatabase;

    public static Fragment newInstance() {

        return new ManageMoviesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_movies, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        final EditText moviesEditText = view.findViewById(R.id.editText_movies_name);
        Button btnSave = view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMovies(123, moviesEditText.getText().toString());
            }
        });

        return view;
    }

    private void addNewMovies(Integer moviesImgUrl, String moviesName) {
        String moviesId = mDatabase.child("movieslist").push().getKey();
        Movies movies = new Movies("https://i2.wp.com/speculativechic.com/wp-content/uploads/2017/05/kubo-main_0.jpeg?resize=600%2C379\"", "Kubo and the Two Strings");

        mDatabase.child("movieslist").child(moviesId).setValue(movies);
    }
}
