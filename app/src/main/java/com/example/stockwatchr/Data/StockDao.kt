package com.example.stockwatchr.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStocks(stockList: List<StockListItem>)

    @Query("SELECT * FROM stock_table")
    fun getAllStocks(): LiveData<List<StockListItem>>

    @Query("SELECT * FROM stock_table WHERE symbol = :symbol LIMIT 1")
    suspend fun getStockBySymbol(symbol: String): StockListItem? // Query by stock symbol

}