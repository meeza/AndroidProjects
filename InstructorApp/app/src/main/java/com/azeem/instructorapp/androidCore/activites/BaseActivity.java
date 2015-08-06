package com.azeem.instructorapp.androidCore.activites;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.azeem.instructorapp.R;

/**
 * Created by admin on 5/4/2015.
 */
public class BaseActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setActionBarDouble(String title, String subtitle){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv_actionBarTitle = (TextView) findViewById(R.id.tv_actionBarTitle);
        tv_actionBarTitle.setText(title);

        TextView tv_actionBarSubTitle = (TextView) findViewById(R.id.tv_actionBarSubTitle);
        tv_actionBarSubTitle.setText(subtitle);
    }




}
