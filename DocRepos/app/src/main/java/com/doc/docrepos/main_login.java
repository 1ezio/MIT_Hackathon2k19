package com.doc.docrepos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doc.docrepos.models.doc_registration_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class main_login extends AppCompatActivity {
    EditText loginusername, loginpassword;
    DatabaseReference mref, pref,lref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        loginusername=(EditText)findViewById(R.id.login_username);

        loginpassword=(EditText)findViewById(R.id.login_password);

        mref= FirebaseDatabase.getInstance().getReference("clinic");
        pref=FirebaseDatabase.getInstance().getReference("clinic");
        lref=FirebaseDatabase.getInstance().getReference("clinic");
    }

    public void login(View view) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading");

        progressDialog.show();
        final String getusername = loginusername.getText().toString().trim();
        final String getpassword = loginpassword.getText().toString().trim();

        if (!getusername.isEmpty() && !getpassword.isEmpty()) {

            mref.child("clinic 1").child("Doctor").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String username = snapshot.child("username").getValue().toString();
                            String password = snapshot.child("password").getValue().toString();
                            Log.i("Login", username);
                            Log.i("Login", password);

                            if (getusername.equals(username)) {

                                if (getpassword.equals(password)) {

                                    Intent intent = new Intent(main_login.this, pateint_id_input.class);
                                    startActivity(intent);
                                    loginusername.setText("");
                                    loginpassword.setText("");


                                }else {
                                    Toast.makeText(main_login.this, "USER NOT FOUND", Toast.LENGTH_SHORT).show();
                                }

                            }
                            }

                        }
                        progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        final String getusernamep = loginusername.getText().toString();
        final String getpasswordp = loginpassword.getText().toString();
        if (!getusername.isEmpty() && !getpassword.isEmpty()){
            pref.child("clinic 1").child("receptionist").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String usernamep = snapshot.child("username").getValue().toString();
                            String passwordp = snapshot.child("password").getValue().toString();
                            Log.i("Login", usernamep);
                            Log.i("Login", passwordp);

                            if (getusernamep.equals(usernamep)) {

                                if (getpasswordp.equals(passwordp)) {

                                    Intent intent = new Intent(main_login.this, check_new_patient.class);
                                    startActivity(intent);
                                    loginusername.setText("");
                                    loginpassword.setText("");
                                    progressDialog.dismiss();
                                } else {
                                    Toast.makeText(main_login.this, "Password invalid", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }

                            }
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            final String getlusername = loginusername.getText().toString();
            final String getlpassword = loginpassword.getText().toString();

            if (!getlusername.isEmpty() && !getlpassword.isEmpty()) {

                lref.child("clinic 1").child("test_labs").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                String username = dataSnapshot1.child("username").getValue().toString();
                                String password = dataSnapshot1.child("password").getValue().toString();
                                Log.i("Login", username);
                                Log.i("Login", password);

                                if (getlusername.equals(username)) {

                                    if (getlpassword.equals(password)) {

                                        Intent intent = new Intent(main_login.this, test_lab.class);
                                        startActivity(intent);
                                        loginusername.setText("");
                                        loginpassword.setText("");
                                        progressDialog.dismiss();

                                    }

                                }
                            }

                        }




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            }


    }
}
