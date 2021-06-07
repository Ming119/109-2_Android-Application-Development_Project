package edu.ntut.finalproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.controllers.SearchAdapter;
import edu.ntut.finalproject.models.Item;

public class TabFragment_search extends Fragment {
    private static final String ARG_COUNT = "ARG_COUNT";

    private SearchView   searchView;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    private ArrayList<Item> itemArray = new ArrayList<>();


    public static TabFragment_profile_login newInstance(int counter) {
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        TabFragment_profile_login fragment = new TabFragment_profile_login();
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment_search() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_search, container, false);

        searchView   = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.search_recyclerView);

        searchAdapter = new SearchAdapter(getActivity(), itemArray);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayList<Item> filterdItemArray = filter(itemArray, newText);
                //searchAdapter = new SearchAdapter(getActivity(), filterdItemArray);
                return true;
            }

            private ArrayList<Item> filter(ArrayList<Item> itemArray, String query) {
                final String lowerCaseQuery = query.toLowerCase();

                final ArrayList<Item> filterdItemArray = new ArrayList<>();
                for (Item item : itemArray) {
                    final String title = item.getTitle();
                    final String desc  = item.getDescription();

                    if (title.contains(lowerCaseQuery) || desc.contains(lowerCaseQuery))
                        filterdItemArray.add(item);
                }

                return filterdItemArray;
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
