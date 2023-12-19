package com.development.petcare.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.mains.HomeActivity
import com.development.petcare.R
import com.development.petcare.objects.adapters.VetAdapter
import com.development.petcare.objects.providers.VetProvider

class CarerServiceActivity : AppCompatActivity() {
    private lateinit var carer_service_home:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carer_service)
        initComponents()
        initRecycleView()
        initListeners()
    }

    private fun initComponents() {
        carer_service_home = findViewById(R.id.carer_service_home)
    }

    private fun initRecycleView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_carer)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VetAdapter(VetProvider.VetList)
    }

    private fun initListeners() {
       carer_service_home.setOnClickListener { toHome() }
    }
    private fun toHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}