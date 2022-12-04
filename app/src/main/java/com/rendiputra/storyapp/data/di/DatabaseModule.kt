package com.rendiputra.storyapp.data.di

import android.content.Context
import androidx.room.Room
import com.rendiputra.storyapp.data.local.database.StoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideStoryDatabase(@ApplicationContext context: Context): StoryDatabase =
        Room.databaseBuilder(context.applicationContext, StoryDatabase::class.java, "story_database")
            .fallbackToDestructiveMigration()
            .build()
}