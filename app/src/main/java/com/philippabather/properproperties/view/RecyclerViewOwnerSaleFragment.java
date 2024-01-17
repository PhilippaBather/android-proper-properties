package com.philippabather.properproperties.view;

import static com.philippabather.properproperties.constants.Constants.BUNDLE_ARGUMENT_PARCELABLE_LIST_SALES;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.philippabather.properproperties.R;
import com.philippabather.properproperties.adapter.SalePropertyAdapter;
import com.philippabather.properproperties.domain.Role;
import com.philippabather.properproperties.domain.SaleProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerViewSaleFragment - el fragmento que maneja la presentación de imuebles para vender en un
 * RecyclerView.
 *
 * @author Philippa Bather
 */
public class RecyclerViewOwnerSaleFragment extends Fragment {

    private List<SaleProperty> salePropertyList;
    private SalePropertyAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view_sale, container, false);

        // recibe datos desde los argumentos pasados
        assert getArguments() != null;
        salePropertyList = new ArrayList<>();
        salePropertyList = getArguments().getParcelableArrayList(BUNDLE_ARGUMENT_PARCELABLE_LIST_SALES);

        // crea el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview_sale_property_list);
        recyclerView.setHasFixedSize(true);

        // establece el LinearLayoutManager
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // establece el Adapter y bindea al RecyclerView
        adapter = new SalePropertyAdapter(salePropertyList, Role.PROPRIETOR);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}