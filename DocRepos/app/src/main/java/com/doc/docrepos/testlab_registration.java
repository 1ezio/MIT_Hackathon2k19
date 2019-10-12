package com.doc.docrepos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.doc.docrepos.models.test_lab_model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class testlab_registration extends AppCompatActivity {
    EditText test_labusename;
    EditText test_labpassword;
    DatabaseReference ref;
    private test_lab_model labModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlab_registration);
        test_labusename=(EditText)findViewById(R.id.test_username);
        test_labpassword=(EditText)findViewById(R.id.test_password);

        labModel= new test_lab_model();

        SharedPreferences login = getSharedPreferences("LOGIN", 0);
        String user = login.getString("user", "0");

        ref= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("test_labs").push();


    }

    public void lab_save(View view) {
        labModel.setLabusername(test_labusename.getText().toString());
        labModel.setLabpassword(test_labpassword.getText().toString());

        ref.child("username").setValue(labModel.getLabusername());
        ref.child("password").setValue(labModel.getLabpassword());
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);

    }

    public void end(View view) {
        if (test_labpassword.length()==4){
            labModel.setLabusername(test_labusename.getText().toString());
            labModel.setLabpassword(test_labpassword.getText().toString());

            ref.child("username").setValue(labModel.getLabusername());
            ref.child("password").setValue(labModel.getLabpassword());

            Intent i=new Intent(testlab_registration.this,login.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "PASSWORD SHOULD BE OF 4 DIGIT", Toast.LENGTH_LONG).show();
        }

    }
}
