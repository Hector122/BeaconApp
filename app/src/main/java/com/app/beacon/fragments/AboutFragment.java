package com.app.beacon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.beacon.R;
import com.app.beacon.activists.ChangePasswordActivity;


/**
 * Fragment only use to show information about the application.
 * Created by Hector_2 on 2/2/2016.
 */
public class AboutFragment extends Fragment implements View.OnClickListener{
    RelativeLayout mChangePasswordViewContainer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_user_profile, container,false);

        initializerVariables(view);

        return view;
    }

   private void initializerVariables(View view){
       mChangePasswordViewContainer = (RelativeLayout) view.findViewById(R.id.change_password_view_container);
       mChangePasswordViewContainer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
        startActivityForResult(intent, 5);
    }
}

