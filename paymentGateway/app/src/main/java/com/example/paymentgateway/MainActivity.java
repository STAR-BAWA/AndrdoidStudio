package com.example.paymentgateway;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button b1;
    Checkout checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.button);

        Checkout.preload(getApplicationContext());
        checkout = new Checkout();
        checkout.setKeyID("rzp_test_ySA8nyhwNbQZKJ");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

    }

   public void startPayment() {
      /**
       * Set your logo here
       */
      checkout.setImage(R.drawable.ic_launcher_background);

      /**
       * Reference to current activity
       */
      final Activity activity = this;

      /**
       * Pass your payment options to the Razorpay Checkout as a JSONObject
       */
      try {
          JSONObject options = new JSONObject();

          options.put("name", "Merchant Name");
          options.put("description", "Reference No. #123456");
          options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
//          options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
          options.put("theme.color", "#3399cc");
          options.put("currency", "INR");
          options.put("amount", "50000");//pass amount in currency subunits
          options.put("prefill.email", "gaurav.kumar@example.com");
          options.put("prefill.contact","9988776655");
          JSONObject retryObj = new JSONObject();
          retryObj.put("enabled", true);
          retryObj.put("max_count", 4);
          options.put("retry", retryObj);

          checkout.open(activity, options);

      } catch(Exception e) {
        Log.e("TAG", "Error in starting Razorpay Checkout", e);
      }
    }

    @Override
    public void onPaymentSuccess(String s) {
        // Add your implementation to handle payment success scenario
    }

    @Override
    public void onPaymentError(int i, String s) {
        // Add your implementation to handle payment error scenario
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
