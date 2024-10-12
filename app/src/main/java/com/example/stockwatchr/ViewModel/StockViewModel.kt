package com.example.stockwatchr.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.stockwatchr.Data.StockDatabase
import com.example.stockwatchr.Data.StockListItem
import com.example.stockwatchr.StockRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockViewModel(application: Application) : AndroidViewModel(application){
    private val repository: StockRepository
    val allStocks: LiveData<List<StockListItem>>

    init{
        val stockDao = StockDatabase.getDatabase(application).stockDao()
        repository = StockRepository(stockDao, application.applicationContext)
        allStocks = repository.allStocks

    }

    fun fetchStocks(apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Fetching data from API
                repository.fetchAndInsertStocks(apiKey)
            } catch (e: Exception) {
                Toast.makeText(getApplication(), "Error in fetching data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getStockBySymbol(symbol: String): LiveData<StockListItem?> {
        val stock = MutableLiveData<StockListItem?>()
        viewModelScope.launch {
            stock.value = repository.getStockBySymbol(symbol)
        }
        return stock
    }

}