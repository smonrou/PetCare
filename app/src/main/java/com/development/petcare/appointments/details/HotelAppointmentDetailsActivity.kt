package com.development.petcare.appointments.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.development.petcare.R
import com.development.petcare.objects.providers.HotelProvider.Companion.HotelList
import com.development.petcare.objects.providers.PetProvider.Companion.PetList

class HotelAppointmentDetailsActivity : AppCompatActivity() {
    private lateinit var Hotel_details_cv_userName: TextView
    private lateinit var Hotel_details_cv_petName: TextView
    private lateinit var Hotel_details_cv_petType: TextView
    private lateinit var Hotel_details_cv_location: TextView
    private lateinit var Hotel_details_cv_phoneNumber: TextView
    private lateinit var Hotel_details_cv_dateTime: TextView
    private lateinit var Hotel_details_cv_pwName: TextView
    private lateinit var Hotel_details_cv_pwType: TextView
    private lateinit var Hotel_details_cv_pwLocation: TextView
    private lateinit var Hotel_details_cv_pwPhoneNumber: TextView
    private lateinit var Hotel_details_cv2_hourlyPrice: TextView
    private lateinit var Hotel_details_cv2_hourPrice: TextView
    private lateinit var Hotel_details_go_back: ImageView

    private var identification = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_appointment_details)
        initComponents()
        putValues()
        putHotelValues()
        getPet()
        initListeners()
    }

    private fun initComponents() {
        Hotel_details_cv_userName = findViewById(R.id.Hotel_details_cv_userName)
        Hotel_details_cv_petName = findViewById(R.id.Hotel_details_cv_petName)
        Hotel_details_cv_petType = findViewById(R.id.Hotel_details_cv_petType)
        Hotel_details_cv_location = findViewById(R.id.Hotel_details_cv_location)
        Hotel_details_cv_phoneNumber = findViewById(R.id.Hotel_details_cv_phoneNumber)
        Hotel_details_cv_dateTime = findViewById(R.id.Hotel_details_cv_dateTime)
        Hotel_details_cv_pwName = findViewById(R.id.Hotel_details_cv_pwName)
        Hotel_details_cv_pwType = findViewById(R.id.Hotel_details_cv_pwType)
        Hotel_details_cv_pwLocation = findViewById(R.id.Hotel_details_cv_pwLocation)
        Hotel_details_cv_pwPhoneNumber = findViewById(R.id.Hotel_details_cv_pwPhoneNumber)
        Hotel_details_cv2_hourlyPrice = findViewById(R.id.Hotel_details_cv2_hourlyPrice)
        Hotel_details_cv2_hourPrice = findViewById(R.id.Hotel_details_cv2_hourPrice)
        Hotel_details_go_back = findViewById(R.id.Hotel_details_go_back)
    }

    private fun initListeners() {
        Hotel_details_go_back.setOnClickListener { onBackPressed() }
    }

    @SuppressLint("SetTextI18n")
    private fun putValues() {
        Hotel_details_cv_userName.text = intent.getStringExtra("userName")
        Hotel_details_cv_location.text = intent.getStringExtra("city")
        Hotel_details_cv_phoneNumber.text = intent.getStringExtra("userPhone")
        Hotel_details_cv_dateTime.text =
            "${intent.getStringExtra("startDate")}-${intent.getStringExtra("endDate")}"
    }

    private fun putHotelValues() {
        identification = intent.getStringExtra("hotelId").toString()
        Hotel_details_cv_pwName.text =
            HotelList.find { it.id == identification }?.name
        Hotel_details_cv_pwType.text =
            HotelList.find { it.id == identification }?.experience
        Hotel_details_cv_pwLocation.text =
            HotelList.find { it.id == identification }?.city
        Hotel_details_cv_pwPhoneNumber.text = intent.getStringExtra("userPhone")
        Hotel_details_cv2_hourlyPrice.text =
            HotelList.find { it.id == identification }?.species
        Hotel_details_cv2_hourPrice.text = intent.getStringExtra("dateDifference")
    }

    private fun getPet() {
        Hotel_details_cv_petName.text = PetList[intent.getStringExtra("pet")?.toInt()!!].name
        Hotel_details_cv_petType.text = PetList[intent.getStringExtra("pet")?.toInt()!!].type
    }

}