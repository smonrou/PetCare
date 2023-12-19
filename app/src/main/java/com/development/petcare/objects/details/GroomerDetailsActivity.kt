package com.development.petcare.objects.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.appointments.form.GroomerAppointmentActivity
import com.development.petcare.mains.HomeActivity
import com.development.petcare.objects.providers.GroomerProvider.Companion.GroomerList


class GroomerDetailsActivity : AppCompatActivity() {
    private lateinit var title_grooming_name: TextView
    private lateinit var groomerDetails_stars: TextView
    private lateinit var groomerDetails_location: TextView
    private lateinit var cv_place_description: TextView
    private lateinit var img_1: ImageView
    private lateinit var cv_service_description: TextView
    private lateinit var img_2: ImageView
    private lateinit var cv_experience_description: TextView
    private lateinit var img_3: ImageView
    private lateinit var cv_schedule_description: TextView
    private lateinit var cv_prices_description: TextView
    private lateinit var img_4: ImageView
    private lateinit var iv_toHome: ImageView
    private lateinit var cv_species_description: TextView
    private lateinit var GroomerDetails_toMakeAppointment:CardView

    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groomer_details)
        initComponents()
        initListeners()
        putValues()
    }

    private fun initComponents() {
        title_grooming_name = findViewById(R.id.title_grooming_name)
        groomerDetails_stars = findViewById(R.id.groomerDetails_stars)
        groomerDetails_location = findViewById(R.id.groomerDetails_location)
        cv_place_description = findViewById(R.id.cv_place_description)
        img_1 = findViewById(R.id.img_1)
        cv_service_description = findViewById(R.id.cv_service_description)
        img_2 = findViewById(R.id.img_2)
        cv_experience_description = findViewById(R.id.cv_experience_description)
        img_3 = findViewById(R.id.img_3)
        cv_schedule_description = findViewById(R.id.cv_schedule_description)
        cv_prices_description = findViewById(R.id.cv_prices_description)
        img_4 = findViewById(R.id.img_4)
        iv_toHome = findViewById(R.id.iv_toHome)
        cv_species_description = findViewById(R.id.cv_species_description)
        GroomerDetails_toMakeAppointment = findViewById(R.id.GroomerDetails_toMakeAppointment)
    }

    private fun initListeners() {
        iv_toHome.setOnClickListener { toHome() }
        GroomerDetails_toMakeAppointment.setOnClickListener { toMakeAppointment() }
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun putValues() {
        title_grooming_name.text = intent.getStringExtra("name")
        groomerDetails_stars.text = intent.getStringExtra("numStars")
        groomerDetails_location.text = intent.getStringExtra("city")
        cv_place_description.text = intent.getStringExtra("placeDescription")
        cv_service_description.text = intent.getStringExtra("serviceDescription")
        cv_experience_description.text = intent.getStringExtra("description")
        cv_schedule_description.text = intent.getStringExtra("schedule")
        cv_prices_description.text = intent.getStringExtra("priceRange")
        cv_species_description.text = intent.getStringExtra("species")
        id = intent.getStringExtra("id").toString()
    }
    private fun toMakeAppointment(){
        val intent = Intent(this, GroomerAppointmentActivity::class.java)
        intent.putExtra("id", id)
        Log.i("saul", "$id GroomerDetails")
        userInfo(intent)
        startActivity(intent)
    }
    private fun userInfo(intent: Intent){
        intent.putExtra("userName", "Saúl Monroy")
        intent.putExtra("userAddress", "saulandresmm@gmail.com")
        intent.putExtra("userCity", "Chiquimula")
        intent.putExtra("userPhone", "+52 58811634")
    }
    private fun getGroomerId(): String? {
        val name = title_grooming_name.text
        return (GroomerList.find { it.name == name }?.id)

    }
}