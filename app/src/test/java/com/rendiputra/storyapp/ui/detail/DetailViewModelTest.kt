package com.rendiputra.storyapp.ui.detail

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
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var detailViewModel: DetailViewModel

    private val dummyToken = "token123"
    private val dummyId = "id123"
    private val dummyStory = DataDummy.generateDummyStory()
    private val dummyErrorMessage = "Dummy Error"

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(storyRepository)
    }

    @Test
    fun `when Get Story Should Not Null and Return Success`() {
        val expectedStory = MutableLiveData<Response<Story>>()
        expectedStory.value = Response.Success(dummyStory)

        Mockito.`when`(storyRepository.getDetailStory(dummyToken, dummyId)).thenReturn(expectedStory)

        val actualStory = detailViewModel.getDetailStory(dummyToken, dummyId).getOrAwaitValue()

        Mockito.verify(storyRepository).getDetailStory(dummyToken, dummyId)

        assertNotNull(actualStory)
        assertTrue(actualStory is Response.Success)
        assertEquals(dummyStory, (actualStory as Response.Success).data)
    }

    @Test
    fun `when Get Story Should Empty and Return Empty`() {
        val expectedStory = MutableLiveData<Response<Story>>()
        expectedStory.value = Response.Empty

        Mockito.`when`(storyRepository.getDetailStory(dummyToken, dummyId)).thenReturn(expectedStory)

        val actualStory = detailViewModel.getDetailStory(dummyToken, dummyId).getOrAwaitValue()

        Mockito.verify(storyRepository).getDetailStory(dummyToken, dummyId)

        assertNotNull(actualStory)
        assertTrue(actualStory is Response.Empty)
    }

    @Test
    fun `when Get Story Should Null and Return Error`() {
        val expectedStory = MutableLiveData<Response<Story>>()
        expectedStory.value = Response.Error(dummyErrorMessage)

        Mockito.`when`(storyRepository.getDetailStory(dummyToken, dummyId)).thenReturn(expectedStory)

        val actualStory = detailViewModel.getDetailStory(dummyToken, dummyId).getOrAwaitValue()

        Mockito.verify(storyRepository).getDetailStory(dummyToken, dummyId)

        assertNotNull(actualStory)
        assertTrue(actualStory is Response.Error)
        assertEquals(dummyErrorMessage, (actualStory as Response.Error).message)
    }

}