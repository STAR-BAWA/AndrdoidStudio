package com.example.recyclerview_inkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private lateinit var recyclerView: RecyclerView;
private lateinit var adapter: Myadapter
private lateinit var  ModelList:MutableList<Model>
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView =findViewById(R.id.recyclerview);
        recyclerView.layoutManager = LinearLayoutManager(this)

        val model=Model("Star","19","Engineer")
        val model2=Model("Isham","21","Banker")
        val model3=Model("Nootan","43","Teacher")

        ModelList= mutableListOf()

        ModelList.addAll(listOf(model,model2,model3))
        adapter= Myadapter(ModelList)
        recyclerView.adapter= adapter;


    }
}