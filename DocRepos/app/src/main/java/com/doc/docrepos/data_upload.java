package com.doc.docrepos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.doc.docrepos.models.data_upload_model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class data_upload extends AppCompatActivity {
    EditText entersym, enterdis, entermedi, enterdosage, enterchronic, enterage, enterweight, entertest;


    DatabaseReference reference;
    Button save;
    ImageButton clr_sym,clr_medi, clr_chro, clr_age, clr_weight, clr_dosage;
    private data_upload_model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_upload);

        enterdis=(EditText)findViewById(R.id.diseases);

        save=(Button)findViewById(R.id.save_data);

       clr_sym=(ImageButton)findViewById(R.id.clear_sym_id);


        clr_medi=(ImageButton) findViewById(R.id.clear_medi_id);
        clr_dosage=(ImageButton)findViewById(R.id.clear_dos_id);
        clr_chro=(ImageButton)findViewById(R.id.clr_chro_id);
        clr_age=(ImageButton)findViewById(R.id.clr_age_id);
        clr_weight=(ImageButton)findViewById(R.id.clr_weight_id);






        //     addsym=(TextView) findViewById(R.id.add_symptoms);
      //  adddis=(TextView)findViewById(R.id.add_disease);
        //addmedi=(TextView)findViewById(R.id.add_Medicines);
        reference= FirebaseDatabase.getInstance().getReference();
        model=new data_upload_model();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entersym=(EditText)findViewById(R.id.symptoms);
                entermedi=(EditText)findViewById(R.id.medicines);
                enterdis=(EditText)findViewById(R.id.diseases);
                enterdosage=(EditText) findViewById(R.id.dosage_id);
                enterchronic=(EditText) findViewById(R.id.editText5);
                enterage=(EditText) findViewById(R.id.editText6);
                enterweight=(EditText) findViewById(R.id.editText7 );
                entertest=(EditText)findViewById(R.id.editText8);

                reference=FirebaseDatabase.getInstance().getReference("data").push();

                model.setSym(entersym.getText().toString());
                model.setDis(enterdis.getText().toString());
                model.setMedi(entermedi.getText().toString());
                model.setDos(enterdosage.getText().toString());
                model.setChronic(enterchronic.getText().toString().toLowerCase());
                model.setAGE_input(enterage.getText().toString());
                model.setWeight(enterweight.getText().toString());
                model.setTests_req(entertest.getText().toString());

                reference.child("symptoms").setValue(model.getSym());
                reference.child("disease").setValue(model.getDis());
                reference.child("medicine").setValue(model.getMedi());
                reference.child("dosage").setValue(model.getDos());
                reference.child("chronic").setValue(model.getChronic());
                reference.child("age").setValue(model.getAGE_input());
                reference.child("weight").setValue(model.getWeight());
                reference.child("test_req").setValue(model.getTests_req());

                Toast.makeText(data_upload.this, "uploaded", Toast.LENGTH_SHORT).show();


                clr_sym.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        entersym.setText("");

                    }
                });
                clr_medi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        entermedi.setText("");
                    }
                });

                clr_dosage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterdosage.setText("");
                    }

                });
                clr_chro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterchronic.setText("");
                    }
                });
                clr_age.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterage.setText("");
                    }
                });
                clr_weight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterweight.setText("");
                    }
                });
            }
        });



    }


}
