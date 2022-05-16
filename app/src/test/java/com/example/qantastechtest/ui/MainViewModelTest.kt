package com.example.qantastechtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.qantastechtest.data.Resource
import com.example.qantastechtest.data.model.QantasDataModel
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem
import com.example.qantastechtest.repository.Repository
import com.example.qantastechtest.ui.mainfragment.MainViewModel
import com.example.qantastechtest.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository
    private lateinit var mainViewModel: MainViewModel
    @Mock
    private lateinit var qantasObserver: Observer<Resource<QantasDataModel>>
    @Mock
    private lateinit var qantasDataModel: QantasDataModel

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private val airport1 =
        QantasDataModelItem(airportCode = "Au", airportName = "Austraila")
    private val airport2 = QantasDataModelItem(
        airportCode = "USA",
        airportName = "United State of America"
    )
    private val airport3 =
        QantasDataModelItem(airportCode = "IND", airportName = "INDIA")

    private val dataSource = listOf(airport1, airport2,airport3)


    @Before
    fun setUp(){
        mainViewModel = MainViewModel(repository)
        mainViewModel.qantasDataModelLiveData.observeForever(qantasObserver)
    }

    @Test
    fun test_insertAll(){
        testCoroutineRule.runBlockingTest {
            mainViewModel.insertAll(dataSource)
            verify(repository).saveData(dataSource)
        }
    }

    @Test
    fun test_getLocalData(){
        testCoroutineRule.runBlockingTest {
            mainViewModel.getLocalData()
            verify(repository).fetchLocalData()
        }
    }


    @Test
    fun test_getQantasDataModelWhenSuccess(){
        testCoroutineRule.runBlockingTest {
            doReturn(Response.success(200,qantasDataModel))
                .`when`(repository)
                .fetchRemoteData()
            mainViewModel.getQantasDataModel()
            verify(qantasObserver).onChanged(Resource.Loading())
            verify(qantasObserver).onChanged(Resource.Success(qantasDataModel))
        }
    }
}