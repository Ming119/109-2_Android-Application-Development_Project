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

public class Notification_Tab extends Fragment {

    private ArrayList<Item> itemArray;

    public Notification_Tab() {
        //this.itemArray = itemArray;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_notification, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("edu.ntut.finalproject.loginStatus", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("UID", null);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
