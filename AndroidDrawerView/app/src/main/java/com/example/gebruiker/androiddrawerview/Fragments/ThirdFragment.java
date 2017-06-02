package com.example.gebruiker.androiddrawerview.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gebruiker.androiddrawerview.R;

/**
 * Created by user on 12/31/15.
 */
public class ThirdFragment extends Fragment {

    View myView;

    ///@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.third_layout, container, false);
        return myView;
    }
}
