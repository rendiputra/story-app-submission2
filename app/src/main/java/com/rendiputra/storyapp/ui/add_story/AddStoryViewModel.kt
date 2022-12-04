package com.rendiputra.storyapp.ui.add_story

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rendiputra.storyapp.data.StoryRepository
import com.rendiputra.storyapp.domain.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel @Inject constructor(private val storyRepository: StoryRepository) : ViewModel() {

    fun uploadNewStory(
        token: String,
        image: File,
        description: String,
        latitude: Double? = null,
        longitude: Double? = null,
    ): LiveData<Response<String>> {
        return storyRepository.addNewStory(
            token,
            image,
            description,
            latitude,
            longitude
        )
    }
}