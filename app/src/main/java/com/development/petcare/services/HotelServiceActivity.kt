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
    private lateinit var hotelServiceHome: ImageView
    private lateinit var recyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_service)
        initComponents()
        initRecycleView()
        initListeners()
    }

    private fun initComponents() {
        hotelServiceHome = findViewById(R.id.hotel_service_home)
        recyclerView = findViewById(R.id.recyclerView_hotel)
    }

    private fun initListeners() {
        hotelServiceHome.setOnClickListener { toHome() }
    }
    private fun initRecycleView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HotelAdapter(HotelProvider.HotelList)
    }
    private fun toHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}