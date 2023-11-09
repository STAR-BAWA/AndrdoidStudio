package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class inventory extends AppCompatActivity {

    Button inventorySeller;
    EditText phno;
    EditText password;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        inventorySeller=findViewById(R.id.buttonInventoryDetails);
        phno=findViewById(R.id.editTextPhoneNumberInventory);
        password=findViewById(R.id.inventoryPasswrd);
        inventorySeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inventorySeller.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(inventory.this, "Enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    dialog = new ProgressDialog(inventory.this);
                    dialog.setTitle("Logging In");
                    dialog.setMessage("Logging you in");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    if (phno.getText().toString().length() == 10) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vendor1").child(phno.getText().toString());
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    String pwd = password.getText().toString();
                                    String phoneNumber = snapshot.getKey();
                                    String pwdV = snapshot.child("password").getValue().toString();
                                    if (phoneNumber.equals(phno.getText().toString()) && pwd.equals(pwdV)) {
                                        Toasty.success(inventory.this, "Success", Toast.LENGTH_SHORT, true).show();
                                        Intent it = new Intent(inventory.this, inventoryDetails.class);
                                        it.putExtra("phone_number", phoneNumber);
                                        dialog.dismiss();
                                        startActivity(it);
                                    } else {
                                        dialog.dismiss();
                                        Toasty.error(inventory.this, "Password is incorrect", Toast.LENGTH_SHORT, true).show();
                                    }

                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(inventory.this, "Number Not found ", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
//                            Toast.makeText(inventory.this, "Failure", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(inventory.this, "The number is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
