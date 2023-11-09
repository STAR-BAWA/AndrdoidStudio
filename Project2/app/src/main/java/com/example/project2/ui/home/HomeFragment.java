package com.example.project2.ui.home;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.VendorAdapter;
import com.example.project2.VendorModal;
import com.example.project2.contact_support;
import com.example.project2.databinding.FragmentHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ListView listView;
    VendorAdapter adapter;
    DatabaseReference mbase;
    RecyclerView rcv;
    FloatingActionButton fb;
    private MenuItem menuItem;
    private  SearchView searchView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        fb=root.findViewById(R.id.floatingActionButton);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), contact_support.class));
            }
        });


        rcv=binding.RecyclerView1;


        rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference("Products");
        FirebaseRecyclerOptions<VendorModal> options = new FirebaseRecyclerOptions.Builder<VendorModal>()
                .setQuery(vendorRef, VendorModal.class)
                .build();

        adapter = new VendorAdapter(options,getContext());


        rcv.setAdapter(adapter);
//        Toast.makeText(getContext(), "Hello Recycler Toast", Toast.LENGTH_SHORT).show();


  setHasOptionsMenu(true);
        adapter.startListening();
//        adapter.stopListening();
        return root;
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.searchmenu,menu);
        MenuItem item=menu.findItem(R.id.search_menu);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                 processSearch(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void processSearch(String msg) {

          DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference().child("Products");
        FirebaseRecyclerOptions<VendorModal> options = new FirebaseRecyclerOptions.Builder<VendorModal>()
                .setQuery(vendorRef.orderByChild("BookName").startAt(msg).endAt(msg+"\uf8ff"), VendorModal.class)
                .build();

        adapter=new VendorAdapter(options,getContext());
        adapter.startListening();
        rcv.setAdapter(adapter);

    }
}
