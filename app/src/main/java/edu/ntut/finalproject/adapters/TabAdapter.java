package edu.ntut.finalproject.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import edu.ntut.finalproject.fragments.TabFragment_mainpage;
import edu.ntut.finalproject.fragments.TabFragment_message;
import edu.ntut.finalproject.fragments.TabFragment_post_item;
import edu.ntut.finalproject.fragments.TabFragment_profile_logedin;
import edu.ntut.finalproject.fragments.TabFragment_profile_login;
import edu.ntut.finalproject.fragments.TabFragment_search;

public class TabAdapter extends FragmentStateAdapter {

    static final int ITEM_COUNT = 5;

    private final String uid;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity, String uid) {
        super(fragmentActivity);
        this.uid = uid;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch(position) {
            case 0: return new TabFragment_mainpage();

            case 1: return new TabFragment_search();

            case 2:
                if (uid == null) return new TabFragment_profile_login();
                return new TabFragment_post_item();

            case 3:
                if (uid == null) return new TabFragment_profile_login();
                return new TabFragment_message();

            case 4:
                if (uid == null) return new TabFragment_profile_login();
                return new TabFragment_profile_logedin();
            default: return null;
        }
    }

    @Override
    public int getItemCount() { return ITEM_COUNT; }
}
