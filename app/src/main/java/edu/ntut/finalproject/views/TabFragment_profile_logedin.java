package edu.ntut.finalproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.ntut.finalproject.R;

public class TabFragment_profile_logedin extends Fragment {
    private static final String ARG_COUNT = "ARG_COUNT";

    public static TabFragment_profile_login newInstance(int counter) {
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        TabFragment_profile_login fragment = new TabFragment_profile_login();
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment_profile_logedin() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_profile_logedin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}