package com.igor.coursemanager.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.igor.coursemanager.R
import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.presentation.date.owner.DateOwner
import com.igor.coursemanager.view.activity.MainActivity
import java.util.*

class DatePickerDialog : DialogFragment() {

    private lateinit var datePicker: DatePicker
    private lateinit var dateOwner: DateOwner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dateOwner = requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.date_picker_fragment, container, false)
        setup(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(dateOwner.date) {
            datePicker.updateDate(year, month, day)
        }
    }

    private fun setup(view: View) {
        datePicker = view.findViewById(R.id.date_picker_view)
        view.findViewById<Button>(R.id.possitive_button).setOnClickListener {
            setNewDate()?.let { newDate ->
                dateOwner.date = newDate
            }
            dismiss()
        }
        view.findViewById<Button>(R.id.negative_button).setOnClickListener { dialog?.cancel() }
    }

    private fun setNewDate(): SimpleDate? {
        val calendar = Calendar.getInstance()
        return if (yearLess(calendar[Calendar.YEAR])
            || (yearEqual(calendar[Calendar.YEAR]) && monthLess(calendar[Calendar.MONTH]))
            || (monthEqual(calendar[Calendar.MONTH]) && dayLessOrEqual(calendar[Calendar.DAY_OF_MONTH]))
        ) {
            with(datePicker) {
                SimpleDate(
                    dayOfMonth,
                    month,
                    year
                )
            }
        } else null

    }

    private fun yearLess(currentYear: Int): Boolean = datePicker.year < currentYear

    private fun yearEqual(currentYear: Int): Boolean = datePicker.year == currentYear

    private fun monthLess(currentMonth: Int): Boolean = datePicker.month < currentMonth

    private fun monthEqual(currentMonth: Int): Boolean = datePicker.month == currentMonth

    private fun dayLessOrEqual(currentDayOfMonth: Int): Boolean =
        datePicker.dayOfMonth <= currentDayOfMonth
}