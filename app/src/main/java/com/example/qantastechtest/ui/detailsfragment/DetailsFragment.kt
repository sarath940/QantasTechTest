package com.example.qantastechtest.ui.detailsfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.qantastechtest.R
import com.example.qantastechtest.data.model.QantasDataModel
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem
import com.example.qantastechtest.utils.actionBar
import com.example.qantastechtest.utils.setSafeOnClickListener
import com.example.qantastechtest.utils.toolbarTitle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.details_fragment.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(
        R.layout.details_fragment,
        container,
        false
    )

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar?.toolbarTitle = "AirPort Detail"
        back_button.setSafeOnClickListener {
            requireActivity().onBackPressed()
        }

        val qantasDataModelItem =
            arguments?.getSerializable(getString(R.string.airport_data)) as QantasDataModelItem
        city_name_text.text = qantasDataModelItem.city?.cityName
        time_zone_text.text = qantasDataModelItem.city?.timeZoneName
        country_name_text.text = qantasDataModelItem.country?.countryName
        region_name_text.text = qantasDataModelItem.region?.regionName
        location_text.text =
            ("""${qantasDataModelItem.location?.latitude.toString()}/${qantasDataModelItem.location?.latitude.toString()}""")
    }
}