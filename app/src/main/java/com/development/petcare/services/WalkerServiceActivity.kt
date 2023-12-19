package com.development.petcare.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.mains.HomeActivity
import com.development.petcare.objects.adapters.WalkerAdapter
import com.development.petcare.objects.providers.WalkerProvider

class WalkerServiceActivity : AppCompatActivity() {
    private lateinit var walkerService_toHome: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walker_service)
        initComponents()
        initListener()
        initRecycleView()

    }

    private fun initComponents() {
        walkerService_toHome = findViewById(R.id.walkerService_toHome)
    }

    private fun initListener() {
        walkerService_toHome.setOnClickListener { toHome() }
    }

    private fun initRecycleView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_walker)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WalkerAdapter(WalkerProvider.WalkerList)
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


}