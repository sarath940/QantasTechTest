package com.example.qantastechtest.utils

import com.example.qantastechtest.data.model.QantasDataModel
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem

interface ItemCallback {
    fun itemClick(qantasDataModelItem: QantasDataModelItem)
}