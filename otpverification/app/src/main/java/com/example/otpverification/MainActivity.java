package com.example.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.ImsStateCallback;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    Button getotp;
    String verificationCodeBySystem;
    Button verifyotp;
    FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    String otpid;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

//       mAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);
        ed1 = (EditText) findViewById(R.id.editTextPhone);
        ed2 = (EditText) findViewById(R.id.editTextPhone2);

        getotp = findViewById(R.id.button2);
        verifyotp = findViewById(R.id.button3);

//        String phnumber = ed1.getText().toString();

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String phnumber = ed1.getText().toString();
                sendVerificationCodeToUser(phnumber);
//                PhoneAuthCredential credential =PhoneAuthProvider.getCredential(otpid,ed2.getText().toString());
            }
        });


        verifyotp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String otp = ed2.getText().toString().trim();
        if (TextUtils.isEmpty(otp)) {
            Toast.makeText(MainActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
            return;
        }
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, otp);
        signInWithPhoneAuthCredential(credential);
    }
});
    }



    private void sendVerificationCodeToUser(String phoneNumber) {

        // Force reCAPTCHA flow
       PhoneAuthOptions options =
        PhoneAuthOptions.newBuilder(mAuth)
      .setPhoneNumber("+91"+phoneNumber)       // Phone number to verify
      .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
      .setActivity(this)                 // Activity (for callback binding)
      .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
      .build();

//                      mAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);


       PhoneAuthProvider.verifyPhoneNumber(options);


    }

   PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    @Override
    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
        // This callback will be invoked in two situations:
        // 1 - Instant verification. In some cases the phone number can be instantly
        //     verified without needing to send or enter a verification code.
        // 2 - Auto-retrieval. On some devices Google Play services can automatically
        //     detect the incoming verification SMS and perform verification without
        //     user action.
//        Log.d(TAG, "onVerificationCompleted:" + credential);

//        signInWithPhoneAuthCredential(credential);
    }

    @Override
    public void onVerificationFailed(@NonNull FirebaseException e) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

        // Show a message and update the UI
    }

    @Override
    public void onCodeSent(@NonNull String verificationId,
                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
        // The SMS verification code has been sent to the provided phone number, we
        // now need to ask the user to enter the code and then construct a credential
        // by combining the code with a verification ID.
//        Log.d(TAG, "onCodeSent:" + verificationId);

        // Save verification ID and resending token so we can use them later
        otpid= verificationId;
        forceResendingToken = token;
    }
};

   private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, show a success toast
                        Toast.makeText(MainActivity.this, "Verification successful", Toast.LENGTH_SHORT).show();

                        // Start a new activity
                        Intent intent = new Intent(MainActivity.this, welcomeScreen.class);
//                        intent.putExtra("key", value); // Add any extras if needed
                        startActivity(intent);
                        finish(); // Call finish to close the current activity
                    } else {
                        // Sign in failed, display a message to the user
                        Toast.makeText(MainActivity.this, "Verification failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}



}



