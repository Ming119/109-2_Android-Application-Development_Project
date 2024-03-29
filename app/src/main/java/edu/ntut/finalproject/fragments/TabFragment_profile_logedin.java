package edu.ntut.finalproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.activities.ChangePasswordActivity;
import edu.ntut.finalproject.activities.ChangeUsernameActivity;
import edu.ntut.finalproject.activities.ContributionActivity;
import edu.ntut.finalproject.util;

public class TabFragment_profile_logedin extends Fragment {

    private Context context;
    private SharedPreferences sharedPreferences;

    private TextView user_name;
    private TextView user_id;

    private String uname;
    private String uid;

    public TabFragment_profile_logedin() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_profile_logedin,container,false);

        sharedPreferences = context.getSharedPreferences(util.sharePrefName, Context.MODE_PRIVATE);

        user_name = view.findViewById(R.id.user_name);
        user_id   = view.findViewById(R.id.user_id);

        uname = sharedPreferences.getString(util.USERNAME, null);
        uid   = sharedPreferences.getString(util.UID, null);

        user_name.setText(uname);
        user_id.setText(uid);

        TextView change_username = view.findViewById(R.id.text_change_username);
        change_username.setOnClickListener(view12 -> {
            Intent change_username_intent = new Intent(getActivity(), ChangeUsernameActivity.class);
            startActivity(change_username_intent);
        });

        TextView change_password = view.findViewById(R.id.text_change_password);
        change_password.setOnClickListener(view15 -> {
            Intent change_password_intent = new Intent(getActivity(), ChangePasswordActivity.class);
            startActivity(change_password_intent);
        });

        TextView signout = view.findViewById(R.id.text_sign_out);
        signout.setOnClickListener(view1 -> {
            SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
            preferencesEditor.clear();
            preferencesEditor.apply();

            Intent main =  getActivity().getIntent();
            getActivity().finish();
            startActivity(main);
        });

        TextView contribution = view.findViewById(R.id.text_contribution);
        contribution.setOnClickListener(view13 -> {
            Intent intent = new Intent(getActivity(), ContributionActivity.class);
            startActivity(intent);
        });

        TextView version_info = view.findViewById(R.id.text_version_info);
        version_info.setOnClickListener(view14 -> Toast.makeText(getActivity(), getString(R.string.version), Toast.LENGTH_LONG).show());
        
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        uname = sharedPreferences.getString(util.USERNAME, null);
        uid   = sharedPreferences.getString(util.UID, null);

        user_name.setText(uname);
        user_id.setText(uid);

        super.onResume();
    }
}