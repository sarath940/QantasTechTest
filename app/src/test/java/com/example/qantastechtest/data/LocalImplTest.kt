package com.example.qantastechtest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.qantastechtest.data.local.LocalDataSourceImpl
import com.example.qantastechtest.data.local.QantasDao
import com.example.qantastechtest.data.model.QantasDataModel
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
class LocalImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var qantasDao: QantasDao

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var localDataSourceImpl: LocalDataSourceImpl


    private val airport1 =
        QantasDataModel.QantasDataModelItem(airportCode = "Au", airportName = "Austraila")
    private val airport2 = QantasDataModel.QantasDataModelItem(
        airportCode = "USA",
        airportName = "United State of America"
    )
    private val airport3 =
        QantasDataModel.QantasDataModelItem(airportCode = "IND", airportName = "INDIA")

    private val dataSource = listOf(airport1, airport2,airport3)

    @Before
    fun setup() {
        localDataSourceImpl = LocalDataSourceImpl(qantasDao)
    }

    @Test
    fun testInsertAirData() {
        testCoroutineRule.runBlockingTest {
            localDataSourceImpl.saveData(dataSource)
            verify(qantasDao).insertQantasDataModelItem(dataSource[0])
        }
    }
    @Test
    fun testDeleteData() {
        testCoroutineRule.runBlockingTest {
            localDataSourceImpl.deleteData()
            verify(qantasDao).delete()
        }
    }

    @Test
    fun testGetAirData() {
        testCoroutineRule.runBlockingTest {
            localDataSourceImpl.getAirData()
            verify(qantasDao).fetchQantasAirportList()
        }
    }
}