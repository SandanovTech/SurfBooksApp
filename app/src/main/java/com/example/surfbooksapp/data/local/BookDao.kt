package com.example.surfbooksapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.surfbooksapp.data.local.model.BookEntity
import com.example.surfbooksapp.data.local.model.VolumeInfoLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("INSERT INTO books (id, volumeInfoLocal, isFavourite) VALUES ( :id , :volumeInfoLocal, :isFavourite)")
    suspend fun addToFavourite(id: String, volumeInfoLocal: VolumeInfoLocal, isFavourite: Boolean)

    @Delete
    suspend fun deleteFromFavourite(book: BookEntity)

    @Query("Select * from books")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("Select * from books where id = :bookId")
    fun getBookById(bookId: String): BookEntity

    @Query("Select * from books where isFavourite =:isFavourite")
    fun getFavouriteBooks(isFavourite: Boolean): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(bookEntity: List<BookEntity>)
}