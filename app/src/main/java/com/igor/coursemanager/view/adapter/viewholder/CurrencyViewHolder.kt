package com.igor.coursemanager.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.igor.coursemanager.R
import com.igor.coursemanager.model.product.Currency

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val numCodeTextView: TextView = itemView.findViewById(R.id.currency_num_code_text_view)
    private val charCodeTextView: TextView = itemView.findViewById(R.id.currency_num_code_text_view)
    private val nameTextView: TextView = itemView.findViewById(R.id.currency_num_code_text_view)
    private val valueTextView: TextView = itemView.findViewById(R.id.currency_num_code_text_view)

    fun onBind(currency: Currency) {
        numCodeTextView.text = currency.numCode.toString()
        charCodeTextView.text = currency.charCode
        nameTextView.text = currency.name
        valueTextView.text = currency.value.toString()
    }
}