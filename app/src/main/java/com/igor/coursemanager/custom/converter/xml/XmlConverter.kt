package com.igor.coursemanager.custom.converter.xml

import com.igor.coursemanager.custom.converter.Converter
import com.igor.coursemanager.model.product.Product
import java.util.*

abstract class XmlConverter<P : Product> :
    Converter<String, List<P>> {

    protected fun String.getValue(valueName: String): String {
        return if(startsWith(OPENING_BRACKET + valueName)) {
            val startIndex = valueName.length + OPENING_BRACKETS_NUMBER
            val endIndex = length - valueName.length - CLOSING_BRACKETS_NUMBER
            substring(startIndex, endIndex)
        } else throw IllegalFormatConversionException('a', XmlConverter::class.java)
    }
}

private const val OPENING_BRACKET = "<"
private const val OPENING_BRACKETS_NUMBER = 2 // "<", ">"
private const val CLOSING_BRACKETS_NUMBER = 3 // "<", "/", ">"