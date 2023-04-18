package com.example.befree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Profile extends Fragment {
    public Fragment_Profile(){}
    public static Fragment_Profile newInstance(){
        return new Fragment_Profile();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_profile,container,false);

    }
}