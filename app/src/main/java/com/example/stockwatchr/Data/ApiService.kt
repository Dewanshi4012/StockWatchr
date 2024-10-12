package com.example.stockwatchr.Data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("symbol/NASDAQ")
    suspend fun getStockList(@Query("apikey") apiKey: String): List<StockListItem>

}