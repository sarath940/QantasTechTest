package com.example.qantastechtest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.qantastechtest.data.local.LocalDataSource
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem
import com.example.qantastechtest.data.remote.RemoteSource
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
class QantasRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var remoteSource: RemoteSource
    @Mock
    private lateinit var localDataSource: LocalDataSource


    private val airport1 = QantasDataModelItem(airportCode = "Au", airportName = "Austraila")
    private val airport2 = QantasDataModelItem(airportCode = "USA", airportName = "United State of America")
    private val airport3 = QantasDataModelItem(airportCode = "IND", airportName = "INDIA")

    private val dataSource = listOf(airport1, airport2,airport3)


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var qantasRepository: QantasRepository

    @Before
    fun setup(){
        qantasRepository = QantasRepository(remoteSource = remoteSource,
            localDataSource = localDataSource
        )
    }

    @Test
    fun testFetchDataFromRemoteDataSource(){
       testCoroutineRule.runBlockingTest {
          qantasRepository.fetchRemoteData()
           verify(remoteSource).fetchAirData()
       }
    }

    @Test
    fun testSaveDataFromLocalDataSource(){
        testCoroutineRule.runBlockingTest {
            qantasRepository.saveData(dataSource)
            verify(localDataSource).deleteData()
            verify(localDataSource).saveData(dataSource)
        }
    }

    @Test
    fun getDataFromLocalDataSource(){
        testCoroutineRule.runBlockingTest {
            qantasRepository.fetchLocalData()
            verify(localDataSource).getAirData()
        }
    }


}