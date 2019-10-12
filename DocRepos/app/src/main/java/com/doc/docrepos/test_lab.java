package com.doc.docrepos;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.doc.docrepos.models.test_lab_report_model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class test_lab extends AppCompatActivity {

    EditText patid, test_name_input;
    Button click;
    private Bundle imguri;
    ImageView imageView;
    DatabaseReference mref, test_ref;
    Bitmap photo;
    public static final int CAMERA_REQUEST_CODE=1;
    DatabaseReference ref;

    ProgressDialog progressDialog;
    StorageReference mstorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lab);
       patid=(EditText)findViewById(R.id.editText2);

       click=(Button)findViewById(R.id.button8);
       imageView=(ImageView)findViewById(R.id.image);
       progressDialog=new ProgressDialog(this);
       test_name_input=(EditText)findViewById(R.id.editText3);

       mref= FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("patient");

       mstorage= FirebaseStorage.getInstance().getReference();
    }

    public void idcheck(View view) {
        final String chk_id=patid.getText().toString();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot post :dataSnapshot.getChildren()) {
                        String id = post.child("ID").getValue().toString();
                        if (chk_id.equals(id)) {
                            Toast.makeText(test_lab.this, "yes", Toast.LENGTH_SHORT).show();
                            click.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                                }
                            });
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final String chk_id1=patid.getText().toString();
        final String t_name=test_name_input.getText().toString();

        if(requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK){
            photo = (Bitmap) data.getExtras().get("data");


            progressDialog.setMessage("UPLOADING...");
            progressDialog.show();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] b = stream.toByteArray();
            final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("clinic").child("clinic 1").child("test_reports").child(chk_id1).push();
            StorageReference storageReference =FirebaseStorage.getInstance().getReference().child("testlabs").child(chk_id1).child(t_name);
            //StorageReference filePath = FirebaseStorage.getInstance().getReference().child("profile_images").child(userID);
            storageReference.putBytes(b).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Task<Uri> downloaduri=taskSnapshot.getStorage().getDownloadUrl();
                    while(!downloaduri.isComplete());
                    Uri url=downloaduri.getResult();
                    test_lab_report_model model=new test_lab_report_model(test_name_input.getText().toString(),url.toString());
                   databaseReference.setValue(model);

                    Picasso.with(test_lab.this).load(url).fit().centerCrop().into(imageView);
                    Toast.makeText(test_lab.this, "Uploaded", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
