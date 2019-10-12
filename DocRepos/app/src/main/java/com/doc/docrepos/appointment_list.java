package com.doc.docrepos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class appointment_list extends AppCompatActivity {
    ListView ap_list;
    ArrayList<String> id_nmae;
    DatabaseReference rf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        ap_list=(ListView)findViewById(R.id.list_view_id);
        id_nmae=new ArrayList<String>();
        rf= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("appointments");
        rf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot post: dataSnapshot.getChildren()){
                        String idname= post.child("name").getValue()+" "+post.child("Time").getValue();
                        id_nmae.add(idname);
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,id_nmae);
                        ap_list.setAdapter(adapter);
                        ap_list.isStackFromBottom();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
