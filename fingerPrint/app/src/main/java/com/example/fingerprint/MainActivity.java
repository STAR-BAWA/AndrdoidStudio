package com.example.fingerprint;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    BiometricPrompt prompt;

    androidx.biometric.BiometricPrompt.PromptInfo promptInfo;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BiometricManager manager=BiometricManager.from(this);
        int canAuth=manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG);

          if (canAuth == BiometricManager.BIOMETRIC_SUCCESS) {
    Toast.makeText(this, "Biometric authentication is available", Toast.LENGTH_SHORT).show();}       else if (canAuth == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) {
    Toast.makeText(this, "No biometric hardware available on this device", Toast.LENGTH_SHORT).show();
} else if (canAuth == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE) {
    Toast.makeText(this, "Biometric hardware is currently unavailable", Toast.LENGTH_SHORT).show();
} else if (canAuth == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
    Toast.makeText(this, "No biometric enrolled on this device", Toast.LENGTH_SHORT).show();
} else {
    Toast.makeText(this, "Biometric authentication not available", Toast.LENGTH_SHORT).show();}

        Executor executor = ContextCompat.getMainExecutor(this);

        prompt=new androidx.biometric.BiometricPrompt(this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(MainActivity.this, "Failed error no fingerprint", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


        promptInfo=new BiometricPrompt.PromptInfo.Builder().setTitle("Tech Projects").setDescription("Use Fingerprint to login").setDeviceCredentialAllowed(true).build();
        prompt.authenticate(promptInfo);
    }

}