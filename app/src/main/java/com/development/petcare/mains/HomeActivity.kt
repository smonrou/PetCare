package com.development.petcare.mains

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.development.petcare.R
import com.development.petcare.mains.conections.UserCRUD
import com.development.petcare.objects.addobject.loaders.LoadPets
import com.development.petcare.objects.providers.PetProvider.Companion.PetList
import com.development.petcare.services.MyPetsViewsActivity
import com.development.petcare.services.ServicesViewsActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var btn_profile: ImageView
    private lateinit var cv_myPets: CardView
    private lateinit var cvServices: CardView
    private lateinit var txt_name: TextView

    private var username = ""
    private var userMail = ""
    private var userCity = ""
    private var photoURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initComponents()
        initListener()
        create()
        loadInfo()

        updateList()
    }

    private fun initComponents() {
        btn_profile = findViewById(R.id.btn_profile)
        cv_myPets = findViewById(R.id.cv_myPets)
        cvServices = findViewById(R.id.cvServices)
        txt_name = findViewById(R.id.txt_name)

    }

    private fun initListener() {
        btn_profile.setOnClickListener { openProfile() }
        cv_myPets.setOnClickListener { openMyPets() }
        cvServices.setOnClickListener { openServices() }
    }

    private fun create() {
        if (intent.getStringExtra("code") == "1") {
            val new = UserCRUD()
            lifecycleScope.launch {
                new.createUser(
                    intent.getStringExtra("age")!!,
                    intent.getStringExtra("username")!!,
                    intent.getStringExtra("country")!!,
                    intent.getStringExtra("password")!!,
                    intent.getStringExtra("phone")!!,
                    intent.getStringExtra("city")!!,
                    intent.getStringExtra("user_email")!!
                )
            }
        }
    }

    private fun getUserData(callback: (Map<String, Any>?) -> Unit) {
        val db = Firebase.firestore
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid.toString()
        val documentReference = db.collection("User").document(uid)
        documentReference.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documentSnapshot = task.result
                    val dataUser = documentSnapshot?.data
                    callback(dataUser)
                } else {
                    callback(null)
                }
            }
    }

    private fun loadInfo() {
        lifecycleScope.launch {
            getUserData { dataUser ->
                if (dataUser != null) {
                    username = (dataUser["name"] as? String).toString()
                    userMail = (dataUser["user_email"] as? String).toString()
                    userCity = (dataUser["userCity"] as? String).toString()
                    photoURL = (dataUser["profilePicture"] as? String).toString()
                    txt_name.text = username
                } else {
                    loadInfo()
                }
            }
        }
    }

    private fun sendInfo(intent: Intent) {
        intent.putExtra("name", username)
        intent.putExtra("email", userMail)
        intent.putExtra("city", userCity)
        intent.putExtra("picture", photoURL)
    }

    private fun openProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        sendInfo(intent)
        startActivity(intent)
    }

    private fun openMyPets() {
        val intent = Intent(this, MyPetsViewsActivity::class.java)
        startActivity(intent)
    }

    private fun openServices() {
        val intent = Intent(this, ServicesViewsActivity::class.java)
        sendInfo(intent)
        startActivity(intent)
    }

    private fun updateList() {
        PetList.clear()
        lifecycleScope.launch {
            LoadPets().obtenerDatosPetsDeUsuario()
        }
    }
}

enum class ProviderType {
    BASIC
}