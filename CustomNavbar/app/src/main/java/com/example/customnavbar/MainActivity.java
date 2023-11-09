package com.example.customnavbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnview = findViewById(R.id.bottomNavigationView);

        bnview.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.Home:
                        loadFrag(new BlankFragmentA());
                        break;

                    case R.id.About:
                        loadFrag(new BlankFragmentB());
                        break;
                    case R.id.Contact:
                        loadFrag(new BlankFragmentC());
                        break;
                    case R.id.logout:
                        loadFrag(new BlankFragmentD());
                        break;
                }

                return true;//shows that the item is selected
            }
        });

    }

    void loadFrag(Fragment BlankFragmentA)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.Frame,BlankFragmentA);
        ft.commit();
    }
}