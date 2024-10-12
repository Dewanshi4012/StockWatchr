package com.example.stockwatchr.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.stockwatchr.Data.StockListItem
import com.example.stockwatchr.R
import com.example.stockwatchr.databinding.StockItemBinding

class StockAdapter: RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    private var stockList = emptyList<StockListItem>()
    private var filteredStockList = emptyList<StockListItem>()

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int, stockSymbol: String)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class StockViewHolder(private val binding: StockItemBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition, binding.stockSymbol.text.toString())
            }
        }

        fun bind(stock: StockListItem) {
            binding.stockSymbol.text = stock.symbol
            binding.stockPrice.text = stock.price.toString()
            binding.stockChange.text = stock.change.toString()
            binding.stockName.text = stock.name

            val change = stock.change
            if (change != null) {
                if (change >= 0) {
                    binding.stockChange.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.teelGreen)
                    ) // Positive change
                } else {
                    binding.stockChange.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.red)
                    ) // Negative change
                }
            } else {
                binding.stockChange.text = "-"
            }

            val price = stock.price
            if (price != null) {
                binding.stockPrice.text = "$${price.toString()}"
            } else {
                binding.stockPrice.text = "-"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = StockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentStock = filteredStockList[position]
        holder.bind(currentStock)
    }

    override fun getItemCount(): Int {
        return filteredStockList.size
    }

    fun setStocksList(stocks: List<StockListItem>) {
        stockList = stocks
        filteredStockList = stocks
        notifyDataSetChanged()
    }

    fun filterStocks(query: String) {
        filteredStockList = if (query.isEmpty()) {
            stockList
        } else {
            stockList.filter { it.symbol.contains(query, true) }
        }
        notifyDataSetChanged()
    }
}


