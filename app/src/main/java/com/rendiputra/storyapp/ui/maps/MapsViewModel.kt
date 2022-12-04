package com.rendiputra.storyapp.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rendiputra.storyapp.data.StoryRepository
import com.rendiputra.storyapp.domain.Response
import com.rendiputra.storyapp.domain.Story
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val storyRepository: StoryRepository) : ViewModel() {

    fun getStories(token: String): LiveData<Response<List<Story>>> {
        return storyRepository.getStoriesWithLatLng(token)
    }

}