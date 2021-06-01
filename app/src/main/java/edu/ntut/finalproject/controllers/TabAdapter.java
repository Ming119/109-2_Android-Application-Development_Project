package edu.ntut.finalproject.controllers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class TabAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> TabList = new ArrayList<>();

    public TabAdapter(@NonNull FragmentManager  fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addFragment(Fragment fragment) {
        TabList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return TabList.get(position);
    }

    @Override
    public int getItemCount() { return TabList.size(); }
}
