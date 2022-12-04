@file:OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)

package com.rendiputra.storyapp.ui.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.rendiputra.storyapp.data.StoryRepository
import com.rendiputra.storyapp.data.datastore.AuthPreferences
import com.rendiputra.storyapp.domain.Login
import com.rendiputra.storyapp.domain.Register
import com.rendiputra.storyapp.domain.Response
import com.rendiputra.storyapp.util.DataDummy
import com.rendiputra.storyapp.util.MainDispatcherRule
import com.rendiputra.storyapp.util.getOrAwaitValue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthViewModelTest {

    @Mock
    private lateinit var storyRepository: StoryRepository
    @Mock
    private lateinit var authPreferences: AuthPreferences

    private lateinit var authViewModel: AuthViewModel

    private val dummyName = "Rendi Putra Pradana"
    private val dummyEmail = "rendiputrapradana@gmail.com"
    private val dummyPassword = "zxcasdqwe123"

    private val dummyLogin = DataDummy.generateDummyLogin()
    private val dummyLoginFailedMessage = "Login failed"

    private val dummyRegister = DataDummy.generateDummyRegister()
    private val dummyRegisterFailedMessage = "Register failed"

    private val dummyAuthToken = "dkjsnfjk12snfkjna213"

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        authViewModel = AuthViewModel(storyRepository, authPreferences)
    }

    @Test
    fun `when Login Success then Return Success`() = runTest {
        val expectedResponse = MutableLiveData<Response<Login>>()
        expectedResponse.value = Response.Success(dummyLogin)

        Mockito.`when`(storyRepository.login(dummyEmail, dummyPassword)).thenReturn(expectedResponse)

        val actualResponse = authViewModel.login(dummyEmail, dummyPassword).getOrAwaitValue()

        Mockito.verify(storyRepository).login(dummyEmail, dummyPassword)

        assertNotNull(actualResponse)
        assertTrue(actualResponse is Response.Success)
        assertEquals(dummyLogin, (actualResponse as Response.Success).data)
    }

    @Test
    fun `when Login Failed then Return Error`() = runTest {
        val expectedResponse = MutableLiveData<Response<Login>>()
        expectedResponse.value = Response.Error(dummyLoginFailedMessage)

        Mockito.`when`(storyRepository.login(dummyEmail, dummyPassword)).thenReturn(expectedResponse)

        val actualResponse = authViewModel.login(dummyEmail, dummyPassword).getOrAwaitValue()

        Mockito.verify(storyRepository).login(dummyEmail, dummyPassword)

        assertNotNull(actualResponse)
        assertTrue(actualResponse is Response.Error)
        assertEquals(dummyLoginFailedMessage, (actualResponse as Response.Error).message)
    }

    @Test
    fun `when Register Success then Return Success`() = runTest {
        val expectedResponse = MutableLiveData<Response<Register>>()
        expectedResponse.value = Response.Success(dummyRegister)

        Mockito.`when`(storyRepository.register(dummyName, dummyEmail, dummyPassword)).thenReturn(expectedResponse)

        val actualResponse = authViewModel.register(dummyName, dummyEmail, dummyPassword).getOrAwaitValue()

        Mockito.verify(storyRepository).register(dummyName, dummyEmail, dummyPassword)

        assertNotNull(actualResponse)
        assertTrue(actualResponse is Response.Success)
        assertEquals(dummyRegister, (actualResponse as Response.Success).data)
    }

    @Test
    fun `when Register Failed then Return Error`() = runTest {
        val expectedResponse = MutableLiveData<Response<Register>>()
        expectedResponse.value = Response.Error(dummyRegisterFailedMessage)

        Mockito.`when`(storyRepository.register(dummyName, dummyEmail, dummyPassword)).thenReturn(expectedResponse)

        val actualResponse = authViewModel.register(dummyName, dummyEmail, dummyPassword).getOrAwaitValue()

        Mockito.verify(storyRepository).register(dummyName, dummyEmail, dummyPassword)

        assertNotNull(actualResponse)
        assertTrue(actualResponse is Response.Error)
        assertEquals(dummyRegisterFailedMessage, (actualResponse as Response.Error).message)
    }

    @Test
    fun `success get auth token`() = runTest {
        val expectedResponse = flow {
            emit(dummyAuthToken)
        }

        Mockito.`when`(authPreferences.authToken).thenReturn(expectedResponse)
        authViewModel.getAuthToken()
        val actualResponse = authViewModel.authToken.getOrAwaitValue()
        Mockito.verify(authPreferences).authToken

        assertNotNull(actualResponse)
        assertEquals(dummyAuthToken, actualResponse)
    }

    @Test
    fun `remove auth token`() = runTest {
        authViewModel.removeAuthToken()
        Mockito.verify(authPreferences).removeAuthToken()
    }

    @Test
    fun `update auth token`() = runTest {
        authViewModel.updateAuthToken(dummyAuthToken)
        Mockito.verify(authPreferences).updateAuthToken(dummyAuthToken)
    }
}