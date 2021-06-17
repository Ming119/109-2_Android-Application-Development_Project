package edu.ntut.finalproject.fragments;

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

import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.adapters.ChatAdapter;
import edu.ntut.finalproject.models.Chat;
import edu.ntut.finalproject.util;

public class TabFragment_message extends Fragment {

    private String uid;
    private ArrayList<Chat> chatArray;

    private ChatAdapter chatAdapter;

    public TabFragment_message() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_message, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(util.sharePrefName, Context.MODE_PRIVATE);
        Chat chat = new Chat();

        uid = sharedPreferences.getString(util.UID, null);
        try {
            chatArray = chat.getChats(uid);
            chatAdapter = new ChatAdapter(getActivity(), chatArray, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = view.findViewById(R.id.tab_message_recyclerView);
        recyclerView.setAdapter(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        Chat chat = new Chat();
        try {
            this.chatArray = chat.getChats(uid);
            chatAdapter.setMessageArray(chatArray);
            chatAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }

}
