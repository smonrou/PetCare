package com.development.petcare.appointments.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.objects.providers.CarerProvider.Companion.CarersList
import com.development.petcare.objects.providers.PetProvider.Companion.PetList

class CarerAppointmentDetailsActivity : AppCompatActivity() {
    private lateinit var Carer_details_cv_userName:TextView
    private lateinit var Carer_details_cv_petName:TextView
    private lateinit var Carer_details_cv_petType:TextView
    private lateinit var Carer_details_cv_location:TextView
    private lateinit var Carer_details_cv_phoneNumber:TextView
    private lateinit var Carer_details_cv_dateTime:TextView
    private lateinit var Carer_details_cv_gcName:TextView
    private lateinit var Carer_details_cv_gcType:TextView
    private lateinit var Carer_details_cv_gcLocation:TextView
    private lateinit var Carer_details_cv_gcPhoneNumber:TextView
    private lateinit var Carer_details_cv2_totalPrice:TextView
    private lateinit var Carer_details_cv_toPayment:CardView
    private lateinit var Carer_details_go_back: ImageView

    private var identification = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carer_appointment_details)
        initComponents()
        initListeners()
        putValues()
        getPet()
        putCarerValues()
        checkMessage()
    }
    private fun initComponents(){
        Carer_details_cv_userName = findViewById(R.id.Carer_details_cv_userName)
        Carer_details_cv_petName = findViewById(R.id.Carer_details_cv_petName)
        Carer_details_cv_petType = findViewById(R.id.Carer_details_cv_petType)
        Carer_details_cv_location = findViewById(R.id.Carer_details_cv_location)
        Carer_details_cv_phoneNumber = findViewById(R.id.Carer_details_cv_phoneNumber)
        Carer_details_cv_dateTime = findViewById(R.id.Carer_details_cv_dateTime)
        Carer_details_cv_gcName = findViewById(R.id.Carer_details_cv_gcName)
        Carer_details_cv_gcType = findViewById(R.id.Carer_details_cv_gcType)
        Carer_details_cv_gcLocation = findViewById(R.id.Carer_details_cv_gcLocation)
        Carer_details_cv_gcPhoneNumber = findViewById(R.id.Carer_details_cv_gcPhoneNumber)
        Carer_details_cv2_totalPrice = findViewById(R.id.Carer_details_cv2_totalPrice)
        Carer_details_cv_toPayment = findViewById(R.id.Carer_details_cv_toPayment)
        Carer_details_go_back = findViewById(R.id.Carer_details_go_back)
    }
    private fun initListeners(){
        Carer_details_cv_toPayment.setOnClickListener {  }
        Carer_details_go_back.setOnClickListener { onBackPressed() }

    }
    @SuppressLint("SetTextI18n")
    private fun putValues(){
        Carer_details_cv_userName.text = intent.getStringExtra("userName")
        Carer_details_cv_location.text = intent.getStringExtra("userCity")
        Carer_details_cv_phoneNumber.text = intent.getStringExtra("userPhone")
        Carer_details_cv_dateTime.text = "${intent.getStringExtra("date")} - ${intent.getStringExtra("time")}"
    }
    private fun putCarerValues(){
        identification = intent.getStringExtra("carerId").toString()
        Carer_details_cv_gcName.text = CarersList.find { it.id == identification }?.name
        Carer_details_cv_gcType.text = CarersList.find { it.id == identification }?.species
        Carer_details_cv_gcLocation.text = CarersList.find { it.id == identification }?.city
        Carer_details_cv_gcPhoneNumber.text = intent.getStringExtra("userPhone")
        Carer_details_cv2_totalPrice.text = CarersList.find { it.id == identification }?.profession
    }
    private fun getPet() {
        Carer_details_cv_petName.text = PetList[intent.getStringExtra("pet")?.toInt()!!].name
        Carer_details_cv_petType.text = PetList[intent.getStringExtra("pet")?.toInt()!!].type
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

}