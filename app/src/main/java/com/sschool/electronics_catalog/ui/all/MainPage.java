package com.sschool.electronics_catalog.ui.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sschool.electronics_catalog.MainActivity;
import com.sschool.electronics_catalog.R;

import java.util.Objects;

public class MainPage extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button buttonProcessors = view.findViewById(R.id.buttonProcessors);
        buttonProcessors.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) getActivity();
            Objects.requireNonNull(activity).nextFragment(R.id.nav_processors);
        });

        Button buttonVideocards = view.findViewById(R.id.buttonVideocards);
        buttonVideocards.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) getActivity();
            Objects.requireNonNull(activity).nextFragment(R.id.nav_videocards);
        });

        Button buttonSmartphones = view.findViewById(R.id.buttonSmartphones);
        buttonSmartphones.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) getActivity();
            Objects.requireNonNull(activity).nextFragment(R.id.nav_smartphones);
        });

        Button buttonHeadphones = view.findViewById(R.id.buttonHeadphones);
        buttonHeadphones.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) getActivity();
            Objects.requireNonNull(activity).nextFragment(R.id.nav_headphones);
        });
        return view;
    }
}