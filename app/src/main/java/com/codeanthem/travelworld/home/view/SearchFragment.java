package com.codeanthem.travelworld.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codeanthem.travelworld.R;
import com.codeanthem.travelworld.home.model.DestinationModel;
import com.codeanthem.travelworld.home.viewmodel.SearchViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class SearchFragment extends Fragment {

    // view declaration
    MaterialButton btHotel, btFlight;
    RecyclerView rvSummerDeals, rvRedeemPoints, rvAccommodationWorld, rvEliteWorld;

    // view model
    SearchViewModel model;

    // adaptors
    DestinationAdaptor summerDealsAdapter, redeemPointsAdapter;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, null);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btHotel = view.findViewById(R.id.btHotel);
        btFlight = view.findViewById(R.id.btFlight);
        rvSummerDeals = view.findViewById(R.id.rvSummerDeals);
        rvRedeemPoints = view.findViewById(R.id.rvRedeemPoints);
        rvAccommodationWorld = view.findViewById(R.id.rvAccommodationWorld);
        rvEliteWorld = view.findViewById(R.id.rvEliteWorld);

        // layout mangaer
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSummerDeals.setLayoutManager(manager1);

        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvRedeemPoints.setLayoutManager(manager2);

        // view model initialization
        model = new ViewModelProvider(this).get(SearchViewModel.class);


    }

    @Override
    public void onResume() {
        super.onResume();

        model.getSummerDeals().observe(this, new Observer<List<DestinationModel>>() {
            @Override
            public void onChanged(List<DestinationModel> list) {

                summerDealsAdapter = new DestinationAdaptor(list);
                rvSummerDeals.setAdapter(summerDealsAdapter);

            }
        });

        model.getRedeemPoints().observe(this, new Observer<List<DestinationModel>>() {
            @Override
            public void onChanged(List<DestinationModel> list) {

                redeemPointsAdapter = new DestinationAdaptor(list);
                rvRedeemPoints.setAdapter(redeemPointsAdapter);

            }
        });

    }
}
