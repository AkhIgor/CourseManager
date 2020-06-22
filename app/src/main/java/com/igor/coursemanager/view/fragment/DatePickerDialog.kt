package com.igor.coursemanager.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.igor.coursemanager.R

class DatePickerDialog : DialogFragment() {

    val

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.date_picker_fragment, container, false)
        setup(view)
        return view
    }

    private fun setup(view: View) {
        view.findViewById<Button>(R.id.possitive_button).setOnClickListener { setNewDate() }
        view.findViewById<Button>(R.id.negative_button).setOnClickListener { dialog?.cancel() }
    }

    private fun setNewDate() {

    }
}