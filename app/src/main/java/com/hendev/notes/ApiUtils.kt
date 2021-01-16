package com.hendev.retrofitkullanimi

import com.hendev.notes.NotlarDaoInterface

class ApiUtils {
    companion object {
        val BASE_URL = "https://jhenimex.000webhostapp.com/"
        fun getNotlarDaoInterface(): NotlarDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(NotlarDaoInterface::class.java)
        }
    }
}
