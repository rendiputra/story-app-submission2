package com.rendiputra.storyapp.ui.detail

import androidx.lifecycle.ViewModel
import com.rendiputra.storyapp.data.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val storyRepository: StoryRepository) : ViewModel() {

    fun getDetailStory(token: String, id: String) =
        storyRepository.getDetailStory(token, id)

}