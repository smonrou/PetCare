package com.development.petcare.objects.addobject


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.development.petcare.R
import com.development.petcare.mains.conections.Storage
import com.development.petcare.objects.basics.Pet
import com.development.petcare.objects.providers.PetProvider.Companion.PetList
import com.development.petcare.services.MyPetsViewsActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.launch
import java.util.UUID

class AddPetActivity : AppCompatActivity() {

    private lateinit var btn_addpet: CardView
    private lateinit var AddPet_goBack: ImageView
    private lateinit var AddPet_et_petName: EditText
    private lateinit var AddPet_et_petType: Spinner
    private lateinit var AddPet_campo_mensaje: EditText
    private lateinit var AddPet_profilePetPicture: CircleImageView

    var imageUri: String = ""
    private lateinit var photoUri: Uri

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            AddPet_profilePetPicture.setImageURI(uri)
            imageUri = uri.toString()
            photoUri = uri
        } else {
            Log.i("Admin", "No se seleccionó una fotografía")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pet)
        initComponents()
        initListener()
        initSpinner()
    }

    private fun initComponents() {
        btn_addpet = findViewById(R.id.btn_addpet)
        AddPet_goBack = findViewById(R.id.AddPet_goBack)
        AddPet_et_petName = findViewById(R.id.AddPet_et_petName)
        AddPet_et_petType = findViewById(R.id.AddPet_et_petType)
        AddPet_campo_mensaje = findViewById(R.id.AddPet_campo_mensaje)
        AddPet_profilePetPicture = findViewById(R.id.AddPet_profilePetPicture)
    }

    private fun initListener() {
        btn_addpet.setOnClickListener {
            lifecycleScope.launch { addPetlinkUser() }
        }
        AddPet_goBack.setOnClickListener { toMyPetsView() }
        AddPet_profilePetPicture.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun addPet() {
        val name = AddPet_et_petName.text.toString()
        val description = AddPet_campo_mensaje.text.toString()
        val type = when (AddPet_et_petType.selectedItemPosition) {
            1 -> "Dog"
            2 -> "Cat"
            3 -> "Bird"
            4 -> "Fish"
            5 -> "Other"
            else -> "Other"
        }
        lifecycleScope.launch {
            PetList.add(Pet(name, type, false, false, description, imageUri))
        }
    }

    private suspend fun getPhotoURL(): Array<String> {
        return Storage().uploadImage(photoUri, "/pet-profile-photos/")
    }

    private suspend fun addPetlinkUser() {
        lifecycleScope.launch {
            val (photoFilename, photoURL) = getPhotoURL()
            val db = Firebase.firestore
            val mAuth = FirebaseAuth.getInstance()
            val uid = mAuth.currentUser?.uid.toString()
            val nombreDocumentoPet = UUID.randomUUID().toString()
            val datosMascota = hashMapOf(
                "name" to AddPet_et_petName.text.toString(),
                "description" to AddPet_campo_mensaje.text.toString(),
                "type" to getTypeByPosition(),
                "photoFileName" to photoFilename,
                "photoURL" to photoURL,
                "ownerId" to "/User/$uid"
            )
            db.collection("pet").document(nombreDocumentoPet)
                .set(datosMascota)
                .addOnSuccessListener {
                    addPet()
                    val documentReference = db.collection("User").document(uid)
                    documentReference.update(
                        "pets",
                        FieldValue.arrayUnion(nombreDocumentoPet)
                    )
                        .addOnSuccessListener {
                            runOnUiThread { toMyPetsView() }
                        }
                        .addOnFailureListener { e ->
                            Log.i("Error", "Error adding the pet: $e")
                        }
                }
                .addOnFailureListener { e ->
                    Log.i("Error", "Error adding the pet: $e")
                }
        }
    }

    private fun initSpinner() {
        val typeList = arrayOf("Select a Type", "Dog", "Cat", "Bird", "Fish", "Other")
        val adapter = object : ArrayAdapter<String>(this, R.layout.spinner_item_layout, typeList) {
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
                    R.layout.spinner_item_layout,
                    parent,
                    false
                )
                val iconImageView = view.findViewById<ImageView>(R.id.spinner_item_icon)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                when (position) {
                    0 -> iconImageView.setImageResource(R.drawable.ic_select)
                    1 -> iconImageView.setImageResource(R.drawable.ic_dogbone)
                    2 -> iconImageView.setImageResource(R.drawable.ic_cat_pet)
                    3 -> iconImageView.setImageResource(R.drawable.ic_bird)
                    4 -> iconImageView.setImageResource(R.drawable.ic_fish)
                    5 -> iconImageView.setImageResource(R.drawable.ic_other)
                    else -> iconImageView.setImageResource(0)
                }
                textView.text = getItem(position)
                return view
            }
        }
        AddPet_et_petType.adapter = adapter
    }

    private fun getTypeByPosition(): String {
        return when (AddPet_et_petType.selectedItemPosition) {
            1 -> "Dog"
            2 -> "Cat"
            3 -> "Bird"
            4 -> "Fish"
            5 -> "Other"
            else -> "Other"
        }
    }

    private fun toMyPetsView() {
        val intent = Intent(this, MyPetsViewsActivity::class.java)
        startActivity(intent)
    }

}