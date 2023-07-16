package com.example.weather

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.weather.databinding.ActivityWea2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class wea2 : AppCompatActivity() {
    lateinit var bind:ActivityWea2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityWea2Binding.inflate(layoutInflater)
        setContentView(bind.root)
        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }
        val giflink = "https://i.gifer.com/srK.gif"
        val idbg = bind.backgroundImageView
        Glide.with(this)
            .asGif()
            .error(R.drawable.img5)
            .load(giflink)
            .into(idbg)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitData = retrofitBuilder.create(myinterface::class.java)
        defaultCity("kolkata",retrofitData)
        bind.floatingActionButton.setOnClickListener {
            if(bind.searchtxt.text.toString().isNotEmpty()){
                calltheApi(bind.searchtxt.text.toString(),retrofitData)
            }
            else{
                Toast.makeText(this,"Please serach the city name",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun defaultCity(s: String, retrofitData: myinterface?) {
        val apiKey = "97e04001e8fb0738923e95b3fbabe428"
        val call = retrofitData?.getweather(s,apiKey)
        call?.enqueue(object : Callback<datajson?> {
            override fun onResponse(call: Call<datajson?>?, response: Response<datajson?>?) {
                val responseBody = response?.body()
                if(responseBody != null) {
                    bind.cityname.text = responseBody?.name
                    bind.temp.text =  (((responseBody?.main?.temp?.minus(273))?.times(100.0))?.roundToInt()
                        ?.div(100.0)).toString() + "°C"
                    if (responseBody != null) {
                        when (responseBody.weather[0].description) {
                            "clear sky", "mist" -> {
                                Glide.with(this@wea2)
                                    .load(R.drawable.cloud)
                                    .into(bind.image)
                                val giflink = "https://i.gifer.com/srK.gif"
                                val idbg = bind.backgroundImageView
                                Glide.with(this@wea2)
                                    .asGif()
                                    .error(R.drawable.img5)
                                    .load(giflink)
                                    .into(idbg)

                            }
                            "haze", "overcast clouds" -> {
                                Glide.with(this@wea2)
                                    .load(R.drawable.img_3)
                                    .into(bind.image)
                                val id = "https://thumbs.gfycat.com/IllDisastrousIndigowingedparrot-max-1mb.gif"
                                val idbg = bind.backgroundImageView
                                Glide.with(this@wea2)
                                    .asGif()
                                    .error(R.drawable.img5)
                                    .load(id)
                                    .into(idbg)
                            }
                            else -> {
                                Glide.with(this@wea2)
                                    .load(R.drawable.img4)
                                    .into(bind.image)
                                val id = "https://media.tenor.com/ZAw_hz_GBBsAAAAd/rain.gif"
                                val idbg = bind.backgroundImageView
                                Glide.with(this@wea2)
                                    .asGif()
                                    .load(id)
                                    .into(idbg)
                            }
                        }
                    }
                    bind.threes.text = responseBody?.wind?.speed.toString()
                    bind.twoH.text = responseBody?.main?.humidity.toString()
                    bind.description.text = responseBody.weather[0].description
                }
                else{

                }
            }

            override fun onFailure(call: Call<datajson?>?, t: Throwable?) {
                //Give the Failure log
            }
        })
    }

    private fun calltheApi(city: String, retrofitData: myinterface) {
        val apiKey = "97e04001e8fb0738923e95b3fbabe428"
        val call = retrofitData.getweather(city,apiKey)
        call.enqueue(object : Callback<datajson?> {
            override fun onResponse(call: Call<datajson?>?, response: Response<datajson?>?) {
                val responseBody = response?.body()
                if(responseBody != null) {
                    bind.cityname.text = responseBody?.name
                    bind.temp.text =
                        (((responseBody?.main?.temp?.minus(273))?.times(100.0))?.roundToInt()
                            ?.div(100.0)).toString() + "°C"
                    if (responseBody != null) {
                        when (responseBody.weather[0].description) {
                            "clear sky", "mist" -> {
                                Glide.with(this@wea2)
                                    .load(R.drawable.cloud)
                                    .into(bind.image)
                                val giflink = "https://i.gifer.com/srK.gif"
                                val idbg = bind.backgroundImageView
                                Glide.with(this@wea2)
                                    .asGif()
                                    .error(R.drawable.img5)
                                    .load(giflink)
                                    .into(idbg)

                            }
                            "haze", "overcast clouds" -> {
                                Glide.with(this@wea2)
                                    .load(R.drawable.img_3)
                                    .into(bind.image)
                                  val id = "https://thumbs.gfycat.com/IllDisastrousIndigowingedparrot-max-1mb.gif"
                                val idbg = bind.backgroundImageView
                                Glide.with(this@wea2)
                                    .asGif()
                                    .error(R.drawable.img5)
                                    .load(id)
                                    .into(idbg)
                            }
                            else -> {
                                Glide.with(this@wea2)
                                    .load(R.drawable.img4)
                                    .into(bind.image)
                               val id = "https://media.tenor.com/ZAw_hz_GBBsAAAAd/rain.gif"
                                val idbg = bind.backgroundImageView
                                Glide.with(this@wea2)
                                    .asGif()
                                    .load(id)
                                    .into(idbg)
                            }
                        }
                    }
                    bind.threes.text = responseBody?.wind?.speed.toString()
                    bind.twoH.text = responseBody?.main?.humidity.toString()
                    bind.description.text = responseBody.weather[0].description
                }
                else{
                    val dia = AlertDialog.Builder(this@wea2)
                    dia.setIcon(R.drawable.img5)
                    dia.setTitle("The City is not available")
                    dia.setMessage("Please Enter the another city name")
                    dia.show()
                    defaultCity("kolkata",retrofitData)
                }
            }

            override fun onFailure(call: Call<datajson?>?, t: Throwable?) {
               //Give the Failure log
            }
        })
    }
}