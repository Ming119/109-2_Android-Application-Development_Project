package edu.ntut.finalproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.controllers.ItemAdapter;

public class TabFragment_mainpage extends Fragment {

    private static final String ARG_COUNT = "ARG_COUNT";

    private RecyclerView recyclerView;

    public static TabFragment_profile_login newInstance(int counter) {
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        TabFragment_profile_login fragment = new TabFragment_profile_login();
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment_mainpage() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_main, container, false);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        recyclerView = view.findViewById(R.id.recyclerView);;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), gridColumnCount));
        recyclerView.setAdapter(new ItemAdapter(getActivity()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
