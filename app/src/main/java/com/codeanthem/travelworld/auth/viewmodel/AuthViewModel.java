package com.codeanthem.travelworld.auth.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.codeanthem.travelworld.auth.model.AuthModel;
import com.codeanthem.travelworld.auth.model.AuthRepository;

public class AuthViewModel extends AndroidViewModel {

    AuthRepository repository = AuthRepository.getInstance();

    SharedPreferences preferences = getApplication().getSharedPreferences("app", Context.MODE_PRIVATE);

    public AuthViewModel(@NonNull Application application) {
        super(application);
    }


        public MutableLiveData<AuthModel> signIn(String email,String password){


            MediatorLiveData<AuthModel> liveData = new MediatorLiveData<>();

            liveData.addSource(repository.signIn(email, password), new Observer<AuthModel>() {
                @Override
                public void onChanged(AuthModel authModel) {

                    if(authModel.getSuccess() == AuthRepository.LOGIN_SUCCESS){
                        saveLoginInfo(email);
                    }
                    liveData.setValue(authModel);
                }
            });

            return liveData;



        }

    public MutableLiveData<AuthModel> signUp(String email,String password){


        MediatorLiveData<AuthModel> liveData = new MediatorLiveData<>();

        liveData.addSource(repository.signUp(email, password), new Observer<AuthModel>() {
            @Override
            public void onChanged(AuthModel authModel) {
                liveData.setValue(authModel);
            }
        });

        return liveData;

    }

    public MutableLiveData<AuthModel> forgotPassword(String email){


        MediatorLiveData<AuthModel> liveData = new MediatorLiveData<>();

        liveData.addSource(repository.forgotPassword(email), new Observer<AuthModel>() {
            @Override
            public void onChanged(AuthModel authModel) {
                liveData.setValue(authModel);
            }
        });

        return liveData;

    }

    void saveLoginInfo(String email){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLogin", true);
        editor.putString("email", email);
        editor.apply();

    }





}
