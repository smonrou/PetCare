package com.development.petcare.appointments.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.development.petcare.R
import java.util.Calendar

class EndTimePickerFragment(private val startTime: String, private val listener: (String) -> Unit) :
    DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val startTimeParts = startTime.split(":")
        val startHour = startTimeParts[0].toInt()

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _: TimePicker?, hourOfDay: Int, _: Int ->
                val endHour = if (hourOfDay < startHour) {
                    startHour
                } else {
                    hourOfDay
                }
                listener("$endHour:00")
            },
            hour,
            minute,
            true
        )

        timePickerDialog.updateTime(hour, minute)

        return timePickerDialog
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

    }
}