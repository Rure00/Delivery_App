package com.project.deliveryapp.retrofit

import com.google.gson.GsonBuilder
import com.project.deliveryapp.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.InputStream
import java.net.URL
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection


object RetrofitClient {
    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    private const val BASE_URL = "http://124.59.46.235:8080/"
    private const val CONNECT_TIMEOUT_SEC = 20000L

    fun getInstance() : Retrofit {

        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        // 반환
        return instance!!
    }
}
