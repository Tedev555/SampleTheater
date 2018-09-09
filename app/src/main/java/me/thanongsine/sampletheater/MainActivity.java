package me.thanongsine.sampletheater;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import me.thanongsine.sampletheater.fragments.AboutFragment;
import me.thanongsine.sampletheater.fragments.ManageMoviesFragment;
import me.thanongsine.sampletheater.fragments.MoviesListFragment;
import me.thanongsine.sampletheater.fragments.SigninFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        selectFragment(MoviesListFragment.newInstance());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_movies_list:
                        selectFragment(MoviesListFragment.newInstance());
                        break;
                    case R.id.action_manage_movies:
                        selectFragment(SigninFragment.newInstance());
                        break;
                    case R.id.action_about:
                        selectFragment(AboutFragment.newInstance());
                        break;
                }
                return true;
            }
        });
    }

    private void selectFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
