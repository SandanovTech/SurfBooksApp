package com.example.surfbooksapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("INSERT INTO books (id, isFavourite) VALUES (:itemId, :isFavourite)", )
    suspend fun addToFavourite(itemId: String, isFavourite: Boolean)

    @Query("DELETE from books Where id = :itemId")
    suspend fun deleteFromFavourite(itemId: String)
}