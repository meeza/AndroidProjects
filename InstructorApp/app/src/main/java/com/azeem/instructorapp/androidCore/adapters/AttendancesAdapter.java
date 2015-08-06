package com.azeem.instructorapp.androidCore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.beans.MyCourse;
import com.azeem.instructorapp.beans.Student;

/**
 * Created by admin on 5/4/2015.
 */
public class AttendancesAdapter extends ArrayAdapter {

    private LayoutInflater inflater;

    Student[] students;

    public AttendancesAdapter(Context context, Student[] students) {
        super(context, R.layout.item_attendance, students);
        inflater = LayoutInflater.from(getContext());
        this.students = students;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_attendance, null);

            viewHolder.background = convertView.findViewById(R.id.ll_topViewItemAttendance);
            viewHolder.rollNumber = (TextView) convertView.findViewById(R.id.tv_rollNumber);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_studentName);
            viewHolder.presentText = (TextView) convertView.findViewById(R.id.tv_present_attendance);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Student student = students[position];
        viewHolder.name.setText(student.name);
        viewHolder.rollNumber.setText(student.rollNumber);

        setBackground(viewHolder, student.present);

        viewHolder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.present = !student.present;
                setBackground(viewHolder, student.present);
            }
        });
        return convertView;
    }

    private void setBackground(ViewHolder viewHolder, boolean status){
        if(status){
            viewHolder.background.setBackgroundColor(getContext().getResources().getColor(R.color.app_green_light));
            viewHolder.presentText.setVisibility(View.VISIBLE);
        }

        else{
            viewHolder.background.setBackgroundColor(getContext().getResources().getColor(R.color.gray_background));
            viewHolder.presentText.setVisibility(View.INVISIBLE);
        }


    }

    private static class ViewHolder{
        TextView name;
        TextView rollNumber;
        View background;
        View presentText;
    }
}
