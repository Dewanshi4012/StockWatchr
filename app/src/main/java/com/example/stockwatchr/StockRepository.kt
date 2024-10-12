package com.example.stockwatchr

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.stockwatchr.Data.RetrofitInstance
import com.example.stockwatchr.Data.StockDao
import com.example.stockwatchr.Data.StockListItem

class StockRepository(private val stockDao: StockDao, private val context: Context) {

    val allStocks: LiveData<List<StockListItem>> = stockDao.getAllStocks()

    suspend fun fetchAndInsertStocks(apiKey: String): List<StockListItem> {
        return if (MyUtil.isInternetAvailable(context)) {
            // Fetching the data from the API
            val stockList = RetrofitInstance.api.getStockList(apiKey)
            // Inserting it into ROOM
            stockDao.insertStocks(stockList)
            stockList // Return the fetched stock list
        } else {
            // Return cached stocks when offline
            stockDao.getAllStocks().value ?: emptyList()
        }
    }


    suspend fun getStockBySymbol(symbol: String): StockListItem? {
        return stockDao.getStockBySymbol(symbol)
    }

}