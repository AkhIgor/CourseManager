package com.igor.coursemanager.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igor.coursemanager.R
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.view.adapter.viewholder.CurrencyViewHolder

class CurrencyListAdapter(
    private val currencies: List<Currency>
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val viewHolder = View.inflate(parent.context, R.layout.currency_list_item, parent)
        return CurrencyViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.onBind(currencies[position])
    }
}