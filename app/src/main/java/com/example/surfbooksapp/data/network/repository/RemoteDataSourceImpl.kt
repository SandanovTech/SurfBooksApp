package com.example.surfbooksapp.data.network.repository

import android.util.Log
import com.example.surfbooksapp.data.network.api.GoogleApi
import com.example.surfbooksapp.data.network.model.BookResponse
import retrofit2.awaitResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: GoogleApi) : RemoteDataSource {
    override suspend fun getBooksFromApiByName(name: String): BookResponse? {
        return try {
            val response = api.getBooksByName(name).awaitResponse()
            if (response.isSuccessful) {
                val body =
                    requireNotNull(response.body()) { "Api method getBooksByName returned null" }
                Log.d("RemoteDataSourceImpl", body.toString())
                BookResponse(
                    totalItems = body.totalItems,
                    kind = body.kind,
                    items = body.items
                )
            } else {
                Log.e("RemoteDataSourceImpl", "response isn't success")
                null
            }
        } catch (e: Exception) {
            Log.e("RemoteDataSourceImpl", e.message.toString())
            null
        }
    }
}