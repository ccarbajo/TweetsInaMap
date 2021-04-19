package com.example.tweetsinmap.data.api

import com.example.tweetsinmap.data.entities.response.TweetsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    //https://api.twitter.com/1.1/search/tweets.json?q=nasa&result_type=popular
    @GET("1.1/search/tweets.json")
    fun getTweetsByLocation(@Query("q") tweet: String, @Query("geocode") geoCode: String): Call<List<TweetsResponse>>//Observable<DetailsData>
}

class ApiService {
    companion object{
        fun getAPI(): Api {
            var builder : OkHttpClient.Builder = OkHttpClient.Builder()
            builder.interceptors().add(Interceptor {
                var request: Request = it.request()
                var requestBuilder: Request.Builder = it.request().newBuilder()
                requestBuilder.header("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAAq1OgEAAAAAR3ckDhJflE%2BKyY4rThEX%2BJuT9Qg%3DgNyjbL5qbWEuCdBpLsrG8PrU7A6wXMeiupLyPklK4Za5bLypUD")
                requestBuilder.method(request.method(), request.body())
                it.proceed(requestBuilder.build())
            })
            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://api.twitter.com")
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(Api::class.java)
        }
    }
}