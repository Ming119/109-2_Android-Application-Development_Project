package edu.ntut.finalproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.User;

public class TabFragment_profile extends Fragment {

    private User user = new User();
    private boolean logedin;

    public TabFragment_profile() {
        logedin = user.loginState();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (logedin == true)
            return inflater.inflate(R.layout.profile_logedin, container, false);
        else
            return inflater.inflate(R.layout.profile_login, container, false);
    }
}
