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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.activities.RegisterActivity;
import edu.ntut.finalproject.models.User;
import edu.ntut.finalproject.util;

public class TabFragment_profile_login extends Fragment {

    private Context context;
    private SharedPreferences sharedPreferences;

    private EditText studentID;
    private EditText password;

    public TabFragment_profile_login() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_profile_login,container,false);

        sharedPreferences = context.getSharedPreferences(util.sharePrefName, Context.MODE_PRIVATE);
        studentID = view.findViewById(R.id.login_studentID);
        password  = view.findViewById(R.id.login_passowrd);

        Button login = view.findViewById(R.id.btn_login);
        login.setOnClickListener((View v) -> {
            String lid = studentID.getText().toString();
            String lpw = password.getText().toString();

            if (lid.isEmpty()) {
                Toast.makeText(getActivity(), R.string.nullUID, Toast.LENGTH_LONG).show();
                return;
            }

            if (lid.length() != 9) {
                Toast.makeText(getActivity(), R.string.UIDformat, Toast.LENGTH_LONG).show();
                return;
            }

            if (lpw.isEmpty()) {
                Toast.makeText(getActivity(), R.string.nullPW, Toast.LENGTH_LONG).show();
                return;
            }

            User user = new User();
            try {
                if (user.Login(lid, lpw)) {
                    Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                    preferencesEditor.putString("UID", user.getUid());
                    preferencesEditor.putString("USERNAME", user.getName());
                    preferencesEditor.apply();

                    Intent main =  getActivity().getIntent();
                    getActivity().finish();
                    startActivity(main);
                } else
                    Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        TextView gotoRegister = view.findViewById(R.id.text_gotoRegister);
        gotoRegister.setOnClickListener(v -> {
            Intent register = new Intent(context, RegisterActivity.class);
            startActivity(register);
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
