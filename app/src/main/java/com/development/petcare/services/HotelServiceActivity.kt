package com.development.petcare.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.mains.HomeActivity
import com.development.petcare.R
import com.development.petcare.objects.providers.HotelProvider
import com.development.petcare.objects.adapters.HotelAdapter

class HotelServiceActivity : AppCompatActivity() {
    private lateinit var hotel_service_home: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_service)
        initComponents()
        initRecycleView()
        initListeners()
    }

    private fun initComponents() {
        hotel_service_home = findViewById(R.id.hotel_service_home)
    }

    private fun initListeners() {
        hotel_service_home.setOnClickListener { toHome() }
    }
    private fun initRecycleView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_hotel)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HotelAdapter(HotelProvider.HotelList)
    }
    private fun toHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}