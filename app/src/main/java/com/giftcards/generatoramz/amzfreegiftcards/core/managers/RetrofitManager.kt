package com.giftcards.generatoramz.amzfreegiftcards.core.managers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {

    fun buildKeys() : Request {
        return Retrofit.Builder().baseUrl("http://psnk456eysddfqoqg54herw435dhq0.esy.es")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(Request::class.java)
    }

    fun getKey(app: String, key: Int, onReceived: (String) -> Unit, onFail: () -> Unit) {
        buildKeys().getAdKey(app, key).enqueue(object : Callback<Any> {

            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                onReceived(response?.body().toString())
            }

            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                onFail()
            }
        })
    }
}
