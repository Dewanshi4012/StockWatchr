package com.example.stockwatchr.View

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.stockwatchr.Data.StockListItem
import com.example.stockwatchr.R
import com.example.stockwatchr.ViewModel.StockViewModel
import com.example.stockwatchr.databinding.StockDetailBinding

class StockDetailActivity: AppCompatActivity() {

    private lateinit var stockViewModel: StockViewModel
    private lateinit var binding : StockDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StockDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stockSymbol = intent.getStringExtra("stockSymbol").orEmpty()

        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        stockViewModel.getStockBySymbol(stockSymbol).observe(this) { stock ->
            stock?.let {
                displayStockDetails(it)
            }
        }
    }

    private fun displayStockDetails(stock: StockListItem) {

        binding.stockSymbol.text = stock.symbol
        binding.stockName.text = stock.name

        // Stock Price
        val price = stock.price
        binding.stockPrice.text = price?.let { "$${String.format("%.3f", it.toDouble())}" } ?: "N/A"

        // Set text color based on price value
        if (price != null) {
            binding.stockPrice.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    if (price >= 0.0) R.color.teelGreen else R.color.red
                )
            )
        }

        // Stock Change

        val change = stock.change
        binding.stockChange.text = change?.let { String.format("%.3f", it.toDouble()) } ?: "N/A"

        // Set text color based on change value
        if (change != null) {
            binding.stockChange.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    if (change >= 0.0) R.color.teelGreen else R.color.red
                )
            )
        }
        // Stock Open
        binding.stockOpen.text =
            stock.open?.let { "Open: $${String.format("%.3f", it.toDouble())}" } ?: "Open: -"

        // Stock Day Low
        binding.stockDayLow.text =
            stock.dayLow?.let { "Low: $${String.format("%.3f", it.toDouble())}" } ?: "Low: -"

        // Stock Day High
        binding.stockDayHigh.text =
            stock.dayHigh?.let { "High: $${String.format("%.3f", it.toDouble())}" } ?: "High: -"

        // Stock Changes Percentage
        binding.stockPercentChange.text =
            stock.changesPercentage?.let { "Change: ${String.format("%.3f", it)}%" } ?: "Change: -"

        // Stock Previous Close
        binding.stockPreviousClose.text =
            stock.previousClose?.let { "Prev: $${String.format("%.3f", it.toDouble())}" } ?: "Prev: -"

        // Stock 52 Week Low
        binding.stock52WeekLow.text =
            stock.yearLow?.let { "52W Low: ${String.format("%.3f", it.toDouble())}" } ?: "52W Low: -"

        // Stock 52 Week High
        binding.stock52WeekHigh.text =
            stock.yearHigh?.let { "52W High: ${String.format("%.3f", it.toDouble())}" } ?: "52W High: -"

        binding.stockMarketCap.text =
            stock.marketCap?.let { "MktCap: ${formatValue(it.toDouble())}" } ?: "MktCap: -"

        // Stock Volume
        binding.stockVolume.text =
            stock.volume?.let { "Vol: ${formatValue(it.toDouble())}" } ?: "Vol: -"
        // Stock EPS
        binding.stockEPS.text =
            stock.eps?.let { "EPS: ${String.format("%.3f", it.toDouble())}" } ?: "EPS: -"

        // Stock P/E Ratio
        binding.stockPE.text =
            stock.pe?.let { "P/E: ${String.format("%.3f", it.toDouble())}" } ?: "P/E: -"
    }

    private fun formatValue(value: Double): String {
        return if (value >= 10000000) {
            // Convert to Crores
            String.format("%.2f Cr", value / 10000000)
        } else {
            // For values less than a crore, return as is
            String.format("%.2f", value)
        }
    }
}



