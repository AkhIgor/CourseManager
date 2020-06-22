package com.igor.coursemanager.network.converter

import android.util.Log
import com.igor.coursemanager.converter.xml.CurrencyConverter
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.model.product.Product
import com.igor.coursemanager.converter.xml.XmlConverter
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