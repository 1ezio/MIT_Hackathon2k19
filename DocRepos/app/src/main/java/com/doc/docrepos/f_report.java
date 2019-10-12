package com.doc.docrepos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class f_report extends AppCompatActivity {
    EditText fr_id;
    TextView fr_name, fr_age,fr_weight,fr_gender, fr_disease, fr_medicine,fr_chronic;
    TextView tname,tage,tchro,tdis, tmedi,tweight,tgender;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_report);
        fr_id=(EditText)findViewById(R.id.fr_id_id);

        fr_name=(TextView)findViewById(R.id.fr_name_id);
        fr_age=(TextView)findViewById(R.id.fr_age_id);
        fr_gender=(TextView)findViewById(R.id.fr_gender_id);
        fr_weight=(TextView)findViewById(R.id.fr_weight_id);
        fr_disease=(TextView)findViewById(R.id.fr_dis_id);
        fr_chronic=(TextView)findViewById(R.id.fr_chronic_id);
        fr_medicine=(TextView)findViewById(R.id.fr_med_id);

        tname=(TextView)findViewById(R.id.textView28);
        tage=(TextView)findViewById(R.id.textView32);
        tchro=(TextView)findViewById(R.id.textView35);
        tdis=(TextView)findViewById(R.id.textView36);
        tmedi=(TextView)findViewById(R.id.textView37);
        tgender=(TextView)findViewById(R.id.textView34);
        tweight=(TextView)findViewById(R.id.textView33);


        data= FirebaseDatabase.getInstance().getReference("patient_record");

    }

    public void view_repo(View view) {
        final String chk_id=fr_id.getText().toString();
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot postsnapshot1 : dataSnapshot.getChildren()) {
                        String id = postsnapshot1.child("id").getValue().toString();
                        if (chk_id.equals(id)) {
                            Toast.makeText(f_report.this, "PATEINT EXISTS", Toast.LENGTH_SHORT).show();

                            fr_name.setVisibility(View.VISIBLE);
                            fr_age.setVisibility(View.VISIBLE);
                            fr_weight.setVisibility(View.VISIBLE);
                            fr_chronic.setVisibility(View.VISIBLE);
                            fr_medicine.setVisibility(View.VISIBLE);
                            fr_disease.setVisibility(View.VISIBLE);
                            fr_gender.setVisibility(View.VISIBLE);

                            tname.setVisibility(View.VISIBLE);
                            tage.setVisibility(View.VISIBLE);
                            tweight.setVisibility(View.VISIBLE);
                            tchro.setVisibility(View.VISIBLE);
                            tmedi.setVisibility(View.VISIBLE);
                            tgender.setVisibility(View.VISIBLE);
                            tdis.setVisibility(View.VISIBLE);



                                fr_name.setText(postsnapshot1.child("name").getValue().toString());
                            fr_age.setText(postsnapshot1.child("age").getValue().toString());
                            fr_chronic.setText(postsnapshot1.child("chronic").getValue().toString());
                            fr_gender.setText(postsnapshot1.child("gender").getValue().toString());
                            String a=postsnapshot1.child("medicine").getValue().toString()+ postsnapshot1.child("medicine2").getValue().toString();

                            if (a.contains(" ")){
                                a.replaceAll(a,"\n");
                            }
                            fr_medicine.setText(a);
                            fr_weight.setText(postsnapshot1.child("weight").getValue().toString());
                            fr_disease.setText(postsnapshot1.child("disease").getValue().toString());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void done(View view) {
        Intent i = new Intent(f_report.this,pateint_id_input.class);
        startActivity(i);
    }
}
