package com.azeem.instructorapp.logics;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.beans.InstructorDetails;
import com.azeem.instructorapp.parse.ParseHelper;
import com.parse.ParseException;

/**
 * Created by admin on 4/26/2015.
 */
public class AuthenticationController {
    private Context context;
    AuthenticationCallbackListener authenticationCallbackListener;
    SharedPreferences sharedPreferences;
    InstructorDetails instructorDetails;

    private String mobileNumber;


    public AuthenticationController(Context context, AuthenticationCallbackListener listener){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.authentication_data), Context.MODE_PRIVATE);
        authenticationCallbackListener = listener;
    }

    public void performRegistration(InstructorDetails userDetails){
        instructorDetails = userDetails;
        sharedPreferences.edit().putString("school", userDetails.schoolName).apply();
        instructorDetails.mobileNumber = sharedPreferences.getString("phoneNumber", "");
        BackgroundWorker backgroundWorker = new BackgroundWorker();
        backgroundWorker.execute(false);
    }

    public void performLogIn(String number) {
        mobileNumber = number;
        sharedPreferences.edit().putString("phoneNumber", number).apply();
        BackgroundWorker backgroundWorker = new BackgroundWorker();
        backgroundWorker.execute(true);
    }

    public String getSchoolName(){
        return sharedPreferences.getString("school", "XYZ");
    }

    private class BackgroundWorker extends AsyncTask<Boolean, String, Integer> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Boolean... params) {
            if(params[0])
                return doLogin();
            else{
                return doRegister();
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            switch (result){
                case -1:
                    authenticationCallbackListener.onError();
                    break;

                case 0:
                    authenticationCallbackListener.onLoggedIn(true);
                    break;
                case 1:
                    authenticationCallbackListener.onLoggedIn(false);
                    break;
                case 2:
                    authenticationCallbackListener.onRegister(RegistrationResponses.registeredSuccessfully);
                    break;
                case 3:
                    authenticationCallbackListener.onRegister(RegistrationResponses.alreadyHaveAccount);
                    break;
            }
        }
    }

    private int doLogin(){
        ParseHelper parseHelper = ParseHelper.getInstance(context);
        try {
            if(parseHelper.authenticateByDeviceId(mobileNumber, sharedPreferences.getString("deviceId", "123"))){
                sharedPreferences.edit().putBoolean("authenticated", true).apply();
                return 0;
//                registrationListener.onLoggedIn(true);
            }
            else{
                return 1;
//                registrationListener.onLoggedIn(false);
            }
        } catch (ParseException e) {
//            registrationListener.onError();

            e.printStackTrace();
            return -1;
        }
    }

    private int doRegister(){
        ParseHelper parseHelper = ParseHelper.getInstance(context);
        try {
            if(!parseHelper.isPatientAvailable(instructorDetails.mobileNumber)){
                parseHelper.registerNewPatient(instructorDetails, sharedPreferences.getString("deviceId", "123"));
                sharedPreferences.edit().putBoolean("authenticated", true).apply();
                return 2;
            }
            else{
//                registrationListener.onRegister(RegistrationResponses.alreadyHaveAccount);
                return 3;
            }

        } catch (ParseException e) {
//            registrationListener.onError();
            e.printStackTrace();
            return -1;
        }
    }


    public static interface AuthenticationCallbackListener {
        public void onRegister(RegistrationResponses status);
        public void onLoggedIn(boolean status);
        public void onError();
    }

    public static enum RegistrationResponses{
        registeredSuccessfully, alreadyHaveAccount
    }
}
