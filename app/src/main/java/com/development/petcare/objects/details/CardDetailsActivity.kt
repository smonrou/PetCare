package com.development.petcare.objects.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.development.petcare.R
import com.development.petcare.objects.providers.CardProvider.Companion.CardList
import com.development.petcare.services.CardManager

class CardDetailsActivity : AppCompatActivity() {
    private lateinit var cardDetails_et_cardName: EditText
    private lateinit var cardDetails_et_cardNumber: EditText
    private lateinit var cardDetails_et_cardDueDate: EditText
    private lateinit var cardDetails_et_cardCvv: EditText
    private lateinit var cardDetails_et_cardPropietary: EditText
    private lateinit var cardDetails_btn_delete: Button
    private lateinit var cv_card: ImageView

    private var position = ""
    private var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)
        initComponents()
        setValues()
        initListeners()
    }

    private fun initComponents() {
        cardDetails_et_cardName = findViewById(R.id.cardDetails_et_cardName)
        cardDetails_et_cardNumber = findViewById(R.id.cardDetails_et_cardNumber)
        cardDetails_et_cardDueDate = findViewById(R.id.cardDetails_et_cardDueDate)
        cardDetails_et_cardCvv = findViewById(R.id.cardDetails_et_cardCvv)
        cardDetails_et_cardPropietary = findViewById(R.id.cardDetails_et_cardPropietary)
        cardDetails_btn_delete = findViewById(R.id.cardDetails_btn_delete)
        cv_card = findViewById(R.id.cv_card)
    }

    private fun initListeners() {
        cardDetails_btn_delete.setOnClickListener {
            removeCard()
            toCardManager()
        }
    }

    private fun setValues() {
        position = intent.getStringExtra("position").toString()
        cardDetails_et_cardName.setText(intent.getStringExtra("cardName"))
        cardDetails_et_cardNumber.setText(intent.getStringExtra("cardNumber"))
        cardDetails_et_cardDueDate.setText(intent.getStringExtra("date"))
        cardDetails_et_cardCvv.setText(intent.getStringExtra("code"))
        cardDetails_et_cardPropietary.setText(intent.getStringExtra("proprietary"))
        type = intent.getStringExtra("type").toString()
        val imagePosition = setCardIconType(type)
        cv_card.setImageResource(imagePosition)
    }

    private fun setCardIconType(type: String): Int {
        return when (type) {
            "Visa" -> R.drawable.ic_big_visa_card
            "MasterCard" -> R.drawable.ic_big_mastercard
            "Amex" -> R.drawable.ic_big_amex
            else -> R.drawable.ic_othercard
        }
    }

    private fun removeCard() {
        CardList.remove(CardList.find { it.cardName == intent.getStringExtra("cardName").toString() })
    }

    private fun toCardManager() {
        val intent = Intent(this, CardManager::class.java)
        startActivity(intent)
        finish()
    }
}