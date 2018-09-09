package me.thanongsine.sampletheater;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder>{
    private List<Movies> moviesList;

    public MoviesListAdapter(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.movies_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movies movies = moviesList.get(i);
        ImageView moviesImg = viewHolder.moviesImg;
        TextView moviesTitle = viewHolder.moviesTitleTextView;

        Picasso.get()
                .load("https://i2.wp.com/speculativechic.com/wp-content/uploads/2017/05/kubo-main_0.jpeg?resize=600%2C379")
                .placeholder(R.drawable.sunset01)
                .into(moviesImg);
        moviesTitle.setText("Kubo");
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView moviesImg;
        TextView moviesTitleTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            moviesImg = itemView.findViewById(R.id.img_movies);
            moviesTitleTextView = itemView.findViewById(R.id.textView_movies_title);
        }
    }
}
