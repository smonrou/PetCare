package com.development.petcare.appointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.development.petcare.R
import com.development.petcare.objects.providers.CardProvider


class PaymentActivity : AppCompatActivity() {
    private lateinit var tv_priceAmount: TextView
    private lateinit var tv_serviceName: TextView
    private lateinit var tv_whoName: TextView
    private lateinit var tv_cardName: TextView
    private lateinit var spinner_cardSelect: Spinner
    private lateinit var spinner_paypal: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        initComponents()
        initListeners()
        initSpinnerCardsIcon()
        getInfo()
        Log.i("saul", intent.getStringExtra("id").toString() + " PaymentActivity")
    }

    private fun initComponents() {
        tv_priceAmount = findViewById(R.id.tv_priceAmount)
        tv_serviceName = findViewById(R.id.tv_serviceName)
        tv_whoName = findViewById(R.id.tv_whoName)
        tv_cardName = findViewById(R.id.tv_cardName)
        spinner_cardSelect = findViewById(R.id.spinner_cardSelect)
        spinner_paypal = findViewById(R.id.spinner_paypal)
    }

    private fun initListeners() {
        spinner_cardSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                tv_cardName.text = CardProvider.CardList[position].cardName
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                tv_cardName.text = CardProvider.CardList[0].cardName
            }
        }
    }

    private fun getInfo() {
        tv_priceAmount.text = intent.getStringExtra("price")
        tv_serviceName.text = intent.getStringExtra("name")
        tv_whoName.text = intent.getStringExtra("service")
    }

    private fun initSpinnerCardsIcon() {
        val typeList = CardProvider.CardList.map { it.cardName }
        val adapter: ArrayAdapter<String> =
            object : ArrayAdapter<String>(this, R.layout.spinner_item_layout_transparent, typeList) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    return createView(position, convertView, parent)
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    return createView(position, convertView, parent)
                }

                private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = convertView ?: layoutInflater.inflate(
                        R.layout.spinner_item_layout_transparent,
                        parent,
                        false
                    )
                    val iconImageView = view.findViewById<ImageView>(R.id.spinner_item_icon)
                    val textView = view.findViewById<TextView>(android.R.id.text1)
                    val item = getItem(position)
                    val iconResId = getIconResourceForPosition(position)
                    iconImageView.setImageResource(iconResId)
                    textView.text = item

                    return view
                }

                private fun getIconResourceForPosition(position: Int): Int {
                    val pet = CardProvider.CardList[position]
                    return when (pet.type) {
                        "Visa" -> R.drawable.ic_visa
                        "MasterCard" -> R.drawable.ic_mastercard
                        "American Express" -> R.drawable.ic_big_amex
                        else -> R.drawable.ic_othercardcolored
                    }
                }
            }
        spinner_cardSelect.adapter = adapter
        spinner_paypal.adapter = adapter
    }
}