package com.rendiputra.storyapp.ui.maps

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.rendiputra.storyapp.data.StoryRepository
import com.rendiputra.storyapp.domain.Response
import com.rendiputra.storyapp.domain.Story
import com.rendiputra.storyapp.util.DataDummy
import com.rendiputra.storyapp.util.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var mapsViewModel: MapsViewModel

    private val dummyToken = "token123"
    private val dummyStories = DataDummy.generateDummyStories()
    private val dummyErrorMessage = "Dummy Error"

    @Before
    fun setUp() {
        mapsViewModel = MapsViewModel(storyRepository)
    }

    @Test
    fun `when Get Stories Should Not Null and Return Success`() {
        val expectedStories = MutableLiveData<Response<List<Story>>>()
        expectedStories.value = Response.Success(dummyStories)

        Mockito.`when`(storyRepository.getStoriesWithLatLng(dummyToken)).thenReturn(expectedStories)

        val actualStories = mapsViewModel.getStories(dummyToken).getOrAwaitValue()

        Mockito.verify(storyRepository).getStoriesWithLatLng(dummyToken)

        assertNotNull(actualStories)
        assertTrue(actualStories is Response.Success)
        assertEquals(dummyStories.size, (actualStories as Response.Success).data.size)
    }

    @Test
    fun `when Get Stories Should Empty and Return Empty`() {
        val expectedStories = MutableLiveData<Response<List<Story>>>()
        expectedStories.value = Response.Empty

        Mockito.`when`(storyRepository.getStoriesWithLatLng(dummyToken)).thenReturn(expectedStories)

        val actualStories = mapsViewModel.getStories(dummyToken).getOrAwaitValue()

        Mockito.verify(storyRepository).getStoriesWithLatLng(dummyToken)

        assertNotNull(actualStories)
        assertTrue(actualStories is Response.Empty)
    }

    @Test
    fun `when Get Stories Should Null and Return Error`() {
        val expectedStories = MutableLiveData<Response<List<Story>>>()
        expectedStories.value = Response.Error(dummyErrorMessage)

        Mockito.`when`(storyRepository.getStoriesWithLatLng(dummyToken)).thenReturn(expectedStories)

        val actualStories = mapsViewModel.getStories(dummyToken).getOrAwaitValue()

        Mockito.verify(storyRepository).getStoriesWithLatLng(dummyToken)

        assertNotNull(actualStories)
        assertTrue(actualStories is Response.Error)
        assertEquals(dummyErrorMessage, (actualStories as Response.Error).message)
    }
}