package com.example.qantastechtest.repository

import com.example.qantastechtest.data.local.QantasDao
import com.example.qantastechtest.data.remote.api.ApiInterface

class QantasRepository constructor(
    private val apiInterface: ApiInterface,
    private val qantasDao: QantasDao
) {
}