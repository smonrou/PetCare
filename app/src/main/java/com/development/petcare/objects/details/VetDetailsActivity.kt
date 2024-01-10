package com.development.petcare.objects.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.appointments.form.CarerAppointmentActivity
import com.development.petcare.objects.adapters.PhotoAdapter
import com.development.petcare.objects.adapters.ServiceAdapter
import com.development.petcare.objects.providers.VetProvider.Companion.VetList
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class VetDetailsActivity : AppCompatActivity() {
    private lateinit var vd_name: TextView
    private lateinit var vd_country: TextView
    private lateinit var vd_city: TextView
    private lateinit var vd_emergency: ImageView
    private lateinit var vd_specialty: TextView
    private lateinit var vd_experience: TextView
    private lateinit var rv_services: RecyclerView
    private lateinit var rv_photos: RecyclerView
    private lateinit var vd_species: TextView
    private lateinit var vd_address: TextView
    private lateinit var vd_schedule: TextView
    private lateinit var vd_Refund: TextView
    private lateinit var btn_appointment: Button
    private lateinit var vd_goBack: ImageView
    private lateinit var vd_vetType: TextView


    private var id = ""
    private var name = ""
    private var mail = ""
    private var city = ""
    private var phoneNumber = ""
    private val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vet_details)
        id = intent.getStringExtra("id").toString()
        initComponents()
        initListeners()
        initRecyclerViews()
        setValues()


    }

    private fun initComponents() {
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
        vd_vetType = findViewById(R.id.vd_vetType)
    }

    private fun initListeners() {
        btn_appointment.setOnClickListener { toCarerAppointment() }
        vd_goBack.setOnClickListener {
            onBackPressed()
            finish()
        }
    }

    private fun initRecyclerViews() {
        rv_services.layoutManager = LinearLayoutManager(this)
        rv_services.adapter = VetList.find { it.id == id }?.servicesTitles?.let { ServiceAdapter(it) }
        rv_photos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_photos.adapter = VetList.find { it.id == id }?.photos?.let { PhotoAdapter(it) }
        Log.i("Fotos", VetList.find { it.id == id }?.photos.toString())
    }

    private fun setValues() {
        vd_name.text = VetList.find { it.id == id }?.name
        vd_country.text = VetList.find { it.id == id }?.country
        vd_city.text = VetList.find { it.id == id }?.city
        VetList.find { it.id == id }?.attendsEmergencies?.let { setEmergencyStatus(it) }
        vd_specialty.text = VetList.find { it.id == id }?.specialty
        vd_experience.text = VetList.find { it.id == id }?.experience
        vd_species.text = VetList.find { it.id == id }?.species
        vd_address.text = VetList.find { it.id == id }?.address
        setSchedule()
        vd_Refund.text = VetList.find { it.id == id }?.refundPolicy
        vd_vetType.text = VetList.find { it.id == id }?.vetType
    }

    private fun setEmergencyStatus(status: String) {
        when (status) {
            "y" -> vd_emergency.setImageResource(R.drawable.ic_emergency)
            "n" -> vd_emergency.setImageResource(R.drawable.ic_emergencyoff)
            else -> vd_emergency.setImageResource(R.drawable.ic_emergencyoff)
        }
    }

    private fun setSchedule() {
        val scheduleArray = VetList.find { it.id == id }?.schedule
        val stringBuilder = StringBuilder()
        if (scheduleArray != null) {
            for (item in scheduleArray) {
                stringBuilder.append(item).append("\n")
            }
        }
        vd_schedule.text = stringBuilder.toString()
    }
    private suspend fun getUserData(): Map<String, Any>? = suspendCoroutine { continuation ->
        val db = Firebase.firestore
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid.toString()
        val documentReference = db.collection("User").document(uid)
        documentReference.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documentSnapshot = task.result
                    val dataUser = documentSnapshot?.data
                    continuation.resume(dataUser)
                } else {
                    continuation.resume(null)
                }
            }
    }

    private suspend fun loadInfo() {
        val dataUser = getUserData()
        if (dataUser != null) {
            name = (dataUser["name"] as? String).toString()
            mail = (dataUser["user_email"] as? String).toString()
            city = (dataUser["userCity"] as? String).toString()
            phoneNumber = (dataUser["phoneNumber"] as? String).toString()
        } else {
            loadInfo()
        }
    }

    private fun toCarerAppointment() {
        lifecycleScope.launch {
            loadInfo()
            val intent = Intent(context, CarerAppointmentActivity::class.java)
            intent.putExtra("userName", name)
            intent.putExtra("userAddress", mail)
            intent.putExtra("userCity", city)
            intent.putExtra("userPhone", phoneNumber)
            intent.putExtra("carerId", id)
            startActivity(intent)
        }
    }

}