package com.doc.docrepos;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.doc.docrepos.models.appointment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class timer_Fragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    DatabaseReference databaseReference;
    appointment model;


    public timer_Fragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView tv = (TextView) getActivity().findViewById(R.id.textView12);
        TextView tv2=(TextView)getActivity().findViewById(R.id.ar_time_id);
        model=new appointment();
        databaseReference= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("appointments").push();
        //Set a message for user
        // Display the user changed time on TextView
        tv.setText(tv.getText()+ String.valueOf(hourOfDay)+":"+String.valueOf(minute));
//        tv2.setText(tv2.getText()+ String.valueOf(hourOfDay)+":"+String.valueOf(minute));



    }
}
