package me.thanongsine.sampletheater.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import me.thanongsine.sampletheater.R;

public class SigninFragment extends Fragment {
    private FirebaseAuth mAuth;

    public static Fragment newInstance() {

        return new SigninFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        final EditText emailEditText = view.findViewById(R.id.editText_email);
        final EditText passwordEditText = view.findViewById(R.id.editText_password);
        Button btnSignin = view.findViewById(R.id.btn_sign_in);

        mAuth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.w("SigningFragmentLog", "signInWithEmail:True");
                                } else {
                                    Log.w("SigningFragmentLog", "signInWithEmail:Fail");
                                }
                            }
                        });
            }
        });

        return view;
    }
}
