package me.thanongsine.sampletheater.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.thanongsine.sampletheater.R;

public class TemplateFragment extends Fragment {
    public static Fragment newInstance() {

        return new TemplateFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate layout for fragment
        //View view = inflater.inflate(R.layout.layout_template, container, false)
        //return  view;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
