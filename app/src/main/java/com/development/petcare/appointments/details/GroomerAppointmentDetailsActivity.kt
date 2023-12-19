package com.development.petcare.appointments.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.appointments.PaymentActivity
import com.development.petcare.objects.providers.GroomerProvider.Companion.GroomerList
import com.development.petcare.objects.providers.PetProvider.Companion.PetList



class GroomerAppointmentDetailsActivity : AppCompatActivity() {
    private lateinit var Groomer_details_cv_userName: TextView
    private lateinit var Groomer_details_cv_petName: TextView
    private lateinit var Groomer_details_cv_petType: TextView
    private lateinit var Groomer_details_cv_location: TextView
    private lateinit var Groomer_details_cv_phoneNumber: TextView
    private lateinit var Groomer_details_cv_dateTime: TextView
    private lateinit var Groomer_details_cv_gcName: TextView
    private lateinit var Groomer_details_cv_gcType: TextView
    private lateinit var Groomer_details_cv_gcLocation: TextView
    private lateinit var Groomer_details_cv_gcPhoneNumber: TextView
    private lateinit var Groomer_details_cv2_totalPrice: TextView
    private lateinit var PetWalk_details_go_back: ImageView
    private lateinit var Groomer_details_cv_toPayment:CardView
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groomer_appointment_details)
        initComponents()
        initListeners()
        putValues()
        checkMessage()
        id = intent.getStringExtra("id").toString()
        Log.i("saul", "$id GroomerAppointmentDetailsActivity")
        setGroomerDetails()
    }

    private fun initComponents() {
        Groomer_details_cv_userName = findViewById(R.id.Groomer_details_cv_userName)
        Groomer_details_cv_petName = findViewById(R.id.Groomer_details_cv_petName)
        Groomer_details_cv_petType = findViewById(R.id.Groomer_details_cv_petType)
        Groomer_details_cv_location = findViewById(R.id.Groomer_details_cv_location)
        Groomer_details_cv_phoneNumber = findViewById(R.id.Groomer_details_cv_phoneNumber)
        Groomer_details_cv_dateTime = findViewById(R.id.Groomer_details_cv_dateTime)
        Groomer_details_cv_gcName = findViewById(R.id.Groomer_details_cv_gcName)
        Groomer_details_cv_gcType = findViewById(R.id.Groomer_details_cv_gcType)
        Groomer_details_cv_gcLocation = findViewById(R.id.Groomer_details_cv_gcLocation)
        Groomer_details_cv_gcPhoneNumber = findViewById(R.id.Groomer_details_cv_gcPhoneNumber)
        Groomer_details_cv2_totalPrice = findViewById(R.id.Groomer_details_cv2_totalPrice)
        PetWalk_details_go_back = findViewById(R.id.PetWalk_details_go_back)
        Groomer_details_cv_toPayment = findViewById(R.id.Groomer_details_cv_toPayment)
    }

    private fun initListeners() {
        PetWalk_details_go_back.setOnClickListener { onBackPressed() }
        Groomer_details_cv_toPayment.setOnClickListener{ toPayment() }
    }

    @SuppressLint("SetTextI18n")
    private fun putValues() {
        Groomer_details_cv_userName.text = intent.getStringExtra("userName")
        Groomer_details_cv_location.text = intent.getStringExtra("userCity")
        Groomer_details_cv_phoneNumber.text = intent.getStringExtra("userPhone")
        Groomer_details_cv_dateTime.text =
            "${intent.getStringExtra("date")}    ${intent.getStringExtra("time")}"

        getPet()

    }

    private fun getPet() {
        Groomer_details_cv_petName.text = PetList[intent.getStringExtra("pet")?.toInt()!!].name
        Groomer_details_cv_petType.text = PetList[intent.getStringExtra("pet")?.toInt()!!].type
    }

    private fun setGroomerDetails() {
        val name =  GroomerList.find { it.id == id }?.name.toString()
        val type = GroomerList.find { it.id == id }?.species.toString()
        val location = GroomerList.find { it.id == id }?.city.toString()
        val price = GroomerList.find { it.id == id }?.priceRange.toString()
        val phone = intent.getStringExtra("userPhone")
        Groomer_details_cv_gcName.text = name
        Groomer_details_cv_gcType.text = type
        Groomer_details_cv_gcLocation.text = location
        Groomer_details_cv2_totalPrice.text = price
        Groomer_details_cv_gcPhoneNumber.text = phone
    }

    private fun checkMessage() {
        val toast = Toast.makeText(
            this,
            "Check if all the information is correct.",
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    private fun toPayment(){
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("service", "Grooming")
        intent.putExtra("name", GroomerList.find { it.id == id }?.name)
        intent.putExtra("price", GroomerList.find { it.id == id }?.priceRange)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
