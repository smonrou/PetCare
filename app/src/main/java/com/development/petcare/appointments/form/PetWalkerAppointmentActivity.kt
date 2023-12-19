package com.development.petcare.appointments.form

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.development.petcare.R
import com.development.petcare.appointments.details.PetWalkAppointmentDetailsActivity
import com.development.petcare.appointments.fragments.DatePickerFragment
import com.development.petcare.appointments.fragments.EndTimePickerFragment
import com.development.petcare.appointments.fragments.StartTimePickerFragment
import com.development.petcare.objects.providers.PetProvider.Companion.PetList


class PetWalkerAppointmentActivity : AppCompatActivity() {
    private lateinit var PetWalk_appointment_cv_toDetails: CardView
    private lateinit var PetWalk_appointment_yourName: TextView
    private lateinit var PetWalk_appointment_yourAddres: TextView
    private lateinit var PetWalk_appointment_yourCity: TextView
    private lateinit var PetWalk_appointment_yourPhoneNumber: TextView
    private lateinit var PetWalk_appointment_petSpinner: Spinner
    private lateinit var PetWalk_appointment_etDate: EditText
    private lateinit var PetWalk_appointment_etStartTime: EditText
    private lateinit var PetWalk_appointment_etEndTime: EditText
    private lateinit var PetWalk_appointment_txtPrice: TextView
    private lateinit var PetWalk_appointment_goBack: ImageView


    private var startTime: String = ""
    private var endTime: String = ""
    private var isDateSelected: String = ""
    private var date: String = ""

    private var name: String = ""
    private var type: String = ""
    private var city: String = ""
    private var phone: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_walker_appointment)
        initComponents()
        initListener()
        putValues()
    }

    private fun initComponents() {
        PetWalk_appointment_cv_toDetails = findViewById(R.id.PetWalk_appointment_cv_toDetails)
        PetWalk_appointment_yourName = findViewById(R.id.PetWalk_appointment_yourName)
        PetWalk_appointment_yourAddres = findViewById(R.id.PetWalk_appointment_yourAddres)
        PetWalk_appointment_yourCity = findViewById(R.id.PetWalk_appointment_yourCity)
        PetWalk_appointment_yourPhoneNumber = findViewById(R.id.PetWalk_appointment_yourPhoneNumber)
        PetWalk_appointment_txtPrice = findViewById(R.id.PetWalk_appointment_txtPrice)
        PetWalk_appointment_goBack = findViewById(R.id.PetWalk_appointment_goBack)
        PetWalk_appointment_petSpinner = findViewById(R.id.PetWalk_appointment_petSpinner)

        initSpinnerIcon()
        initEditText()
    }


    private fun initListener() {
        PetWalk_appointment_cv_toDetails.setOnClickListener { toAppointmentDetails() }
        PetWalk_appointment_goBack.setOnClickListener { onBackPressed() }
    }

    private fun toAppointmentDetails() {
        if (startTime != endTime && isDateSelected.isNotBlank()) {
            val intent = Intent(this, PetWalkAppointmentDetailsActivity::class.java)
            sendingValues(intent)
            startActivity(intent)
        } else {
            errorMessage()
        }
    }

    private fun initEditText() {
        PetWalk_appointment_etDate = findViewById(R.id.PetWalk_appointment_etDate)
        PetWalk_appointment_etStartTime = findViewById(R.id.PetWalk_appointment_etStartTime)
        PetWalk_appointment_etEndTime = findViewById(R.id.PetWalk_appointment_etEndTime)

        PetWalk_appointment_etDate.setOnClickListener { showDatePicker() }
        PetWalk_appointment_etStartTime.setOnClickListener { showStartTimePicker() }
        PetWalk_appointment_etEndTime.setOnClickListener { showEndTimePicker() }
    }

    private fun putValues() {
        PetWalk_appointment_yourName.text = intent.getStringExtra("UserName")
        PetWalk_appointment_yourAddres.text = intent.getStringExtra("UserEmail")
        PetWalk_appointment_yourCity.text = intent.getStringExtra("walkerLocation")
        PetWalk_appointment_yourPhoneNumber.text = intent.getStringExtra("phoneNumber")
        PetWalk_appointment_txtPrice.text = intent.getStringExtra("walkerPrice")
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        PetWalk_appointment_etDate.setText("$day/ $month/ $year")
        isDateSelected = "selected"
        date = "$day/$month/$year"
    }

    private fun showStartTimePicker() {

        val startTimePickerFragment = StartTimePickerFragment { selectedTime ->
            startTime = selectedTime
            PetWalk_appointment_etStartTime.setText(startTime)
        }
        startTimePickerFragment.show(supportFragmentManager, "StartTimePicker")

    }

    private fun showEndTimePicker() {
        if (startTime.isNotBlank()) {
            val endTimePickerFragment = EndTimePickerFragment(startTime) { selectedTime ->
                endTime = selectedTime
                PetWalk_appointment_etEndTime.setText(endTime)
            }
            endTimePickerFragment.show(supportFragmentManager, "EndTimePicker")
        } else {
            timeMessage()
        }
    }

    private fun timeDiference(): Int {
        return (endTime.substringBefore(":").toInt() - startTime.substringBefore(":").toInt())
    }

    private fun spinnerSelection(): Int {
        return PetWalk_appointment_petSpinner.selectedItemPosition
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

    private fun timeMessage() {
        val toast = Toast.makeText(
            this,
            "Put the start time first",
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun gettingValues() {
        name = intent.getStringExtra("walkerName").toString()
        type = intent.getStringExtra("walkerSpecies").toString()
        city = intent.getStringExtra("walkerLocation").toString()
        phone = intent.getStringExtra("walkerPhone").toString()
    }

    private fun sendingValues(intent: Intent) {
        gettingValues()
        intent.putExtra("UserName", "Saul Monroy")
        intent.putExtra("UserEmail", "saulandresmm@gmail.com")
        intent.putExtra("city", PetWalk_appointment_yourCity.text)
        intent.putExtra("UserPhone", PetWalk_appointment_yourPhoneNumber.text)
        intent.putExtra("price", PetWalk_appointment_txtPrice.text)

        intent.putExtra("walkername", name)
        intent.putExtra("walkerLocation", city)
        intent.putExtra("walkerSpecies", type)
        intent.putExtra("phoneNumber", phone)
        intent.putExtra("hourPrice", intent.getStringExtra("hourPrice"))

        intent.putExtra("pet", spinnerSelection().toString())
        intent.putExtra("date", date)
        intent.putExtra("startTime", startTime)
        intent.putExtra("endTime", endTime)
        intent.putExtra("hours", timeDiference().toString())
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
        PetWalk_appointment_petSpinner.adapter = adapter
    }
}