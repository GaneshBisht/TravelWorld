package com.codeanthem.travelworld.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codeanthem.travelworld.home.model.DestinationModel;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    public static final int CATEGORY_SUMMER_DEALS = 1;
    public static final int CATEGORY_REDEEM_POINTS =2;

    List<DestinationModel> listSummerDeals, listRedeemPoints;

    public SearchViewModel(@NonNull Application application) {
        super(application);

        initializeList();
    }

    private void initializeList(){
        listSummerDeals = new ArrayList<>();
        listSummerDeals.add(new DestinationModel(1,CATEGORY_SUMMER_DEALS));
        listSummerDeals.add(new DestinationModel(1,CATEGORY_SUMMER_DEALS));
        listSummerDeals.add(new DestinationModel(1,CATEGORY_SUMMER_DEALS));
        listSummerDeals.add(new DestinationModel(1,CATEGORY_SUMMER_DEALS));
        listSummerDeals.add(new DestinationModel(1,CATEGORY_SUMMER_DEALS));

        listRedeemPoints = new ArrayList<>();
        listRedeemPoints.add(new DestinationModel(1, CATEGORY_REDEEM_POINTS));
        listRedeemPoints.add(new DestinationModel(1, CATEGORY_REDEEM_POINTS));
        listRedeemPoints.add(new DestinationModel(1, CATEGORY_REDEEM_POINTS));
        listRedeemPoints.add(new DestinationModel(1, CATEGORY_REDEEM_POINTS));
        listRedeemPoints.add(new DestinationModel(1, CATEGORY_REDEEM_POINTS));
    }


    public MutableLiveData<List<DestinationModel>>getSummerDeals(){

        MutableLiveData<List<DestinationModel>> liveData = new MutableLiveData<>();

        liveData.setValue(listSummerDeals);

        return liveData;
    }

    public MutableLiveData<List<DestinationModel>>getRedeemPoints(){

        MutableLiveData<List<DestinationModel>> liveData = new MutableLiveData<>();

        liveData.setValue(listRedeemPoints);

        return liveData;
    }
}
