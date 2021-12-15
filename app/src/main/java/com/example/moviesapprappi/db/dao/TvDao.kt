package com.example.moviesapprappi.db.dao

import androidx.room.*
import com.example.moviesapprappi.model.Tv

@Dao
interface TvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tvs: List<Tv>)

    @Update
    fun updateTv(tv: Tv)

    @Query("SELECT * FROM Tv WHERE id = :id_")
    fun getTv(id_: Int): Tv?

    @Query("SELECT * FROM Tv WHERE page = :page_")
    fun getTvList(page_: Int): List<Tv>
}
