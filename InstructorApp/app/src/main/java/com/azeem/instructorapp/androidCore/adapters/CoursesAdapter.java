package com.azeem.instructorapp.androidCore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.beans.MyCourse;


public class CoursesAdapter extends ArrayAdapter {

    private LayoutInflater inflater;

    MyCourse[] bundles;

    public CoursesAdapter(Context context, MyCourse[] bundles) {
        super(context, R.layout.iteam_course, bundles);
        inflater = LayoutInflater.from(getContext());
        this.bundles = bundles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.iteam_course, null);

            viewHolder.courseName = (TextView) convertView.findViewById(R.id.tv_titleCourseItem);
            viewHolder.subTitle = (TextView) convertView.findViewById(R.id.tv_subTitleCourseItem);
            viewHolder.period = (TextView) convertView.findViewById(R.id.tv_timingsCourseItem);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MyCourse bundle = bundles[position];
        viewHolder.courseName.setText(bundle.courseName);
        viewHolder.subTitle.setText(bundle.subTitle);
        viewHolder.period.setText(bundle.period);
        return convertView;
    }

    private static class ViewHolder{
        TextView courseName;
        TextView subTitle;
        TextView period;

    }
}
