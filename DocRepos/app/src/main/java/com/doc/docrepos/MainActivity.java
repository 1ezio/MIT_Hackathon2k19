package com.doc.docrepos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.doc.docrepos.models.clinin_name;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText cname;
    String cname2;
    DatabaseReference mref;
    private clinin_name clicnic_name_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cname=(EditText)findViewById(R.id.clinic_name);
      //  cname2=cname.getText().toString();
        clicnic_name_model=new clinin_name();
        mref= FirebaseDatabase.getInstance().getReference();
    }



    public void next(View view) {
        Intent intent=new Intent(MainActivity.this,doc_registration.class);
        cname2=cname.getText().toString();
        SharedPreferences login = getSharedPreferences("LOGIN", 0);
        SharedPreferences.Editor editor = login.edit();
        editor.putString("user", cname.getText().toString());
        editor.commit();
       intent.putExtra("Vlaue",cname2);
        startActivity(intent);
        finish();
        //mref= FirebaseDatabase.getInstance().getReference().child("clinic");
        /*clicnic_name_model.setCmname(cname.getText().toString());
        mref.child(clicnic_name_model.getCmname()).setValue(clicnic_name_model);
      /*  Map<String,Object> map=new HashMap<>();
        map.put("clinic_name",cname.getText().toString());
        mref.setValue(map);*/
    }
}
