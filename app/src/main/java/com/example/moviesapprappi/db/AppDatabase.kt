package com.example.moviesapprappi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesapprappi.db.converters.*
import com.example.moviesapprappi.db.dao.MovieDao
import com.example.moviesapprappi.db.dao.TvDao
import com.example.moviesapprappi.model.Movie
import com.example.moviesapprappi.model.Tv


@Database(
    entities = [(Movie::class), (Tv::class)],
    version = 1, exportSchema = false
)

@TypeConverters(
    value = [
        (StringListConverter::class), (IntegerListConverter::class),
        (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)
    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}