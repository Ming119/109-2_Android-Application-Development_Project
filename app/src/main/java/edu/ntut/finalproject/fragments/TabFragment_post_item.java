package edu.ntut.finalproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Item;
import edu.ntut.finalproject.util;

public class TabFragment_post_item extends Fragment {

    private SharedPreferences sharedPreferences;

    private EditText et_title;
    private EditText et_desc;
    private EditText et_price;

    public TabFragment_post_item() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_post_item, container, false);

        sharedPreferences = getContext().getSharedPreferences(util.sharePrefName, Context.MODE_PRIVATE);
        et_title = view.findViewById(R.id.upload_title);
        et_desc  = view.findViewById(R.id.upload_desc);
        et_price = view.findViewById(R.id.upload_price);

        Button upload_new = view.findViewById(R.id.btn_upload_new);
        upload_new.setOnClickListener(v -> {
            String title = et_title.getText().toString();
            String desc  = et_desc.getText().toString();
            String price = et_price.getText().toString();

            if (title.isEmpty()) {
                Toast.makeText(getActivity(), R.string.nullTitle, Toast.LENGTH_LONG).show();
                return;
            }

            if (price.isEmpty()) {
                Toast.makeText(getActivity(), R.string.nullPrice, Toast.LENGTH_LONG).show();
                return;
            }

            Item item = new Item();
            try {
                if (item.newItem(title, desc, Integer.parseInt(price), sharedPreferences.getString(util.UID, null))) {
                    Toast.makeText(getActivity(), R.string.uploadSuccess, Toast.LENGTH_SHORT).show();

                    Intent intent = getActivity().getIntent();
                    getActivity().finish();
                    startActivity(intent);
                }

            } catch (IOException e) {
                Toast.makeText(getActivity(), R.string.uploadFail, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
