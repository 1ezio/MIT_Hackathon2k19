package com.doc.docrepos.new_prescription;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.doc.docrepos.R;
import com.doc.docrepos.doc_prescription;
import com.doc.docrepos.pateint_id_input;
import com.doc.docrepos.timer_Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class patient_id_input_2 extends AppCompatActivity {
    EditText idtext;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_id_input_2);
        idtext=(EditText)findViewById(R.id.editText9);
        reference= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("patient");
    }

    public void pid2(View view) {
        final String idi=idtext.getText().toString();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot postsnapshot :dataSnapshot.getChildren()) {
                        String username = postsnapshot.child("ID").getValue().toString();
                        if(idi.equals(username)){
                            Bundle bundle = new Bundle();
                            bundle.putString("edttext", idi);
                            Fragment fragment = new prescription_fragment();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frag, fragment).addToBackStack(null).commit();
                            fragment.setArguments(bundle);
                            /*Intent intent=new Intent(patient_id_input_2.this, doc_prescription.class);
                            startActivity(intent);
                            finish();*/




                        }else
                            Toast.makeText(patient_id_input_2.this, "NOT FOUND", Toast.LENGTH_SHORT).show();

                    }
                }else Toast.makeText(patient_id_input_2.this, "Not FOUND", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
