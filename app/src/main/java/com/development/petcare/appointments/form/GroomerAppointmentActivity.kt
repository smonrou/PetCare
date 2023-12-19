package com.development.petcare.appointments.form

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.appointments.details.GroomerAppointmentDetailsActivity
import com.development.petcare.appointments.fragments.DatePickerFragment
import com.development.petcare.appointments.fragments.StartTimePickerFragment
import com.development.petcare.objects.providers.GroomerProvider.Companion.GroomerList
import com.development.petcare.objects.providers.PetProvider.Companion.PetList


class GroomerAppointmentActivity : AppCompatActivity() {
    private lateinit var Groomer_appointment_yourName: TextView
    private lateinit var Groomer_appointment_yourAddress: TextView
    private lateinit var Groomer_appointment_yourCity: TextView
    private lateinit var Groomer_appointment_yourPhoneNumber: TextView
    private lateinit var Groomer_appointment_petSpinner: Spinner
    private lateinit var Groomer_appointment_etDate: EditText
    private lateinit var Groomer_appointment_etTime: EditText
    private lateinit var Groomer_appointment_txtPrice: TextView
    private lateinit var Groomer_appointment_cv_toDetails: CardView
    private lateinit var PetWalk_appointment_goBack:ImageView

    private var isDateSelected = ""
    private var date = ""
    private var time = ""
    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groomer_appointment)
        initComponents()
        id = intent.getStringExtra("id").toString()
        initListeners()
        initSpinnerIcon()
        putValues()
    }

    private fun initComponents() {
        Groomer_appointment_yourName = findViewById(R.id.Groomer_appointment_yourName)
        Groomer_appointment_yourAddress = findViewById(R.id.Groomer_appointment_yourAddress)
        Groomer_appointment_yourCity = findViewById(R.id.Groomer_appointment_yourCity)
        Groomer_appointment_yourPhoneNumber = findViewById(R.id.Groomer_appointment_yourPhoneNumber)
        Groomer_appointment_cv_toDetails = findViewById(R.id.Groomer_appointment_cv_toDetails)
        Groomer_appointment_petSpinner = findViewById(R.id.Groomer_appointment_petSpinner)
        Groomer_appointment_etDate = findViewById(R.id.Groomer_appointment_etDate)
        Groomer_appointment_etTime = findViewById(R.id.Groomer_appointment_etTime)
        Groomer_appointment_txtPrice = findViewById(R.id.Groomer_appointment_txtPrice)
        PetWalk_appointment_goBack = findViewById(R.id.PetWalk_appointment_goBack)
    }

    private fun putValues() {
        Groomer_appointment_yourName.text = intent.getStringExtra("userName")
        Groomer_appointment_yourAddress.text = intent.getStringExtra("userAddress")
        Groomer_appointment_yourCity.text = intent.getStringExtra("userCity")
        Groomer_appointment_yourPhoneNumber.text = intent.getStringExtra("userPhone")
        Groomer_appointment_txtPrice.text = getValuesFromGroomersList()
    }

    private fun initListeners() {
        Groomer_appointment_cv_toDetails.setOnClickListener { nothingEmpty() }
        Groomer_appointment_etDate.setOnClickListener { showDatePicker() }
        Groomer_appointment_etTime.setOnClickListener { showTimePicker() }
        PetWalk_appointment_goBack.setOnClickListener { onBackPressed() }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        Groomer_appointment_etDate.setText("$day/ $month/ $year")
        isDateSelected = "selected"
        date = "$day/$month/$year"
    }

    private fun showTimePicker() {
        val startTimePickerFragment = StartTimePickerFragment { selectedTime ->
            time = selectedTime
            Groomer_appointment_etTime.setText(time)
        }
        startTimePickerFragment.show(supportFragmentManager, "StartTimePicker")
    }

    private fun sendValuesToAppointmentDetails(intent: Intent) {
        val spinner = spinnerSelection().toString()
        Log.i("saul", "$id GroomerAppointmentActivity")
        intent.putExtra("id", id)
        intent.putExtra("date", date)
        intent.putExtra("time", time)
        intent.putExtra("pet", spinner)
        intent.putExtra("userName", Groomer_appointment_yourName.text)
        intent.putExtra("userAddress", Groomer_appointment_yourAddress.text)
        intent.putExtra("userCity", Groomer_appointment_yourCity.text)
        intent.putExtra("userPhone", Groomer_appointment_yourPhoneNumber.text)
    }
    private fun getValuesFromGroomersList(): String? {
        val id = intent.getStringExtra("id").toString()
        return GroomerList.find { it.id == id }?.priceRange
    }

    private fun nothingEmpty() {
        if (isDateSelected.isNotEmpty() && time.isNotEmpty()) {
            toAppointmentDetails()
        } else errorMessage()
    }

    private fun toAppointmentDetails() {
        intent = Intent(this, GroomerAppointmentDetailsActivity::class.java)
        sendValuesToAppointmentDetails(intent)
        startActivity(intent)
    }

    private fun spinnerSelection(): Int {
        return Groomer_appointment_petSpinner.selectedItemPosition
    }

    private fun errorMessage() {
        val toast = Toast.makeText(
            this,
            "Make sure you put all the information and the correct time",
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    private fun initSpinnerIcon() {
        val typeList = PetList.map { it.name }
        val adapter: ArrayAdapter<String> =
            object : ArrayAdapter<String>(this, R.layout.spinner_item_layout, typeList) {
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

                    val item = getItem(position)

                    val iconResId = getIconResourceForPosition(position)
                    iconImageView.setImageResource(iconResId)

                    textView.text = item

                    return view
                }

                private fun getIconResourceForPosition(position: Int): Int {
                    val pet = PetList[position]
                    return when (pet.type) {
                        "Dog" -> R.drawable.ic_dogbone
                        "Cat" -> R.drawable.ic_cat_pet
                        "Bird" -> R.drawable.ic_bird
                        "Fish" -> R.drawable.ic_fish
                        else -> R.drawable.ic_other
                    }
                }
            }
        Groomer_appointment_petSpinner.adapter = adapter
    }
}