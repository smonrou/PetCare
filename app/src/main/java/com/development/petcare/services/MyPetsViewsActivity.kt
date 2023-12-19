package com.development.petcare.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.objects.addobject.AddPetActivity
import com.development.petcare.mains.HomeActivity
import com.development.petcare.R
import com.development.petcare.objects.adapters.PetAdapter
import com.development.petcare.objects.providers.PetProvider.Companion.PetList

class MyPetsViewsActivity : AppCompatActivity() {

    private lateinit var myPetsView_home: ImageView
    private lateinit var addPetViews: ImageView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pets_views)

        initComponents()
        initRecyclerView()
        initListener()
    }
    private fun initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PetAdapter(PetList)
    }
    private fun initComponents(){
        myPetsView_home = findViewById<ImageView>(R.id.myPetsView_home)
        addPetViews = findViewById(R.id.addPetViews)
    }
    private fun initListener(){
        myPetsView_home.setOnClickListener { toHome() }
        addPetViews.setOnClickListener { toAddPetsView()

        }
    }
    private fun toAddPetsView(){
        val intent = Intent(this, AddPetActivity::class.java)
        startActivity(intent)
    }
    private fun toHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}