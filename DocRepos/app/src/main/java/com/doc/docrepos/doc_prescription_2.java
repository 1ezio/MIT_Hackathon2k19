package com.doc.docrepos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doc.docrepos.models.patient_record_show;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class doc_prescription_2 extends AppCompatActivity {

    TextView name2, age2, weight2, gend2,id2,tests;
    EditText symptoms2;
    EditText chrodisease2;
    Spinner disease2, medicines2, dosages2;
    ImageButton imgbtn;
    DatabaseReference dataref2, symref2;
    String ID_r2;
    EditText enter_medicine2, enter_dosage2;
    ListView medicinelist2;
    ArrayList<String> medlist2;
     List<String> d;
    private String patient_id2="default";
    ArrayAdapter<String> adapter2;
    private List<String> m2,dosa2, dis2;
    String age_v;
    String rep_id, rep_name,rep_age,rep_weight, rep_gender,rep_chronic, rep_dis, rep_medicine, rep_sym;
    DatabaseReference report_database;
    patient_record_show model_record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_prescription);

        name2=(TextView)findViewById(R.id.name_id);
        id2=(TextView)findViewById(R.id.patient_id);
        age2=(TextView)findViewById(R.id.age_id);

        imgbtn=(ImageButton)findViewById(R.id.imageButton2);

        medicinelist2=(ListView)findViewById(R.id.medicine_list_id);
        Intent intent=getIntent();

        if(intent!=null){
            patient_id2=intent.getStringExtra("Vaue");
        }

        tests=(TextView)findViewById(R.id.textView18);
        weight2=(TextView)findViewById(R.id.weight_id);
        gend2=(TextView)findViewById(R.id.gender_id);
        chrodisease2=(EditText) findViewById(R.id.chro_dis_id);
        model_record=new patient_record_show();
        enter_medicine2=(EditText)findViewById(R.id.medicine_eneter_id);
        enter_dosage2=(EditText)findViewById(R.id.dosage_enter_id);

        symptoms2=(EditText)findViewById(R.id.symptoms_id);

        dosages2=(Spinner)findViewById(R.id.spinner2);


        disease2=(Spinner)findViewById(R.id.disease_id);
        medicines2=(Spinner)findViewById(R.id.medicine_id);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medos=enter_medicine2.getText().toString()+" "+enter_dosage2.getText().toString();
                addToList(medos);

            }
        });
        disease2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rep_dis=d.get(position);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dosages2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String medicine = dosa2.get(position);
                rep_medicine=dosa2.get(position);

                addToList(medicine);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        report_database=FirebaseDatabase.getInstance().getReference("patient_record").push();
        symref2= FirebaseDatabase.getInstance().getReference("data");
        dataref2= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("patient");
        dataref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot postsnapshot :dataSnapshot.getChildren()){
                        if(postsnapshot.getKey().equals(patient_id2)) {
                            final String fid = postsnapshot.child("ID").getValue().toString();
                            age_v = postsnapshot.child("AGE").getValue().toString();
                            final String fweight = postsnapshot.child("WEIGHT").getValue().toString();
                            final String fage = postsnapshot.child("AGE").getValue().toString();
                            final String fname = postsnapshot.child("NAME").getValue().toString();
                            final String fgender = postsnapshot.child("GENDER").getValue().toString();


                            name2.setText(fname);
                            id2.setText(fid);
                            weight2.setText(fweight);
                            age2.setText(fage);
                            gend2.setText(fgender);

                            rep_id = fid;
                            rep_name = fname;
                            rep_age = fage;

                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        medlist2 = new ArrayList<String>();
        adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,medlist2);
        medicinelist2.setAdapter(adapter2);


    }


    private void addToList(String medicine) {
        medlist2.add(medicine);
        adapter2.notifyDataSetChanged();
    }

    public void s(View view) {
        final String getsymp= symptoms2.getText().toString().toLowerCase();
        final String getdis=chrodisease2.getText().toString().toLowerCase();

        symref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                if(dataSnapshot1.exists()){
                    d=new ArrayList<String>();

                    m2=new ArrayList<String>();

                    dosa2=new ArrayList<String>();

                    for(DataSnapshot postsnapshot2 :dataSnapshot1.getChildren()){
                        String sym= postsnapshot2.child("symptoms").getValue().toString();

                        String c=postsnapshot2.child("chronic").getValue().toString();
                        if(sym.contains(getsymp)){
                            String Dise= postsnapshot2.child("disease").getValue(String.class);

                            if(!d.contains(Dise)){
                                d.add(Dise);
                                String test=postsnapshot2.child("test_req").getValue(String.class);

                                tests.setText(test);
                            }
                          /*  for (int i=1; i <d.size();i++){
                                if(d.equals(Dise+i)) {
                                    d.remove(d);

                                }

                              }*/
                            String medi= postsnapshot2.child("medicine").getValue(String.class);
                            String dos= postsnapshot2.child("dosage").getValue(String.class);
                            if(!c.contains(getdis)){

                                m2.add("");
                                m2.add(medi);
                                dosa2.add("");
                                dosa2.add(dos);


                                }

                        }
                    }
                    ArrayAdapter<String> disadapter=new ArrayAdapter<String>(doc_prescription_2.this,android.R.layout.simple_spinner_item,d);
                    disadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    disease2.setAdapter(disadapter);



                    ArrayAdapter<String> medadapter=new ArrayAdapter<String>(doc_prescription_2.this,android.R.layout.simple_spinner_item,m2);
                    medadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    medicines2.setAdapter(medadapter);

                    ArrayAdapter<String> dosadapter= new ArrayAdapter<String>(doc_prescription_2.this,android.R.layout.simple_spinner_item,dosa2);
                    dosadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dosages2.setAdapter(dosadapter);




                }else Toast.makeText(doc_prescription_2.this, "none", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void print(View view) {

        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        String jobName = this.getString(R.string.app_name) + " Document";
        //  printManager.print(jobName, pda, null);

        PrintDocumentAdapter pda = new PrintDocumentAdapter(){

            @Override
            public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback){
                InputStream input = null;
                OutputStream output = null;

                try {

                    input = new FileInputStream("");
                    output = new FileOutputStream(destination.getFileDescriptor());

                    byte[] buf = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = input.read(buf)) > 0) {
                        output.write(buf, 0, bytesRead);
                    }

                    callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});

                } catch (FileNotFoundException ee){
                    //Catch exception
                } catch (Exception e) {
                    //Catch exception
                } finally {
                    try {
                        input.close();
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback callback, Bundle extras){

                if (cancellationSignal.isCanceled()) {
                    callback.onLayoutCancelled();
                    return;
                }


                PrintDocumentInfo pdi = new PrintDocumentInfo.Builder("Name of file").setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).build();

                callback.onLayoutFinished(pdi, true);
            }
        };
    }

    public void save_database(View view) {

        model_record.setRec_id_m(id2.getText().toString());
        model_record.setRec_name_m(name2.getText().toString());
        model_record.setRec_age_m(age2.getText().toString());
        model_record.setRec_weight_m(weight2.getText().toString());
        model_record.setRec_gender_m(gend2.getText().toString());
        model_record.setRec_chro_m_(chrodisease2.getText().toString());
        model_record.setRec_medicine(rep_medicine);
        model_record.setRec_dis_m(rep_dis);
        model_record.setRec_medicine(enter_medicine2.getText().toString()+enter_dosage2.getText().toString());

        report_database.child("id").setValue(model_record.getRec_id_m());
        report_database.child("name").setValue(model_record.getRec_name_m());
        report_database.child("age").setValue(model_record.getRec_age_m());
        report_database.child("weight").setValue(model_record.getRec_weight_m());
        report_database.child("gender").setValue(model_record.getRec_gender_m());
        report_database.child("chronic").setValue(model_record.getRec_chro_m_());
        report_database.child("medicine").setValue(model_record.getRec_medicine());
        report_database.child("disease").setValue(model_record.getRec_dis_m());
        report_database.child("medicine2").setValue(model_record.getRec_medicine());
        Intent i= new Intent(doc_prescription_2.this,pateint_id_input.class);
        startActivity(i);

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

    }

    public void vr(View view) {
        Intent intent =new Intent(doc_prescription_2.this, test_lab_view.class);
        SharedPreferences ghnta = getSharedPreferences("Report", 0);
        SharedPreferences.Editor editor = ghnta.edit();
        editor.putString("repos", ID_r2);
        editor.commit();
        intent.putExtra("ids",ID_r2);
        startActivity(intent);
        finish();


        startActivity(intent);
    }

    public class Screenshot {

        public Bitmap takescreenshot(View v) {
            v.setDrawingCacheEnabled(true);
            v.buildDrawingCache(true);
            Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
            v.setDrawingCacheEnabled(false);
            return b;
        }

        public  Bitmap takescreenshotOfRootView(View v) {
            return takescreenshot(v.getRootView());
        }

    }
    @Override
    public void onBackPressed() {
     
        super.onBackPressed();
    }
}



