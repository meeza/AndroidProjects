package com.azeem.instructorapp.androidCore.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.azeem.instructorapp.R;

import java.util.List;

/**
 * Created by admin on 4/26/2015.
 */
public class MobileNumberAuthenticationFragment extends Fragment{
    public static interface MobileNumberFragmentListener{
        public void onContinueNumber(String number);
    }

    MobileNumberFragmentListener listener;
    List<String> cities;

    public static MobileNumberAuthenticationFragment newInstance(MobileNumberFragmentListener mobileNumberFragmentListener) {
        MobileNumberAuthenticationFragment fragment = new MobileNumberAuthenticationFragment();
        fragment.listener = mobileNumberFragmentListener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authentication, container, false);

        final TextView mobileNumTV = (TextView) rootView.findViewById(R.id.tv_reg_phone);

        rootView.findViewById(R.id.tv_registration1_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNum = mobileNumTV.getText().toString();
                if(mobileNum.length() < 1){
                    Toast.makeText(getActivity(), "Please complete all fields", Toast.LENGTH_SHORT).show();
                }else {
                    listener.onContinueNumber(mobileNum);
                }

            }
        });

        return rootView;
    }
}
