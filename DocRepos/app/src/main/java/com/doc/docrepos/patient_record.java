package com.doc.docrepos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class patient_record extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    EditText edt;
    List<String> reports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_record);
        listView=(ListView)findViewById(R.id.l_id);
        reports=new ArrayList<String>();
        edt=(EditText)findViewById(R.id.editText10);


    }

    public void zdaeteayh(View view) {
        final String chk_id=edt.getText().toString();
        databaseReference= FirebaseDatabase.getInstance().getReference("patient_record");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot post :dataSnapshot.getChildren()){
                        String id = post.getKey();
                        if (chk_id.equals(id)) {
                            Toast.makeText(patient_record.this, "PATEINT EXISTS", Toast.LENGTH_SHORT).show();
                            reports.add(post.child("date").getValue().toString());


                        }
                    }
                    ArrayAdapter<String> reportada=new ArrayAdapter<String>(patient_record.this,android.R.layout.simple_list_item_1,reports);
                    reportada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    listView.setAdapter(reportada);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
