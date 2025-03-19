package com.example.surfbooksapp.data.network.api

import com.example.surfbooksapp.data.network.model.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApi {
    @GET("volumes")
    fun getBooksByName(@Query("q") name: String): Call<BookResponse>
}
