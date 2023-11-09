package com.example.bookworm_user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class payment_activity extends AppCompatActivity {
TextView totalAmtTextView;
Button completed_order;
EditText addressEditText;
EditText landmarkEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        totalAmtTextView=findViewById(R.id.Total_amt);
   String totalAmount=getIntent().getStringExtra("totalAmount");
   totalAmtTextView.setText(totalAmount);
completed_order=findViewById(R.id.Completed);
        DatabaseReference AddressRef = FirebaseDatabase.getInstance().getReference("Users");



   completed_order.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           addressEditText=findViewById(R.id.add_deliv_details);
           landmarkEditText=findViewById(R.id.neareast_landmark);

           String address=addressEditText.getText().toString();
           String landmark=landmarkEditText.getText().toString();
           DeliveryDetailsModel deliveryDetailsModel=new DeliveryDetailsModel(address,landmark);
           AddressRef.child("+1916387499381").child("delivery_details").push().setValue(deliveryDetailsModel);
           Toast.makeText(payment_activity.this,"Order Placed",Toast.LENGTH_SHORT).show();
       }
   });



    }
}