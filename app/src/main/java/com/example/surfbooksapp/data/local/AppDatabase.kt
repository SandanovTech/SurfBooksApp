package com.example.surfbooksapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.surfbooksapp.data.local.converterType.BookTypeConverters
import com.example.surfbooksapp.data.local.model.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
@TypeConverters(BookTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}