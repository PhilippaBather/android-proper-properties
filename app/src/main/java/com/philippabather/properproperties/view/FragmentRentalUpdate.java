package com.philippabather.properproperties;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.philippabather.properproperties.presenter.PropertyUpdatePresenter;


public class FragmentRentalUpdate extends Fragment {

    private PropertyUpdatePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rental_update, container, false);

        return view;
    }
}