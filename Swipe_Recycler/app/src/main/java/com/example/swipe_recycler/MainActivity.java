package com.example.swipe_recycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity {


    List<ViewModel> list;
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<ViewModel>();
        ViewModel vh = new ViewModel("Star", "23", "8318407131", R.drawable.ic_launcher_foreground);
        ViewModel vh2 = new ViewModel("Isham", "16", "8318407131", R.drawable.ic_launcher_foreground);
        ViewModel vh3 = new ViewModel("Ravi", "20", "8318407131", R.drawable.ic_launcher_foreground);
        ViewModel vh4 = new ViewModel("Sonia", "43", "8318407131", R.drawable.ic_launcher_foreground);

        list.add(vh);
        list.add(vh2);
        list.add(vh3);
        list.add(vh4);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this, list);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

     String deleted_Movie=null;
    String del_age=null;
    String del_contact=null;
    int image_code= 0;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
            //used only for dragging the items of a recycler view  not in swiping 
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


            int position= viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                 deleted_Movie=list.get(position).name;
                 del_contact=list.get(position).contact;
                 del_age=list.get(position).Age;
                 image_code=list.get(position).img_code;
                  list.remove(position);
                    adapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView,"DELETED", BaseTransientBottomBar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position,new ViewModel(deleted_Movie,del_age,del_contact,image_code));
                            adapter.notifyItemInserted(position);
                        }
                    }).show();
//                                      Toast.makeText(MainActivity.this, "Left Swiped", Toast.LENGTH_SHORT).show();
                    break;
                    
                case ItemTouchHelper.RIGHT:
                    deleted_Movie=list.get(position).name;
                 del_contact=list.get(position).contact;
                 del_age=list.get(position).Age;
                 image_code=list.get(position).img_code;
                  list.remove(position);
                    adapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView,"ARCHIVED", BaseTransientBottomBar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position,new ViewModel(deleted_Movie,del_age,del_contact,image_code));
                            adapter.notifyItemInserted(position);
                        }
                    }).show();
                    break;
                    

            }
        }

        public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,float dX, float dY,int actionState, boolean isCurrentlyActive){

    new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            .addSwipeLeftBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.teal_200))
            .addSwipeRightBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_700))
            .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
            .addSwipeRightActionIcon(R.drawable.ic_baseline_archive_24)
            .create()
            .decorate();

    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
}
    };
}
      
        
        