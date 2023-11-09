package com.example.bookworm_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.rey.material.widget.FloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.paperdb.Paper;

//import io.paperdb.Paper;

public class Home_Activity extends AppCompatActivity {
    private Button LogoutBtn, helpdeskBtn;
   private FloatingActionButton Cart_btn;
    private RecyclerView recyclerView;
    User_Main_Product_Adapter adapter;
    DatabaseReference mbase;
    private SearchView searchView;
    private MenuItem menuItem;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mbase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LogoutBtn=(Button) findViewById(R.id.logout_btn);
        helpdeskBtn=(Button) findViewById(R.id.helpdesk_btn);
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Menu");
        FirebaseRecyclerOptions<User_Main_Product_Modal> options = new FirebaseRecyclerOptions.Builder<User_Main_Product_Modal>().setQuery(userRef, User_Main_Product_Modal.class).build();
        adapter = new User_Main_Product_Adapter(options);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "Home Recycler", Toast.LENGTH_SHORT).show();
        adapter.startListening();

        Cart_btn = (FloatingActionButton) findViewById(R.id.cart_btn);
        Cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home_Activity.this,ShoppingCart.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.logout_btn) {
            Paper.book().destroy();
            Intent intent = new Intent(Home_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.helpdesk_btn) {
            Intent intent = new Intent(Home_Activity.this, Contact_Support_Activity_user.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.User_details) {
            Intent intent = new Intent(Home_Activity.this, User_profil_phone.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.order_details) {
            Intent intent = new Intent(Home_Activity.this, order_user.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
MenuInflater inflater=getMenuInflater();
inflater.inflate(R.menu.menus,menu);
return true;
    }


 /*     LogoutBtn=(Button) findViewById(R.id.logouts_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                Paper.book().destroy();
//                Intent intent =new Intent(Home_Activity.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        helpdeskBtn=(Button) findViewById(R.id.helpdesks_btn);
        helpdeskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Activity.this,Contact_Support_Activity_user.class);
                startActivity(intent);
            }
        });
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
      super.onCreateOptionsMenu(menu,inflater);
  }
*/

  private void processSearch(String msg) {

      DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference().child("Products");
      FirebaseRecyclerOptions<User_Main_Product_Modal> options = new FirebaseRecyclerOptions.Builder<User_Main_Product_Modal>()
              .setQuery(vendorRef.orderByChild("BookName").startAt(msg).endAt(msg+"\uf8ff"), User_Main_Product_Modal.class)
              .build();

     adapter=new User_Main_Product_Adapter(options);
      adapter.startListening();
      recyclerView.setAdapter(adapter);

  }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}