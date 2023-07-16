package com.example.weather


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface myinterface {
    @GET("weather")
    fun getweather(@Query("q")city:String,@Query("appid")Apikey:String):Call<datajson>
}