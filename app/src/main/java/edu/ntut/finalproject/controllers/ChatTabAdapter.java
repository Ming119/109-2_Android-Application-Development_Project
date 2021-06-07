package edu.ntut.finalproject.controllers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import edu.ntut.finalproject.models.Item;
import edu.ntut.finalproject.views.Notification_Tab;

public class ChatTabAdapter extends FragmentStateAdapter {

    private final int TAB_COUNT = 5;

    private ArrayList<Item> itemArray;
    private String uid;

    public ChatTabAdapter(@NonNull FragmentActivity fragmentActivity, String uid) {
        super(fragmentActivity);

        this.uid = uid;

        Item item = new Item();
        try {
            itemArray = item.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new Notification_Tab();

            case 1:
                ArrayList<Item> newItemArray = new ArrayList<>();
                for (int i = 0; i < itemArray.size(); ++i) {
                    if (itemArray.get(i).getUid() == uid)
                        newItemArray.add(itemArray.get(i));
                }
                return new Notification_Tab();
            case 2:
                return new Notification_Tab();

            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
