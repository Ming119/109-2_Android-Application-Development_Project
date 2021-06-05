package edu.ntut.finalproject.controllers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import edu.ntut.finalproject.models.User;
import edu.ntut.finalproject.views.TabFragment_mainpage;
import edu.ntut.finalproject.views.TabFragment_notification;
import edu.ntut.finalproject.views.TabFragment_post;
import edu.ntut.finalproject.views.TabFragment_profile_logedin;
import edu.ntut.finalproject.views.TabFragment_profile_login;
import edu.ntut.finalproject.views.TabFragment_search;

public class TabAdapter extends FragmentStateAdapter {

    static final int ITEM_COUNT = 5;

    private String uid  = null;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);;
        //uid  = sharedPreferences.getString("UID", null);

        switch(position) {
            case 0: return new TabFragment_mainpage();

            case 1: return new TabFragment_search();

            case 2: return new TabFragment_post();

            case 3: return new TabFragment_notification();

            case 4:
                if (uid ==  null) return new TabFragment_profile_logedin();
                else return new TabFragment_profile_login();

            default: return null;
        }
    }

    @Override
    public int getItemCount() { return ITEM_COUNT; }
}
