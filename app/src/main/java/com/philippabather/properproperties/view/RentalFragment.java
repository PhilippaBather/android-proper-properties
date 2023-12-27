package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_PARCELABLE_LIST_RENTALS;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPRIETOR_ID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.adapter.RentalPropertyAdapter;
import com.philippabather.properproperties.domain.RentalProperty;
import com.philippabather.properproperties.domain.Role;

import java.util.ArrayList;
import java.util.List;


public class RentalFragment extends Fragment {
    private List<RentalProperty> rentalPropertyList;
    private RentalPropertyAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private long proprietorId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rental, container, false);

        // recibe datos desde los argumentos pasados
//        Bundle bundle = getArguments();
        rentalPropertyList = new ArrayList<>();
        assert getArguments() != null;
        rentalPropertyList = getArguments().getParcelableArrayList(BUNDLE_ARGUMENT_PARCELABLE_LIST_RENTALS);
        proprietorId = getArguments().getLong(INTENT_EXTRA_PROPRIETOR_ID);


        // crea el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview_rental_property_list);
        recyclerView.setHasFixedSize(true);

        // establece el LinearLayoutManager
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // establece el Adapter y bindea al RecyclerView
        adapter = new RentalPropertyAdapter(rentalPropertyList, null, Role.PROPRIETOR, proprietorId);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}