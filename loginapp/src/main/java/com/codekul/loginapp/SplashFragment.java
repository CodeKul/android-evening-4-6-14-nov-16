package com.codekul.loginapp;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"Otto.ttf");
        ((TextView)rootView.findViewById(R.id.textAppName)).setTypeface(tf);
        ((TextView)rootView.findViewById(R.id.btnLogin)).setTypeface(tf);
        ((TextView)rootView.findViewById(R.id.btnRegister)).setTypeface(tf);

        rootView.findViewById(R.id.btnLogin).setOnClickListener(this::clicked);
        rootView.findViewById(R.id.btnRegister).setOnClickListener(this::clicked);

        return rootView;
    }

    private void clicked(View view) {
        if(view.getId() == R.id.btnLogin) ((MainActivity)getActivity())
                .runFragmentTxn(new LoginFragment(),"login");

        if(view.getId() == R.id.btnRegister) ((MainActivity)getActivity())
                .runFragmentTxn(new RegisterFragment(),"registration");
    }
}
