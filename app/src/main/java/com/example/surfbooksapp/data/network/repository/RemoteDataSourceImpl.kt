package com.example.surfbooksapp.data.network.repository

import android.util.Log
import com.example.surfbooksapp.data.network.api.GoogleApi
import com.example.surfbooksapp.data.network.model.BookResponse
import retrofit2.awaitResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: GoogleApi) : RemoteDataSource {
    override suspend fun getBooksFromApiByName(name: String): BookResponse {
        return try {
            val response = api.getBooksByName(name).awaitResponse()
            if (response.isSuccessful) {
                val body =
                    requireNotNull(response.body()) { "Api method getBooksByName returned null" }
                Log.d("RemoteDataSourceImpl", body.toString())
                BookResponse(
                    items = body.items
                )
            } else {
                Log.e("RemoteDataSourceImpl", "response isn't success")
                throw (IllegalStateException("API request failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("RemoteDataSourceImpl", e.message.toString())
            throw e
        }
    }
}