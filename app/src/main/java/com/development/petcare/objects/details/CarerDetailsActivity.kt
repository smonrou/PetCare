

package com.development.petcare.objects.details


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.appointments.form.CarerAppointmentActivity
import com.development.petcare.objects.providers.CarerProvider.Companion.CarersList
import de.hdodenhof.circleimageview.CircleImageView


class CarerDetailsActivity : AppCompatActivity() {
    private lateinit var title_name:TextView
    private lateinit var carer_pictures:CircleImageView
    private lateinit var carerDetails_tvLocation:TextView
    private lateinit var carerDetails_tvProfession:TextView
    private lateinit var carerDetails_tvAge:TextView
    private lateinit var carerDetails_tvSpecies:TextView
    private lateinit var CarerDetails_go_back:ImageView
    private lateinit var Carer_toMakeAppointment:CardView

    private var identification = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carer_details)
        initComponents()
        initListeners()
        putValues()
    }
    private fun initComponents(){
        title_name = findViewById(R.id.title_name)
        carer_pictures = findViewById(R.id.carer_pictures)
        carerDetails_tvLocation = findViewById(R.id.carerDetails_tvLocation)
        carerDetails_tvProfession = findViewById(R.id.carerDetails_tvProfession)
        carerDetails_tvAge = findViewById(R.id.carerDetails_tvAge)
        carerDetails_tvSpecies = findViewById(R.id.carerDetails_tvSpecies)
        CarerDetails_go_back = findViewById(R.id.CarerDetails_go_back)
        Carer_toMakeAppointment = findViewById(R.id.Carer_toMakeAppointment)
    }
    private fun initListeners(){
        CarerDetails_go_back.setOnClickListener { onBackPressed() }
        Carer_toMakeAppointment.setOnClickListener { toCarerAppointment() }
    }
    private fun putValues(){
        title_name.text = intent.getStringExtra("name")
        carerDetails_tvLocation.text = intent.getStringExtra("city")
        carerDetails_tvAge.text = intent.getStringExtra("age")
        carerDetails_tvProfession.text = intent.getStringExtra("profession")
        carerDetails_tvSpecies.text = intent.getStringExtra("species")
    }
    private fun toCarerAppointment(){
        val intent = Intent(this, CarerAppointmentActivity::class.java)
        userInfo(intent)
        startActivity(intent)
    }
    private fun userInfo(intent: Intent){
        intent.putExtra("userName", "Saúl Monroy")
        intent.putExtra("userAddress", "saulandresmm@gmail.com")
        intent.putExtra("userCity", "Chiquimula")
        intent.putExtra("userPhone", "+52 58811634")
        intent.putExtra("carerId", getId())
    }
    private fun getId(): String {
        val name = title_name.text
        identification = (CarersList.find { it.name == name }?.id).toString()
        return identification
    }
}