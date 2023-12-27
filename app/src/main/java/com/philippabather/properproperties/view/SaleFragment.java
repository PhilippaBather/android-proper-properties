package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_PARCELABLE_LIST_SALES;
import static com.philippabather.properproperties.constants.Constants.INTENT_EXTRA_PROPRIETOR_ID;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.adapter.SalePropertyAdapter;
import com.philippabather.properproperties.domain.Role;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.ArrayList;
import java.util.List;

public class SaleFragment extends Fragment {

    private List<SaleProperty> salePropertyList;
    private SalePropertyAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private long proprietorId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view_sale, container, false);

        // recibe datos desde los argumentos pasados
        assert getArguments() != null;
        salePropertyList = new ArrayList<>();
        salePropertyList = getArguments().getParcelableArrayList(BUNDLE_ARGUMENT_PARCELABLE_LIST_SALES);
        proprietorId = getArguments().getLong(INTENT_EXTRA_PROPRIETOR_ID);

        // crea el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview_sale_property_list);
        recyclerView.setHasFixedSize(true);

        // establece el LinearLayoutManager
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // establece el Adapter y bindea al RecyclerView
        adapter = new SalePropertyAdapter(salePropertyList, null, Role.PROPRIETOR, proprietorId);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}