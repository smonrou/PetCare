package com.development.petcare.mains.logs


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.mains.HomeActivity
import com.development.petcare.mains.ProviderType
import com.development.petcare.objects.providers.UserProvider
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var edt_user: EditText
    private lateinit var edt_password: EditText
    private lateinit var txt_login: ImageView
    private lateinit var toMakeAccount: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initComponents()
        initListeners()
        credentials()
        UserProvider.activeUserList.clear()
    }

    fun initComponents() {
        edt_user = findViewById(R.id.edt_user)
        edt_password = findViewById(R.id.edt_password)
        txt_login = findViewById(R.id.signIn)
        toMakeAccount = findViewById(R.id.toMakeAccount)
    }

    fun initListeners() {
        txt_login.setOnClickListener { singIn() }
        toMakeAccount.setOnClickListener { toCreateAccount() }
    }

    private fun toCreateAccount() {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
    }

    private fun singIn() {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(
                edt_user.text.toString(),
                edt_password.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    toHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                } else Toast.makeText(this, "Authentication Error", Toast.LENGTH_LONG).show()
            }
    }

    fun toHome(email: String, provider: ProviderType) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("provider", provider.name)
        intent.putExtra("code", "0")
        startActivity(intent)
    }
    @SuppressLint("SetTextI18n")
    fun credentials(){
        edt_user.setText("saulandresmmwork@gmail.com")
        edt_password.setText("12345678")
    }
}



