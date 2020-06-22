package com.igor.coursemanager.converter.xml

import com.igor.coursemanager.model.product.Currency

class CurrencyConverter : XmlConverter<Currency>() {
    private val currencyList = mutableListOf<Currency>()

    private var numCode: Int = 0
    private lateinit var charCode: String
    private var nominal: Int = 0
    private lateinit var name: String
    private var value: Double = 0.0

    override fun convert(from: String): List<Currency> {
        val iterator = from.lines().iterator()

        while (iterator.hasNext()) {
            numCode = iterator.next().getValue(NUM_CODE).toInt()
            charCode = iterator.next().getValue(CHAR_CODE)
            nominal = iterator.next().getValue(NOMINAL).toInt()
            name = iterator.next().getValue(NAME)
            value = iterator.next().getValue(VALUE).toDouble()

            currencyList.add(
                Currency(
                    numCode,
                    charCode,
                    nominal,
                    name,
                    value
                )
            )
        }

        return currencyList
    }
}

private const val NUM_CODE = "NumCode"
private const val CHAR_CODE = "CharCode"
private const val NOMINAL = "Nominal"
private const val NAME = "Name"
private const val VALUE = "Value"
