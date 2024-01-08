package com.development.petcare.objects.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.adapters.PhotoAdapter
import com.development.petcare.objects.providers.PhotoProvider


class VetDetailsActivity : AppCompatActivity() {
    private lateinit var vd_name:TextView
    private lateinit var vd_country:TextView
    private lateinit var vd_city:TextView
    private lateinit var vd_emergency:ImageView
    private lateinit var vd_specialty:TextView
    private lateinit var vd_experience:TextView
    private lateinit var rv_services:RecyclerView
    private lateinit var vd_species:TextView
    private lateinit var vd_address:TextView
    private lateinit var vd_schedule:TextView
    private lateinit var vd_Refund:TextView
    private lateinit var rv_photos:RecyclerView
    private lateinit var btn_appointment:Button
    private lateinit var vd_goBack:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vet_details)
        initComponents()
        initListeners()
        initRecyclerViews()
    }
    private fun initComponents(){
        vd_name = findViewById(R.id.vd_name)
        vd_country = findViewById(R.id.vd_country)
        vd_city = findViewById(R.id.vd_city)
        vd_emergency = findViewById(R.id.vd_emergency)
        vd_specialty = findViewById(R.id.vd_specialty)
        vd_experience = findViewById(R.id.vd_experience)
        rv_services = findViewById(R.id.rv_services)
        vd_species = findViewById(R.id.vd_species)
        vd_address = findViewById(R.id.vd_address)
        vd_schedule = findViewById(R.id.vd_schedule)
        vd_Refund = findViewById(R.id.vd_Refund)
        rv_photos = findViewById(R.id.rv_photos)
        btn_appointment = findViewById(R.id.btn_appointment)
        vd_goBack = findViewById(R.id.vd_goBack)
    }
    private fun initListeners(){
        btn_appointment.setOnClickListener {  }
        btn_appointment.setOnClickListener { onBackPressed()
        finish()}
    }
    private fun initRecyclerViews(){
        rv_services.layoutManager = LinearLayoutManager(this)
        rv_photos.layoutManager = LinearLayoutManager(this)
        rv_photos.adapter = PhotoAdapter(PhotoProvider.PhotoList)
    }
}