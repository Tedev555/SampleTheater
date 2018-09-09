package me.thanongsine.sampletheater.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.thanongsine.sampletheater.Movies;
import me.thanongsine.sampletheater.MoviesListAdapter;
import me.thanongsine.sampletheater.R;

public class ManageMoviesFragment extends Fragment {
    private DatabaseReference mDatabase;
    private Uri uri;
    private boolean aBoolean;
    private ImageView uploadImg;

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
        uploadImg = view.findViewById(R.id.img_movies_upload);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromDevice();
            }
        });
        final EditText moviesNameEditText = view.findViewById(R.id.editText_movies_name);
        Button btnSave = view.findViewById(R.id.btn_save);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToFirebase(moviesNameEditText.getText().toString());
            }
        });

        return view;
    }

    private void getImageFromDevice() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Please Choose App"), 1);
    }

    private void addNewMovies(String moviesImgUrl, String moviesName) {
        String moviesId = mDatabase.child("movieslist").push().getKey();

        Movies movies = new Movies(moviesImgUrl, moviesName);

        mDatabase.child("movieslist").child(moviesId).setValue(movies);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {

            uri = data.getData();
            aBoolean = false;

            try {

                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 400, 300, true);
                uploadImg.setImageBitmap(bitmap1);

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(getActivity(), "Please Choose Photo", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadImageToFirebase(final String moviesName) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        final StorageReference moveisImageRef = storageReference.child("MoviesImages/" + moviesName + "image");

        UploadTask uploadTask = moveisImageRef.putFile(uri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw Objects.requireNonNull(task.getException());
                }

                // Continue with the task to get the download URL
                return moveisImageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Log.e("downloadUri: ", downloadUri.toString());
                    addNewMovies(downloadUri.toString(),moviesName);
                } else {
                    // Handle failures
                    // ...
                }
            }
        });
    }
}
