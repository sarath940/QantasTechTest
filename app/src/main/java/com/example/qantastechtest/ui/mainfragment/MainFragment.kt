package com.example.qantastechtest.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qantastechtest.R
import com.example.qantastechtest.data.Resource
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem
import com.example.qantastechtest.utils.ItemCallback
import com.example.qantastechtest.utils.Utilities
import com.example.qantastechtest.utils.actionBar
import com.example.qantastechtest.utils.toolbarTitle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(),ItemCallback {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    private lateinit var itemCallback: ItemCallback
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(
        R.layout.main_fragment,
        container,
        false
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar?.toolbarTitle = "AirPort List"
        setupRecyclerView()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        itemCallback = this
        if (Utilities.hasInternetConnection(requireContext())) {
            mainViewModel.getQantasDataModel()
        } else {
            mainViewModel.getLocalData().observe(viewLifecycleOwner) {
                setAdapterData(it)
            }
        }

        retry_button.setOnClickListener {
            if (Utilities.hasInternetConnection(requireContext())) mainViewModel.getQantasDataModel() else Utilities.showToast(
                requireContext(),
                getString(R.string.check_ur_internet_connection),
                Toast.LENGTH_SHORT
            )
        }

        mainViewModel.qantasDataModelLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.toList()?.let { it1 -> mainViewModel.insertAll(it1) }
                    setAdapterData(it.data)
                }
                is Resource.Error -> {
                    Utilities.showToast(requireContext(), it.message, Toast.LENGTH_SHORT)
                    showLoading(false)
                }
                is Resource.Loading -> {
                    showLoading(true)
                }
            }
        }

    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) loading_group.visibility = View.VISIBLE else loading_group.visibility =
            View.GONE
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(this@MainFragment)
        val linearLayoutManager = LinearLayoutManager(activity)
        recycler_view.apply {
            adapter = mainAdapter
            layoutManager = linearLayoutManager
        }

    }


    private fun setAdapterData(data: List<QantasDataModelItem>?) {
        if (data?.isEmpty() == true) {
            no_data.visibility = View.VISIBLE
            retry_button.visibility=View.VISIBLE
            recycler_view.visibility = View.GONE
        } else {
            recycler_view.visibility = View.VISIBLE
            no_data.visibility = View.GONE
            retry_button.visibility=View.GONE
            mainAdapter.differ.submitList(data)
        }

    }


    override fun itemClick(qantasDataModelItem: QantasDataModelItem) {
        val bundle = Bundle().apply {
            putSerializable(getString(R.string.airport_data),qantasDataModelItem)
        }
        if (findNavController().currentDestination?.id == R.id.mainFragment) NavHostFragment.findNavController(
            this
        ).navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }
}
