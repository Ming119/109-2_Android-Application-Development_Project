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
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import edu.ntut.finalproject.R;

public class TabFragment_profile_logedin extends Fragment {

    private static final String ARG_COUNT = "ARG_COUNT";

    private Context context;
    private SharedPreferences sharedPreferences;

    private TextView user_name;
    private TextView user_id;

    private Button settings_btn ;
    private Button mywallet_btn;
    private Button change_password_btn;
    private Button sign_out_btn;
    private Button favourites_btn;
    private Button feedback_btn;


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

        settings_btn = view.findViewById(R.id.setting_btn);
        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent setting_intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(setting_intent);

            }
        });

        mywallet_btn = view.findViewById(R.id.wallet_btn);
        mywallet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mywallet_intent = new Intent(getActivity(),MyWalletActivity.class);
                startActivity(mywallet_intent);
            }
        });


        favourites_btn = view.findViewById(R.id.favourites_btn);
        favourites_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favourite_intent = new Intent(getActivity(),FavouritesActivity.class);
                startActivity(favourite_intent);
            }
        });


        sign_out_btn = view.findViewById(R.id.signout_btn);
        sign_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();

                Intent main =  getActivity().getIntent();
                getActivity().finish();
                startActivity(main);
            }
        });


        feedback_btn = view.findViewById(R.id.feedback);
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback_intent = new Intent(getActivity(),FeedbackActivity.class);
                startActivity(feedback_intent);
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