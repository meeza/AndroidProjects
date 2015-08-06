package com.azeem.instructorapp.parse;

import android.content.Context;

import com.azeem.instructorapp.R;
import com.azeem.instructorapp.beans.InstructorDetails;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by admin on 4/25/2015.
 */
public class ParseHelper {

    private static ParseHelper instance;

    public static synchronized ParseHelper getInstance(Context context){
        if (instance == null)
            instance = new ParseHelper(context);
        return instance;
    }

    private ParseHelper(Context context) {
        Parse.enableLocalDatastore(context);
        Parse.initialize(context, context.getString(R.string.application_id), context.getString(R.string.client_key));
    }

    public void registerNewPatient(InstructorDetails userDetails, String deviceId) throws ParseException {
        ParseObject parseObject = new ParseObject("Patients");
        parseObject.put("patientName", userDetails.name);
        parseObject.put("mobileNumber", userDetails.mobileNumber);
        parseObject.put("email", userDetails.email);
        parseObject.put("schoolName", userDetails.schoolName);
        parseObject.put("androidDeviceId", deviceId);
        parseObject.save();
    }

    public boolean isPatientAvailable(String mobileNumber) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Patients");
        query.whereEqualTo("mobileNumber", mobileNumber);
        return query.find().size() > 0;
    }

    public boolean authenticateByDeviceId(String mobileNumber, String deviceId) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Patients");
        query.whereEqualTo("mobileNumber", mobileNumber);
        query.whereEqualTo("androidDeviceId", deviceId);
        return query.find().size() > 0;
    }


}
