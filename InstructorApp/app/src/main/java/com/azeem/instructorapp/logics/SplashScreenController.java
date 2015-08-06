package com.azeem.instructorapp.logics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Settings;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.parse.ParseHelper;

/**
 * Created by admin on 4/25/2015.
 */
public class SplashScreenController {
    private Context context;
    SplashListener splashListener;
    SharedPreferences sharedPreferences;

    public SplashScreenController(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.authentication_data), Context.MODE_PRIVATE);
    }

    public void perform(SplashListener listener){
        splashListener = listener;
        BackgroundWorker backgroundWorker = new BackgroundWorker();
        backgroundWorker.execute();
    }

    private class BackgroundWorker extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
//            ParseHelper.getInstance(context);
            if(!sharedPreferences.contains("deviceId"))
                sharedPreferences.edit().putString("deviceId",
                        Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)).apply();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(sharedPreferences.getBoolean("authenticated", false)){
                splashListener.onSplashDone(1);
            }else {
                splashListener.onSplashDone(0);
            }
        }
    }


    public static interface SplashListener{
        public void onSplashDone(int status);
    }

}
