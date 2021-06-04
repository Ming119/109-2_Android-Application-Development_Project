package edu.ntut.finalproject.controllers;

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

    private User user = new User();
    private boolean logedin;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        logedin = user.loginState();

        switch(position) {
            case 0: return new TabFragment_mainpage();

            case 1: return new TabFragment_search();

            case 2: return new TabFragment_post();

            case 3: return new TabFragment_notification();

            case 4:
                if (logedin) return new TabFragment_profile_logedin();
                else return new TabFragment_profile_login();
        }
        return null;
    }

    @Override
    public int getItemCount() { return ITEM_COUNT; }
}
