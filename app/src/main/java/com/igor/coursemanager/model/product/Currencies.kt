package com.igor.coursemanager.model.product

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList

data class Currencies @JvmOverloads constructor(
    @field:Attribute(name = "Date", required = false)
    var date: String = "",
    @field:Attribute(name = "date", required = false)
    var oldDate: String = "",
    @field:Attribute(name = "name", required = false)
    var name: String = "",
    @field:ElementList(required = false, entry = "Valute", inline = true, empty = true)
    var currrencies: MutableList<Currency> = mutableListOf()
)