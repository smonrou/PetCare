package com.development.petcare.mains.logs


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.mains.HomeActivity
import com.development.petcare.mains.ProviderType

import com.google.firebase.auth.FirebaseAuth

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var ca_etName: EditText
    private lateinit var ca_etAddress: EditText
    private lateinit var ca_etPassword: EditText
    private lateinit var ca_etPhone: EditText
    private lateinit var ca_etCountryCode: EditText
    private lateinit var ca_etCity: EditText
    private lateinit var ca_etAge: EditText
    private lateinit var btn_create: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        ca_etName = findViewById(R.id.ca_etName)
        ca_etAddress = findViewById(R.id.ca_etAddress)
        ca_etPassword = findViewById(R.id.ca_etPassword)
        ca_etPhone = findViewById(R.id.ca_etPhone)
        ca_etCountryCode = findViewById(R.id.ca_etCountryCode)
        ca_etCity = findViewById(R.id.ca_etCity)
        ca_etAge = findViewById(R.id.ca_etAge)
        btn_create = findViewById(R.id.btn_create)
    }



    private fun initListeners() {
        btn_create.setOnClickListener {
            setup(etsNotEmpty())
            //create(etsNotEmpty())
        }
    }

    private fun etsNotEmpty(): Boolean {
        var status = false
        if (ca_etName.text.isNotBlank() || ca_etAddress.text.isNotBlank() || ca_etPassword.text.isNotBlank() || ca_etPhone.text.isNotBlank() || ca_etCountryCode.text.isNotBlank() || ca_etCity.text.isNotBlank() || ca_etAge.text.isNotBlank()) {
            status = true
        }
        return status
    }

    private fun setup(control: Boolean) {
        if (control){
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    ca_etAddress.text.toString(),
                    ca_etPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        toHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else Toast.makeText(this, "Autentication Error", Toast.LENGTH_LONG).show()
                }
        }else Toast.makeText(this, "Please enter all the data", Toast.LENGTH_LONG).show()
    }

    fun toHome(email: String, provider: ProviderType) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("provider", provider.name)
        intent.putExtra("code", "1")
        intent.putExtra("username", ca_etName.text.toString())
        intent.putExtra("user_email", ca_etAddress.text.toString())
        intent.putExtra("password", ca_etPassword.text.toString())
        intent.putExtra("phone", ca_etPhone.text.toString())
        intent.putExtra("country", ca_etCountryCode.text.toString())
        intent.putExtra("city", ca_etCity.text.toString())
        intent.putExtra("age", ca_etAge.text.toString())
        startActivity(intent)
    }
}