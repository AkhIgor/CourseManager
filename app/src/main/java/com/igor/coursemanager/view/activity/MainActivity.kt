package com.igor.coursemanager.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.igor.coursemanager.R
import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.network.interactor.InteractorImpl
import java.util.Calendar
import java.util.TimeZone

class MainActivity : AppCompatActivity() {

    private val interactor = InteractorImpl()
    var date: SimpleDate = initDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]

        return SimpleDate(day, month, year)
    }

    private fun showDateDialog() {

    }
}

private const val MOSCOW_TIME_ZONE = "Moscow"
