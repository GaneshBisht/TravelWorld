package com.codeanthem.travelworld.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codeanthem.travelworld.R;
import com.codeanthem.travelworld.auth.model.AuthModel;
import com.codeanthem.travelworld.auth.viewmodel.AuthViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    // view initialization
    TextInputLayout tilEmail, tilPassword;
    TextInputEditText etEmail, etPassword;
    MaterialButton btRegister;
    MaterialTextView tvLogin;

    // view model initialization
    AuthViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //vie Initialization
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btRegister = findViewById(R.id.btRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // event handler
        btRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

        // view model initialization
        model = new ViewModelProvider(this).get(AuthViewModel.class);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btRegister:
                register();
                break;
            case R.id.tvLogin:
                finish();
                break;
        }
    }

    private  void register(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        model.signUp(email, password).observe(this, new Observer<AuthModel>() {
            @Override
            public void onChanged(AuthModel authModel) {

                Toast.makeText(RegisterActivity.this, authModel.getMsg(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}