package com.azeem.instructorapp.androidCore.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.androidCore.adapters.AttendancesAdapter;
import com.azeem.instructorapp.androidCore.adapters.CoursesAdapter;
import com.azeem.instructorapp.beans.MyCourse;
import com.azeem.instructorapp.beans.Student;
import com.azeem.instructorapp.logics.AuthenticationController;

/**
 * Created by admin on 5/4/2015.
 */
public class AttendanceActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list);

        MyCourse myCourse = (MyCourse) getIntent().getSerializableExtra("course");
        if(myCourse != null){
            setActionBarDouble(myCourse.courseName, "Students Attendances");
        }

        ListView listView = (ListView) findViewById(R.id.listView_list_screen);

        String[] studentsNames = {"Sadia Fatima", "Abdullah Ansari", "SUNIL KUMAR MEENA", "Mohammad Shumail", "AADITYA BHARAT KUMAR PANDYA", "Mahreen Fatima", "ABHISHEK ANAND", "ABHISHEK TIWARI",
                "AMAN GUPTA", "AMIT SAXENA", "Rashid Khan"};

        int size = studentsNames.length;
        final Student[] students = new Student[size];
        for(int i = 0 ; i < size; i++){
            Student student = new Student();
            student.name = studentsNames[i];
            student.rollNumber = "20150"+i;
            students[i] = student;
        }

        AttendancesAdapter bundlesAdapter = new AttendancesAdapter(this, students);
        listView.setAdapter(bundlesAdapter);
    }
}
