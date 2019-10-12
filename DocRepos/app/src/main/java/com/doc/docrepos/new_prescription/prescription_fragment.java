package com.doc.docrepos.new_prescription;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doc.docrepos.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class prescription_fragment extends Fragment {

    TextView new_id,new_name, new_age, new_gender,new_weight;
    DatabaseReference new_ref;



    public prescription_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=     inflater.inflate(R.layout.fragment_prescription_fragment, container, false);
        String value=getArguments().getString("edttext");
        new_id=(TextView)view.findViewById(R.id.new_id);
        new_name=(TextView)view.findViewById(R.id.new_name_id);
        new_age=(TextView)view.findViewById(R.id.new_age_id);
        new_gender=(TextView)view.findViewById(R.id.new_gneder_id);
        new_weight=(TextView)view.findViewById(R.id.new_weight_id);
        new_ref= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("patient").child(getArguments().getString("edttext"));
        new_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot postsnapshot1 :dataSnapshot.getChildren()){
                        final String fid=postsnapshot1.child("ID").getValue().toString();
                        final String fweight=postsnapshot1.child("WEIGHT").getValue().toString();
                        final String fage=postsnapshot1.child("AGE").getValue().toString();
                        final String fname=postsnapshot1.child("NAME").getValue().toString();
                        final String fgender=postsnapshot1.child("GENDER").getValue().toString();
                      //  final String fcdisease=postsnapshot1.child("CDISEASES").getValue().toString();

                        new_name.setText(fname);
                        new_id.setText(fid);

                        new_weight.setText(fweight);
                        new_age.setText(fage);
                        new_gender.setText(fgender);




                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        return view;
    }

}
