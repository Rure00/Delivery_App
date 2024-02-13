package com.project.deliveryapp.naver

import android.util.Log
import com.naver.maps.geometry.LatLng
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class NaverGeoCoding {

    private val GEOCODING_URL: String = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode"
    private val CLIENT_ID: String = "b69lxhyt8v"
    private val CLIENT_SECRET: String = "2ySIJFZDthLU8WOUoDS1pib3etz3XC1LARMrqlkt"

    fun getLatLng(address: String): LatLng? {
        val urlStr = GEOCODING_URL + "?query" + URLEncoder.encode(address, "UTF-8") + "&X-NCP-APIGW-API-KEY-ID=" + CLIENT_ID + "&X-NCP-APIGW-API-KEY=" + CLIENT_SECRET;
        val url = URL(urlStr)

        val stringBuilder = StringBuilder()
        val connection = url.openConnection() as HttpURLConnection?
        connection?.let {
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            connection.requestMethod = "GET"
            connection.doInput = true

            val responseCode = connection.responseCode
            val bufferedReader = if(responseCode == 200) {
                BufferedReader(InputStreamReader(connection.inputStream))
            } else {
                BufferedReader(InputStreamReader(connection.errorStream))
            }


            while(true) {
                val line = bufferedReader.readLine() ?: break
                stringBuilder.append(line+"\n")
            }

            var indexFirst: Int = 0
            var indexLast: Int = 0

            indexFirst = stringBuilder.indexOf("\"x\":\"")
            indexLast = stringBuilder.indexOf("\",\"y\":")
            val x = java.lang.Double.valueOf(stringBuilder.substring(indexFirst + 5, indexLast))
            Log.d("GeoCoding", "x : $x")

            indexFirst = stringBuilder.indexOf("\"y\":\"")
            indexLast = stringBuilder.indexOf("\",\"distance\":")
            val y = java.lang.Double.valueOf(stringBuilder.substring(indexFirst + 5, indexLast))
            Log.d("GeoCoding", "y : $y")



            bufferedReader.close()
            connection.disconnect()

            return LatLng(y, x)
        }

        return null
    }
}