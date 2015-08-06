package com.azeem.instructorapp.androidCore.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.beans.MyCourse;
import com.azeem.instructorapp.logics.AuthenticationController;

public class CourseDashboardActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_dashboard);
        final MyCourse myCourse = (MyCourse) getIntent().getSerializableExtra("course");
        if(myCourse != null){
            setActionBarDouble(myCourse.courseName, "Course Dashboard");
        }

        findViewById(R.id.ll_attendances_courseDashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDashboardActivity.this, AttendanceActivity.class);
                intent.putExtra("course", myCourse);
                startActivity(intent);
            }
        });

    }
}
