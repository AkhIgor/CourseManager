package com.igor.coursemanager.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.igor.coursemanager.R
import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.presentation.date.owner.DateOwner
import java.util.*

class MainActivity : AppCompatActivity(), DateOwner {

    override var date: SimpleDate = initDate()
    private lateinit var navHost: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHost = findViewById(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.changing_date -> {
                showDateDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initDate(): SimpleDate {
        val timeZone = TimeZone.getTimeZone(MOSCOW_TIME_ZONE)
        val calendar = Calendar.getInstance()
        calendar.timeZone = timeZone
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH] + 1
        val year = calendar[Calendar.YEAR]

        return SimpleDate(day, month, year)
    }

    private fun showDateDialog() {
        navHost.findNavController().navigate(R.id.action_currency_fragment_to_datePickerDialog)
    }
}

private const val MOSCOW_TIME_ZONE = "Moscow"
