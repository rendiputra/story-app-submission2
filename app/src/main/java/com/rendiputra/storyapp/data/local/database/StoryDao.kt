package com.rendiputra.storyapp.data.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rendiputra.storyapp.data.local.entity.StoryEntity

@Dao
interface StoryDao {

    @Query("SELECT * FROM story")
    fun getStories(): PagingSource<Int, StoryEntity>

    @Query("DELETE FROM story")
    suspend fun deleteStories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<StoryEntity>)
}