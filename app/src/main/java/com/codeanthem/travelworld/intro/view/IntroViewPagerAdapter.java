package com.codeanthem.travelworld.intro.view;

import android.icu.text.Transliterator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.codeanthem.travelworld.intro.model.IntroModel;

import java.util.List;

public class IntroViewPagerAdapter extends FragmentStateAdapter {

    List<IntroModel> list;

    public IntroViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<IntroModel> list) {
        super(fragmentActivity);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

       IntroFragment fragment = new IntroFragment();

       Bundle bundle = new Bundle();
       bundle.putSerializable("intro", list.get(position));
       fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
