package com.example.project2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.example.project2.ui.home.HomeFragment;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class AdminAddNewProduct extends AppCompatActivity {
    ArrayList<VendorModal> vm;
    String SellerRating;
    ImageView inputProductImage;
    public final int gallery_code=1000;
   public EditText edbookname,edbookauth,edbookprice,edbookdesc;
    private Uri ImageUri;
    private Button addnewbook;
    String Download_imageUrl;
    String saveCurrentDate,saveCurrentTime;
    private String productRandomKey;

   public String bookname,bookauth,bookprice,bookdesc;
    private StorageReference ProductImageReference;
    private DatabaseReference prodRef;
    private ProgressDialog loadingbar;
    EditText Sellerphno;
    String author;
    EditText edauthor;
    EditText isbn;
    EditText OIN;
    String DataBasePath;
     DatabaseReference ref_for_img;
    ActivityResultLauncher<String> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);


        inputProductImage = findViewById(R.id.imagegallery);
        edbookname = findViewById(R.id.editTextBookName);
        edbookauth = findViewById(R.id.bookAuth);
        edbookprice = findViewById(R.id.PriceOfbook);
        edbookdesc = findViewById(R.id.BriefDescription);
//        ProductImageReference = FirebaseStorage.getInstance().getReference().child("product_image");
        addnewbook = findViewById(R.id.AddnewDataAdmin_side);
        loadingbar = new ProgressDialog(this);
        prodRef = FirebaseDatabase.getInstance().getReference();
        Sellerphno=findViewById(R.id.VendorContactAdminSide);
        edauthor=findViewById(R.id.bookAuth);
        isbn=findViewById(R.id.editTextISBN);
        OIN=findViewById(R.id.editTextBookOIN);
        ref_for_img=FirebaseDatabase.getInstance().getReference("Products");
        //
        
        //for storing the image in database
        

        inputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
//                launcher.launch("image/*");
                Toast.makeText(AdminAddNewProduct.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

        addnewbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(AdminAddNewProduct.this, "Hello ", Toast.LENGTH_SHORT).show();
                loadingbar.setTitle("Save Product");
                loadingbar.setMessage("Saving Data");
                loadingbar.setCanceledOnTouchOutside(true);
                loadingbar.show();
                validateData();
            }
        });

    }
        private void openGallery() {
//
            Intent galleryIntent=new Intent();
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent,gallery_code);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode ==gallery_code && data!=null)
        {
            ImageUri=data.getData();
            inputProductImage.setImageURI(ImageUri);
        }
    }

    private void validateData()
    {
        Toast.makeText(this, "Last step", Toast.LENGTH_SHORT).show();
        bookname=edbookname.getText().toString();
        bookauth=edbookauth.getText().toString();
        bookprice=edbookprice.getText().toString();
        bookdesc=edbookdesc.getText().toString();
        String bookOIN=OIN.getText().toString();

        if(ImageUri==null)
        {
            Toast.makeText(this, "Product Image Is mandatory...", Toast.LENGTH_SHORT).show();

        }
        if(bookname.isEmpty())
        {
            Toast.makeText(this, "This field is mandatory", Toast.LENGTH_SHORT).show();
        }
        if(bookauth.isEmpty())
        {
            Toast.makeText(this, "This field is mandatory", Toast.LENGTH_SHORT).show();
        }
        if(bookprice.isEmpty())
        {
            Toast.makeText(this, "This field is mandatory", Toast.LENGTH_SHORT).show();
        }
        if(bookdesc.isEmpty())
        {
            Toast.makeText(this, "This field is mandatory", Toast.LENGTH_SHORT).show();
        }
        if(bookauth.isEmpty())
        {
            Toast.makeText(this, "Enter the author name", Toast.LENGTH_SHORT).show();
        }
        if(bookOIN.isEmpty())
        {
             Toast.makeText(this, "Enter the OIN Number", Toast.LENGTH_SHORT).show();
        }
        else
        {
//            HomeFragment homeFragment=new HomeFragment();
//            homeFragment.setArguments(bundle);

//         image uri is not passed since there are only dummies
         storeProductInfo();
        }

    }
//    public void storeProductInfo() {
//        Calendar cal = Calendar.getInstance();//android api
//        SimpleDateFormat currDate = new SimpleDateFormat("MMM dd, yyyy");//simple class
//        saveCurrentDate = currDate.format(cal.getTime());
//        SimpleDateFormat currTime = new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime = currTime.format(cal.getTime());
//
//
//        productRandomKey = saveCurrentDate + saveCurrentTime;
//        Toast.makeText(this, "Last step 2 ", Toast.LENGTH_SHORT).show();
//        ProductImageReference = FirebaseStorage.getInstance().getReference().child("product_image /"+productRandomKey+".jpeg");
//        StorageReference filePath=ProductImageReference;
//        //this will be a very unique random key
//
//        Toast.makeText(this, "This is the last step", Toast.LENGTH_SHORT).show();
//
//
//
//        final UploadTask uploadTask=filePath.putFile(ImageUri);
//
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                String error=e.toString();
//                loadingbar.dismiss();
//                Toast.makeText(AdminAddNewProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                //used to store the url
//                Task<Uri> uritask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                    @Override
//                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//
//                        if(task.isSuccessful())
//                        {
////                            Toast.makeText(AdminAddNewProduct.this, "Image url", Toast.LENGTH_SHORT).show();
//                            Download_imageUrl=task.getResult().getMetadata().toString();
//                            loadingbar.dismiss();
//                            Toast.makeText(AdminAddNewProduct.this, "Sucess ", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(AdminAddNewProduct.this, "Brown Toast ", Toast.LENGTH_SHORT).show();
//                        }
//                        Download_imageUrl=filePath.toString();
//
//                        return filePath.getDownloadUrl();
//
//                    }
//                });
//            }
//        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                if(task.isSuccessful()) {
//                    Download_imageUrl=task.getResult().toString();
////                    Toast.makeText(AdminAddNewProduct.this, Download_imageUrl.toString(), Toast.LENGTH_SHORT).show();
//                    Save_Product_to_database();
//                }
//            }
//        });
//
//         Save_Product_to_database();
//
//        Toast.makeText(this, "Hello message", Toast.LENGTH_SHORT).show();
//    }

    public void storeProductInfo() {
    Calendar cal = Calendar.getInstance();//android api
    SimpleDateFormat currDate = new SimpleDateFormat("MMM dd, yyyy");//simple class
    saveCurrentDate = currDate.format(cal.getTime());
    SimpleDateFormat currTime = new SimpleDateFormat("HH:mm:ss a");
    saveCurrentTime = currTime.format(cal.getTime());

    productRandomKey = saveCurrentDate + saveCurrentTime;
//    Toast.makeText(this, "Last step 2 ", Toast.LENGTH_SHORT).show();
    ProductImageReference = FirebaseStorage.getInstance().getReference().child("product_image /"+productRandomKey+".jpeg");
    StorageReference filePath = ProductImageReference;

//    Toast.makeText(this, "This is the last step", Toast.LENGTH_SHORT).show();

    final UploadTask uploadTask = filePath.putFile(ImageUri);

    uploadTask.addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            String error = e.toString();
            loadingbar.dismiss();
            Toast.makeText(AdminAddNewProduct.this,e.getMessage()+
                    "", Toast.LENGTH_SHORT).show();
        }
    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            ProductImageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Download_imageUrl = uri.toString();
                    Save_Product_to_database();
                }
            });
        }
    }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
            if (task.isSuccessful()) {
                Download_imageUrl = task.getResult().toString();
            }
        }
    });
}

public void Save_Product_to_database() {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Vendor1").child(Sellerphno.getText().toString());
    reference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            String SellerRating = snapshot.child("Rating").getValue(String.class);

            if (isbn.getText().toString().length() == 6 && OIN.getText().toString().length() == 6) {
                HashMap<String, Object> prodMap = new HashMap<>();
                prodMap.put("ID", productRandomKey);
                prodMap.put("BookName", bookname);
                prodMap.put("Approval", "Not Approved");
                prodMap.put("book_auth", bookauth);
                prodMap.put("book_price", bookprice);
                prodMap.put("Seller_phone", Sellerphno.getText().toString());
                prodMap.put("BookDescription", bookdesc);
                prodMap.put("product_Image", Download_imageUrl);
                prodMap.put("Date", saveCurrentDate);
                prodMap.put("time", saveCurrentTime);
                prodMap.put("ISBN", isbn.getText().toString());
                prodMap.put("OIN", OIN.getText().toString());
                prodMap.put("Rating", SellerRating);

                prodRef.child("Products").child(productRandomKey).updateChildren(prodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.success(AdminAddNewProduct.this, "Data Added successfully", Toast.LENGTH_SHORT, true).show();
                        } else {
                            Toasty.error(AdminAddNewProduct.this, "Data could not be added", Toast.LENGTH_SHORT, true).show();
                        }
                    }
                });

                prodRef.child("Vendor1").child(Sellerphno.getText().toString()).child("VendorProducts").child(OIN.getText().toString()).updateChildren(prodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            loadingbar.dismiss();
                        } else {
                            Toast.makeText(AdminAddNewProduct.this, "Error occurred in adding to vendor side", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminAddNewProduct.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                Toast.makeText(AdminAddNewProduct.this, "Please use atleast 6 numbers in isbn and OIN", Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(AdminAddNewProduct.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}

}