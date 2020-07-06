package com.igor.coursemanager.network.custom.converter

import android.util.Log
import com.igor.coursemanager.custom.converter.xml.CurrencyConverter
import com.igor.coursemanager.custom.converter.xml.XmlConverter
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.model.product.Product
import kotlin.reflect.KClass

class ResponseConverter {

    private val converters: Map<KClass<out Product>, XmlConverter<out Product>> =
        mapOf(Currency::class to CurrencyConverter())

    fun <P : Product> convert(value: String, clazz: KClass<P>): List<P>? {
        val converter = converters[clazz]
        return if (converter != null) {
            (converter.convert(value) as? List<P>)
        } else {
            Log.e("ResponseConverter", "converter not found")
            null
        }
    }
}