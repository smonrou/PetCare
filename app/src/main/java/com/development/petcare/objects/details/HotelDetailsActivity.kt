package com.development.petcare.objects.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.appointments.form.HotelAppointmentActivity
import com.development.petcare.objects.providers.HotelProvider.Companion.HotelList

class HotelDetailsActivity : AppCompatActivity() {
    private lateinit var title_name:TextView
    private lateinit var hotelDetails_tvLocation:TextView
    private lateinit var hotelDetails_tvStars:TextView
    private lateinit var hotelDetails_tvDescription:TextView
    private lateinit var hotelDetails_tvPrice:TextView
    private lateinit var go_back: ImageView
    private lateinit var Hotel_toMakeAppointment:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)
        initComponents()
        initListeners()
        initValues()
    }

    private fun initComponents() {
        title_name = findViewById(R.id.title_name)
        hotelDetails_tvLocation = findViewById(R.id.hotelDetails_tvLocation)
        hotelDetails_tvStars = findViewById(R.id.hotelDetails_tvStars)
        hotelDetails_tvDescription = findViewById(R.id.hotelDetails_tvDescription)
        hotelDetails_tvPrice = findViewById(R.id.hotelDetails_tvPrice)
        go_back = findViewById(R.id.go_back)
        Hotel_toMakeAppointment = findViewById(R.id.Hotel_toMakeAppointment)
    }

    private fun initListeners() {
        go_back.setOnClickListener { onBackPressed() }
        Hotel_toMakeAppointment.setOnClickListener { toHotelAppointment() }
    }
    private fun initValues(){
        title_name.text = intent.getStringExtra("hotel_name")
        hotelDetails_tvLocation.text = intent.getStringExtra("city")
        hotelDetails_tvStars.text = intent.getStringExtra("stars")
        hotelDetails_tvDescription.text = intent.getStringExtra("description")
        hotelDetails_tvPrice.text = intent.getStringExtra("price")
    }
    private fun toHotelAppointment(){
        val name = title_name.text
        val intent = Intent(this, HotelAppointmentActivity::class.java)
        val id = (HotelList.find { it.hotel_name == name }?.id_hotel).toString()
        intent.putExtra("hotelId", id)
        intent.putExtra("userName", "Saúl Monroy")
        intent.putExtra("userAddress", "saulandresmm@gmail.com")
        intent.putExtra("city", "Chiquimula")
        intent.putExtra("userPhone", "+52 58811634")
        startActivity(intent)
    }

}