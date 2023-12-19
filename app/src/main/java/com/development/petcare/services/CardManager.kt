package com.development.petcare.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.mains.HomeActivity
import com.development.petcare.objects.adapters.CardAdapter
import com.development.petcare.objects.addobject.AddCardActivity
import com.development.petcare.objects.providers.CardProvider.Companion.CardList

class CardManager : AppCompatActivity() {
    private lateinit var rv_creditCard: RecyclerView
    private lateinit var cc_toHome: ImageView
    private lateinit var cv_addCard:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_manager)
        initComponents()
        initListeners()
        initRecyclerView()
    }

    private fun initComponents() {
        cc_toHome = findViewById(R.id.cc_toHome)
        cv_addCard = findViewById(R.id.cv_addCard)
    }

    private fun initListeners() {
        cc_toHome.setOnClickListener { toHome() }
        cv_addCard.setOnClickListener { toAddCard() }
    }

    private fun toAddCard() {
        val intent = Intent(this, AddCardActivity::class.java)
        startActivity(intent)
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        rv_creditCard = findViewById(R.id.rv_creditCard)
        rv_creditCard.layoutManager = LinearLayoutManager(this)
        rv_creditCard.adapter = CardAdapter(CardList)
    }
}