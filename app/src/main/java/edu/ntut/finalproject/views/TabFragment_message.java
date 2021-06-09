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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.controllers.ChatAdapter;

public class TabFragment_message extends Fragment {

    private RecyclerView recyclerView;

    public TabFragment_message() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_message, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("edu.ntut.finalproject.loginStatus", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("UID", null);

        recyclerView = view.findViewById(R.id.tab_message_recyclerView);
        recyclerView.setAdapter(new ChatAdapter(getActivity(), uid));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
