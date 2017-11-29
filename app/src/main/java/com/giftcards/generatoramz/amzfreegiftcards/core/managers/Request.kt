package com.giftcards.generatoramz.amzfreegiftcards.core.managers

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Request {

    companion object {
        val INTERSTITIAL_APPNEXT = 1
        val APPNEXT_VIDEO = 2
        val NATIVEX = 3
        val ADCOLONY_ZONE = 4
        val ADCOLONY_APP = 5
        val OFFERTORO_APP = 6
        val OFFERTORO_SECRET = 7
        val VUNGLE = 8
        val BANNER_APPNEXT = 9
        var UNITY = 12
        var ADMOB_APP = 13
        var ADMOB_INTERSTITIAL = 15
        var ADMOB_VIDEO = 16
    }

    @FormUrlEncoded
    @POST("/keysstorage.php")
    fun getAdKey(
            @Field("app") app: String,
            @Field("key") key: Int
    ) : Call<Any>
}