package com.rendiputra.storyapp.ui.add_story

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.rendiputra.storyapp.data.StoryRepository
import com.rendiputra.storyapp.domain.Response
import com.rendiputra.storyapp.util.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class AddStoryViewModelTest {

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var addStoryViewModel: AddStoryViewModel

    private val dummyToken = "dummy.token"
    private val dummyDescription = "Dummy Description."

    private val dummySuccessUploadStoryMessage = "Success add new story!"
    private val dummyErrorUploadStoryMessage = "Error add new story!"

    @Mock
    private lateinit var dummyImage: File

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        addStoryViewModel = AddStoryViewModel(storyRepository)
    }

    @Test
    fun `when Add New Story Should Success`() {
        val expectedOutput = MutableLiveData<Response<String>>()
        expectedOutput.value = Response.Success(dummySuccessUploadStoryMessage)

        Mockito.`when`(storyRepository.addNewStory(dummyToken, dummyImage, dummyDescription)).thenReturn(expectedOutput)

        val actualOutput = addStoryViewModel.uploadNewStory(dummyToken, dummyImage, dummyDescription).getOrAwaitValue()

        Mockito.verify(storyRepository).addNewStory(dummyToken, dummyImage, dummyDescription)

        assertNotNull(actualOutput)
        assertTrue(actualOutput is Response.Success)
        assertEquals(dummySuccessUploadStoryMessage, (actualOutput as Response.Success).data)
    }

    @Test
    fun `when Add New Story Should Error`() {
        val expectedOutput = MutableLiveData<Response<String>>()
        expectedOutput.value = Response.Error(dummyErrorUploadStoryMessage)

        Mockito.`when`(storyRepository.addNewStory(dummyToken, dummyImage, dummyDescription)).thenReturn(expectedOutput)

        val actualOutput = addStoryViewModel.uploadNewStory(dummyToken, dummyImage, dummyDescription).getOrAwaitValue()

        Mockito.verify(storyRepository).addNewStory(dummyToken, dummyImage, dummyDescription)

        assertNotNull(actualOutput)
        assertTrue(actualOutput is Response.Error)
        assertEquals(dummyErrorUploadStoryMessage, (actualOutput as Response.Error).message)
    }

}