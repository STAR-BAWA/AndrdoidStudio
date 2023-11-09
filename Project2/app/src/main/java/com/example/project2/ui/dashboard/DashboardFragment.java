package com.example.project2.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project2.AdminAddNewProduct;
import com.example.project2.R;
import com.example.project2.databinding.FragmentDashboardBinding;
import com.example.project2.inventory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import es.dmoral.toasty.Toasty;

public class DashboardFragment extends Fragment {

    private ImageView inventory;
    private FragmentDashboardBinding binding;
    TextView txt;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inventory=binding.buttonInventory;
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Inventory", Toast.LENGTH_SHORT).show();
                Toasty.success(getContext(),"Sucess!",Toasty.LENGTH_SHORT,true).show();
                startActivity(new Intent(getContext(), inventory.class));
            }
        });




        ImageView b1= (ImageView) binding.buttonAddproduct;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AdminAddNewProduct.class));
            }
        });
//            Bundle bundle=getArguments();
//            if(bundle!=null) {
//                String val = bundle.getString("booknameKey");
//                txt.setText(val);
//            }
//
//            else
//                Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
//
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}