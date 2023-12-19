package com.development.petcare.objects.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.appointments.form.PetWalkerAppointmentActivity
import com.development.petcare.mains.HomeActivity


class WalkerDetailsActivity : AppCompatActivity() {
    private lateinit var walkerDetails_name: TextView
    private lateinit var walkerDetails_age: TextView
    private lateinit var walkerDetails_location: TextView
    private lateinit var walkerDetails_howIs: TextView
    private lateinit var walkerDetails_description: TextView
    private lateinit var walkerDetails_pricestxt: TextView
    private lateinit var walkerDetails_servicetxt: TextView
    private lateinit var walkerDetails_experiencetxt: TextView
    private lateinit var walkerDetails_scheduletxt: TextView
    private lateinit var walkerDetails_makeAppointment: CardView
    private lateinit var walkerDetails_toHome: ImageView

    private var species:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walker_details)
        initComponents()
        initListeners()
        putValues()
    }

    private fun initComponents() {
        walkerDetails_name = findViewById(R.id.walkerDetails_name)
        walkerDetails_age = findViewById(R.id.walkerDetails_age)
        walkerDetails_location = findViewById(R.id.walkerDetails_location)
        walkerDetails_howIs = findViewById(R.id.walkerDetails_howIs)
        walkerDetails_description = findViewById(R.id.walkerDetails_description)
        walkerDetails_pricestxt = findViewById(R.id.walkerDetails_pricestxt)
        walkerDetails_servicetxt = findViewById(R.id.walkerDetails_servicetxt)
        walkerDetails_experiencetxt = findViewById(R.id.walkerDetails_experiencetxt)
        walkerDetails_scheduletxt = findViewById(R.id.walkerDetails_scheduletxt)
        walkerDetails_makeAppointment = findViewById(R.id.walkerDetails_makeAppointment)
        walkerDetails_toHome = findViewById(R.id.walkerDetails_toHome)

    }

    private fun initListeners() {
        walkerDetails_toHome.setOnClickListener { toHome() }
        walkerDetails_makeAppointment.setOnClickListener { toMakeAppointment() }
    }

    @SuppressLint("SetTextI18n")
    private fun putValues() {
        walkerDetails_name.text = intent.getStringExtra("name")
        walkerDetails_age.text = intent.getStringExtra("age")
        walkerDetails_location.text = intent.getStringExtra("city")
        walkerDetails_howIs.text = "How is " + intent.getStringExtra("name")
        walkerDetails_description.text = intent.getStringExtra("walkerDescription")
        walkerDetails_pricestxt.text = intent.getStringExtra("hourPrice")
        walkerDetails_servicetxt.text = intent.getStringExtra("serviceDescription")
        walkerDetails_experiencetxt.text = intent.getStringExtra("experience")
        walkerDetails_scheduletxt.text = intent.getStringExtra("schedule")
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun toMakeAppointment() {
        val intent = Intent(this, PetWalkerAppointmentActivity::class.java)
        intent.putExtra("UserName", "Saúl Monroy" )
        intent.putExtra("UserEmail", "saulandresmm@gmail.com")
        intent.putExtra("city", walkerDetails_location.text) //Solo de prueba, datos que aún no se guardan del Usuario
        intent.putExtra( "phoneNumber", "+502 58811634") //Solo de prueba, datos que aún no se guardan del Usuario


        intent.putExtra("walkerName",  walkerDetails_name.text)
        intent.putExtra("walkerAge",  walkerDetails_age.text)
        intent.putExtra("walkerLocation",  walkerDetails_location.text)
        intent.putExtra("walkerPrice",  walkerDetails_pricestxt.text)
        intent.putExtra("walkerSchedule",  walkerDetails_scheduletxt.text)
        intent.putExtra("walkerEmail",  "petWalker@gmail.com")
        intent.putExtra("walkerPhone",  "+502 20202020")
        species = intent.getStringExtra("species").toString()
        intent.putExtra("walkerSpecies", species )
        startActivity(intent)
    }
}