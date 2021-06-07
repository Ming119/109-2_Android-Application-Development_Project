package edu.ntut.finalproject.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.controllers.ChatTabAdapter;
import edu.ntut.finalproject.models.Item;

public class TabFragment_notification extends Fragment {

    private static final String ARG_COUNT = "ARG_COUNT";

    public static TabFragment_profile_login newInstance(int counter) {
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        TabFragment_profile_login fragment = new TabFragment_profile_login();
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment_notification() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_notification, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("edu.ntut.finalproject.loginStatus", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("UID", null);

        TabLayout tabLayout  = view.findViewById(R.id.nofication_tabLayer);
        ViewPager2 viewPager = view.findViewById(R.id.notification_viewPager);

        viewPager.setAdapter(new ChatTabAdapter(getActivity(), uid));
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> { }).attach();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
