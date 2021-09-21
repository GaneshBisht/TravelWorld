package com.codeanthem.travelworld.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    // view declaration
    TextInputLayout tilEmail;
    TextInputEditText etEmail;
    MaterialButton btForgotPassword;

    AuthViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // view Initialization
        tilEmail = findViewById(R.id.tilEmail);
        etEmail = findViewById(R.id.etEmail);
        btForgotPassword = findViewById(R.id.btForgotPassword);

        // Event handler
        btForgotPassword.setOnClickListener(this);

        model = new ViewModelProvider(this).get(AuthViewModel.class);

    }

    @Override
    public void onClick(View view) {
        String email = etEmail.getText().toString();
        model.forgotPassword(email).observe(this, new Observer<AuthModel>() {
            @Override
            public void onChanged(AuthModel authModel) {
                Toast.makeText(ForgotPasswordActivity.this, authModel.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}