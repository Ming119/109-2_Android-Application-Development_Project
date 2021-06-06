package edu.ntut.finalproject.views;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.User;

public class TabFragment_profile_login extends Fragment {

    private static final String ARG_COUNT = "ARG_COUNT";

    private Context context;
    private SharedPreferences sharedPreferences;

    private EditText studentID;
    private EditText password;
    private Button   login;
    private Text     gotoRegister;
    private ImageView showPW;

    @NonNull
    public static TabFragment_profile_login newInstance(int counter) {
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        TabFragment_profile_login fragment = new TabFragment_profile_login();
        fragment.setArguments(args);
        return fragment;
    }
    
    public TabFragment_profile_login() { }

    public EditText student() {return studentID; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_profile_login,container,false);

        sharedPreferences = context.getSharedPreferences("edu.ntut.finalproject.loginStatus", Context.MODE_PRIVATE);
        studentID = view.findViewById(R.id.login_studentID);
        password  = view.findViewById(R.id.login_passowrd);
        login     = view.findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isloged = false;
                User user = new User();

                try {
                    isloged = user.Login(studentID.getText().toString(), password.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (isloged) {
                    SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                    preferencesEditor.putString("UID", user.getUid());
                    preferencesEditor.putString("USERNAME", user.getName());
                    preferencesEditor.apply();
                    Toast.makeText(getActivity(), "Login Success.", Toast.LENGTH_SHORT).show();

                    Intent main =  getActivity().getIntent();
                    getActivity().finish();
                    startActivity(main);

                } else
                    Toast.makeText(getActivity(), "Login Failed.", Toast.LENGTH_LONG).show();
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
