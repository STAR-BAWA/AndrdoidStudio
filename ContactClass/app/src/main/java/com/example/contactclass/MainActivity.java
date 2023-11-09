package com.example.contactclass;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactclass.SqliteDatabase;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
   private SqliteDatabase mDatabase;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      RecyclerView contactView = findViewById(R.id.myContactList);
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      contactView.setLayoutManager(linearLayoutManager);
      contactView.setHasFixedSize(true);
      mDatabase = new SqliteDatabase(this);
      ArrayList<Contacts> allContacts = mDatabase.listContacts();
      if (allContacts.size() > 0) {
         contactView.setVisibility(View.VISIBLE);
         ContactAdapter mAdapter = new ContactAdapter(this, allContacts);
         contactView.setAdapter(mAdapter);
      }
      else {
         contactView.setVisibility(View.GONE);
         Toast.makeText(this, "There is no contact in the database. Start adding now", Toast.LENGTH_LONG).show();
      }
      Button btnAdd = findViewById(R.id.btnAdd);
      btnAdd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            addTaskDialog();
         }
      });
   }
   private void addTaskDialog() {
      LayoutInflater inflater = LayoutInflater.from(this);
      View subView = inflater.inflate(R.layout.add_contacts, null);
      final EditText nameField = subView.findViewById(R.id.enterName);
      final EditText noField = subView.findViewById(R.id.enterPhoneNum);
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setTitle("Add new CONTACT");
      builder.setView(subView);
      builder.create();
      builder.setPositiveButton("ADD CONTACT", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
            final String name = nameField.getText().toString();
            final String ph_no = noField.getText().toString();
            if (TextUtils.isEmpty(name)) {
               Toast.makeText(MainActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
            }
            else {
               Contacts newContact = new Contacts(name, ph_no);
               mDatabase.addContacts(newContact);
               finish();
               startActivity(getIntent());
            }
         }
      });
      builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
               Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
         }
      });
      builder.show();
   }
   @Override
   protected void onDestroy() {
      super.onDestroy();
      if (mDatabase != null) {
         mDatabase.close();
      }
   }
}