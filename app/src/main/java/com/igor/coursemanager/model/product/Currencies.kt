package com.igor.coursemanager.model.product

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList

//@Root(name = "ValCurs")
data class Currencies @JvmOverloads constructor(
    @field:Attribute(name = "Date")
    var date: String = "",
    @field:Attribute(name = "name")
    var name: String = "",
    @field:ElementList(required = false, entry = "Valute", inline = true, empty = true)
    var currrencies: MutableList<Currency> = mutableListOf()
)