package com.doc.docrepos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doc.docrepos.models.test_lab_report_model;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class test_lab_view extends AppCompatActivity {

    ListView test_list;
    DatabaseReference ref, mref;
    EditText pid;
    List<test_lab_report_model> model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lab_view);
        test_list = (ListView) findViewById(R.id.tes_labs_list);
        pid=(EditText)findViewById(R.id.editText4);
        model = new ArrayList<>();
        test_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                test_lab_report_model model2 = model.get(position);
                Intent intent = new Intent();
                intent.setData(Uri.parse(model2.getTest_img_uri()));
                startActivity(intent);
            }
        });

    }
    public  void report_data(){

    }

    public void view_list(View view) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading");

        progressDialog.show();
        ref = FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("test_reports").child(pid.getText().toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                if (dataSnapshot1.exists()) {
                    for (DataSnapshot post : dataSnapshot1.getChildren()) {

                        test_lab_report_model models = post.getValue(test_lab_report_model.class);
                        model.add(models);
                        String[] reports = new String[model.size()];
                        for (int i = 0; i < reports.length; i++) {
                            reports[i] = model.get(i).getTest_name();

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, reports) {

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {

                                View view = super.getView(position, convertView, parent);
                                TextView mytext = (TextView) view.findViewById(android.R.id.text1);
                                mytext.setTextColor(Color.BLACK);
                                progressDialog.dismiss();
                                return view;
                            }
                        };
                        test_list.setAdapter(adapter);


                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(test_lab_view.this, "Nothing to show", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}

