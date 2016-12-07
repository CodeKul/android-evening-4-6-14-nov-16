package com.codekul.loginapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_PASSWORD = "password";

    public static LoginFragment getInstance(String userName, String password) {

        LoginFragment fragment = new LoginFragment();

        Bundle bundle = new Bundle();
        bundle.putString(KEY_USER_NAME,userName);
        bundle.putString(KEY_PASSWORD,password);

        fragment.setArguments(bundle);

        return fragment;
    }

    public LoginFragment() {

        super();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        String userName = args.getString(KEY_USER_NAME);
        String password = args.getString(KEY_PASSWORD);

        final View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        ((EditText)rootView.findViewById(R.id.edtUserName)).setText(userName);
        ((EditText)rootView.findViewById(R.id.edtPassword)).setText(password);

        return rootView;
    }

}
