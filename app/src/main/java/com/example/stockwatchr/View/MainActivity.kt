package com.example.stockwatchr.View

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockwatchr.NetworkChangeReceiver
import com.example.stockwatchr.R
import com.example.stockwatchr.ViewModel.StockViewModel
import com.example.stockwatchr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var stockViewModel: StockViewModel
    private lateinit var stockAdapter: StockAdapter
    private lateinit var apiKey : String
    private lateinit var searchView: SearchView
    private val networkChangeReceiver = NetworkChangeReceiver()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiKey = "q43cnDfgGp3rAocOGjsTW6sD6oEeQx3a"

        searchView = findViewById(R.id.search_view)

        stockAdapter = StockAdapter()
        binding.recyclerView.adapter = stockAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        stockViewModel.allStocks.observe(this) { stocks ->
            // Updating UI with the stock data
            stockAdapter.setStocksList(stocks)
        }

        // Fetching data from API
        stockViewModel.fetchStocks(apiKey)

        stockAdapter.setOnItemClickListener(object: StockAdapter.onItemClickListener {
            override fun onItemClick(position: Int, stockSymbol: String) {
                val intent = Intent(this@MainActivity, StockDetailActivity::class.java)
                intent.putExtra("stockSymbol", stockSymbol)
                startActivity(intent)
            }

        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                stockAdapter.filterStocks(newText ?: "")
                checkIfNoResults(stockAdapter.itemCount)
                return true
            }
        })
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(networkChangeReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkChangeReceiver)
    }
    private fun checkIfNoResults(stockCount: Int) {
        if (stockCount == 0) {
            //noResultsTextView.visibility = View.VISIBLE
            Toast.makeText(this,"No Data found for the Symbol",Toast.LENGTH_SHORT).show()
        }
    }
}
