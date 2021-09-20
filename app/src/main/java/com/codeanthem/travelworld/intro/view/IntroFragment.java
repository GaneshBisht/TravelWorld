package com.codeanthem.travelworld.intro.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codeanthem.travelworld.R;
import com.codeanthem.travelworld.intro.model.IntroModel;
import com.google.android.material.textview.MaterialTextView;

public class IntroFragment extends Fragment {

    // view initialisation
    ImageView ivMain;
    MaterialTextView tvTitle;
    MaterialTextView tvDesc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_intro, null);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // view initialisation
        ivMain = view.findViewById(R.id.ivMain);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvDesc = view. findViewById(R.id.tvDesc);

        setItemValue();



    }

    private void setItemValue(){

        Bundle bundle = getArguments();
        IntroModel introModel = (IntroModel) bundle.getSerializable("intro");
        ivMain.setImageResource(introModel.getImageId());
        tvTitle.setText(introModel.getTitle());
        tvDesc.setText(introModel.getDescription());
    }
}
