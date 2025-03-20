package com.example.surfbooksapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.surfbooksapp.data.local.model.BookEntity
import com.example.surfbooksapp.data.local.model.VolumeInfoLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
//    @Query("INSERT INTO books (id, volumeInfoLocal, isFavourite) VALUES ( :id , :volumeInfoLocal, :isFavourite)")
    @Update
    suspend fun addToFavourite(book: BookEntity)

    @Update
    suspend fun deleteFromFavourite(book: BookEntity)

    @Query("Select * from books")
    fun getAllBooks(): List<BookEntity>

    @Query("Select * from books where id = :bookId")
    fun getBookById(bookId: String): BookEntity

    @Query("SELECT * FROM books WHERE isFavourite = :isFavouriteInt")
    fun getFavouriteBooks(isFavouriteInt : Int): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(bookEntity: List<BookEntity>)
}