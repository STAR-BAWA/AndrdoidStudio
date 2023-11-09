package com.example.bookworm_user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rey.material.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewProductActivity extends AppCompatActivity {
private String CategoryName, Description,Price,Pname, saveCurrentDate,saveCurrentTime;
private static final int GalleryPick=1;
private Button AddNewProductButton;
private EditText InputProductName,InputProductDescription, InputProductPrice;
private ImageView InputProductImage;
private Uri ImageUri;
    private ProgressDialog loadingBar;
private String ProductRandomKey,  downloadImageUrl;
private StorageReference ProductImagesRef;
private DatabaseReference ProductRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);
        Toast.makeText(this,"Welcome Admin",Toast.LENGTH_SHORT).show();
        CategoryName=getIntent().getExtras().get("category").toString();

        AddNewProductButton=(Button) findViewById(R.id.add_new_product);
        InputProductImage=(ImageView) findViewById(R.id.select_product_img);
        InputProductName=(EditText) findViewById(R.id.product_name);
        InputProductDescription=(EditText) findViewById(R.id.product_description);
        InputProductPrice=(EditText) findViewById(R.id.product_price);
        ProductImagesRef= FirebaseStorage.getInstance().getReference().child("Product Image");

InputProductImage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        OpenGallery();
    }


});

AddNewProductButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ValidateProductData();
    }
});
    }

    private void ValidateProductData() {
        Description=InputProductDescription.getText().toString();
        Pname=InputProductName.getText().toString();
        Price=InputProductPrice.getText().toString();

        if(ImageUri==null)
        {
            Toast.makeText(this, "Product Image is required", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Please Write Product Description", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Please Write Product Price", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Pname))
        {
            Toast.makeText(this, "Please Write Product Name", Toast.LENGTH_SHORT).show();
        }
else{
StoreProductInformation();        }

    }

    private void StoreProductInformation() {
        loadingBar.setTitle("Login Account");
        loadingBar.setMessage("Please wait, while we are adding the new product");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd,yyyy");
         saveCurrentDate=currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime=currentTime.format(calendar.getTime());

        ProductRandomKey=saveCurrentDate+saveCurrentTime;
        StorageReference filePath=ProductImagesRef.child(ImageUri.getLastPathSegment()+ ProductRandomKey+".jpg");
        final UploadTask uploadTask=filePath.putFile(ImageUri);

      uploadTask.addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
String msg=e.toString();
Toast.makeText(AdminAddNewProductActivity.this,"Error:"+msg,Toast.LENGTH_SHORT).show();
         loadingBar.dismiss();
          }
      }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
          @Override
          public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
              Toast.makeText(AdminAddNewProductActivity.this, "Image uploaded Successfully",Toast.LENGTH_SHORT).show();
        Task<Uri> urlTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();

                }
                downloadImageUrl = filePath.getDownloadUrl().toString();
                return filePath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
               if(task.isSuccessful())
               {loadingBar.dismiss();
                   Toast.makeText(AdminAddNewProductActivity.this,"Product image saved to Database Successfully...",Toast.LENGTH_SHORT).show();

                   SaveProductIndoToDBase();

               }
            }

            private void SaveProductIndoToDBase() {
                HashMap<String,Object>productMap=new HashMap<>();
                productMap.put("Pid",ProductRandomKey);
                productMap.put("date",saveCurrentDate);
                productMap.put("time",saveCurrentTime);
                productMap.put("description",Description);
                productMap.put("image",downloadImageUrl);
                productMap.put("price",Pname);


            }
        });

          }
      });
    }

    private void OpenGallery() {

        Intent galleryIntent= new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==GalleryPick &&requestCode==RESULT_OK && data!=null)
    {
ImageUri=data.getData();
InputProductImage.setImageURI(ImageUri);
    }


    }
}