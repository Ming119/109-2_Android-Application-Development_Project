package edu.ntut.finalproject.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import edu.ntut.finalproject.R;

public class TabFragment_profile_logedin extends Fragment {

    private static final String ARG_COUNT = "ARG_COUNT";

    private Context context;
    private SharedPreferences sharedPreferences;

    private TextView user_name;
    private TextView user_id;

    private TextView setting;
    private TextView change_username;
    private TextView change_password;
    private TextView signout;
    private TextView contribution;
    private TextView version_info;

    @NonNull
    public static TabFragment_profile_login newInstance(int counter) {
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        TabFragment_profile_login fragment = new TabFragment_profile_login();
        fragment.setArguments(args);
        return fragment;

    }

    public TabFragment_profile_logedin() {

    }

    public boolean onBackPressed() {
        return false;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_profile_logedin,container,false);

        sharedPreferences = context.getSharedPreferences("edu.ntut.finalproject.loginStatus", Context.MODE_PRIVATE);

        user_name = view.findViewById(R.id.user_name);
        user_id   = view.findViewById(R.id.user_id);

        user_name.setText(sharedPreferences.getString("USERNAME", ""));
        user_id.setText(sharedPreferences.getString("UID", ""));



        setting = view.findViewById(R.id.text_setting);
        setting.setOnClickListener(v -> {
            Intent setting_intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(setting_intent);

        });

        change_username = view.findViewById(R.id.text_change_username);
        change_username.setOnClickListener(view12 -> {
            Intent change_username_intent = new Intent(getActivity(), ChangeUsernameActivity.class);
            startActivity(change_username_intent);
        });

        change_password = view.findViewById(R.id.text_change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change_password_intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(change_password_intent);
            }
        });

        signout = view.findViewById(R.id.text_sign_out);
        signout.setOnClickListener(view1 -> {
            SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
            preferencesEditor.clear();
            preferencesEditor.apply();

            Intent main =  getActivity().getIntent();
            getActivity().finish();
            startActivity(main);
        });

        contribution = view.findViewById(R.id.text_contribution);
        contribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContributionActivity.class);
                startActivity(intent);
            }
        });

        version_info = view.findViewById(R.id.text_version_info);
        version_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), getString(R.string.version), Toast.LENGTH_LONG).show();
            }
        });
        
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

}

/*
    @Override
    public View onCreateViews(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View setting_view = inflater.inflate(R.layout.tab_profile_logedin,container,false);
        settings_btn = setting_view.findViewById(R.id.setting_btn);
        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent setting_intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(setting_intent);

            }
        });

 */