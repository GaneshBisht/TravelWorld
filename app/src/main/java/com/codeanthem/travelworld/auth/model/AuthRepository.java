package com.codeanthem.travelworld.auth.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;

public class AuthRepository {

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_CREDENTIAL_INVALID = 0;
    public static final int LOGIN_FAILED = -1;

    public static final int SIGNUP_SUCCESS = 1;
    public static final int SIGNUP_USER_ALREADY_EXISTS = 0;
    public static final int SIGNUP_FAILED = -1;

    public static final int FORGOT_PASSWORD_SUCCESSFULLY = 1;
    public static final int FORGOT_PASSWORD_USER_NOT_EXIST = 0;
    public static final int FORGOT_PASSWORD_FAILED = -1;


    public static AuthRepository repository;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public static AuthRepository getInstance(){
        if(repository == null){
            repository = new AuthRepository();
        }

        return repository;
    }

    public MutableLiveData<AuthModel> signIn(String email, String password){

        MutableLiveData<AuthModel> liveData = new MutableLiveData<>();

        firestore.collection("users")
                .whereEqualTo("email",email)
                .whereEqualTo("password",password)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();

                        if(documents.size() > 0){
                            // success
                            liveData.setValue(new AuthModel(LOGIN_SUCCESS,"Login Successful"));
                        } else {
                            // credential error
                            liveData.setValue(new AuthModel(LOGIN_CREDENTIAL_INVALID,"Email or Password Incorrect"));
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        liveData.setValue(new AuthModel(LOGIN_FAILED, "Login Error"));


                    }
                });
        return liveData;

    }

    public MutableLiveData<AuthModel> signUp(String email, String password){

        MutableLiveData<AuthModel> liveData = new MutableLiveData<>();

        firestore.collection("users").whereEqualTo("email",email)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();

                        if(docs.size() > 0){
                            // user already exists
                            liveData.setValue(new AuthModel(SIGNUP_USER_ALREADY_EXISTS,"User Already Exists"));
                        }
                        else {
                            // sign up process
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("email", email);
                            map.put("passowrd", password);
                            firestore.collection("users").document().set(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            liveData.setValue(new AuthModel(SIGNUP_SUCCESS,"Signup Successful"));
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            liveData.setValue(new AuthModel(SIGNUP_FAILED, "Signup Failed"));
                                        }
                                    });
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        liveData.setValue(new AuthModel(SIGNUP_FAILED, "Signup Failed"));
                    }
                });

        return liveData;

    }

    public MutableLiveData<AuthModel> forgotPassword(String email){

        MutableLiveData<AuthModel> liveData = new MutableLiveData<>();

        firestore.collection("users").whereEqualTo("email", email)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                        if(docs.size()>0){
                            // user exists
                            liveData.setValue(new AuthModel(FORGOT_PASSWORD_SUCCESSFULLY, "Email Sent Successful"));
                        }
                        else{
                            // user doesn't exists
                            liveData.setValue(new AuthModel(FORGOT_PASSWORD_USER_NOT_EXIST, "User does not exist"));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        liveData.setValue(new AuthModel(FORGOT_PASSWORD_FAILED, "Unable to send email"));
                    }
                });
        return liveData;
    }


}
