package com.igor.coursemanager.network.custom.model

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Request(
    private val request: String
) {

    @Throws
    fun send(): String {
        val response = StringBuilder()
        val url = URL(request)
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            bufferedReader.forEachLine { input ->
                response.append(input)
            }
        } finally {
            urlConnection.disconnect()
        }

        return response.toString()
    }
}