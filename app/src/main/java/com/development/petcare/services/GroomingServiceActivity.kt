package com.development.petcare.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.mains.HomeActivity
import com.development.petcare.objects.adapters.GroomerAdapter
import com.development.petcare.objects.providers.GroomerProvider.Companion.GroomerList

class GroomingServiceActivity : AppCompatActivity() {
    private lateinit var groomingHome: ImageView
    private lateinit var recyclerViewGroomer: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grooming_service)
        initComponents()
        initRecycleView()
        initListeners()
    }

    private fun initComponents() {
        groomingHome = findViewById(R.id.grooming_home)
    }

    private fun initListeners() {
        groomingHome.setOnClickListener { toHome() }
    }

    private fun initRecycleView() {
        recyclerViewGroomer = findViewById(R.id.recyclerView_groomer)
        recyclerViewGroomer.layoutManager = LinearLayoutManager(this)
        recyclerViewGroomer.adapter = GroomerAdapter(GroomerList)
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}