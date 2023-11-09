package com.example.firebasestorage;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
ImageView img,imgv2;
Button fetchimg;
Button b1;
ActivityResultLauncher<String> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=findViewById(R.id.imageView);
        b1=findViewById(R.id.button);
        fetchimg=findViewById(R.id.button2);
        imgv2=findViewById(R.id.imageView2);


        DatabaseReference dbref=FirebaseDatabase.getInstance().getReference();
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();

     launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
         @Override
         public void onActivityResult(Uri result) {
             img.setImageURI(result);
            final StorageReference storageReference=firebaseStorage.getReference("child");
             Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            storageReference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this, "stored in firebase", Toast.LENGTH_SHORT).show();
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Image uploaded", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Failed upload", Toast.LENGTH_SHORT).show();
                }
            });
         }
     });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launcher.launch("image/*");

            }
        });


        fetchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbref=database.getReference();
                System.out.println(dbref.toString());

                //Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/fir-storage-f2998.appspot.com/o/child?alt=media&token=f4e3ece3-8a46-45f4-b49e-b7ca3da1f04f").into(imgv2);
//                Picasso.get().load(dbref.child("image").toString()).into(imgv2);
                
                dbref.child("image").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String image=snapshot.getValue(String.class);
                        Picasso.get().load(image).into(imgv2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Image failed", Toast.LENGTH_SHORT).show();
                    }
                });
                
                Toast.makeText(MainActivity.this, "Toast fetch", Toast.LENGTH_SHORT).show();
            }
        });

    }
}