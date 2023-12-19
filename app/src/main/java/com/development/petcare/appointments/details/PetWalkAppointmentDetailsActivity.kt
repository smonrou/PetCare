package com.development.petcare.appointments.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.development.petcare.R
import com.development.petcare.objects.details.WalkerDetailsActivity
import com.development.petcare.objects.providers.PetProvider.Companion.PetList
import com.development.petcare.objects.providers.WalkerProvider.Companion.WalkerList

class PetWalkAppointmentDetailsActivity : AppCompatActivity() {
    private lateinit var PetWalk_details_cv_userName:TextView
    private lateinit var PetWalk_details_cv_petName:TextView
    private lateinit var PetWalk_details_cv_petType:TextView
    private lateinit var PetWalk_details_cv_location:TextView
    private lateinit var PetWalk_details_cv_phoneNumber:TextView
    private lateinit var PetWalk_details_cv_dateTime:TextView
    private lateinit var PetWalk_details_cv_pwName:TextView
    private lateinit var PetWalk_details_cv_pwLocation:TextView
    private lateinit var PetWalk_details_cv_pwPhoneNumber:TextView
    private lateinit var PetWalk_details_cv_pwType:TextView
    private lateinit var PetWalk_details_cv2_hourlyPrice:TextView
    private lateinit var PetWalk_details_cv2_hourPrice:TextView
    private lateinit var PetWalk_details_cv2_totalPrice:TextView
    private lateinit var PetWalk_details_go_back:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_walker_appointment_details)
        initComponents()
        initListeners()
        putValues()
        getPet()
        calcPrice()
        checkMessage()
    }
    private fun initComponents(){
        PetWalk_details_cv_userName = findViewById(R.id.PetWalk_details_cv_userName)
        PetWalk_details_cv_petName = findViewById(R.id.PetWalk_details_cv_petName)
        PetWalk_details_cv_petType = findViewById(R.id.PetWalk_details_cv_petType)
        PetWalk_details_cv_location = findViewById(R.id.PetWalk_details_cv_location)
        PetWalk_details_cv_phoneNumber = findViewById(R.id.PetWalk_details_cv_phoneNumber)
        PetWalk_details_cv_dateTime = findViewById(R.id.PetWalk_details_cv_dateTime)
        PetWalk_details_cv_pwName = findViewById(R.id.PetWalk_details_cv_pwName)
        PetWalk_details_cv_pwLocation = findViewById(R.id.PetWalk_details_cv_pwLocation)
        PetWalk_details_cv_pwPhoneNumber = findViewById(R.id.PetWalk_details_cv_pwPhoneNumber)
        PetWalk_details_cv2_hourlyPrice = findViewById(R.id.PetWalk_details_cv2_hourlyPrice)
        PetWalk_details_cv2_hourPrice = findViewById(R.id.PetWalk_details_cv2_hourPrice)
        PetWalk_details_cv2_totalPrice = findViewById(R.id.PetWalk_details_cv2_totalPrice)
        PetWalk_details_go_back = findViewById(R.id.PetWalk_details_go_back)
        PetWalk_details_cv_pwType = findViewById(R.id.PetWalk_details_cv_pwType)
    }
    private fun initListeners(){
        PetWalk_details_go_back.setOnClickListener { onBackPressed() }
    }
    @SuppressLint("SetTextI18n")
    private fun putValues(){
        PetWalk_details_cv_userName.text = intent.getStringExtra("UserName")
        PetWalk_details_cv_location.text = intent.getStringExtra("city")
        PetWalk_details_cv_phoneNumber.text = intent.getStringExtra("UserPhone")
        PetWalk_details_cv_pwName.text = intent.getStringExtra("walkername")
        PetWalk_details_cv_pwLocation.text = intent.getStringExtra("walkerLocation")
        PetWalk_details_cv_pwPhoneNumber.text = intent.getStringExtra("phoneNumber")
        PetWalk_details_cv2_hourlyPrice.text = intent.getStringExtra("hourPrice")
        PetWalk_details_cv2_hourPrice.text = intent.getStringExtra("hours")

    }
    private fun getPet(){
        PetWalk_details_cv_petName.text = PetList[intent.getStringExtra("pet")?.toInt()!!].name
        PetWalk_details_cv_petType.text = PetList[intent.getStringExtra("pet")?.toInt()!!].type
            val date = intent.getStringExtra("date")
            val stime = intent.getStringExtra("startTime")
            val etime = intent.getStringExtra("endTime")
            val all = "$date    $stime - $etime"
        PetWalk_details_cv_dateTime.text = all
        PetWalk_details_cv_pwType.text = WalkerList.find { it.name == PetWalk_details_cv_pwName.text }?.species
    }
    private fun calcPrice(){
        val num1 = intent.getStringExtra("price")!!.toInt()
        val num2 = intent.getStringExtra("hours")!!.toInt()
        PetWalk_details_cv2_totalPrice.text = (num1*num2).toString()

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