package com.development.petcare.appointments.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.development.petcare.R
import java.util.Calendar


class StartTimePickerFragment(private val listener: (String) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _: TimePicker?, hourOfDay: Int, _: Int ->
                listener("$hourOfDay:00")
            },
            hour,
            minute,
            true
        )
        return timePickerDialog
    }
}