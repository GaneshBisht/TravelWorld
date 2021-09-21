package com.codeanthem.travelworld.intro.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codeanthem.travelworld.R;
import com.codeanthem.travelworld.auth.view.LoginActivity;
import com.codeanthem.travelworld.home.view.HomeActivity;
import com.codeanthem.travelworld.intro.viewmodel.IntroViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import me.relex.circleindicator.CircleIndicator3;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    // view declaration
    ViewPager2 vpIntro;
    CircleIndicator3 circleIndicator3;
    MaterialButton btContinue;
    MaterialTextView tvSkip;

    // adapter
    IntroViewPagerAdapter adapter;

    // view model
    IntroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // view initialisation
        vpIntro = findViewById(R.id.vpIntro);
        circleIndicator3 = findViewById(R.id.circleIndicator);
        btContinue = findViewById(R.id.btContinue);
        tvSkip = findViewById(R.id.tvSkip);

        // view model initialisation
        viewModel = new ViewModelProvider(this).get(IntroViewModel.class);

        // Event listener
        btContinue.setOnClickListener(this);
        tvSkip.setOnClickListener(this);

        // Set Adapter
        adapter = new IntroViewPagerAdapter( this, viewModel.getAllIntro());
        vpIntro.setAdapter(adapter);

        // CircleIndicator config
        circleIndicator3.setViewPager(vpIntro);

        // chech for login
        if(viewModel.isLogin()){
            Intent iHome = new Intent(this, HomeActivity.class);
            startActivity(iHome);
            finish();
        }
        else if(viewModel.isIntroSeen()){
            Intent iHome = new Intent(this, LoginActivity.class);
            startActivity(iHome);
            finish();
        }



    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.liveDataNext.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent iNext = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(iNext);
                finish();
            }
        });

        vpIntro.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                viewModel.setCurrentIntro(position);
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btContinue:
                int nextItem = viewModel.nextIntro();
                if(nextItem != -1){
                    vpIntro.setCurrentItem(nextItem);
                }
                break;
            case R.id.tvSkip:
                viewModel.nextActivity();
                break;
        }

    }
}