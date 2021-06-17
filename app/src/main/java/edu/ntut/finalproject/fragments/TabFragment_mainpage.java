package edu.ntut.finalproject.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.adapters.ItemAdapter;
import edu.ntut.finalproject.models.Item;

public class TabFragment_mainpage extends Fragment {

    private ArrayList<Item> itemArray;
    private ItemAdapter itemAdapter;

    public TabFragment_mainpage() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_main, container, false);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        Item item = new Item();
        try {
            this.itemArray = item.getItems();
            itemAdapter = new ItemAdapter(getActivity(), itemArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), gridColumnCount));
        recyclerView.setAdapter(itemAdapter);

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
            itemAdapter.setItemArray(itemArray);
            itemAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onResume();
    }
}
