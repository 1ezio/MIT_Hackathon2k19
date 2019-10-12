package com.doc.docrepos;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doc.docrepos.models.appointment;
import com.doc.docrepos.models.patient_detail_model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class details extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText pname, page, pchrodisease, pweight;
    TextView pid;
    Spinner pspinner;
    DatabaseReference preference,  name_reference;
    String gender;
    TextView appointment_time;
    appointment model;
    private patient_detail_model pmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        pid=(TextView)findViewById(R.id.p_id);
        pmodel=new patient_detail_model();
        model=new appointment();
        Long timestamp=System.currentTimeMillis()/1000;
        String currenttime=timestamp.toString();
        pid.setText(currenttime);
        pname=(EditText)findViewById(R.id.p_name);
        page=(EditText)findViewById(R.id.p_age);
        pchrodisease=(EditText)findViewById(R.id.p_chrodisease);
        pweight=(EditText)findViewById(R.id.p_wieght);

        appointment_time=(TextView) findViewById(R.id.textView12);
        name_reference=FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("appointments").push();

        List<String> gender=new ArrayList<>();
        gender.add("");
        gender.add("Male");
        gender.add("Female");
        gender.add("others");

        pspinner=(Spinner)findViewById(R.id.p_gender);
        ArrayAdapter<String> vdataAdapter = new ArrayAdapter<String>(details.this, android.R.layout.simple_spinner_item, gender);
        vdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        pspinner.setAdapter(vdataAdapter);
        pspinner.setOnItemSelectedListener(this);
        preference= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("patient").child(pid.getText().toString());


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner vspin2 = (Spinner) parent;
        if (vspin2.getId() == R.id.p_gender) {
            if (position == 1) {
                gender = "Male";
            } else if (position == 2) {
                gender = "Female";
            } else if (position == 3) {
                gender = "others";
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void psave(View view) {
        if(Integer.parseInt(page.getText().toString())<=115){
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

            pmodel.setName(pname.getText().toString());
            pmodel.setAge(page.getText().toString());
            pmodel.setChrodiseases(pchrodisease.getText().toString());
            pmodel.setId(pid.getText().toString());
            pmodel.setGender(gender);
            pmodel.setWeight(pweight.getText().toString());
            model.setAppointments(appointment_time.getText().toString());


            SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
            String format = s.format(new Date());

            pmodel.setDate(format);
            preference.child("date").setValue(pmodel.getDate());

            name_reference.child("Time").setValue(model.getAppointments());

            name_reference.child("name").setValue(pmodel.getName());
            preference.child("ID").setValue(pmodel.getId());
            preference.child("NAME").setValue(pmodel.getName());
            preference.child("AGE").setValue(pmodel.getAge());
            preference.child("GENDER").setValue(pmodel.getGender());
            preference.child("WEIGHT").setValue(pmodel.getWeight());
            preference.child("CDISEASES").setValue(pmodel.getChrodiseases());



        }else {
            Toast.makeText(this, "Enter Valid Age", Toast.LENGTH_SHORT).show();
        }




    }

    public void appointment(View view) {
        appointment_time.setVisibility(View.VISIBLE);
        DialogFragment newFragment = new timer_Fragment();
        newFragment.show(getSupportFragmentManager(),"TimePicker");
    }

    public void upload_report(View view) {
        Intent intent= new Intent(details.this,test_lab.class);
        startActivity(intent);

    }
}
