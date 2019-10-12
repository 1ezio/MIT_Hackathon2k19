package com.doc.docrepos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class check_new_patient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_new_patient);
    }

    public void new_pat(View view) {
        Intent i=new Intent(check_new_patient.this,details.class);
        startActivity(i);
    }

    public void already_regis(View view) {
        Intent i=new Intent(check_new_patient.this,appointment_.class);
        startActivity(i);
    }

    public void upld_rep(View view) {
        Intent i=new Intent(check_new_patient.this,test_lab.class);
        startActivity(i);
    }

    public void bjh(View view) {
        Intent i= new Intent (check_new_patient.this, appointment_list.class);
        startActivity(i);
    }
}
