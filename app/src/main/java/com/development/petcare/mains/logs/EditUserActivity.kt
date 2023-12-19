package com.development.petcare.mains.logs


import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.development.petcare.R
import com.development.petcare.mains.HomeActivity
import com.development.petcare.mains.ProfileActivity
import com.development.petcare.mains.conections.Storage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.launch
import java.lang.Exception

class EditUserActivity : AppCompatActivity() {
    private lateinit var tv_name: TextView
    private lateinit var tv_email: TextView
    private lateinit var et_yourName: EditText
    private lateinit var et_yourCity: EditText
    private lateinit var et_yourPhone: EditText
    private lateinit var et_yourCode: EditText
    private lateinit var et_yourAge: EditText
    private lateinit var btn_changeInfo: CardView
    private lateinit var btn_Home: ImageView
    private lateinit var editUser_profilePicture: CircleImageView


    private lateinit var userInfo: Map<String, Any>
    private var changesMade = false
    private val mAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var UID = mAuth.currentUser?.uid.toString()
    private var photoURL = ""
    private lateinit var photoUri:Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        initComponents()
        initListeners()
        loadInfo()
        setupTextWatchers()
        setProfilePicture()
    }

    private fun updateUser(control: Boolean) {
        if (control) {
            lifecycleScope.launch { updateUserData() }
        } else Toast.makeText(this, "The previous information is the same.", Toast.LENGTH_LONG)
            .show()
    }

    private suspend fun updateUserData() {

        val userDocumentRef = FirebaseFirestore.getInstance().collection("User").document(UID)
        val (photoFilename, photoURL) = getPhotoURL()
        val dataToUpdate = mutableMapOf<String, Any?>(
            "age" to et_yourAge.text.toString(),
            "name" to et_yourName.text.toString(),
            "nationality" to et_yourCode.text.toString(),
            "phoneNumber" to et_yourPhone.text.toString(),
            "userCity" to et_yourCity.text.toString(),
            "profilePicture" to photoURL,
            "photoFilename" to photoFilename
        )
        userDocumentRef
            .update(dataToUpdate)
            .addOnSuccessListener { toProfile() }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Error updating document: $e",
                    Toast.LENGTH_LONG
                ).show()
                Log.i("Error", "$e")
            }
    }

    private fun getUserData(callback: (Map<String, Any>?) -> Unit) {
        val documentReference = db.collection("User").document(UID)
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
                    val age = dataUser["age"] as? String
                    val name = dataUser["name"] as? String
                    val nationality = dataUser["nationality"] as? String
                    val phoneNumber = dataUser["phoneNumber"] as? String
                    val userCity = dataUser["userCity"] as? String
                    val userEmail = dataUser["user_email"] as? String
                    photoURL = (dataUser["profilePicture"] as? String).toString()
                    userInfo = dataUser
                    runOnUiThread {
                        tv_name.text = name
                        tv_email.text = userEmail
                        et_yourName.setText(name)
                        et_yourCity.setText(userCity)
                        et_yourPhone.setText(phoneNumber)
                        et_yourCode.setText(nationality)
                        et_yourAge.setText(age)

                    }
                }
            }
        }
    }

    private fun setupTextWatchers() {
        et_yourName.addTextChangedListener(createTextWatcher())
        et_yourCity.addTextChangedListener(createTextWatcher())
        et_yourPhone.addTextChangedListener(createTextWatcher())
        et_yourCode.addTextChangedListener(createTextWatcher())
        et_yourAge.addTextChangedListener(createTextWatcher())
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                changesMade = true
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun haveChangesOccurred(): Boolean {
        val changes = changesMade
        changesMade = false
        return changes
    }

    private fun initComponents() {
        tv_name = findViewById(R.id.tv_name)
        tv_email = findViewById(R.id.tv_email)
        et_yourName = findViewById(R.id.et_yourName)
        et_yourCity = findViewById(R.id.et_yourCity)
        et_yourPhone = findViewById(R.id.et_yourPhone)
        et_yourCode = findViewById(R.id.et_yourCode)
        et_yourAge = findViewById(R.id.et_yourAge)
        btn_changeInfo = findViewById(R.id.btn_changeInfo)
        btn_Home = findViewById(R.id.btn_Home)
        editUser_profilePicture = findViewById(R.id.cv_topImage)


    }
    private fun setProfilePicture(){
        Glide.with(this).load(intent.getStringExtra("photoURL")).into(editUser_profilePicture)
    }

    private fun initListeners() {
        btn_Home.setOnClickListener { toHome() }
        btn_changeInfo.setOnClickListener { updateUser(haveChangesOccurred()) }
        editUser_profilePicture.setOnClickListener { showPopupMenu() }
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun toProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        sendInfo(intent)
        startActivity(intent)
    }
    private fun sendInfo(intent: Intent) {
        intent.putExtra("name", tv_name.text)
        intent.putExtra("email", tv_email.text)
        intent.putExtra("picture", photoURL)
    }
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            editUser_profilePicture.setImageURI(uri)
            photoURL = uri.toString()
            photoUri = uri
        } else {
            Log.i("Admin", "No se seleccionó una fotografía")
        }
    }
    private fun showPopupMenu() {
        editUser_profilePicture.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.change_profilePicture -> {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        true
                    }

                    R.id.view_image -> {
                        showProfilePicture()
                        true
                    }

                    else -> false
                }
            }
            popupMenu.inflate(R.menu.popup_menu_image)
            try {
                val fieldPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldPopup.isAccessible = true
                val mPopup = fieldPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setFoceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.i("Smou", "Error Showing Menu Icons.", e)
            } finally {
                popupMenu.show()
            }
        }
    }
    private fun showProfilePicture() {
        val drawable: Drawable? = editUser_profilePicture.drawable

        val fullscreenImageView = ImageView(this)
        fullscreenImageView.setImageDrawable(drawable)


        val fullscreenDialog = androidx.appcompat.app.AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            .setView(fullscreenImageView)
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        fullscreenDialog.show()
    }
    private suspend fun getPhotoURL(): Array<String> {
        return Storage().uploadImage(photoUri, "/user-profile-photos/")
    }
}