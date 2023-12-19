package com.development.petcare.mains

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.development.petcare.R
import com.development.petcare.mains.logs.AuthActivity
import com.development.petcare.mains.logs.EditUserActivity
import com.development.petcare.services.CardManager
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {

    private lateinit var cv_logout:CardView
    private lateinit var ProfileView_home:ImageView
    private lateinit var cv_changeInfo:CardView
    private lateinit var txt_profileName:TextView
    private lateinit var txt_profileMail:TextView
    private lateinit var profilePicture:CircleImageView
    private lateinit var cv_addCard:CardView
    private var photo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initComponents()
        loadProfilePicture()
        initListeners()
    }

    private fun initComponents(){
        cv_logout = findViewById(R.id.cv_logout)
        ProfileView_home = findViewById(R.id.ProfileView_home)
        cv_changeInfo = findViewById(R.id.cv_changeInfo)
        txt_profileName = findViewById(R.id.txt_profileName)
        txt_profileMail = findViewById(R.id.txt_profileMail)
        profilePicture = findViewById(R.id.profilePicture)
        cv_addCard = findViewById(R.id.cv_addCard)
        setET()
    }

    private fun initListeners(){
        cv_logout.setOnClickListener { logOut() }
        ProfileView_home.setOnClickListener { toHome() }
        cv_changeInfo.setOnClickListener{ toEditUser() }
        cv_addCard.setOnClickListener { toCardManager() }

    }
    private fun setET(){
        txt_profileName.text = intent.getStringExtra("name")
        txt_profileMail.text = intent.getStringExtra("email")
        photo = intent.getStringExtra("picture").toString()
    }
    private fun loadProfilePicture(){
        Glide.with(this).load(photo).into(profilePicture)
    }
    private fun logOut(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }
    private fun toEditUser(){
        val intent = Intent(this, EditUserActivity::class.java)
        intent.putExtra("photoURL", photo)
        startActivity(intent)
    }
    private fun toHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
    private fun toCardManager(){
        val intent = Intent(this, CardManager::class.java)
        startActivity(intent)
    }
}