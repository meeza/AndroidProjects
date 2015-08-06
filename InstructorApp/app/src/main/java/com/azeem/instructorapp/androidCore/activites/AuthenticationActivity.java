package com.azeem.instructorapp.androidCore.activites;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.androidCore.fragments.MobileNumberAuthenticationFragment;
import com.azeem.instructorapp.androidCore.fragments.RegistrationFragment1;
import com.azeem.instructorapp.beans.InstructorDetails;
import com.azeem.instructorapp.logics.AuthenticationController;

/**
 * Created by admin on 4/26/2015.
 */
public class AuthenticationActivity extends BaseActivity implements RegistrationFragment1.RegistrationActivityListener, MobileNumberAuthenticationFragment.MobileNumberFragmentListener{
    AuthenticationController authenticationController;
    String phoneNumber;

    AuthenticationController.AuthenticationCallbackListener authenticationCallbackListener = new AuthenticationController.AuthenticationCallbackListener() {
        @Override
        public void onRegister(AuthenticationController.RegistrationResponses status) {
            switch (status){
                case alreadyHaveAccount:
                    showDialog("Error!", "Already there is an account on this number", "Dismiss");
                    break;
                case registeredSuccessfully:
                    Intent intent = new Intent(AuthenticationActivity.this, CoursesListActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }

        @Override
        public void onLoggedIn(boolean status) {
            if(status){
                Intent intent = new Intent(AuthenticationActivity.this, CoursesListActivity.class);
                startActivity(intent);
                finish();
            }else {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                Fragment currentFragment = RegistrationFragment1.newInstance(AuthenticationActivity.this);
                fragmentTransaction.replace(R.id.fl_registration_fragmentContainer, currentFragment).commit();
            }
        }

        @Override
        public void onError() {
            showDialog("Error!", "Operation unsuccessful", "Dismiss");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        setActionBarDouble("InstructorApp", "Register");

        authenticationController = new AuthenticationController(this, authenticationCallbackListener);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment currentFragment = MobileNumberAuthenticationFragment.newInstance(this);
        fragmentTransaction.replace(R.id.fl_registration_fragmentContainer, currentFragment).commit();
    }


    @Override
    public void onContinue(InstructorDetails details) {
        authenticationController.performRegistration(details);
    }

    @Override
    public void onContinueNumber(String number) {
        authenticationController.performLogIn(number);
    }

    private void showDialog(String title, String msg, String buttonTxt){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(buttonTxt, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }}).show();
    }
}
