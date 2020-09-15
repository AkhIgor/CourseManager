package com.igor.coursemanager.presentation.toolbar

import android.content.Context
import android.os.Build
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.igor.coursemanager.R
import com.igor.coursemanager.presentation.listener.SearchTextListener

class ToolbarManagerImpl(
    toolbar: Toolbar,
    private val searchTextListener: SearchTextListener
) : ToolbarManager {
    private var searchTextView: EditText = toolbar.findViewById(R.id.search_edit_text)
    private var clearButton: Button = toolbar.findViewById(R.id.clear_button)
    private var homeButton: Button = toolbar.findViewById(R.id.return_button)
    private var searchButton: Button = toolbar.findViewById(R.id.search_button)
    private var appNameTextView: TextView = toolbar.findViewById(R.id.app_name_text)
    private val inputManager: InputMethodManager

    init {
        inputManager =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                searchTextView.context.getSystemService(InputMethodManager::class.java)
            } else {
                searchTextView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            }
    }

    override fun setUpViews() {
        searchTextView.addTextChangedListener(searchTextListener)
        clearButton.setOnClickListener {
            searchTextListener.clearSearch(searchTextView)
        }
        homeButton.setOnClickListener {
            homeButton.visibility = View.GONE
            clearButton.visibility = View.GONE
            searchTextView.visibility = View.GONE
            searchButton.visibility = View.VISIBLE
            appNameTextView.visibility = View.VISIBLE
            searchTextListener.clearSearch(searchTextView)
            hideKeyboard()
        }
        searchButton.setOnClickListener {
            homeButton.visibility = View.VISIBLE
            clearButton.visibility = View.VISIBLE
            searchTextView.visibility = View.VISIBLE
            searchButton.visibility = View.GONE
            appNameTextView.visibility = View.GONE
            showKeyboard()
        }
    }

    override fun searchedData(): Editable = searchTextView.text

    private fun hideKeyboard() {
        inputManager.hideSoftInputFromWindow(
            searchTextView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
        searchTextView.clearFocus()
    }

    private fun showKeyboard() {
        searchTextView.requestFocus()
        inputManager.showSoftInput(
            searchTextView,
            InputMethodManager.SHOW_IMPLICIT
        )
    }
}