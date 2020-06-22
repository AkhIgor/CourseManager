package com.igor.coursemanager.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
    val numCode: Int,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double
) : Parcelable, Product