package com.development.petcare.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.development.petcare.mains.HomeActivity
import com.development.petcare.R
import com.development.petcare.objects.addobject.loaders.LoadGroomers
import com.development.petcare.objects.addobject.loaders.LoadVet
import com.development.petcare.objects.addobject.loaders.LoadWalkers
import com.development.petcare.objects.providers.GroomerProvider.Companion.GroomerList
import com.development.petcare.objects.providers.PhotoProvider.Companion.PhotoList
import com.development.petcare.objects.providers.VetProvider.Companion.VetList
import com.development.petcare.objects.providers.WalkerProvider.Companion.WalkerList
import kotlinx.coroutines.launch

class ServicesViewsActivity : AppCompatActivity() {
    private lateinit var activity_services_Home: ImageView
    private lateinit var cv_hotel: CardView
    private lateinit var cv_carer: CardView
    private lateinit var cv_grooming: CardView
    private lateinit var cv_walk: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services_views)
        initComponents()
        initListeners()
        updateWalkerList()
        updateGroomerList()
        updateVetList()
    }

    private fun initListeners() {
        activity_services_Home.setOnClickListener { toHome() }
        cv_hotel.setOnClickListener { toHotels() }
        cv_carer.setOnClickListener { toCarers() }
        cv_grooming.setOnClickListener { toGrooming() }
        cv_walk.setOnClickListener { toPetWalk() }
    }

    private fun initComponents() {
        activity_services_Home = findViewById(R.id.activity_services_Home)
        cv_hotel = findViewById(R.id.cv_hotel)
        cv_carer = findViewById(R.id.cv_carer)
        cv_grooming = findViewById(R.id.cv_grooming)
        cv_walk = findViewById(R.id.cv_walk)
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun toHotels() {
        val intent = Intent(this, HotelServiceActivity::class.java)
        startActivity(intent)
    }

    private fun toCarers() {
        val intent = Intent(this, CarerServiceActivity::class.java)
        startActivity(intent)
    }

    private fun toGrooming() {
        val intent = Intent(this, GroomingServiceActivity::class.java)
        startActivity(intent)
    }

    private fun toPetWalk() {
        val intent = Intent(this, WalkerServiceActivity::class.java)
        startActivity(intent)
    }

    private fun updateWalkerList() {
        WalkerList.clear()
        lifecycleScope.launch {
            LoadWalkers().getDataByCityForPetWalker(intent.getStringExtra("city").toString())
        }
    }

    private fun updateGroomerList() {
        GroomerList.clear()
        lifecycleScope.launch {
            LoadGroomers().getDataByCityForPetStylist(intent.getStringExtra("city").toString())
        }
    }
    private fun updateVetList(){
        VetList.clear()
        PhotoList.clear()
        lifecycleScope.launch {
            LoadVet().getDataByCountryForPetVetList(intent.getStringExtra("city").toString())
        }
    }
}