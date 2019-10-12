package com.doc.docrepos;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class login extends AppCompatActivity {
    private Button btn1;
    private TextView register;
    private ImageButton lag_sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1=(Button)findViewById(R.id.userlogin);
        loadlocale();
        lag_sel=(ImageButton)findViewById(R.id.language_id);
        lag_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlanguagedialog();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,main_login.class);
                startActivity(intent);


            }
        });
        register=(TextView)findViewById(R.id.textView8);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void showlanguagedialog() {
        final String[] listitems={"English","हिंदी","ಕನ್ನಡ","తెలుగు","मराठी","বাংলা"};
        AlertDialog.Builder mbuilder=new AlertDialog.Builder(login.this);
        mbuilder.setTitle("SELECT LANGUAGE");
        mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==1){
                    setlocale("hi");
                    recreate();
                } if(which==2){
                    setlocale("kn");
                    recreate();
                } if(which==3){
                    setlocale("te");
                    recreate();
                } if(which==4){
                    setlocale("mr");
                    recreate();
                } if(which==5){
                    setlocale("bn");
                    recreate();
                } if(which==0){
                    setlocale("en");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog=mbuilder.create();
        mDialog.show();
    }

    private void setlocale(String lang) {

        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();

    }
    public  void loadlocale(){
        SharedPreferences preferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String languagess=preferences.getString("My_lang","");
        setlocale(languagess);
    }

    public void upload(View view) {
        Intent i=new Intent(login.this,data_upload.class);
        startActivity(i);
    }

    public void fwg(View view) {
        Intent i = new Intent(login.this,f_report.class);
        startActivity(i);
    }
}
