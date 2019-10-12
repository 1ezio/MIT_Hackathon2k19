package com.doc.docrepos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.doc.docrepos.models.clinin_name;
import com.doc.docrepos.models.doc_registration_model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class doc_registration extends AppCompatActivity {
     ImageButton nextpage;
     EditText doc_name;
     EditText doc_email;
     EditText doc_username;
     EditText doc_password;
     private doc_registration_model register_model;
     DatabaseReference reference;
     String str;
     Button btn;
     TextView addmore;
     String ID_r;
    private clinin_name model2;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_registration);
        nextpage=(ImageButton) findViewById(R.id.p_appointment);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(doc_registration.this,rceptionaist_registration.class);
                startActivity(intent);
            }
        });

        btn= (Button)findViewById(R.id.button2);

        str=getIntent().getExtras().getString("Vlaue");
        doc_name=(EditText)findViewById(R.id.docname);
        model2=new clinin_name();
        doc_username=(EditText)findViewById(R.id.docusername);
        doc_email=(EditText)findViewById(R.id.docemail);
        register_model=new doc_registration_model();
        reference= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("Doctor").push();
        doc_password=(EditText)findViewById(R.id.docpassword);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //      reference= FirebaseDatabase.getInstance().getReference("clinic").child(getIntent().getExtras().getString("Vlaue"));


                if(isValidEmailId(doc_email.getText().toString().trim())&&doc_password.length()==4 ){
                    Toast.makeText(getApplicationContext(), "Valid Email Address. or password", Toast.LENGTH_SHORT).show();
                    register_model.setDname(doc_name.getText().toString());
                    register_model.setDemail(doc_email.getText().toString());
                    register_model.setDusername(doc_username.getText().toString());
                    register_model.setDpassword(doc_password.getText().toString());

                    reference.child("name").setValue(register_model.getDname());
                    reference.child("email").setValue(register_model.getDemail());
                    reference.child("username").setValue(register_model.getDusername());
                    reference.child("password").setValue(register_model.getDpassword());
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                }else{
                    Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}
