package com.igor.coursemanager.presentation.toolbar

import android.text.Editable

interface ToolbarManager {

    fun setUpViews()

    fun searchedData(): Editable
}