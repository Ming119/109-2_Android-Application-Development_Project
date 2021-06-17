package edu.ntut.finalproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.adapters.SearchAdapter;
import edu.ntut.finalproject.models.Item;

public class TabFragment_search extends Fragment {

    private SearchAdapter searchAdapter;

    private ArrayList<Item> itemArray;

    public TabFragment_search() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_search, container, false);

        SearchView searchView = view.findViewById(R.id.searchView);
        RecyclerView recyclerView = view.findViewById(R.id.search_recyclerView);

        Item item = new Item();
        try {
            this.itemArray = item.getItems();
            searchAdapter = new SearchAdapter(getActivity(), itemArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        Item item = new Item();
        try {
            this.itemArray = item.getItems();
            searchAdapter.setItemArray(itemArray);
            searchAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onResume();
    }
}
