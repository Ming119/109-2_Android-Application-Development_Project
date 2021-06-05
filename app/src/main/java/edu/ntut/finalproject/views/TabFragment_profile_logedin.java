package edu.ntut.finalproject.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import edu.ntut.finalproject.R;

public class TabFragment_profile_logedin extends Fragment {

    private static final String ARG_COUNT = "ARG_COUNT";

    Button settings_btn ;
    Button mywallet_btn;
    Button favourites_btn;
    Button feedback_btn;


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
        //return inflater.inflate(R.layout.tab_profile_logedin, container, false);


        View the_view = inflater.inflate(R.layout.tab_profile_logedin,container,false);

        settings_btn = the_view.findViewById(R.id.setting_btn);
        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent setting_intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(setting_intent);

            }
        });

        mywallet_btn = the_view.findViewById(R.id.wallet_btn);
        mywallet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mywallet_intent = new Intent(getActivity(),MyWalletActivity.class);
                startActivity(mywallet_intent);
            }
        });


        favourites_btn = the_view.findViewById(R.id.favourites_btn);
        favourites_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favourite_intent = new Intent(getActivity(),FavouritesActivity.class);
                startActivity(favourite_intent);
            }
        });

        feedback_btn = the_view.findViewById(R.id.feedback);
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback_intent = new Intent(getActivity(),FeedbackActivity.class);
                startActivity(feedback_intent);
            }
        });
        
        return the_view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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