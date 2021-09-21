package com.codeanthem.travelworld.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codeanthem.travelworld.R;
import com.codeanthem.travelworld.auth.model.AuthModel;
import com.codeanthem.travelworld.auth.model.AuthRepository;
import com.codeanthem.travelworld.auth.viewmodel.AuthViewModel;
import com.codeanthem.travelworld.home.view.HomeActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // view declaration
    TextInputLayout tilEmail,tilPassword;
    TextInputEditText etEmail, etPassword;
    MaterialButton btLogin;
    MaterialTextView tvForgotPassword, tvRegister;

    // viewModel Declatration
    AuthViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // view initialization
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvRegister = findViewById(R.id.tvRegister);


        // event handler
        btLogin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

        // view model initialization
        model = new ViewModelProvider(this).get(AuthViewModel.class);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btLogin:
                setBtLogin();
                break;
            case R.id.tvForgotPassword:

                Intent iForgot = new Intent(this, ForgotPasswordActivity.class);
                startActivity(iForgot);

                break;
            case R.id.tvRegister:

                Intent iRegister = new Intent(this, RegisterActivity.class);
                startActivity(iRegister);

                break;
        }

    }

    private void setBtLogin(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        model.signIn(email,password).observe(this, new Observer<AuthModel>() {
            @Override
            public void onChanged(AuthModel authModel) {

                if(authModel.getSuccess() == AuthRepository.LOGIN_SUCCESS){

                    Intent iHome = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(iHome);
                    finish();;

                }

                Toast.makeText(LoginActivity.this, authModel.getMsg(), Toast.LENGTH_SHORT).show();

            }
        });



    }



}