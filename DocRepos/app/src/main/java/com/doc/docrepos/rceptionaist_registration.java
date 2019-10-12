package com.doc.docrepos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.doc.docrepos.models.rec_reg_model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class rceptionaist_registration extends AppCompatActivity {
    EditText recusername, recpassword;
    DatabaseReference ref;
    String string;
    private rec_reg_model rec_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rceptionaist_registration);
        recusername=(EditText)findViewById(R.id.rec_username);
        recpassword=(EditText)findViewById(R.id.rec_password);
        rec_model=new rec_reg_model();
        SharedPreferences login = getSharedPreferences("LOGIN", 0);
        String user = login.getString("user", "0");

        ref= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("receptionist").push();

    }
    public void save(View view) {
        if(recpassword.length()==4){
            rec_model.setRecuser(recusername.getText().toString());
            rec_model.setRecpass(recpassword.getText().toString());

            ref.child("username").setValue(rec_model.getRecuser());
            ref.child("password").setValue(rec_model.getRecpass());
            finish();
            overridePendingTransition( 0, 0);
            startActivity(getIntent());
            overridePendingTransition( 0, 0);
        }else Toast.makeText(this, "INVALID PASSWORD", Toast.LENGTH_SHORT).show();



    }

    public void btn(View view) {
        Intent intent=new Intent(rceptionaist_registration.this,testlab_registration.class);
        startActivity(intent);
    }
}
