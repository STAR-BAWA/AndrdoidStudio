package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
public class MainActivity extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    Button register;
    FirebaseAuth auth;
    String password;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.editTextTextPersonName);
        ed2=(EditText) findViewById(R.id.editTextTextPersonName2);
        auth=FirebaseAuth.getInstance();
        register=findViewById(R.id.button);
//        password=ed2.getText().toString();
//        email=ed1.getText().toString();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=ed2.getText().toString();
                email=ed1.getText().toString();
                if(password.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter full details", Toast.LENGTH_SHORT).show();
                }
                else
                    regis(email,password);
            }
        });
    }
    public void regis(String email,String password){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "data saved", Toast.LENGTH_SHORT).show();   
                }
                else
                    Toast.makeText(MainActivity.this, "Not saved", Toast.LENGTH_SHORT).show();
            }
        });

    }
}