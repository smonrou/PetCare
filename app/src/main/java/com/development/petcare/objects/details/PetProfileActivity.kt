package com.development.petcare.objects.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.appcompat.app.AppCompatActivity
import com.development.petcare.objects.providers.PetProvider.Companion.PetList
import de.hdodenhof.circleimageview.CircleImageView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import com.bumptech.glide.Glide
import com.development.petcare.services.MyPetsViewsActivity
import com.development.petcare.R
import java.lang.Exception
import java.lang.reflect.Field


class PetProfileActivity : AppCompatActivity() {

    private lateinit var petProfile_goBack: ImageView
    private lateinit var et_petName: EditText
    private lateinit var campo_mensaje: EditText
    private lateinit var et_petType: EditText
    private lateinit var btn_delete: Button
    private lateinit var btn_edit: Button
    private lateinit var profilePetPicture: CircleImageView

    var imageUri: String? = "null"
    var changed: Boolean = false

    private val myClass = PutTextOnET()

    val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            profilePetPicture.setImageURI(uri)
            imageUri = uri.toString()
        } else {
            Log.i("Admin", "No se seleccionó una fotografía")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_profile)

        initComponents()
        initListeners()
        putValues()
        prevValues()
        setProfilePicture()
    }

    private fun initComponents() {
        petProfile_goBack = findViewById(R.id.petProfile_goBack)
        et_petName = findViewById(R.id.et_petName)
        campo_mensaje = findViewById(R.id.campo_mensaje)
        et_petType = findViewById(R.id.et_petType)
        btn_delete = findViewById(R.id.btn_delete)
        profilePetPicture = findViewById(R.id.profilePetPicture)
        btn_edit = findViewById(R.id.btn_edit)
    }
    private fun setProfilePicture(){
        val photo = intent.getStringExtra("uri")
        Glide.with(this).load(photo).into(profilePetPicture)
    }

    private fun initListeners() {
        petProfile_goBack.setOnClickListener { toPetViews() }
        btn_delete.setOnClickListener {
            deletePet()
            toPetViews()
        }
        btn_edit.setOnClickListener {
            if (compareValues()) {
                saveEditedPet()
                picChanged(changed)
                toPetViews()
            } else {
                Toast.makeText(applicationContext, "No changes", Toast.LENGTH_SHORT).show()
            }

        }
        profilePetPicture.setOnClickListener { showPopupMenu() }
    }

    private fun putValues() {
        myClass.setEditText(et_petName)
        intent.getStringExtra("name")?.let { myClass.addTextToEditText(it) }
        myClass.setEditText(et_petType)
        var value = intent.getStringExtra("type")
        if (value != null) {
            myClass.addTextToEditText(value)
        }
        myClass.setEditText(campo_mensaje)
        intent.getStringExtra("description")?.let { myClass.addTextToEditText(it) }
        if (intent.getStringExtra("uri") != "null") {
            profilePetPicture.setImageURI(Uri.parse(intent.getStringExtra("uri")))
        } else {
            profilePetPicture.setImageURI(Uri.parse(imageUri))
        }
    }

    private fun showPopupMenu() {
        profilePetPicture.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.change_profilePicture -> {
                        pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
                        changed = true
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
        val drawable: Drawable? = profilePetPicture.drawable

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

    private fun deletePet() {
        val pet = (intent.getStringExtra("name"))
        PetList.removeAll { it.name == pet }
    }

    private fun toPetViews() {
        val intent = Intent(this, MyPetsViewsActivity::class.java)
        startActivity(intent)
    }

    private fun saveEditedPet() {
        val pet = (intent.getStringExtra("name"))
        PetList.find { it.name == pet }?.description = campo_mensaje.text.toString()
    }

    private fun prevValues(): String {
        val description = intent.getStringExtra("description")
        val uri = intent.getStringExtra("uri")
        val desc_uri = description + uri
        return desc_uri
    }

    private fun currentValues(): String {
        val description = campo_mensaje.text.toString()
        val uri = imageUri
        val desc_uri = description + uri
        return desc_uri
    }

    private fun compareValues(): Boolean {
        return prevValues() != currentValues()
    }

    private fun picChanged(changed: Boolean) {
        val pet = (intent.getStringExtra("name"))
        if (changed) {
            PetList.find { it.name == pet }?.uri = imageUri
        } else {
            PetList.find { it.name == pet }?.uri = intent.getStringExtra("uri")
        }
    }

    class PutTextOnET() {
        private lateinit var editText: EditText

        fun setEditText(editText: EditText) {
            this.editText = editText
        }

        fun addTextToEditText(text: String) {
            editText.append(text)
        }
    }
}