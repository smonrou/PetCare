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
import com.development.petcare.appointments.details.CarerAppointmentDetailsActivity
import com.development.petcare.appointments.fragments.DatePickerFragment
import com.development.petcare.appointments.fragments.StartTimePickerFragment
import com.development.petcare.objects.providers.PetProvider.Companion.PetList
import com.development.petcare.objects.providers.VetProvider.Companion.VetList

class CarerAppointmentActivity : AppCompatActivity() {

    private lateinit var Carer_appointment_yourName: TextView
    private lateinit var Carer_appointment_yourAddress: TextView
    private lateinit var Carer_appointment_yourCity: TextView
    private lateinit var Carer_appointment_yourPhoneNumber: TextView
    private lateinit var Carer_appointment_petSpinner: Spinner
    private lateinit var Carer_appointment_etDate: EditText
    private lateinit var Carer_appointment_etTime: EditText
    private lateinit var Carer_appointment_cv_toDetails: CardView
    private lateinit var Carer_appointment_goBack: ImageView
    private lateinit var Carer_appointment_servSpinner: Spinner


    private var identification = ""
    private var isDateSelected = ""
    private var date = ""
    private var time = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carer_appointment)
        identification = intent.getStringExtra("carerId").toString()
        initComponents()
        initListeners()
        initSpinnerIcon()
        initServiceSpinner()
        putValues()
    }

    private fun initComponents() {
        Carer_appointment_yourName = findViewById(R.id.Carer_appointment_yourName)
        Carer_appointment_yourAddress = findViewById(R.id.Carer_appointment_yourAddress)
        Carer_appointment_yourCity = findViewById(R.id.Carer_appointment_yourCity)
        Carer_appointment_yourPhoneNumber = findViewById(R.id.Carer_appointment_yourPhoneNumber)
        Carer_appointment_petSpinner = findViewById(R.id.Carer_appointment_petSpinner)
        Carer_appointment_etDate = findViewById(R.id.Carer_appointment_etDate)
        Carer_appointment_etTime = findViewById(R.id.Carer_appointment_etTime)
        Carer_appointment_cv_toDetails = findViewById(R.id.Carer_appointment_cv_toDetails)
        Carer_appointment_goBack = findViewById(R.id.Carer_appointment_goBack)
        Carer_appointment_servSpinner = findViewById(R.id.Carer_appointment_servSpinner)
    }

    private fun initListeners() {
        Carer_appointment_cv_toDetails.setOnClickListener { toAppointmentDetails() }
        Carer_appointment_goBack.setOnClickListener { onBackPressed() }
        Carer_appointment_etDate.setOnClickListener { showDatePicker() }
        Carer_appointment_etTime.setOnClickListener { showTimePicker() }
    }

    private fun putValues() {
        Carer_appointment_yourName.text = intent.getStringExtra("userName")
        Carer_appointment_yourAddress.text = intent.getStringExtra("userAddress")
        Carer_appointment_yourCity.text = intent.getStringExtra("userCity")
        Carer_appointment_yourPhoneNumber.text = intent.getStringExtra("userPhone")
    }

    private fun getId(): String {
        identification = intent.getStringExtra("carerId").toString()
        return identification
    }


    private fun spinnerPetSelection(): Int {
        return Carer_appointment_petSpinner.selectedItemPosition
    }
    private fun spinnerServSelection(): Int {
        return Carer_appointment_servSpinner.selectedItemPosition
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun showTimePicker() {
        val startTimePickerFragment = StartTimePickerFragment { selectedTime ->
            time = selectedTime
            Carer_appointment_etTime.setText(time)
        }
        startTimePickerFragment.show(supportFragmentManager, "StartTimePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        Carer_appointment_etDate.setText("$day/ $month/ $year")
        isDateSelected = "selected"
        date = "$day/$month/$year"
    }

    private fun sendValues(intent: Intent) {
        intent.putExtra("userName", Carer_appointment_yourName.text)
        intent.putExtra("userAddress", Carer_appointment_yourAddress.text)
        intent.putExtra("userCity", Carer_appointment_yourCity.text)
        intent.putExtra("userPhone", Carer_appointment_yourPhoneNumber.text)
        intent.putExtra("carerId", getId())
        intent.putExtra("pet", spinnerPetSelection().toString())
        intent.putExtra("service", spinnerServSelection().toString())
        intent.putExtra("date", date)
        intent.putExtra("time", time)
    }

    private fun toAppointmentDetails() {

        if (isDateSelected.isNotBlank() && time.isNotBlank()) {
            val intent = Intent(this, CarerAppointmentDetailsActivity::class.java)
            sendValues(intent)
            startActivity(intent)
        } else {
            errorMessage()
        }
    }

    private fun errorMessage() {
        val toast = Toast.makeText(
            this, "Make sure you put all the information and the correct time", Toast.LENGTH_LONG
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
        Carer_appointment_petSpinner.adapter = adapter
    }
    private fun initServiceSpinner() {
        val services = VetList.find { it.id == identification}?.servicesTitles
        val prices = VetList.find { it.id == identification}?.fees
        val adapter: ArrayAdapter<String> =
            object : ArrayAdapter<String>(this, R.layout.spinner_layout_service, services!!) {
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
                        R.layout.spinner_layout_service,
                        parent,
                        false
                    )
                    val price = view.findViewById<TextView>(R.id.spinner_item_price)
                    val textView = view.findViewById<TextView>(R.id.spinner_item_service)
                    val item = getItem(position)
                    textView.text = item
                    price.text = prices?.get(position)
                    return view
                }
            }
        Carer_appointment_servSpinner.adapter = adapter
    }
}
