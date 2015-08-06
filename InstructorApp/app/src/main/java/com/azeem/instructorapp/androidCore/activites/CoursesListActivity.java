package com.azeem.instructorapp.androidCore.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.androidCore.adapters.CoursesAdapter;
import com.azeem.instructorapp.beans.MyCourse;
import com.azeem.instructorapp.logics.AuthenticationController;

/**
 * Created by admin on 5/4/2015.
 */
public class CoursesListActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list);
        AuthenticationController authenticationController = new AuthenticationController(this, null);
        setActionBarDouble("Courses", authenticationController.getSchoolName());

        ListView listView = (ListView) findViewById(R.id.listView_list_screen);

        String[] subjects = {"Mathematics", "Calculus", "Physics", "Science & Technology"};

        final MyCourse[] courses = new MyCourse[4];
        for(int i = 0 ; i < 4; i++){
            MyCourse course = new MyCourse();
            course.courseName = subjects[i%subjects.length];
            course.subTitle = "Standard: "+(i+1);
            course.period = (9 + i) + " O'Clock";
            courses[i] = course;
        }


        final Intent sendingIntent = new Intent(this, CourseDashboardActivity.class);

        CoursesAdapter bundlesAdapter = new CoursesAdapter(this, courses);
        listView.setAdapter(bundlesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendingIntent.putExtra("course", courses[position]);
                startActivity(sendingIntent);
            }
        });
    }
}
