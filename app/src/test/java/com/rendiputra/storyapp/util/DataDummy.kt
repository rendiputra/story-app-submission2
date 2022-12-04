package com.rendiputra.storyapp.util

import com.rendiputra.storyapp.data.local.entity.StoryEntity
import com.rendiputra.storyapp.domain.Login
import com.rendiputra.storyapp.domain.Register
import com.rendiputra.storyapp.domain.Story

object DataDummy {

    fun generateDummyStories(): List<Story> {
        return (1..10).map { no ->
            Story(
                id = "id $no",
                name = "Name $no",
                description = "Description $no",
                photoUrl = "https://story-api.dicoding.dev/images/stories/photos-1668743321688_oXgiMgrS.jpg",
                createdAt = "2022-11-18T03:48:41.690Z",
                lat = 0.0,
                lon = 0.0
            )
        }
    }

    fun generateDummyStoryEntities(): List<StoryEntity> {
        return (1..2).map { no ->
            StoryEntity(
                id = "id $no",
                name = "Name $no",
                description = "Description $no",
                photoUrl = "https://story-api.dicoding.dev/images/stories/photos-1668743321688_oXgiMgrS.jpg",
                createdAt = "2022-11-18T03:48:41.690Z",
                lat = 0.0,
                lon = 0.0
            )
        }
    }


    fun generateDummyStory(): Story {
        return Story(
            id = "id",
            name = "Name",
            description = "Description",
            photoUrl = "https://story-api.dicoding.dev/images/stories/photos-1668743321688_oXgiMgrS.jpg",
            createdAt = "2022-11-18T03:48:41.690Z",
            lat = 0.0,
            lon = 0.0
        )
    }

    fun generateDummyLogin(): Login =
        Login(
            "User Name",
            "user123",
            "token123"
        )

    fun generateDummyRegister(): Register =
        Register(
            true,
            "Register success"
        )
}