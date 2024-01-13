package com.development.petcare.appointments.form

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.appointments.details.HotelAppointmentDetailsActivity
import com.development.petcare.appointments.fragments.DatePickerFragment
import com.development.petcare.objects.providers.HotelProvider.Companion.HotelList
import com.development.petcare.objects.providers.PetProvider.Companion.PetList


class HotelAppointmentActivity : AppCompatActivity() {
    private lateinit var Hotel_appointment_yourName: TextView
    private lateinit var Hotel_appointment_yourAddres: TextView
    private lateinit var Hotel_appointment_yourCity: TextView
    private lateinit var Hotel_appointment_yourPhoneNumber: TextView
    private lateinit var Hotel_appointment_txtPrice: TextView
    private lateinit var Hotel_appointment_cv_toDetails: CardView
    private lateinit var Hotel_appointment_goBack: ImageView
    private lateinit var Hotel_appointment_petSpinner: Spinner
    private lateinit var Hotel_appointment_etDate: EditText
    private lateinit var Hotel_appointment_etEndDate: EditText

    private var identification = ""
    private var isStartDateSelected = ""
    private var isEndDateSelected = ""
    private var startDate = ""
    private var endDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_appointment)
        initComponents()
        initSpinnerIcon()
        initListeners()
        getId()
        putValues()

    }

    private fun initComponents() {
        Hotel_appointment_yourName = findViewById(R.id.Hotel_appointment_yourName)
        Hotel_appointment_yourAddres = findViewById(R.id.Hotel_appointment_yourAddres)
        Hotel_appointment_yourCity = findViewById(R.id.Hotel_appointment_yourCity)
        Hotel_appointment_yourPhoneNumber = findViewById(R.id.Hotel_appointment_yourPhoneNumber)
        Hotel_appointment_txtPrice = findViewById(R.id.Hotel_appointment_txtPrice)
        Hotel_appointment_cv_toDetails = findViewById(R.id.Hotel_appointment_cv_toDetails)
        Hotel_appointment_goBack = findViewById(R.id.Hotel_appointment_goBack)
        Hotel_appointment_petSpinner = findViewById(R.id.Hotel_appointment_petSpinner)
        Hotel_appointment_etDate = findViewById(R.id.Hotel_appointment_etDate)
        Hotel_appointment_etEndDate = findViewById(R.id.Hotel_appointment_etEndDate)
    }

    private fun initListeners() {
        Hotel_appointment_cv_toDetails.setOnClickListener { toAppointmentDetails() }
        Hotel_appointment_goBack.setOnClickListener { onBackPressed() }
        Hotel_appointment_etDate.setOnClickListener { showStartDatePicker() }
        Hotel_appointment_etEndDate.setOnClickListener { showEndDatePicker() }
    }

    private fun showStartDatePicker() {
        val datePicker =
            DatePickerFragment { day, month, year -> onStartDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onStartDateSelected(day: Int, month: Int, year: Int) {
        Hotel_appointment_etDate.setText("$day/ $month/ $year")
        isStartDateSelected = "selected"
        startDate = "$day/$month/$year"
    }

    private fun showEndDatePicker() {
        val datePicker =
            DatePickerFragment { day, month, year -> onEndDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onEndDateSelected(day: Int, month: Int, year: Int) {
        Hotel_appointment_etEndDate.setText("$day/ $month/ $year")
        isEndDateSelected = "selected"
        endDate = "$day/$month/$year"
    }

    private fun putValues() {
        Hotel_appointment_yourName.text = intent.getStringExtra("userName")
        Hotel_appointment_yourAddres.text = intent.getStringExtra("userAddress")
        Hotel_appointment_yourCity.text = intent.getStringExtra("city")
        Hotel_appointment_yourPhoneNumber.text = intent.getStringExtra("userPhone")
        Hotel_appointment_txtPrice.text = HotelList.find { it.id == identification }!!.species

    }

    private fun getId(): String {
        identification = intent.getStringExtra("hotelId").toString()
        return identification
    }

    private fun sendValues(intent: Intent) {
        val spinner = spinnerSelection().toString()
        intent.putExtra("userName", Hotel_appointment_yourName.text)
        intent.putExtra("userAddress", Hotel_appointment_yourAddres.text)
        intent.putExtra("city", Hotel_appointment_yourCity.text)
        intent.putExtra("userPhone", Hotel_appointment_yourPhoneNumber.text)
        intent.putExtra("hotelId", getId())
        intent.putExtra("pet", spinner)
        intent.putExtra("startDate", startDate)
        intent.putExtra("endDate", endDate)
        intent.putExtra("dateDifference", onDateDifference().toString())
    }
    private fun spinnerSelection(): Int {
        return Hotel_appointment_petSpinner.selectedItemPosition
    }
    private fun onDateDifference(): Int{
        return (endDate.substringBefore("/").toInt() - startDate.substringBefore("/").toInt())
    }

    private fun toAppointmentDetails() {
        if (isStartDateSelected.isNotBlank() && isEndDateSelected.isNotBlank()) {
            if (startDate != endDate) {
                val intent = Intent(this, HotelAppointmentDetailsActivity::class.java)
                sendValues(intent)
                startActivity(intent)
            } else {
                errorDate()
            }
        } else {
            errorMessage()
        }

    }

    private fun errorMessage() {
        val toast = Toast.makeText(
            this,
            "Make sure you put all the information and the correct Date",
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun errorDate() {
        val toast = Toast.makeText(
            this,
            "The day of arrival and departure cannot be the same.",
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
        Hotel_appointment_petSpinner.adapter = adapter
    }
}