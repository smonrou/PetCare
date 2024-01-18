package com.development.petcare.objects.details

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.appointments.form.HotelAppointmentActivity
import com.development.petcare.objects.adapters.BedroomAdapter
import com.development.petcare.objects.adapters.ExtrasAdapter
import com.development.petcare.objects.providers.HotelProvider.Companion.HotelList

class HotelDetailsActivity : AppCompatActivity() {
    private lateinit var hotel_details_name: TextView
    private lateinit var hotel_details_city: TextView
    private lateinit var hotel_details_country: TextView
    private lateinit var hotel_details_cv2_experience: TextView
    private lateinit var hotel_details_cv2_species: TextView
    private lateinit var rv_bedrooms: RecyclerView
    private lateinit var hotel_details_cv4_schedules: TextView
    private lateinit var rv_extras: RecyclerView
    private lateinit var hotel_details_makeAppointment: TextView
    private lateinit var Hotel_details_go_back: ImageView
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)
        initComponents()
        initListeners()
        id = intent.getStringExtra("id").toString()
        initValues()
        initRecyclerViews()
    }

    private fun initComponents() {
        hotel_details_name = findViewById(R.id.hotel_details_name)
        hotel_details_city = findViewById(R.id.hotel_details_city)
        hotel_details_country = findViewById(R.id.hotel_details_country)
        hotel_details_cv2_experience = findViewById(R.id.hotel_details_cv2_experience)
        hotel_details_cv2_species = findViewById(R.id.hotel_details_cv2_species)
        hotel_details_cv4_schedules = findViewById(R.id.hotel_details_cv4_schedules)
        hotel_details_makeAppointment = findViewById(R.id.hotel_details_makeAppointment)
        Hotel_details_go_back = findViewById(R.id.Hotel_details_go_back)
    }

    private fun initListeners() {
        hotel_details_makeAppointment.setOnClickListener { toHotelAppointment() }
        Hotel_details_go_back.setOnClickListener { onBackPressed() }
    }

    private fun initValues() {
        val id = intent.getStringExtra("id")
        hotel_details_name.text = HotelList.find { it.id == id }?.name.toString()
        hotel_details_city.text = HotelList.find { it.id == id }?.city.toString()
        hotel_details_country.text = HotelList.find { it.id == id }?.country.toString()
        hotel_details_cv2_experience.text = HotelList.find { it.id == id }?.experience.toString()
        hotel_details_cv2_species.text = HotelList.find { it.id == id }?.species.toString()
        setSchedule()
    }

    private fun initRecyclerViews() {
        rv_bedrooms = findViewById(R.id.rv_bedrooms)
        rv_extras = findViewById(R.id.rv_extras)
        rv_bedrooms.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_bedrooms.adapter = HotelList.find { it.id == id }?.bedrooms?.let { BedroomAdapter(it) }
        rv_extras.layoutManager = LinearLayoutManager(this)
        rv_extras.adapter = HotelList.find { it.id == id }?.extrasTitles?.let { ExtrasAdapter(it) }
    }

    private fun toHotelAppointment() {
        val intent = Intent(this, HotelAppointmentActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun setSchedule() {
        val scheduleArray = HotelList.find { it.id == id }?.schedule
        val stringBuilder = StringBuilder()
        if (scheduleArray != null) {
            for (item in scheduleArray) {
                stringBuilder.append(item).append("\n")
            }
        }
        hotel_details_cv4_schedules.text = stringBuilder.toString()
    }
}