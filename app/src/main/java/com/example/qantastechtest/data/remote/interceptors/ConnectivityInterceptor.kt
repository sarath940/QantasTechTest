package com.example.qantastechtest.data.remote.interceptors

import android.content.Context
import com.example.qantastechtest.R
import com.example.qantastechtest.utils.Utilities
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utilities.hasInternetConnection(context)) {
            throw IOException(context.getString(R.string.check_ur_internet_connection))
        }
        return chain.proceed(chain.request())
    }
}