package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_PARCELABLE_LIST_RENTALS;

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

/**
 * RecyclerViewRentalFragment - el fragmento que maneja la presentación de imuebles de alquiler en un
 * RecyclerView.
 *
 * @author Philippa Bather
 */
public class RecyclerViewOwnerRentalFragment extends Fragment {
    private List<RentalProperty> rentalPropertyList;
    private RentalPropertyAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view_rental, container, false);

        // recibe datos desde los argumentos pasados
        rentalPropertyList = new ArrayList<>();
        assert getArguments() != null;
        rentalPropertyList = getArguments().getParcelableArrayList(BUNDLE_ARGUMENT_PARCELABLE_LIST_RENTALS);

        // crea el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview_rental_property_list);
        recyclerView.setHasFixedSize(true);

        // establece el LinearLayoutManager
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // establece el Adapter y bindea al RecyclerView
        adapter = new RentalPropertyAdapter(rentalPropertyList, Role.PROPRIETOR);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}