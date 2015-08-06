package com.azeem.instructorapp.androidCore.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.beans.InstructorDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/26/2015.
 */
public class RegistrationFragment1 extends Fragment {
    public static interface RegistrationActivityListener{
        public void onContinue(InstructorDetails details);
    }

    RegistrationActivityListener listener;
    List<String> schools;

    public static Fragment newInstance(RegistrationActivityListener listener) {
        RegistrationFragment1 fragment = new RegistrationFragment1();
        fragment.listener = listener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration_1, container, false);
        schools = new ArrayList<>();

        final Spinner spinner_city = (Spinner) rootView.findViewById(R.id.spinner_schools);
        addSpinnerCity(spinner_city);

        final TextView nameTV = (TextView) rootView.findViewById(R.id.tv_reg_name);
        final TextView emailTV = (TextView) rootView.findViewById(R.id.tv_reg_email);

        rootView.findViewById(R.id.tv_registration1_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstructorDetails instructorDetails = new InstructorDetails();
                instructorDetails.name = nameTV.getText().toString();
                instructorDetails.email = emailTV.getText().toString();
                instructorDetails.schoolName = (String) spinner_city.getSelectedItem();
                if(instructorDetails.name.length() < 1 ||
                        instructorDetails.email.length() < 1){
                    Toast.makeText(getActivity(), "Please complete all fields", Toast.LENGTH_SHORT).show();
                }else {
                    listener.onContinue(instructorDetails);
                }

            }
        });

        return rootView;
    }

    private void addSpinnerCity(Spinner spinner_city)
    {
        // Spinner Drop down elements
        schools.add("Little Flower High School, Abids, Hyderabad");
        schools.add("The Hyderabad Public School, Begumpet, Hyderabad");
        schools.add("Oakridge Internatioal School, Cyberabad, Hyderabad");
        schools.add("Manthan International School");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, schools);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_city.setAdapter(dataAdapter);
    }
}
