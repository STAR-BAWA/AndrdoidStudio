package com.example.project2.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project2.databinding.FragmentNotificationsBinding;
import com.example.project2.notification_list;
import com.example.project2.vendor_new_order;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class NotificationsFragment extends Fragment {

    EditText seller_number;
    Button fetch;
    EditText password;
    private FragmentNotificationsBinding binding;
    FloatingActionButton btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        seller_number=binding.sellerNumberNotification;
        password=binding.sellerPasscodeNotification;


        fetch =binding.buttonNotifcation;

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seller_number.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Details are missing", Toast.LENGTH_SHORT, true).show();
                } else {
//
                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Vendor1");

                  reference.child(seller_number.getText().toString()).addValueEventListener(new ValueEventListener() {
                     @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {
                    String get_phone_number = snapshot.child("Phone_Number").getValue(String.class);
                    String get_password = snapshot.child("password").getValue(String.class);
                    if (get_phone_number.equals(seller_number.getText().toString()) && get_password.equals(password.getText().toString())) {
                        Toasty.success(view.getContext(), "Sucess", Toast.LENGTH_SHORT, true).show();
                        Intent it=new Intent(view.getContext(), vendor_new_order.class);
                        it.putExtra("Number",get_phone_number);

                        startActivity(it);
                    }
//                    Toast.makeText(getContext(), "Hello ", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}