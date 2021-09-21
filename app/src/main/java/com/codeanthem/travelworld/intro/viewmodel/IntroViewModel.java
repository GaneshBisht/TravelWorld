package com.codeanthem.travelworld.intro.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.codeanthem.travelworld.R;
import com.codeanthem.travelworld.intro.model.IntroModel;

import java.util.ArrayList;
import java.util.List;

public class IntroViewModel extends AndroidViewModel {

    List<IntroModel> listIntro;

    int currentIntro = 0;

    SharedPreferences sp = getApplication().getSharedPreferences("app", Context.MODE_PRIVATE);

    public MutableLiveData<Boolean> liveDataNext = new MutableLiveData<>();

    public IntroViewModel(@NonNull Application application) {
        super(application);

        initialiseList();

    }

    // initialise intro list
    private  void initialiseList(){

        listIntro = new ArrayList<>();
        listIntro.add(new IntroModel(0,"Welcome to your travelWorld!","Choose from over million properties worldwide via our innovative App - anytime, anywhere", R.drawable.images_background_intro_slide_1));
        listIntro.add(new IntroModel(1,"Better than best price","We can offer better prices due to our closed community and have no booking or credit card fees", R.drawable.images_background_intro_slide_2));
        listIntro.add(new IntroModel(2,"Book & travel easily","Book in 3 easy steps, get instant confirmation and have your booking details with you at all times", R.drawable.images_background_intro_slide_3));
        listIntro.add(new IntroModel(3,"Benefit even more","When registering, select \"myWorld\" and receive Cashback as well as Shopping Points with every booking made ", R.drawable.images_background_intro_slide_4));

    }

    // fetch all intro list
    public List<IntroModel> getAllIntro(){
        return listIntro;
    }

    // update current intro
    public void setCurrentIntro(int currentIntro){
        this.currentIntro = currentIntro;
    }

    public int nextIntro(){
        if(currentIntro< listIntro.size()-1){
            return ++currentIntro;
        }
        else {
            nextActivity();
            return  -1;
        }

    }

    public  void nextActivity(){
        saveIntroInfo();
        liveDataNext.setValue(true);

    }

    void saveIntroInfo(){

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isSeen", true);
        editor.apply();

    }

    public boolean isLogin(){
       return sp.getBoolean("isLogin", false);

    }

    public boolean isIntroSeen(){
        return  sp.getBoolean("isSeen", false);
    }



}
