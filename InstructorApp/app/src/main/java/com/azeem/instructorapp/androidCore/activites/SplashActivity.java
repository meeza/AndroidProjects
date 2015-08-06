package com.azeem.instructorapp.androidCore.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.logics.SplashScreenController;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SplashScreenController splashScreenController = new SplashScreenController(this);

        splashScreenController.perform(new SplashScreenController.SplashListener() {
            @Override
            public void onSplashDone(int status) {
                if(status == 0){
                    final Intent intent = new Intent(SplashActivity.this, AuthenticationActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    final Intent intent = new Intent(SplashActivity.this, CoursesListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
