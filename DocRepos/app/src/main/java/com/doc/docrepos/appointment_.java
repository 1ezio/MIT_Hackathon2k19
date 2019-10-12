package com.doc.docrepos;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doc.docrepos.models.appointment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class appointment_ extends AppCompatActivity {
    TextView ar_id_chk;
    TextView ar_time;
    DatabaseReference data,data2;
    appointment model1;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_);
        btn=(Button)findViewById(R.id.ar_appointment_id);
        ar_time=(TextView)findViewById(R.id.ar_time_id);
        model1=new appointment();
        ar_id_chk=(TextView)findViewById(R.id.ar_id);
        data=FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("appointments").push();
        data2=FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("patient");
    }

    public void ar_app(View view) {

        DialogFragment newFragment = new timer_frag_2();
        newFragment.show(getSupportFragmentManager(),"TimePicker");

    }

    public void ar_ver(View view) {


        final String chk_id=ar_id_chk.getText().toString();
        data2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot postsnapshot1 : dataSnapshot.getChildren()) {
                        String id = postsnapshot1.child("ID").getValue().toString();
                        if (chk_id.equals(id)) {
                            Toast.makeText(appointment_.this, "PATEINT EXISTS", Toast.LENGTH_SHORT).show();
                            ar_time.setVisibility(View.VISIBLE);
                            btn.setVisibility(View.VISIBLE);

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void akgjub(View view) {
        model1.setAppointments(ar_time.getText().toString());
        data.child("Time").setValue(model1.getAppointments());
        Toast.makeText(this, "  ", Toast.LENGTH_SHORT).show();
    }
}
