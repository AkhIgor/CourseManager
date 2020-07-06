package com.igor.coursemanager.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Parcelize
@Root
data class Currency @JvmOverloads constructor(
    @field:Attribute(name = "ID")
    var id: String = "",
    @field:Element(name = "NumCode")
    var numCode: Int = 0,
    @field:Element(name = "CharCode")
    var charCode: String = "",
    @field:Element(name = "Nominal")
    var nominal: Int = 0,
    @field:Element(name = "Name")
    var name: String = "",
    @field:Element(name = "Value")
    var value: String = ""
) : Parcelable, Product