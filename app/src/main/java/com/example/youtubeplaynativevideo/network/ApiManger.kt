package com.example.youtubeplaynativevideo.network

import android.app.Application
import com.example.youtubeplaynativevideo.network.api.YouTubeApi
import com.example.youtubeplaynativevideo.util.Constants
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ApiManger {

    private lateinit var mRetrofit: Retrofit

    private fun getRetrofitInsatance():Retrofit{
        if (::mRetrofit.isInitialized)
            return mRetrofit
        else{
            mRetrofit = Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit
        }
    }

    fun getInstance () = getRetrofitInsatance().create(YouTubeApi::class.java)


    private fun createOkHttpClient():OkHttpClient{
        val client = OkHttpClient.Builder()
            .addInterceptor(createIntercptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        return client
    }

    private fun createIntercptor():Interceptor{
        val interceptor = Interceptor{chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("key",Constants.API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        return interceptor
    }


}