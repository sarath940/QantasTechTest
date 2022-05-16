package com.example.qantastechtest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.qantastechtest.data.remote.RemoteImpl
import com.example.qantastechtest.data.remote.api.ApiInterface
import com.example.qantastechtest.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiInterface: ApiInterface

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var remoteImpl: RemoteImpl

    @Before
    fun setup() {
        remoteImpl = RemoteImpl(apiInterface)
    }

    @Test
    fun testFetchAirData() {
        testCoroutineRule.runBlockingTest {
            remoteImpl.fetchAirData()
            verify(apiInterface).getQantasDataModel()
        }
    }
}