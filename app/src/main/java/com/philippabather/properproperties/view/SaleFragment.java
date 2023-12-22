package com.philippabather.properproperties.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.adapter.SalePropertyAdapter;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.ArrayList;
import java.util.List;

public class SaleFragment extends Fragment {

    private List<SaleProperty> salePropertyList;
    private SalePropertyAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);

        // recibe datos desde los argumentos pasados
        Bundle bundle = getArguments();
        assert getArguments() != null;
        salePropertyList = new ArrayList<>();
        salePropertyList = getArguments().getParcelableArrayList("sales");

        // crea el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview_sale_property_list);
        recyclerView.setHasFixedSize(true);

        // establece el LinearLayoutManager
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // establece el Adapter y bindea al RecyclerView
        adapter = new SalePropertyAdapter(salePropertyList, null);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}