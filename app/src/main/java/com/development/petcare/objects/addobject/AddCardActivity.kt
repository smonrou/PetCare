package com.development.petcare.objects.addobject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.development.petcare.R
import com.development.petcare.objects.basics.Card
import com.development.petcare.objects.providers.CardProvider.Companion.CardList
import com.development.petcare.services.CardManager
import kotlin.random.Random

class AddCardActivity : AppCompatActivity() {
    private lateinit var addCard_et_cardName: EditText
    private lateinit var addCard_et_cardNumber: EditText
    private lateinit var addCard_et_cardDueDate: EditText
    private lateinit var addCard_et_cardCvv: EditText
    private lateinit var addCard_et_cardPropietary: EditText
    private lateinit var addCard_btn_save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        initComponents()
        initListeners()
        etWatcher()
    }

    private fun initComponents() {
        addCard_et_cardName = findViewById(R.id.addCard_et_cardName)
        addCard_et_cardNumber = findViewById(R.id.addCard_et_cardNumber)
        addCard_et_cardDueDate = findViewById(R.id.addCard_et_cardDueDate)
        addCard_et_cardCvv = findViewById(R.id.addCard_et_cardCvv)
        addCard_et_cardPropietary = findViewById(R.id.addCard_et_cardPropietary)
        addCard_btn_save = findViewById(R.id.addCard_btn_save)
    }

    private fun initListeners() {
        addCard_btn_save.setOnClickListener {
            saveCard()
            toCardManager()
        }
    }

    private fun saveCard() {
        val cardName = addCard_et_cardName.text.toString()
        val proprietary = addCard_et_cardPropietary.text.toString()
        val cardNumber = addCard_et_cardNumber.text.toString()
        val cardCode = addCard_et_cardCvv.text.toString()
        val date = addCard_et_cardDueDate.text.toString()
        val type = when (Random.nextInt(4)) {
            0 -> "Visa"
            1 -> "MasterCard"
            2 -> "Amex"
            else -> "Other"
        }
        val card = Card(cardName, proprietary, cardNumber, cardCode, date, type)
        CardList.add(card)
    }

    private fun toCardManager() {
        val intent = Intent(this, CardManager::class.java)
        startActivity(intent)
        finish()
    }
    private fun etWatcher(){
        addCard_et_cardDueDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                if (editable?.length == 2 && !editable.contains("/")) {
                    editable.insert(2, "/")
                }
            }
        })
    }
}