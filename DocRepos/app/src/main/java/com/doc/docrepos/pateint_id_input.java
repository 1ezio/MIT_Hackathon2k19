package com.doc.docrepos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pateint_id_input extends AppCompatActivity {
    DatabaseReference reference;
     EditText  input_patient_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pateint_id_input);
        input_patient_id=(EditText) findViewById(R.id.editText);
        reference= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("patient");
    }

    public void enter(View view) {
        final String getid=input_patient_id.getText().toString();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot postsnapshot :dataSnapshot.getChildren()) {
                        String username = postsnapshot.child("ID").getValue().toString();
                            if(getid.equals(username)){
                                Intent intent=new Intent(pateint_id_input.this, doc_prescription.class);
                                intent.putExtra("Vaue",getid);
                                startActivity(intent);
                                finish();
                            }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(pateint_id_input.this,login.class);
        startActivity(i);
        super.onBackPressed();
    }

    public void view_pres(View view) {
        Intent i=new Intent(pateint_id_input.this,f_report.class);
        startActivity(i);

    }
}
